package smpl.syntax.ast;

import java.util.ArrayList;
import java.util.Iterator;

import smpl.exceptions.VisitException;

import smpl.syntax.ast.core.Exp;

import smpl.semantics.Visitor;

public class StmtSequence extends Exp {

    ArrayList<Exp> seq;		// sequence of commands

    public StmtSequence() {
	    seq = new ArrayList<>();
    }

    public StmtSequence(Statement s) {
        this();
        seq.add(s);
    }

    public ArrayList<Exp> getSeq() {
	    return seq;
    }

    public StmtSequence add(Statement s) {
        seq.add(s);
        return this;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException {
	    return v.visitStmtSequence(this, arg);
    }

    public String toString() {
        Iterator<Exp> iter = seq.iterator();

        String result = "";
        while (iter.hasNext()) {
            result = result + iter.next().toString() + "\n";
        }

        return result;
    }
}

