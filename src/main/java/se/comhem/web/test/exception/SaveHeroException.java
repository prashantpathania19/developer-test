/**
 * 
 */
package se.comhem.web.test.exception;

/**
 * This class is user defined exception class
 * @author Prashant Pathania
 *
 */
public class SaveHeroException extends Exception{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor with exception message
     * @param msg
     */
    public SaveHeroException(String msg) {
        super(msg);
    }

    /**
     * Constructor with msg and cause
     * @param msg
     * @param cause
     */
    public SaveHeroException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
