package smpl.syntax.ast;

import java.util.ArrayList;
import java.util.Iterator;

import smpl.exceptions.SMPLException;

import smpl.syntax.ast.core.Exp;

import smpl.semantics.Visitor;

public class ExpSequence extends Exp {

    ArrayList<Exp> seq;		// sequence of commands

    public ExpSequence() {
	    seq = new ArrayList<>();
    }

    public ExpSequence(Exp s) {
        this();
        seq.add(0, s);
    }

    public ArrayList<Exp> getSeq() {
	    return seq;
    }

    public ExpSequence add(Exp s) {
        seq.add(0, s);
        return this;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws SMPLException {
	    return v.visitExpSequence(this, arg);
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < seq.size() - 1; i++) {
            result += seq.get(i).toString() + ", ";
        }
        result += seq.get(seq.size() - 1);

        return result;
    }
}

