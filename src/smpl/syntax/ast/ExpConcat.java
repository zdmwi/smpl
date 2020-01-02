package smpl.syntax.ast;

import smpl.exceptions.SMPLException;

import smpl.syntax.ast.core.Exp;

import smpl.semantics.Visitor;

public class ExpConcat extends Exp {

    ExpList exp1, exp2;

    public ExpConcat(ExpList e1, ExpList e2) {
        exp1 = e1;
        exp2 = e2;
    }

    public ExpList getExpL() {
	    return exp1;
    }

    public ExpList getExpR() {
	    return exp2;
    }

    public <S, T> T visit(Visitor<S,T> v, S arg) throws SMPLException {
	    return v.visitExpConcat(this, arg);
    }

  public String toString() {
    return exp1.toString() + " @ " + exp2.toString();
  }
}

