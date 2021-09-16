package pt.amane.dstrabalhofinal.services.exceptions;

public class DataBaseIntegrityViolationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataBaseIntegrityViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataBaseIntegrityViolationException(String message) {
		super(message);
	}

}
