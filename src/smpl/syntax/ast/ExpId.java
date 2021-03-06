package smpl.syntax.ast;

import smpl.exceptions.SMPLException;

import smpl.syntax.ast.core.Exp;

import smpl.semantics.Visitor;

public class ExpId extends Exp {

    String id;

    public ExpId(String st) {
	    id = st;
    }

    public String getId() {
	    return id;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws SMPLException {
	    return v.visitExpId(this, arg);
    }

    public String toString() {
	    return id;
    }
}
