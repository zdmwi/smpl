package smpl.syntax.ast;

import smpl.exceptions.SMPLException;

import smpl.syntax.ast.core.Exp;

import smpl.semantics.Visitor;

public class ExpReadInt extends Exp {

    public ExpReadInt() {}

    public <S, T> T visit(Visitor<S,T> v, S arg) throws SMPLException {
        return v.visitExpReadInt(this, arg);
    }

    public String toString() {
        return "readint()";
    }
}

