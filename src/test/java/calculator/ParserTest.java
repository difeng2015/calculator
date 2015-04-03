package calculator;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParserTest {

  @Test
  public void testGetAction() {
    assertTrue(Parser.getAction(" add (3, 2)").equals("add"));
    assertTrue(Parser.getAction("mult(add(2, 2), div(9, 3))").equals("mult"));
    assertTrue(Parser.getAction(" let( a, let ( b, 10, add(  b, b)),"
        + " let(b, 20, add(a, b) )) ").equals("let"));
  }
  
  @Test
  public void testParenthesisPairsValid() {
    assertTrue(Parser.parenthesisPairsValid(
        "mult(add(2, 2), div(9, 3))") == true);
    assertTrue(Parser.parenthesisPairsValid(
        "let(a, 5, let(b, mult(a, 10), 9))") == true);
    assertTrue(Parser.parenthesisPairsValid(
        "add(sub(3, 1)), 9)") == false);
    assertTrue(Parser.parenthesisPairsValid(
        "mult(add(2, 2), div((9, 3))") == false);
  }

}
