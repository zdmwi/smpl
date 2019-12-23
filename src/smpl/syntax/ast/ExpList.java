package smpl.syntax.ast;

import java.util.ArrayList;

import smpl.exceptions.SMPLException;

import smpl.syntax.ast.core.Exp;

import smpl.semantics.Visitor;

public class ExpList extends Exp {

    ExpSequence content;

    public ExpList(ExpSequence content) {
        this.content = content;
    }

    public ExpSequence getContent() {
	    return content;
    }

    public <S, T> T visit(Visitor<S,T> v, S arg) throws SMPLException {
	    return v.visitExpList(this, arg);
    }

  public String toString() {
    String st = "[";
    ArrayList<Exp> expSeq = content.getSeq();
    for (int i = 0; i < expSeq.size() - 1; i++) {
        st += expSeq.get(i).toString() + ", ";
    }
    st += expSeq.get(expSeq.size() - 1).toString() + "]";
    return st;
  }
}

