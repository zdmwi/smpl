package smpl.exceptions;

/**
 * Class to represent an unrecognized token exception.
 */

public class TokenException extends Exception {

    private static final String fmt = "Unrecognised input '%s'";
    
    public TokenException(String inp) {
	    super(fmt.format(inp));
    }

    public TokenException(String inp, Throwable cause) {
	    super(fmt.format(inp), cause);
    }

}
