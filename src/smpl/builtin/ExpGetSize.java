package smpl.builtin;

import smpl.syntax.ast.core.Exp;

import smpl.exceptions.SMPLException;
import smpl.semantics.Visitor;

public class ExpGetSize extends Exp {

    String p;

    public ExpGetSize() {
        super();
    }

    public ExpGetSize(String p) {
        this.p = p;
    }

    public String getParam() {
        return p;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SMPLException {
        return v.visitExpGetSize(this, state);
    }

    @Override
    public String toString() {
        return "size("+ p +")";
    }

}


