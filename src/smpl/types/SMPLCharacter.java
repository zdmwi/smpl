package smpl.types;

import smpl.exceptions.SMPLException;
import smpl.exceptions.SMPLTypeException;

public class SMPLCharacter extends SMPLValue<SMPLCharacter> {
    
    char value;

    public SMPLCharacter() {
        this('\u0000');
    }

    public SMPLCharacter(Character v) {
        value = v;
    }
    
    public SMPLType getType() {
        return SMPLType.INT;
    }
    
    public SMPLCharacter add(SMPLValue<?> arg) throws SMPLException {
        String msg = String.format("unsupported operand type(s) for +: 'BOOLEAN' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }

    /**
     * Subtract the given value from this value.
     * @param arg The value to be subtracted
     * @return The difference as a new instance of SMPLValue
     * @throws smpl.exceptions.SMPLException
     */
    public SMPLCharacter sub(SMPLValue<?> arg) throws SMPLException {
        String msg = String.format("unsupported operand type(s) for -: 'BOOLEAN' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }

    /**
     * Multiply the given value by this value.
     * @param arg The multiplicand
     * @return The product as a new instance of SMPLValue
     * @throws smpl.exceptions.SMPLException
     */
    public SMPLCharacter mul(SMPLValue<?> arg) throws SMPLException {
        String msg = String.format("unsupported operand type(s) for *: 'BOOLEAN' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }

    /**
     * Divide the given value by this value.
     * @param arg The divisor
     * @return The quotient as a new instance of SMPLValue
     * @throws smpl.exceptions.SMPLException
     */
    public SMPLCharacter div(SMPLValue<?> arg) throws SMPLException {
        String msg = String.format("unsupported operand type(s) for /: 'BOOLEAN' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }

    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The divisor
     * @return The residue modulo arg as a new instance of SMPLValue
     * @throws smpl.exceptions.SMPLException
     */
    public SMPLCharacter mod(SMPLValue<?> arg) throws SMPLException {
        String msg = String.format("unsupported operand type(s) for %: 'BOOLEAN' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }

    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The exponent
     * @return The result of exponentiation as a new instance of SMPLReal
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLDouble pow(SMPLValue<?> arg) throws SMPLException {
        String msg = String.format("unsupported operand type(s) for ^: 'BOOLEAN' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }

    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The sign of the unary expression
     * @return The signed SMPLValue
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLCharacter unary(String arg) throws SMPLException {
        String msg = String.format("unsupported operand type for %s: 'BOOLEAN'", arg);
        throw new SMPLTypeException(msg);
    }
    
    public char charValue() {
        return value;
    }

    public String toString() {
        return String.valueOf(value);
    }
}
