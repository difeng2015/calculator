package calculator;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArithmeticTest {
  @Test
  public void testEvaluate() {
    try {
      assertTrue(Arithmetic.evaluate(
          "let(a, 5, let(b, mult(a, 10), add(b, a)))", null) == 55);
      assertTrue(Arithmetic.evaluate(
          "mult(add(2, 2), div(9, 3))", null) == 12);
      assertTrue(Arithmetic.evaluate(
          "let(a, 5, let(b, div(10, a), add(b, a)))", null) == 7);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
