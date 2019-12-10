package smpl.syntax.ast;

import smpl.exceptions.VisitException;

import smpl.syntax.ast.core.Exp;

import smpl.semantics.Visitor;

public class ExpVar extends Exp {

    String var;

    public ExpVar(String id) {
	    var = id;
    }

    public String getVar() {
	    return var;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException {
	    return v.visitExpVar(this, arg);
    }

    public String toString() {
	    return var;
    }
}
