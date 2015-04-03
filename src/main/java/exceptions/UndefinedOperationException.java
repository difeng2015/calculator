package exceptions;

public class UndefinedOperationException extends Exception {
  private static final long serialVersionUID = -783123970374304620L;

  public UndefinedOperationException(String message) {
    super(message);
  }
}
