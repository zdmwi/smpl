package smpl.builtin;

import smpl.syntax.ast.core.Exp;

import smpl.exceptions.SMPLException;
import smpl.semantics.Visitor;

public class ExpSubstring extends Exp {

    String e1, e2, e3;

    public ExpSubstring() {
        super();
    }

    public ExpSubstring(String e1, String e2, String e3) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
    }

    public String getParam1() {
        return e1;
    }

    public String getParam2() {
        return e2;
    }

    public String getParam3() {
        return e3;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SMPLException {
        return v.visitExpSubstring(this, state);
    }

    @Override
    public String toString() {
        return "substr("+ e1 + ", "+ e2 + ", " + e3 +")";
    }

}


