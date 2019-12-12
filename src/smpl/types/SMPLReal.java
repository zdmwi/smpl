package smpl.types;

import smpl.exceptions.RuntimeException;

/**
 *
 * @author newts
 * Created on 14-Oct-2016
 */
public class SMPLReal extends SMPLValue<SMPLReal> {
    
    double value;

    public SMPLReal() {
        this(0D);
    }

    public SMPLReal(Double v) {
        value = v;
    }
    
    @Override
    public SMPLType getType() {
        return SMPLType.REAL;
    }
    
    @Override
    public SMPLReal add(SMPLValue<?> arg) throws RuntimeException {
        return make(value + arg.doubleValue());
    }

    /**
     * Subtract the given value from this value.
     * @param arg The value to be subtracted
     * @return The difference as a new instance of FnPlotValue
     * @throws fnplot.sys.FnPlotException
     */
    @Override
    public SMPLReal sub(SMPLValue<?> arg) throws RuntimeException {
        return make(value - arg.doubleValue());
    }

    /**
     * Multiply the given value by this value.
     * @param arg The multiplicand
     * @return The product as a new instance of FnPlotValue
     * @throws fnplot.sys.FnPlotException
     */
    @Override
    public SMPLReal mul(SMPLValue<?> arg) throws RuntimeException {
        return make(value * arg.doubleValue());
    }

    /**
     * Divide the given value by this value.
     * @param arg The divisor
     * @return The quotient as a new instance of FnPlotValue
     * @throws fnplot.sys.FnPlotException
     */
    @Override
    public SMPLReal div(SMPLValue<?> arg) throws RuntimeException {
        return make(value / arg.doubleValue());
    }

    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The divisor
     * @return The residue modulo arg as a new instance of FnPlotValue
     * @throws fnplot.values.TypeFnPlotException
     */
    @Override
    public SMPLReal mod(SMPLValue<?> arg) throws RuntimeException {
        if (arg.isInteger()) {
            return make(value % arg.intValue());
        } else {
            return make(value % arg.doubleValue());
        }
    }

    public SMPLReal pow(SMPLValue<?> arg) throws RuntimeException {
        return make(Math.pow(value, arg.doubleValue()));
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
