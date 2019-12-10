package smpl.exceptions;

/**
 * Exception to represent when a variable lookup occurs outside of its scope.
 */
public class UnboundVarException extends RuntimeException {

    public UnboundVarException(String id) {
	    super("Unbound variable " + id);
    }

    public UnboundVarException(String id, Throwable cause) {
	    super("Unbound variable " + id, cause);
    }

}
