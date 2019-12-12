package smpl.exceptions;

/**
 * Parent exception class for all exceptions that can be raised while processing
 * a SMPL program.
 * 
 * @author zdmwi,
 */
public class SMPLException extends Exception {

    private static final long serialVersionUID = 1L;

    public SMPLException() {
        super("FnPlot Error");
    }
    
    public SMPLException(String msg) {
        super(msg);
    }
    
    public SMPLException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
