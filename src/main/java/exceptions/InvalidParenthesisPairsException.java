package exceptions;

public class InvalidParenthesisPairsException extends Exception {
  private static final long serialVersionUID = 608758943503898014L;

  public InvalidParenthesisPairsException(String message) {
    super(message);
  }
}
