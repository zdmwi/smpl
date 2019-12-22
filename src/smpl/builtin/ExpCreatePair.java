package smpl.builtin;

import smpl.syntax.ast.core.Exp;

import smpl.exceptions.SMPLException;
import smpl.semantics.Visitor;

public class ExpCreatePair extends Exp {

    String e1, e2;

    public ExpCreatePair() {
        super();
    }

    public ExpCreatePair(String e1, String e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public String getHead() {
        return e1;
    }

    public String getTail() {
        return e2;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SMPLException {
        return v.visitExpCreatePair(this, state);
    }

    @Override
    public String toString() {
        return "pair("+ e1 + ", " + e2 + ")";
    }

}


