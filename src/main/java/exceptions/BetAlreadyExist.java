package exceptions;
public class BetAlreadyExist extends Exception {
 private static final long serialVersionUID = 1L;
 
 public BetAlreadyExist()
  {
    super();
  }
  /**This exception is triggered if the question already exists 
  *@param s String of the exception
  */
  public BetAlreadyExist(String s)
  {
    super(s);
  }
}