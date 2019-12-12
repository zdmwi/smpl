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
    
    public SMPLString add(SMPLValue<?> arg) throws SMPLException {
        if (arg.getType() == SMPLType.STRING) {
            return make(value + arg);
        }
        String msg = String.format("unsupported operand type(s) for +: 'STRING' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }

    /**
     * Subtract the given value from this value.
     * @param arg The value to be subtracted
     * @return The difference as a new instance of SMPLValue
     * @throws smpl.exceptions.SMPLException
     */
    public SMPLString sub(SMPLValue<?> arg) throws SMPLException {
        String msg = String.format("unsupported operand type(s) for -: 'STRING' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }

    /**
     * Multiply the given value by this value.
     * @param arg The multiplicand
     * @return The product as a new instance of SMPLValue
     * @throws smpl.exceptions.SMPLException
     */
    public SMPLInt mul(SMPLValue<?> arg) throws SMPLException {
        String msg = String.format("unsupported operand type(s) for *: 'STRING' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }

    /**
     * Divide the given value by this value.
     * @param arg The divisor
     * @return The quotient as a new instance of SMPLValue
     * @throws smpl.exceptions.SMPLException
     */
    public SMPLInt div(SMPLValue<?> arg) throws SMPLException {
        String msg = String.format("unsupported operand type(s) for /: 'STRING' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }

    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The divisor
     * @return The residue modulo arg as a new instance of SMPLValue
     * @throws smpl.exceptions.SMPLException
     */
    public SMPLInt mod(SMPLValue<?> arg) throws SMPLException {
        String msg = String.format("unsupported operand type(s) for %: 'STRING' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }

    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The exponent
     * @return The result of exponentiation as a new instance of SMPLReal
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLReal pow(SMPLValue<?> arg) throws SMPLException {
        String msg = String.format("unsupported operand type(s) for ^: 'STRING' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }

    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The sign of the unary expression
     * @return The signed SMPLValue
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLString unary(String arg) throws SMPLException {
        String msg = String.format("unsupported operand type for %s: 'STRING'", arg);
        throw new SMPLTypeException(msg);
    }
    
    public String stringValue() {
        return value;
    }

    public String toString() {
        return value;
    }
}
