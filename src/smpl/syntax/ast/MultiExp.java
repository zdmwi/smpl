package smpl.syntax.ast;

import java.util.ArrayList;

import smpl.exceptions.SMPLException;

import smpl.syntax.ast.core.Exp;

import smpl.semantics.Visitor;

public class MultiExp extends Exp {

    ExpSequence exps;

    public MultiExp() {
	    super();
    }

    public MultiExp(ExpSequence e) {
	    exps = e;
    }

    public ExpSequence getExps() {
	    return exps;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws SMPLException {
	    return v.visitMultiExp(this, arg);
    }

    public String toString() {
        String st = "(";
        ArrayList<Exp> expSeq = exps.getSeq();
        int n = expSeq.size();
        for(int i = 0; i < n - 1; i++) {
            st += expSeq.get(i).toString() + ", ";
        }
        st += expSeq.get(n).toString() + ")";
	    return st;
    }
}
