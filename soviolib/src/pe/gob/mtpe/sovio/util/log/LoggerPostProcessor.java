package pe.gob.mtpe.sovio.util.log;


import java.lang.reflect.Field;
import java.util.Calendar;

import org.slf4j.LoggerFactory;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.LogManager;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import pe.gob.mtpe.sovio.util.process.ProcessResponse;
import pe.gob.mtpe.sovio.util.process.SovioProcess;
import pe.gob.mtpe.sovio.util.spring.AppContext;


@Component
public class LoggerPostProcessor implements BeanPostProcessor {

	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		Long ini = Calendar.getInstance().getTimeInMillis();
		Field[] fields = bean.getClass().getDeclaredFields();
		for(Field field: fields) {
			InjectLogger injectLogger = field.getAnnotation(InjectLogger.class);
			if(injectLogger!=null) {
				field.setAccessible(true);
				try {
					//System.out.println("Inject logger into class: " + bean.getClass());
					if(field.getType().equals(org.slf4j.Logger.class)) {
						field.set(bean, org.slf4j.LoggerFactory.getLogger(bean.getClass()));
					} else {
						field.set(bean, LogManager.getLogger(bean.getClass()));
					}					
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Inject Logger " +  
				+ (Calendar.getInstance().getTimeInMillis() - ini) 
				+ " miliseconds" );		
        return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	
}
