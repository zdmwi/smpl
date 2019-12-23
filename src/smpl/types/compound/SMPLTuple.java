package smpl.types.compound;

import java.util.ArrayList;

import smpl.exceptions.SMPLException;
import smpl.exceptions.SMPLTypeException;

import smpl.types.SMPLValue;
import smpl.types.SMPLType;

public class SMPLTuple extends SMPLValue<SMPLTuple> {
    
    ArrayList<SMPLValue<?>> values;

    public SMPLTuple() {
        super();
    }

    public SMPLTuple(ArrayList<SMPLValue<?>> values) {
        this.values = values;
    }

    public ArrayList<SMPLValue<?>> getValues() {
        return values;
    }
    
    public SMPLType getType() {
        return SMPLType.TUPLE;
    }
    
    public SMPLTuple add(SMPLValue<?> arg) throws SMPLException {
        String msg = String.format("unsupported operand type(s) for +: 'TUPLE' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }

    /**
     * Subtract the given value from this value.
     * @param arg The value to be subtracted
     * @return The difference as a new instance of SMPLValue
     * @throws smpl.exceptions.SMPLException
     */
    public SMPLTuple sub(SMPLValue<?> arg) throws SMPLException {
        String msg = String.format("unsupported operand type(s) for -: 'TUPLE' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }

    /**
     * Multiply the given value by this value.
     * @param arg The multiplicand
     * @return The product as a new instance of SMPLValue
     * @throws smpl.exceptions.SMPLException
     */
    public SMPLTuple mul(SMPLValue<?> arg) throws SMPLException {
        String msg = String.format("unsupported operand type(s) for *: 'TUPLE' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }

    /**
     * Divide the given value by this value.
     * @param arg The divisor
     * @return The quotient as a new instance of SMPLValue
     * @throws smpl.exceptions.SMPLException
     */
    public SMPLTuple div(SMPLValue<?> arg) throws SMPLException {
        String msg = String.format("unsupported operand type(s) for /: 'TUPLE' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }

    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The divisor
     * @return The residue modulo arg as a new instance of SMPLValue
     * @throws smpl.exceptions.SMPLException
     */
    public SMPLTuple mod(SMPLValue<?> arg) throws SMPLException {
        String msg = String.format("unsupported operand type(s) for %: 'TUPLE' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }

    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The exponent
     * @return The result of exponentiation as a new instance of SMPLReal
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLTuple pow(SMPLValue<?> arg) throws SMPLException {
        String msg = String.format("unsupported operand type(s) for ^: 'TUPLE' and '%s'", arg.getType());
        throw new SMPLTypeException(msg);
    }

    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The sign of the unary expression
     * @return The signed SMPLValue
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLTuple unary(String arg) throws SMPLException {
        String msg = String.format("unsupported operand type for %s: 'TUPLE'", arg);
        throw new SMPLTypeException(msg);
    }

    public String toString() {
        String st = "(";
        for(int i = 0; i < values.size() - 1; i++) {
            st += values.get(i).toString() + ", ";
        }
        st += values.get(values.size() - 1).toString() + ")";
        return st;
    }
}
