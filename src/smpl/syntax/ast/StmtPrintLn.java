package smpl.syntax.ast;

import smpl.exceptions.SMPLException;

import smpl.syntax.ast.core.Exp;

import smpl.semantics.Visitor;

public class StmtPrintLn extends Statement {

    Exp exp;

    public StmtPrintLn(Exp e) {
        exp = e;
    }

    public Exp getExp() {
        return exp;
    }

    public <S, T> T visit(Visitor<S,T> v, S arg) throws SMPLException {
        return v.visitStmtPrintLn(this, arg);
    }

    public String toString() {
        return "println(" + exp.toString() + ")";
    }
}

