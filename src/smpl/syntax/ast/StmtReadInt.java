package smpl.syntax.ast;

import smpl.exceptions.SMPLException;

import smpl.semantics.Visitor;

public class StmtReadInt extends Statement {

    public StmtReadInt() {}

    public <S, T> T visit(Visitor<S,T> v, S arg) throws SMPLException {
        return v.visitStmtReadInt(this, arg);
    }

    public String toString() {
        return "readint()";
    }
}

