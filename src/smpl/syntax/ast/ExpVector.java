package smpl.syntax.ast;

import java.util.ArrayList;

import smpl.exceptions.SMPLException;

import smpl.syntax.ast.core.Exp;

import smpl.semantics.Visitor;

public class ExpVector extends Exp {

    ArrayList<Exp> content;

    public ExpVector(ArrayList<Exp> content) {
        this.content = content;
    }

    public ArrayList<Exp> getContent() {
	    return content;
    }

    public <S, T> T visit(Visitor<S,T> v, S arg) throws SMPLException {
	    return v.visitExpVector(this, arg);
    }

  public String toString() {
    String st = "[:";
    for (int i = 0; i < content.size() - 1; i++) {
        st += content.get(i).toString() + ", ";
    }
    st += content.get(content.size() - 1).toString() + ":]";
    return st;
  }
}

