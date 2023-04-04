package pl.vezyr.arkanoidgwt.client.exception;

/**
 * An exception class for invalid level definition.
 * Exception is thrown when list with blocks' type on each row
 * has different size than defined rows number.
 * @author vezyr
 *
 */
public class InvalidLevelDefinition extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidLevelDefinition() {
		super();
	}

	public InvalidLevelDefinition(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidLevelDefinition(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidLevelDefinition(String message) {
		super(message);
	}

	public InvalidLevelDefinition(Throwable cause) {
		super(cause);
	}

}
