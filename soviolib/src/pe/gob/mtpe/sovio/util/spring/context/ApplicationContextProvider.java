package pe.gob.mtpe.sovio.util.spring.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware  {

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		AppContext.setApplicationContext(ctx);
	}

}
