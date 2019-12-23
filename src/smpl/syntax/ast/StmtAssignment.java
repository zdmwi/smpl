package smpl.syntax.ast;

import java.util.ArrayList;

import smpl.exceptions.SMPLException;

import smpl.syntax.ast.core.Exp;

import smpl.semantics.Visitor;

public class StmtAssignment extends Statement {

    ArrayList<String> ids;
    ExpSequence exps;

    public StmtAssignment(ArrayList<String> ids, ExpSequence exps) {
        this.ids = ids;
        this.exps = exps;
    }

    public ArrayList<String> getIds(){
	    return ids;
    }

    public ExpSequence getExps() {
	    return exps;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws SMPLException {
	    return v.visitStmtAssignment(this, arg);
    }

    public String toString() {
        String st = "";
        ArrayList<Exp> expSeq = exps.getSeq();
        for (int i = 0; i < ids.size() - 1; i++) {
            st += ids.get(i).toString() + ", ";
        }
        st += ids.get(ids.size() - 1).toString() + " := ";
        for (int i = 0; i < expSeq.size() - 1; i++) {
            st += expSeq.get(i).toString() + ", ";
        }
        st += expSeq.get(expSeq.size() - 1).toString();
	    return st;
    }
}
