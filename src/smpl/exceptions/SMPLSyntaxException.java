package smpl.exceptions;

public class SMPLSyntaxException extends SMPLException {

    private static final long serialVersionUID = 1L;

    public SMPLSyntaxException() {
        super("Syntax Error:");
    }
    
    public SMPLSyntaxException(String msg) {
        super(msg);
    }    
    
    public SMPLSyntaxException(String msg, Throwable t) {
        super(msg, t);
    }
}
