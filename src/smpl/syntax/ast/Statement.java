package smpl.syntax.ast;

import smpl.exceptions.VisitException;

import smpl.syntax.ast.core.Exp;

import smpl.semantics.Visitor;

public class Statement extends Exp {

    Exp exp;

    public Statement() {
	    super();
    }

    public Statement(Exp e) {
	    exp = e;
    }

    public Exp getExp() {
	    return exp;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException {
	    return v.visitStatement(this, arg);
    }

    public String toString() {
	    return exp.toString();
    }
}
