package smpl.types;

import smpl.exceptions.SMPLException;
import smpl.exceptions.SMPLTypeException;

/**
 *
 * @param <T> The type of base Java value wrapped by this class
 * @author zdmwi,
 * Created on 10-Dec-2019 at 6:00 PM
 */
public abstract class SMPLValue<T extends SMPLValue<T>> {

    /**
     * Create a SMPL value wrapping a Java double
     * 
     * @param v the double value to be wrapped
     * @return The SMPLValue instance to represent that double precision value
     */
    public static SMPLDouble make(Double v) {
        return new SMPLDouble(v);
    }
    
    /**
     * Create a SMPL value wrapping a Java integer
     * @param v The int value to be wrapped
     * @return The SMPLValue instance to represent that integer value.
     */
    public static SMPLInt make(Integer v) {
        return new SMPLInt(v);
    }

    /**
     * Create a SMPL value wrapping a Java string
     * @param v The string value to be wrapped
     * @return The SMPLValue instance to represent that string value.
     */
    public static SMPLString make(String v) {
        return new SMPLString(v);
    }

    /**
     * Create a SMPL value wrapping a Java character
     * @param v The character value to be wrapped
     * @return The SMPLValue instance to represent that string value.
     */
    public static SMPLCharacter make(Character v) {
        return new SMPLCharacter(v);
    }

    /**
     * Create a SMPL value wrapping a Java boolean
     * @param v The string value to be wrapped
     * @return The SMPLValue instance to represent that string value.
     */
    public static SMPLBoolean make(Boolean v) {
        return new SMPLBoolean(v);
    }
    
    /**
     *
     * @return The type of this value.
     */
    public abstract SMPLType getType();
    

    /**
     * Add the given value to this value
     * @param arg The value to be added.
     * @return The sum of the two values as a new instance of SMPLValue.
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under addition
     */
    public SMPLValue<?> add(SMPLValue<?> arg) throws SMPLException {
        throw new SMPLTypeException("Operation '+' called with non-numeric type");
    }

    /**
     * Subtract the given value from this value.
     * @param arg The value to be subtracted
     * @return The difference as a new instance of SMPLValue
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under subtraction
     */
    public SMPLValue<?> sub(SMPLValue<?> arg) throws SMPLException {
        throw new SMPLTypeException("Operation '-' called with non-numeric type");
    }

    /**
     * Multiply the given value by this value.
     * @param arg The multiplicand
     * @return The product as a new instance of SMPLValue
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under multiplication
     */
    public SMPLValue<?> mul(SMPLValue<?> arg) throws SMPLException {
        throw new SMPLTypeException("Operation '*' called with non-numeric type");
    }

    /**
     * Divide the given value by this value.
     * @param arg The divisor
     * @return The quotient as a new instance of SMPLValue
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLValue<?> div(SMPLValue<?> arg) throws SMPLException {
        throw new SMPLTypeException("Operation '/' called with non-numeric type");
    }
    
    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The divisor
     * @return The residue modulo arg as a new instance of SMPLValue
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLValue<?> mod(SMPLValue<?> arg) throws SMPLException {
        throw new SMPLTypeException("Operation '%' called with non-numeric type");
    }

    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The exponent
     * @return The result of exponentiation as a new instance of SMPLValue
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLValue<?> pow(SMPLValue<?> arg) throws SMPLException {
        throw new SMPLTypeException("Operation '^' called with non-numeric type");
    }

    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The head of the linked list to be appended to the current list
     * @return The result of concatenation as a new instance of SMPLValue
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLValue<?> concat(SMPLValue<?> arg) throws SMPLException {
        throw new SMPLTypeException("Operation '@' called with non-pair type");
    }

    /**
     * Compute the remainder of dividing this value by the given value.
     * @param arg The sign of the unary expression
     * @return The signed SMPLValue
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLValue<?> unary(String arg) throws SMPLException {
        String msg = String.format("Unary operation '%s' called with non-numeric type");
        throw new SMPLTypeException(msg);
    }

    /**
     * Compute the logical OR of this value and the given value.
     * @param arg The second operand
     * @return The result of the logical OR between the values
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLValue<?> or(SMPLValue<?> arg) throws SMPLException {
        throw new SMPLTypeException("Operation 'or' called with non-boolean type");
    }

    /**
     * Compute the logical AND of this value and the given value.
     * @param arg The second operand
     * @return The result of the logical AND between the values
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLValue<?> and(SMPLValue<?> arg) throws SMPLException {
        throw new SMPLTypeException("Operation 'and' called with non-boolean type");
    }

    /**
     * Compute the logical NOT of this value.
     * @param arg The second operand
     * @return The result of the logical NOT of the value
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLValue<?> not() throws SMPLException {
        throw new SMPLTypeException("Operation 'not' called with non-boolean type");
    }

    /**
     * Compute the bitwise AND of this value and the given value.
     * @param arg The second operand
     * @return The result of the bitwise AND between the operands
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLValue<?> band(SMPLValue<?> arg) throws SMPLException {
        throw new SMPLTypeException("Operation '&' called with non-numeric type");
    }

    /**
     * Compute the bitwise OR of this value and the given value.
     * @param arg The second operand
     * @return The result of the bitwise OR between the operands
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLValue<?> bor(SMPLValue<?> arg) throws SMPLException {
        throw new SMPLTypeException("Operation '|' called with non-numeric type");
    }

    /**
     * Compute the bitwise OR of this value and the given value.
     * @param arg The second operand
     * @return The result of the bitwise NOT of the operand
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLValue<?> bnot() throws SMPLException {
        throw new SMPLTypeException("Operation '~' called with non-numeric type");
    }

    /**
     * Check if the value and the given value are equal.
     * @param arg The second operand
     * @return The boolean result of the comparison
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLValue<?> eq(SMPLValue<?> arg) throws SMPLException {
        throw new SMPLTypeException();
    }

    /**
     * Check if the value is greater than the given value.
     * @param arg The second operand
     * @return The boolean result of the comparison
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLValue<?> gt(SMPLValue<?> arg) throws SMPLException {
        throw new SMPLTypeException("Operation '>' called with non-numeric type");
    }

    /**
     * Check if the value is greater than or equal to the given value.
     * @param arg The second operand
     * @return The boolean result of the comparison
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLValue<?> gteq(SMPLValue<?> arg) throws SMPLException {
        throw new SMPLTypeException("Operation '>=' called with non-numeric type");
    }

    /**
     * Check if the value is lesser than the given value.
     * @param arg The second operand
     * @return The boolean result of the comparison
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLValue<?> lt(SMPLValue<?> arg) throws SMPLException {
        throw new SMPLTypeException("Operation '<' called with non-numeric type");
    }

    /**
     * Check if the value is lesser than or equal to the given value.
     * @param arg The second operand
     * @return The boolean result of the comparison
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLValue<?> lteq(SMPLValue<?> arg) throws SMPLException {
        throw new SMPLTypeException("Operation '<=' called with non-numeric type");
    }

    /**
     * Check if the value and the given value are not equal.
     * @param arg The second operand
     * @return The boolean result of the comparison
     * @throws smpl.exception.SMPLException if there is a type incompatibility between this value and the argument value under division
     */
    public SMPLValue<?> neq(SMPLValue<?> arg) throws SMPLException {
        throw new SMPLTypeException();
    }

    /**
     *
     * @return The integer value wrapped in this SMPL value
     * @throws SMPLTypeException If there is no such integer
     */
    public int intValue() throws SMPLTypeException {
        throw new SMPLTypeException(SMPLType.INT, getType());
    }

    /**
     *
     * @return The double value wrapped in this SMPL value
     * @throws SMPLTypeException if there is no such real value.
     */
    public double doubleValue() throws SMPLTypeException {
        throw new SMPLTypeException(SMPLType.DOUBLE, getType());
    }

    /**
     *
     * @return The boolean value wrapped in this SMPL value
     * @throws SMPLTypeException If there is no such integer
     */
    public boolean boolValue() throws SMPLTypeException {
        throw new SMPLTypeException(SMPLType.BOOLEAN, getType());
    }

    /**
     *
     * @return The String value wrapped in this SMPL value
     * @throws SMPLTypeException If there is no such integer
     */
    public String stringValue() throws SMPLTypeException {
        throw new SMPLTypeException(SMPLType.STRING, getType());
    }
}
