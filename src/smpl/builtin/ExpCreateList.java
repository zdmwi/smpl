package smpl.builtin;

import smpl.syntax.ast.core.Exp;

import smpl.exceptions.SMPLException;
import smpl.semantics.Visitor;

public class ExpCreateList extends Exp {

    String e1;

    public ExpCreateList() {
        super();
    }

    public ExpCreateList(String e1) {
        this.e1 = e1;
    }

    public String getParam() {
        return e1;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SMPLException {
        return v.visitExpCreateList(this, state);
    }

    @Override
    public String toString() {
        return "list("+ e1 +")";
    }

}


