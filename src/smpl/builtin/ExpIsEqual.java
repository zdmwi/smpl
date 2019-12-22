package smpl.builtin;

import smpl.syntax.ast.core.Exp;

import smpl.exceptions.SMPLException;
import smpl.semantics.Visitor;

public class ExpIsEqual extends Exp {

    String e1, e2;

    public ExpIsEqual() {
        super();
    }

    public ExpIsEqual(String e1, String e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public String getParam1() {
        return e1;
    }

    public String getParam2() {
        return e2;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SMPLException {
        return v.visitExpIsEqual(this, state);
    }

    @Override
    public String toString() {
        return "equal?("+ e1 + ", "+ e2 +")";
    }

}


