package smpl.syntax.ast;

import smpl.exceptions.SMPLException;

import smpl.syntax.ast.core.Exp;

import smpl.semantics.Visitor;

public class ExpPrint extends Exp {

    Exp exp;

    public ExpPrint(Exp e) {
        exp = e;
    }

    public Exp getExp() {
        return exp;
    }

    public <S, T> T visit(Visitor<S,T> v, S arg) throws SMPLException {
        return v.visitExpPrint(this, arg);
    }

    public String toString() {
        return "print(" + exp.toString() + ")";
    }
}

