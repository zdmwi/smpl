package smpl.exceptions;

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
