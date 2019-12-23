package smpl.syntax.ast;

import smpl.exceptions.SMPLException;

import smpl.syntax.ast.core.Exp;

import smpl.semantics.Visitor;

public class StmtPrint extends Statement {

    Exp exp;

    public StmtPrint(Exp e) {
        exp = e;
    }

    public Exp getExp() {
        return exp;
    }

    public <S, T> T visit(Visitor<S,T> v, S arg) throws SMPLException {
        return v.visitStmtPrint(this, arg);
    }

    public String toString() {
        return "print(" + exp.toString() + ")";
    }
}

