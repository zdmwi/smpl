package smpl.syntax.ast;

import smpl.exceptions.SMPLException;

import smpl.syntax.ast.core.Exp;

import smpl.semantics.Visitor;

public class ExpPrintLn extends Exp {

    Exp exp;

    public ExpPrintLn(Exp e) {
        exp = e;
    }

    public Exp getExp() {
        return exp;
    }

    public <S, T> T visit(Visitor<S,T> v, S arg) throws SMPLException {
        return v.visitExpPrintLn(this, arg);
    }

    public String toString() {
        return "println(" + exp.toString() + ")";
    }
}

