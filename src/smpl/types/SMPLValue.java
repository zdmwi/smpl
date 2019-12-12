package smpl.types;

import smpl.exceptions.RuntimeException;
import smpl.exceptions.SMPLTypeException;

/**
 *
 * @param <T> The type of base Java value wrapped by this class
 * @author zdmwi,
 * Created on 10-Dec-2019 at 6:00 PM
 */
public abstract class SMPLValue<T extends SMPLValue<T>> {

    private static final long serialVersionUID = 1L;

    /**
     * Create a SMPL value wrapping a Java double
     * @param v the double value to be wrapped
     * @return The SMPLValue instance to represent that double precision value
     */
    public static SMPLReal make(Double v) {
        return new SMPLReal(v);
    }
    
    /**
     * Create a SMPL value wrapping a Java integer
     * @param v The int value to be wrapped
     * @return The FnPlotValue instance to represent that integer value.
     */
    public static SMPLInt make(Integer v) {
        return new SMPLInt(v);
    }
    
    /**
     *
     * @return The type of this value.
     */
    public abstract SMPLType getType();
    
    /**
     *
     * @return <code>true</code> if and only if this value is numerically
     * equivalent to an integer
     */
    public boolean isInteger() {
        return getType() == SMPLType.INTEGER;
    }

    /**
     * Add the given value to this value
     * @param arg The value to be added.
     * @return The sum of the two values as a new instance of SMPLValue.
     * @throws smpl.exception.RuntimeException if there is a type incompatibility between this value and the argument value under addition
     */
    public SMPLValue<?> add(SMPLValue<?> arg) throws RuntimeException {
        throw new SMPLTypeException("Operation div called with non-numeric type");
    }

    /**
     * Subtract the given value from this value.
     * @param arg The value to be subtracted
     * @return The difference as a new instance of SMPLValue
     * @throws smpl.exception.RuntimeException if there is a type incompatibility between this value and the argument value under subtraction
     */
    public SMPLValue<?> sub(SMPLValue<?> arg) throws RuntimeException {
        throw new SMPLTypeException("Operation sub called with non-numeric type");
    }

    /**
     * Multiply the given value by this value.
     * @param arg The multiplicand
     * @return The product as a new instance of SMPLValue
     * @throws smpl.exception.RuntimeException if there is a type incompatibility between this value and the argument value under multiplication
     */
    public SMPLValue<?> mul(SMPLValue<?> arg) throws RuntimeException {
        throw new SMPLTypeException("Operation mul called with non-numeric type");
    }

    /**
     * Divide the given value by this value.
     * @param arg The divisor
     * @return The quotient as a new instance of SMPLValue
     * @throws smpl.exception.RuntimeException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLValue<?> div(SMPLValue<?> arg) throws RuntimeException {
        throw new SMPLTypeException("Operation div called with non-numeric type");
    }
    
    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The divisor
     * @return The residue modulo arg as a new instance of SMPLValue
     * @throws smpl.exception.RuntimeException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLValue<?> mod(SMPLValue<?> arg) throws RuntimeException {
        throw new SMPLTypeException("Operation mod called with non-numeric type");
    }

    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The exponent
     * @return The result of exponentiation as a new instance of SMPLValue
     * @throws smpl.exception.RuntimeException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLValue<?> pow(SMPLValue<?> arg) throws RuntimeException {
        throw new SMPLTypeException("Operation mod called with non-numeric type");
    }

    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The sign of the unary expression
     * @return The signed SMPLValue
     * @throws smpl.exception.RuntimeException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLValue<?> unary(String arg) throws RuntimeException {
        throw new SMPLTypeException("Operation mod called with non-numeric type");
    }

    /**
     *
     * @return The integer value wrapped in this SMPL value
     * @throws SMPLTypeException If there is no such integer
     */
    public int intValue() throws SMPLTypeException {
        throw new SMPLTypeException(SMPLType.INTEGER, getType());
    }

    /**
     *
     * @return The real value wrapped in this SMPL value
     * @throws SMPLTypeException if there is no such real value.
     */
    public double doubleValue() throws SMPLTypeException {
        throw new SMPLTypeException(SMPLType.REAL, getType());
    }
}
