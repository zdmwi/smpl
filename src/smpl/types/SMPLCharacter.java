package smpl.types;

public class SMPLCharacter extends SMPLInt {
    
    char value;

    public SMPLCharacter() {
        this('\u0000');
    }

    public SMPLCharacter(Character v) {
        super((int) v.charValue());
    }
    
    public SMPLType getType() {
        return SMPLType.CHARACTER;
    }

    public String toString() {
        return "#c" + String.valueOf(value);
    }
}
