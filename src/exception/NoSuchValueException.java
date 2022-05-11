package exception;

public class NoSuchValueException extends Exception {
	public NoSuchValueException(String errorMessage) {
		super(errorMessage);
	}

	public NoSuchValueException() {
		super("There is no object with such value in chosen file!");
	}
}