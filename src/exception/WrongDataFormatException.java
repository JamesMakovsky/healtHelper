package exception;

public class WrongDataFormatException extends Exception {
	public WrongDataFormatException(String errorMessage) {
		super(errorMessage);
	}

	public WrongDataFormatException() {
		super("Wrong format of data. There is no \"ID\" field in json string.");
	}
}