package smpl.types;

import smpl.exceptions.SMPLException;
import smpl.exceptions.SMPLTypeException;

public class SMPLBoolean extends SMPLValue<SMPLBoolean> {
    
    boolean value;

    public SMPLBoolean() {
        this(false);
    }

    public SMPLBoolean(Boolean v) {
        value = v;
    }
    
    public SMPLType getType() {
        return SMPLType.BOOLEAN;
    }
    
    public SMPLBoolean add(SMPLValue<?> arg) throws SMPLException {
        String msg = String.format("unsupported operand type(s) for +: 'BOOLEAN' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }

    /**
     * Subtract the given value from this value.
     * @param arg The value to be subtracted
     * @return The difference as a new instance of SMPLValue
     * @throws smpl.exceptions.SMPLException
     */
    public SMPLBoolean sub(SMPLValue<?> arg) throws SMPLException {
        String msg = String.format("unsupported operand type(s) for -: 'BOOLEAN' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }

    /**
     * Multiply the given value by this value.
     * @param arg The multiplicand
     * @return The product as a new instance of SMPLValue
     * @throws smpl.exceptions.SMPLException
     */
    public SMPLBoolean mul(SMPLValue<?> arg) throws SMPLException {
        String msg = String.format("unsupported operand type(s) for *: 'BOOLEAN' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }

    /**
     * Divide the given value by this value.
     * @param arg The divisor
     * @return The quotient as a new instance of SMPLValue
     * @throws smpl.exceptions.SMPLException
     */
    public SMPLBoolean div(SMPLValue<?> arg) throws SMPLException {
        String msg = String.format("unsupported operand type(s) for /: 'BOOLEAN' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }

    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The divisor
     * @return The residue modulo arg as a new instance of SMPLValue
     * @throws smpl.exceptions.SMPLException
     */
    public SMPLBoolean mod(SMPLValue<?> arg) throws SMPLException {
        String msg = String.format("unsupported operand type(s) for %: 'BOOLEAN' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }

    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The exponent
     * @return The result of exponentiation as a new instance of SMPLReal
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLBoolean pow(SMPLValue<?> arg) throws SMPLException {
        String msg = String.format("unsupported operand type(s) for ^: 'BOOLEAN' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }

    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The sign of the unary expression
     * @return The signed SMPLValue
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLBoolean unary(String arg) throws SMPLException {
        String msg = String.format("unsupported operand type for %s: 'BOOLEAN'", arg);
        throw new SMPLTypeException(msg);
    }
    
    public boolean boolValue() {
        return value;
    }

    public String toString() {
        return "#" + String.valueOf(value).substring(0, 1);
    }
}
