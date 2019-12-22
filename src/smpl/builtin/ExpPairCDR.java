package smpl.builtin;

import smpl.syntax.ast.core.Exp;

import smpl.exceptions.SMPLException;
import smpl.semantics.Visitor;

public class ExpPairCDR extends Exp {

    String p;

    public ExpPairCDR() {
        super();
    }

    public ExpPairCDR(String p) {
        this.p = p;
    }

    public String getParam() {
        return p;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SMPLException {
        return v.visitExpPairCDR(this, state);
    }

    @Override
    public String toString() {
        return "cdr("+ p +")";
    }

}


