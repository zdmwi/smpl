package smpl.types;

import smpl.exceptions.SMPLException;


public class SMPLDouble extends SMPLValue<SMPLDouble> {
    
    double value;

    public SMPLDouble() {
        this(0D);
    }

    public SMPLDouble(Double v) {
        value = v;
    }
    
    @Override
    public SMPLType getType() {
        return SMPLType.DOUBLE;
    }
    
    @Override
    public SMPLDouble add(SMPLValue<?> arg) throws SMPLException {
        return make(value + arg.doubleValue());
    }

    /**
     * Subtract the given value from this value.
     * @param arg The value to be subtracted
     * @return The difference as a new instance of FnPlotValue
     * @throws smpl.exception.SMPLException
     */
    @Override
    public SMPLDouble sub(SMPLValue<?> arg) throws SMPLException {
        return make(value - arg.doubleValue());
    }

    /**
     * Multiply the given value by this value.
     * @param arg The multiplicand
     * @return The product as a new instance of FnPlotValue
     * @throws smpl.exception.SMPLException
     */
    @Override
    public SMPLDouble mul(SMPLValue<?> arg) throws SMPLException {
        return make(value * arg.doubleValue());
    }

    /**
     * Divide the given value by this value.
     * @param arg The divisor
     * @return The quotient as a new instance of FnPlotValue
     * @throws smpl.exception.SMPLException
     */
    @Override
    public SMPLDouble div(SMPLValue<?> arg) throws SMPLException {
        return make(value / arg.doubleValue());
    }

    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The divisor
     * @return The residue modulo arg as a new instance of SMPLValue
     * @throws smpl.exception.SMPLException
     */
    @Override
    public SMPLDouble mod(SMPLValue<?> arg) throws SMPLException {
        if (arg.getType() == SMPLType.INT) {
            return make(value % arg.intValue());
        } else {
            return make(value % arg.doubleValue());
        }
    }

    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The exponent
     * @return The result of exponentiation as a new instance of SMPLDouble
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLDouble pow(SMPLValue<?> arg) throws SMPLException {
        return make(Math.pow((double) value, arg.doubleValue()));
    }

    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The sign of the unary expression
     * @return The signed SMPLValue
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLDouble unary(String arg) throws SMPLException {
        if(arg.equals("+")) {
            return make(value);
        }
        return make(-value);
    }
    
    @Override
    public int intValue() {
        return (int) value;
    }

    @Override
    public double doubleValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
