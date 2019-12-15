package smpl.types;

import smpl.exceptions.SMPLException;

public class SMPLInt extends SMPLValue<SMPLInt> {
    
    int value;

    public SMPLInt() {
        this(0);
    }

    public SMPLInt(Integer v) {
        value = v;
    }
    
    public SMPLType getType() {
        return SMPLType.INT;
    }

    /**
     * Compute the bitwise AND of this value and the given value.
     * @param arg The second operand
     * @return The result of the bitwise AND between the operands
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLInt band(SMPLValue<?> arg) throws SMPLException {
        return make(value & arg.intValue());
    }

    /**
     * Compute the bitwise OR of this value and the given value.
     * @param arg The second operand
     * @return The result of the bitwise OR between the operands
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLInt bor(SMPLValue<?> arg) throws SMPLException {
        return make(value | arg.intValue());
    }

    /**
     * Check if the value and the given value are equal.
     * @param arg The second operand
     * @return The boolean result of the comparison
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLBoolean eq(SMPLValue<?> arg) throws SMPLException {
        return make(value == arg.doubleValue());
    }

    /**
     * Check if the value is greater than the given value.
     * @param arg The second operand
     * @return The boolean result of the comparison
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLBoolean gt(SMPLValue<?> arg) throws SMPLException {
        return make(value > arg.doubleValue());
    }

    /**
     * Check if the value is greater than or equal to the given value.
     * @param arg The second operand
     * @return The boolean result of the comparison
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLBoolean gteq(SMPLValue<?> arg) throws SMPLException {
        return make(value >= arg.doubleValue());
    }

    /**
     * Check if the value is lesser than the given value.
     * @param arg The second operand
     * @return The boolean result of the comparison
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLBoolean lt(SMPLValue<?> arg) throws SMPLException {
        return make(value < arg.doubleValue());
    }

    /**
     * Check if the value is lesser than or equal to the given value.
     * @param arg The second operand
     * @return The boolean result of the comparison
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLBoolean lteq(SMPLValue<?> arg) throws SMPLException {
        return make(value <= arg.doubleValue());
    }

    /**
     * Check if the value and the given value are not equal.
     * @param arg The second operand
     * @return The boolean result of the comparison
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLBoolean neq(SMPLValue<?> arg) throws SMPLException {
        return make(value != arg.doubleValue());
    }
    
    public SMPLInt add(SMPLValue<?> arg) throws SMPLException {
        return make(value + arg.intValue());
    }

    /**
     * Subtract the given value from this value.
     * @param arg The value to be subtracted
     * @return The difference as a new instance of SMPLValue
     * @throws smpl.exceptions.SMPLException
     */
    public SMPLInt sub(SMPLValue<?> arg) throws SMPLException {
        return make(value - arg.intValue());
    }

    /**
     * Multiply the given value by this value.
     * @param arg The multiplicand
     * @return The product as a new instance of SMPLValue
     * @throws smpl.exceptions.SMPLException
     */
    public SMPLInt mul(SMPLValue<?> arg) throws SMPLException {
        return make(value * arg.intValue());
    }

    /**
     * Divide the given value by this value.
     * @param arg The divisor
     * @return The quotient as a new instance of SMPLValue
     * @throws smpl.exceptions.SMPLException
     */
    public SMPLInt div(SMPLValue<?> arg) throws SMPLException {
        return make(value / arg.intValue());
    }

    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The divisor
     * @return The residue modulo arg as a new instance of SMPLValue
     * @throws smpl.exceptions.SMPLException
     */
    public SMPLInt mod(SMPLValue<?> arg) throws SMPLException {
        return make(value % arg.intValue());
    }

    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The exponent
     * @return The result of exponentiation as a new instance of SMPLReal
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLDouble pow(SMPLValue<?> arg) throws SMPLException {
        // casting the value to a double here is important to prevent loss of information
        return make(Math.pow((double) value, arg.doubleValue()));
    }

    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The sign of the unary expression
     * @return The signed SMPLValue
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLInt unary(String arg) throws SMPLException {
        // casting the value to a double here is important to prevent loss of information
        if(arg.equals("+")) {
            return make(value);
        }
        return make(-value);
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
