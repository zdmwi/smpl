package smpl.syntax.ast;

import smpl.exceptions.VisitException;

import smpl.syntax.ast.core.Exp;

import smpl.semantics.Visitor;

public class ExpLit extends Exp {

    int val;

    public ExpLit(Integer v) {
	    val = v.intValue();
    }

    public int getVal() {
	    return val;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException {
	    return v.visitExpLit(this, arg);
    }

    public String toString() {
	    return Integer.toString(val);
    }
}

