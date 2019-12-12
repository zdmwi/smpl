package smpl.exceptions;

/**
 * Class to represent a runtime exception, which should be the parent of all
 * exceptions that might arise because of visits done by an Evaluator instance.
 */
public class RuntimeException extends VisitException {

    private static final long serialVersionUID = 1L;

    public RuntimeException(String msg) {
	    super(msg);
    }

    public RuntimeException(String msg, Throwable cause) {
	    super(msg, cause);
    }
}
