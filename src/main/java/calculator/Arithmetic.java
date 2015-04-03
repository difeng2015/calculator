package calculator;

import java.util.Map;
import java.util.HashMap;

import org.apache.log4j.Logger;

import exceptions.OperandNotFoundException;
import exceptions.UndefinedOperationException;
import exceptions.UndefinedVariableException;

/**
 * This class provides a method to evaluate an expression.
 * 
 * @author difeng
 */
public class Arithmetic {
  private static final Logger logger = Logger.getLogger(Arithmetic.class);
  
  /**
   * Don't let anyone instantiate this class.
   */
  private Arithmetic() {}
  
  /**
   * Evaluates an expression and returns evaluation result.
   * 
   * @param exp
   *        an arithmetic expression
   * @param varsMap
   *        a Map of preassigned variables from parent expression
   * @return evaluation result of the expression
   * @throws UndefinedOperationException
   *         if the operation in the expression is undefined
   * @throws UndefinedVariableException
   *         if a variable is undefined
   * @throws OperandNotFoundException
   *         if an operand is not found at expected position in the expression
   */
  public static int evaluate(String exp, Map<String, Integer> varsMap)
      throws UndefinedOperationException, UndefinedVariableException, OperandNotFoundException {
    logger.info("Arithmetic.evaluate()");
    logger.debug("expression: " + exp);
    
    if(exp.matches("[0-9]+")) {
      return Integer.parseInt(exp);
    }
    else if(!exp.contains("(")) {
      if(varsMap == null || varsMap.get(exp) == null) {
        throw new UndefinedVariableException(exp);
      }
      return varsMap.get(exp);
    }
    
    Integer ret = null;
    
    String action = Parser.getAction(exp);
    if(!action.equals("let")) {
      if(!action.equals("add") && !action.equals("sub")
          && !action.equals("mult") && !action.equals("div")) {
        throw new UndefinedOperationException(action);
      }
      String op1 = Parser.getKthOp(exp, 1);
      String op2 = Parser.getKthOp(exp, 2);
      if(action.equals("add")) {
        ret = evaluate(op1, varsMap) + evaluate(op2, varsMap);
      }
      if(action.equals("sub")) {
        ret = evaluate(op1, varsMap) - evaluate(op2, varsMap);
      }
      if(action.equals("mult")) {
        ret = evaluate(op1, varsMap) * evaluate(op2, varsMap);
      }
      if(action.equals("div")) {
        ret = evaluate(op1, varsMap) / evaluate(op2, varsMap);
      }
    }
    if(action.equals("let")) {
      if(varsMap == null) {
        varsMap = new HashMap<String, Integer>();
      }
      String varName = Parser.getKthOp(exp, 1);
      int varValue = evaluate(Parser.getKthOp(exp, 2), varsMap);
      varsMap.put(varName, varValue);
      String subExp = Parser.getKthOp(exp, 3);
      ret = evaluate(subExp, varsMap);
      varsMap.remove(varName);
    }
    
    return ret;
  }
}
