package smpl.types;

import smpl.exceptions.SMPLException;

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

    /**
     * Compute the logical OR of this value and the given value.
     * @param arg The second operand
     * @return The result of the bitwise OR between the operands
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLBoolean or(SMPLValue<?> arg) throws SMPLException {
        return make(value || arg.boolValue());
    }

    /**
     * Compute the logical AND of this value and the given value.
     * @param arg The second operand
     * @return The result of the bitwise OR between the operands
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLBoolean and(SMPLValue<?> arg) throws SMPLException {
        return make(value && arg.boolValue());
    }

    /**
     * Check if the value and the given value are equal.
     * @param arg The second operand
     * @return The boolean result of the equality comparison
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLBoolean eq(SMPLValue<?> arg) throws SMPLException {
        return make(value == arg.boolValue());
    }

    /**
     * Check if the value and the given value are not equal.
     * @param arg The second operand
     * @return The boolean result of the inequality comparison
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLBoolean neq(SMPLValue<?> arg) throws SMPLException {
        return make(value != arg.boolValue());
    }

    public boolean boolValue() {
        return value;
    }

    public String toString() {
        return "#" + String.valueOf(value).substring(0, 1);
    }
}
