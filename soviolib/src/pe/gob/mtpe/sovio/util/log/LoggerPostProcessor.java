package pe.gob.mtpe.sovio.util.log;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.apache.log4j.LogManager;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;


@Component
public class LoggerPostProcessor implements BeanPostProcessor {

	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		
		Field[] fields = bean.getClass().getDeclaredFields();
		for(Field field: fields) {
			InjectLogger injectLogger = field.getAnnotation(InjectLogger.class);
			if(injectLogger!=null) {
				field.setAccessible(true);
				try {
					System.out.println("Inject logger into class: " + bean.getClass());
					field.set(bean, LogManager.getLogger(bean.getClass()));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
        return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	
}
