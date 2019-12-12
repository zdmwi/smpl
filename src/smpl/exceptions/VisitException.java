package smpl.exceptions;

public class VisitException extends SMPLException {

    private static final long serialVersionUID = 1L;

    public VisitException(String message) {
	    super(message);
    }

    public VisitException(String message, Throwable cause) {
	    super(message, cause);
    }
}
