package pe.gob.mtpe.sovio.util.spring.context;

import org.springframework.context.ApplicationContext;

public class AppContext {

	
	private static ApplicationContext ctx;

	
	public static ApplicationContext getApplicationContext() {
		return ctx;
	}

	public static void setApplicationContext(ApplicationContext ctx) {
		AppContext.ctx = ctx;
	}
	
	
	
}
