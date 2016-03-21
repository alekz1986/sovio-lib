package pe.gob.mtpe.sovio.util.process;

public class SovioProcessException extends Throwable {

	
	public SovioProcessException(String message) {
        super(message);
    }
	
	
	public SovioProcessException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public SovioProcessException(Throwable cause) {
        super(cause);
    }
	
	protected SovioProcessException(String message, Throwable cause,
	            boolean enableSuppression,
	            boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	
	
}
