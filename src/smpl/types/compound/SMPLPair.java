package smpl.types.compound;

import smpl.exceptions.SMPLException;
import smpl.exceptions.SMPLTypeException;

import smpl.types.SMPLValue;
import smpl.types.SMPLType;

public class SMPLPair extends SMPLValue<SMPLPair> {
    
    SMPLValue<?> obj1, obj2;

    public SMPLPair() {
        this(null, null);
    }

    public SMPLPair(SMPLValue<?> p1, SMPLValue<?> p2) {
        obj1 = p1;
        obj2 = p2;
    }
    
    public SMPLType getType() {
        return SMPLType.PAIR;
    }
    
    public SMPLPair add(SMPLValue<?> arg) throws SMPLException {
        String msg = String.format("unsupported operand type(s) for +: 'PAIR' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }

    /**
     * Subtract the given value from this value.
     * @param arg The value to be subtracted
     * @return The difference as a new instance of SMPLValue
     * @throws smpl.exceptions.SMPLException
     */
    public SMPLPair sub(SMPLValue<?> arg) throws SMPLException {
        String msg = String.format("unsupported operand type(s) for -: 'PAIR' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }

    /**
     * Multiply the given value by this value.
     * @param arg The multiplicand
     * @return The product as a new instance of SMPLValue
     * @throws smpl.exceptions.SMPLException
     */
    public SMPLPair mul(SMPLValue<?> arg) throws SMPLException {
        String msg = String.format("unsupported operand type(s) for *: 'PAIR' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }

    /**
     * Divide the given value by this value.
     * @param arg The divisor
     * @return The quotient as a new instance of SMPLValue
     * @throws smpl.exceptions.SMPLException
     */
    public SMPLPair div(SMPLValue<?> arg) throws SMPLException {
        String msg = String.format("unsupported operand type(s) for /: 'PAIR' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }

    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The divisor
     * @return The residue modulo arg as a new instance of SMPLValue
     * @throws smpl.exceptions.SMPLException
     */
    public SMPLPair mod(SMPLValue<?> arg) throws SMPLException {
        String msg = String.format("unsupported operand type(s) for %: 'PAIR' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }

    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The exponent
     * @return The result of exponentiation as a new instance of SMPLReal
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLPair pow(SMPLValue<?> arg) throws SMPLException {
        String msg = String.format("unsupported operand type(s) for ^: 'PAIR' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }

    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The sign of the unary expression
     * @return The signed SMPLValue
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLPair unary(String arg) throws SMPLException {
        String msg = String.format("unsupported operand type for %s: 'PAIR'", arg);
        throw new SMPLTypeException(msg);
    }

    public String toString() {
        return "<" + obj1.toString() + ", " + obj2.toString() + ">";
    }
}
