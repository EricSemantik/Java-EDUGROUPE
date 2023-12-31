package formation.factory.exception;

public class FactoryException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FactoryException() {
		super();
	}

	public FactoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FactoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public FactoryException(String message) {
		super(message);
	}

	public FactoryException(Throwable cause) {
		super(cause);
	}

	
}
