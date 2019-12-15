package smpl.syntax.ast;

import smpl.exceptions.SMPLException;

import smpl.syntax.ast.core.Exp;

import smpl.semantics.Visitor;

public class ExpRead extends Exp {

    public ExpRead() {}

    public <S, T> T visit(Visitor<S,T> v, S arg) throws SMPLException {
        return v.visitExpRead(this, arg);
    }

    public String toString() {
        return "read()";
    }
}

