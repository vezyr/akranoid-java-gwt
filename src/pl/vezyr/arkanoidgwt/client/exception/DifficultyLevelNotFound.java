package pl.vezyr.arkanoidgwt.client.exception;

public class DifficultyLevelNotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DifficultyLevelNotFound() {
		super();
	}

	public DifficultyLevelNotFound(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DifficultyLevelNotFound(String message, Throwable cause) {
		super(message, cause);
	}

	public DifficultyLevelNotFound(String message) {
		super(message);
	}

	public DifficultyLevelNotFound(Throwable cause) {
		super(cause);
	}

}
