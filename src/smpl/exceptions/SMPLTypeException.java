package smpl.exceptions;

import smpl.types.SMPLType;

/**
 *
 * @author zdmwi,
 */
public class SMPLTypeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SMPLTypeException() {
        super("Type Error");
    }
    
    public SMPLTypeException(String message) {
        super(message);
    }
    
    public SMPLTypeException(SMPLType expected, SMPLType received) {
        super("Type Error: Expected " + expected + " but received " + received);
    }
    
    public SMPLTypeException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
