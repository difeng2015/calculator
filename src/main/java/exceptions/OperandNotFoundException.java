package exceptions;

public class OperandNotFoundException extends Exception {
  private static final long serialVersionUID = -2890869617027984137L;

  public OperandNotFoundException(String message) {
    super(message);
  }
}
