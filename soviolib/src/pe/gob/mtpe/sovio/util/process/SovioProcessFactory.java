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

import pe.gob.mtpe.sovio.proceso.acceso.SovioIngreso;
import pe.gob.mtpe.sovio.util.Mensajes;
import pe.gob.mtpe.sovio.util.StringLib;
import pe.gob.mtpe.sovio.util.log.InjectLogger;
import pe.gob.mtpe.sovio.util.spring.context.AppContext;


@Component
public class SovioProcessFactory {

	@InjectLogger
	private static Logger log;
	
	public <T extends ProcessResponse> T getProcess(Class clazz) {
		Long ini = Calendar.getInstance().getTimeInMillis();
		ProcessResponse obj = (ProcessResponse) AppContext
				.getApplicationContext().getBean(clazz);
		ProxyFactory pf = new ProxyFactory(obj);
		pf.addAdvice(new MethodInterceptor() {
			@Override
			public Object invoke(MethodInvocation invocation) throws Throwable {
				SovioProcess sp = invocation.getMethod()
						.getAnnotation(SovioProcess.class);
				Object result = null;
				if(sp!=null) {
					ProcessResponse pr = (ProcessResponse) invocation.getThis();
					try {
						pr.reiniciar();
						pr.setEstado(ProcessState.PROCESSING);
						result = invocation.proceed();
						//invoke using method reflect
						invocation.proceed();
						pr.setEstado(ProcessState.OK);
					} catch (Exception ex) {
						pr.setMensaje( Mensajes.get(Mensajes.Err.GRAVE) );
						pr.setEstado(ProcessState.ERROR);
						log.error( Mensajes.get(Mensajes.Err.GRAVE) );
						log.error( StringLib.getExceptionStackTrace(ex) );
					}
				} else {
					result = invocation.getMethod()
							.invoke(invocation.getThis(), 
									invocation.getArguments());;
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
		
//public static ProcessResponse get(Class clazz) {
		SovioProcessFactory factory = new SovioProcessFactory();
		//factory.get
		return factory.getProcess(clazz);
	}

	


	
	
}
