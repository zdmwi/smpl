package smpl.syntax.ast.core;

import smpl.exceptions.VisitException;

import smpl.semantics.Visitor;

import smpl.syntax.ast.StmtSequence;

public class ArithProgram extends Exp {
    StmtSequence seq;

    public ArithProgram(StmtSequence s) {
	seq = s;
    }

    public StmtSequence getSeq() {
	return seq;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException {
	return v.visitArithProgram(this, arg);
    }

    public String toString() {
	return seq.toString();
    }
}
