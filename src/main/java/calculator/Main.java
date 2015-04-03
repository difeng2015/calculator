package calculator;

import org.apache.log4j.Logger;

import exceptions.InvalidParenthesisPairsException;
import exceptions.OperandNotFoundException;
import exceptions.UndefinedOperationException;
import exceptions.UndefinedVariableException;


/**
 * This class provides the main method.
 * 
 * @author difeng
 */
public class Main {
  private static final Logger logger = Logger.getLogger(Main.class);
  
  /**
   * Reads an expression, calculates it and prints out the result.
   * 
   * @param args
   *        array of String, of which the first one represents a valid
   *        arithmetic expression
   */
  public static void main(String[] args) {
    logger.info("Main.main()");
    
    try {
      String exp = args[0];
      logger.debug("expression: " + exp);
      if(Parser.parenthesisPairsValid(exp) == false) {
        throw new InvalidParenthesisPairsException(exp);
      }
      String result = String.valueOf(Arithmetic.evaluate(exp, null));
      System.out.println(result);
    } catch(ArrayIndexOutOfBoundsException e) {
      logger.error("Error: No expression input found!\n");
    } catch (InvalidParenthesisPairsException e) {
      logger.error("Error: Parenthesis pairs in expression don't match! " + e);
    } catch (UndefinedOperationException e) {
      logger.error("Error: Undefined operation in expression! " + e);
    } catch (UndefinedVariableException e) {
      logger.error("Error: Undefined variable in expression! " + e);
    } catch (OperandNotFoundException e) {
      logger.error("Error: Operand is not found in expression! " + e);
    } 
  }
}