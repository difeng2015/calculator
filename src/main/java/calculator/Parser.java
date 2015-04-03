package calculator;

import org.apache.log4j.Logger;

import exceptions.OperandNotFoundException;

/**
 * This class provides methods to parse an expression.
 * 
 * @author difeng
 */
public class Parser {
  private static final Logger logger = Logger.getLogger(Parser.class);
  
  /**
   * Don't let anyone instantiate this class.
   */
  private Parser() {}
  
  /**
   * Returns the operation's name of an expression. Possible names include
   * "add", "sub", "mult", "div" and "let".
   * 
   * @param exp
   *        an expression
   * @return the operation's name of this expression
   */
  public static String getAction(String exp) {
    logger.info("getAction()");
    logger.debug("expression: " + exp);
    
    int start = 0;
    int end = exp.indexOf('(');
    String action = exp.substring(start, end).trim();
    return action;
  }
  
  /**
   * Returns the k-th operand of an expression.
   * 
   * @param exp
   *        an expression
   * @param k
   *        the index of the wanted operand
   * @return the operand
   * @throws OperandNotFoundException 
   *         if the k-th operand is not found in expression
   */
  public static String getKthOp(String exp, int k) throws OperandNotFoundException {
    logger.info("getAction()");
    logger.debug("expression: " + exp + "  operand #" + k);
    
    int start = -1;
    int depth1 = 0;
    int count = k;
    for(int i = 0; i < exp.length(); i++) {
      if(exp.charAt(i) == '(') {
        depth1++;
      }
      if(exp.charAt(i) == ')') {
        depth1--;
      }
      if((exp.charAt(i) == ',' || exp.charAt(i) == '(')
          && depth1 == 1) {
        count--;
        if(count == 0) {
          start = i + 1;
          break;
        }
      }
    }
    
    //failed to find starting index of k-th operand before end of expression
    if(start == -1) {
      throw new OperandNotFoundException("Operand #" + k + " in expression \"" + exp
          + "\" is not found.");
    }
    
    int end = -1;
    int depth2 = 1;
    for(int i = start; i < exp.length(); i++) {
      if(exp.charAt(i) == '(') {
        depth2++;
      }
      if(exp.charAt(i) == ')') {
        depth2--;
      }
      if((exp.charAt(i) == ',' && depth2 == 1) ||
          (exp.charAt(i) == ')' && depth2 == 0)) {
        end = i;
        break;
      }
    }
    
    String op = exp.substring(start, end).trim();
    
    //k-th operand is an empty String
    if(op.isEmpty()) {
      throw new OperandNotFoundException("Operand #" + k + " in expression \"" + exp
          + "\" is not found.");
    }
    return op;
  }
  
  /**
   * Checks if the parenthesis pairs in an expression are valid.
   * 
   * @param exp
   *        an expression
   * @return <code>true</code> if parenthesis pairs are valid
   */
  public static boolean parenthesisPairsValid(String exp) {
    logger.info("Arithmetic.parenthesisPairsValid()");
    int depth = 0;
    for(int i = 0; i < exp.length(); i++) {
      if(exp.charAt(i) == '(') {
        depth++;
      }
      if(exp.charAt(i) == ')') {
        depth--;
        if(depth < 0) {
          return false;
        }
      }
    }
    return depth == 0 ? true : false;
  }
}
