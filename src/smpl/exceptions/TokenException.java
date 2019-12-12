package smpl.exceptions;

/**
 * Class to represent an unrecognized token exception.
 */

public class TokenException extends SMPLException {

    private static final long serialVersionUID = 1L;
    private static final String fmt = "Unrecognised input '%s'";
    
    public TokenException(String inp) {
	    super(String.format(fmt, inp));
    }

    public TokenException(String inp, Throwable cause) {
	    super(String.format(fmt, inp), cause);
    }

}
