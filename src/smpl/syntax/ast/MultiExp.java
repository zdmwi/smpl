package smpl.syntax.ast;

import java.util.ArrayList;

import smpl.exceptions.SMPLException;

import smpl.syntax.ast.core.Exp;

import smpl.semantics.Visitor;

public class MultiExp extends Exp {

    ArrayList<Exp> exps;

    public MultiExp() {
	    super();
    }

    public MultiExp(ArrayList<Exp> e) {
	    exps = e;
    }

    public ArrayList<Exp> getExps() {
	    return exps;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws SMPLException {
	    return v.visitMultiExp(this, arg);
    }

    public String toString() {
        String st = "(";
        int n = exps.size();
        for(int i = 0; i < n - 1; i++) {
            st += exps.get(i).toString() + ", ";
        }
        st += exps.get(n).toString() + ")";
	    return st;
    }
}
