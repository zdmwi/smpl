package smpl.types;

import smpl.exceptions.RuntimeException;
import static smpl.types.SMPLValue.make;

/**
 *
 * @author zdmwi,
 */
public class SMPLInt extends SMPLValue<SMPLInt> {
    
    int value;

    public SMPLInt() {
        this(0);
    }

    public SMPLInt(Integer v) {
        value = v;
    }
    
    public SMPLType getType() {
        return SMPLType.INTEGER;
    }
    
    public SMPLInt add(SMPLValue<?> arg) throws RuntimeException {
        return make(value + arg.intValue());
    }

    /**
     * Subtract the given value from this value.
     * @param arg The value to be subtracted
     * @return The difference as a new instance of FnPlotValue
     * @throws fnplot.sys.FnPlotException
     */
    public SMPLInt sub(SMPLValue<?> arg) throws RuntimeException {
        return make(value - arg.intValue());
    }

    /**
     * Multiply the given value by this value.
     * @param arg The multiplicand
     * @return The product as a new instance of FnPlotValue
     * @throws fnplot.sys.FnPlotException
     */
    public SMPLInt mul(SMPLValue<?> arg) throws RuntimeException {
        return make(value * arg.intValue());
    }

    /**
     * Divide the given value by this value.
     * @param arg The divisor
     * @return The quotient as a new instance of FnPlotValue
     * @throws fnplot.sys.FnPlotException
     */
    public SMPLInt div(SMPLValue<?> arg) throws RuntimeException {
        return make(value / arg.intValue());
    }

    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The divisor
     * @return The residue modulo arg as a new instance of FnPlotValue
     * @throws fnplot.values.TypeFnPlotException
     */
    public SMPLInt mod(SMPLValue<?> arg) throws RuntimeException {
        if (arg.isInteger()) {
            return make(value % arg.intValue());
        } else {
            return make(value % arg.intValue());
        }
    }

    public SMPLReal pow(SMPLValue<?> arg) throws RuntimeException {
        // casting the value to a double here is important to prevent loss of information
        return make(Math.pow((double) value, arg.doubleValue()));
    }
    
    public int intValue() {
        return value;
    }

    public double doubleValue() {
        return value;
    }

    public String toString() {
        return String.valueOf(value);
    }
}
