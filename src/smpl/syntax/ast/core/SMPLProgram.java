package smpl.syntax.ast.core;

import smpl.exceptions.SMPLException;

import smpl.semantics.Visitor;

import smpl.syntax.ast.StmtSequence;

public class SMPLProgram extends Exp {
    StmtSequence seq;

    public SMPLProgram(StmtSequence s) {
	seq = s;
    }

    public StmtSequence getSeq() {
	return seq;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws SMPLException {
	return v.visitSMPLProgram(this, arg);
    }

    public String toString() {
	return seq.toString();
    }
}
