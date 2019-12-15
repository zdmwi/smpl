package smpl.types;

import smpl.exceptions.SMPLException;
import smpl.exceptions.SMPLTypeException;

public class SMPLString extends SMPLValue<SMPLString> {
    
    String value;

    public SMPLString() {
        this("");
    }

    public SMPLString(String v) {
        value = v;
    }
    
    public SMPLType getType() {
        return SMPLType.STRING;
    }

    /**
     * Check if the value and the given value are equal.
     * @param arg The second operand
     * @return The boolean result of the comparison
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLBoolean eq(SMPLValue<?> arg) throws SMPLException {
        return make(value == arg.stringValue());
    }

    /**
     * Check if the value and the given value are not equal.
     * @param arg The second operand
     * @return The boolean result of the comparison
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLBoolean neq(SMPLValue<?> arg) throws SMPLException {
        return make(value != arg.stringValue());
    }
    
    public SMPLString add(SMPLValue<?> arg) throws SMPLException {
        if (arg.getType() == SMPLType.STRING) {
            return make(value + arg);
        }
        String msg = String.format("unsupported operand type(s) for +: 'STRING' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }
    
    public String stringValue() {
        return value;
    }

    public String toString() {
        return value;
    }
}
