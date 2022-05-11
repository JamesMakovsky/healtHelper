package exception;

public class FileLengthException extends Exception {
	public FileLengthException(String errorMessage) {
		super(errorMessage);
	}

	public FileLengthException() {
		super("There is no such a string in file.");
	}
}