package smpl.syntax.ast;

import smpl.exceptions.VisitException;

import smpl.semantics.Visitor;

import smpl.syntax.ast.core.Exp;
import smpl.types.SMPLValue;

public class ExpLit extends Exp {
    SMPLValue<?> val;

    public ExpLit(SMPLValue<?> v) {
	    val = v;
    }

    public ExpLit(Integer v) {
        val = SMPLValue.make(v);
    }

    public ExpLit(Double v) {
        val = SMPLValue.make(v);
    }

    public SMPLValue<?> getVal() {
	    return val;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException {
	    return v.visitExpLit(this, arg);
    }

    public String toString() {
	    return val.toString();
    }
}

