package pe.gob.mtpe.sovio.util.process;

import java.lang.annotation.Annotation;
import java.util.Calendar;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import pe.gob.mtpe.sovio.util.StringLib;
import pe.gob.mtpe.sovio.util.log.InjectLogger;
import pe.gob.mtpe.sovio.util.spring.AppContext;


@Component
public class SovioProcessFactory {

	@InjectLogger
	private static Logger log;
	
	public <T extends ProcessResponse> T getProcess(Class clazz) {
		Long ini = Calendar.getInstance().getTimeInMillis();
		ProcessResponse obj = (ProcessResponse) AppContext.getApplicationContext().getBean(clazz);
		ProxyFactory pf = new ProxyFactory(obj);
		pf.addAdvice(new MethodInterceptor() {
			@Override
			public Object invoke(MethodInvocation method) throws Throwable {
				System.out.println("Intercep method");
				SovioProcess sp = method.getMethod().getAnnotation(SovioProcess.class);
				Object result = null;
				if(sp!=null) {
					ProcessResponse processResponse = (ProcessResponse) method.getThis();
					try {
						processResponse.reiniciar();
						processResponse.setEstado(ProcessState.PROCESSING);
						result = method.getMethod().invoke(method.getThis(), method.getArguments());
						processResponse.setEstado(ProcessState.OK);
					} catch (Exception ex) {
						processResponse.setMensaje("Se produjo un error");
						processResponse.setEstado(ProcessState.ERROR);
						processResponse.setException(ex);
						log.error("Se produjo un error");
						log.error(StringLib.getExceptionStackTrace(ex));
					}
				} else {
					result = method.getMethod().invoke(method.getThis(), method.getArguments());;
				}
				return result;
			}
		});
		System.out.println("ProcessFactory " +  
				+ (Calendar.getInstance().getTimeInMillis() - ini) 
				+ " miliseconds" );
		return (T) pf.getProxy();
	}
	
	
	public static <T extends ProcessResponse> T get(Class clazz) {
		SovioProcessFactory factory = new SovioProcessFactory();
		return factory.getProcess(clazz);
	}

	


	
	
}
