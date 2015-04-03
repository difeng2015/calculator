package exceptions;

public class UndefinedVariableException extends Exception {
  private static final long serialVersionUID = 2387434836988598357L;

  public UndefinedVariableException(String message) {
    super(message);
  }
}
