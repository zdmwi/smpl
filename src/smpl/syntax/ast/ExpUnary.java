package smpl.syntax.ast;

import smpl.exceptions.VisitException;

import smpl.syntax.ast.core.Exp;

import smpl.semantics.Visitor;

public class ExpUnary extends Exp {

    String sign;
    Exp exp;

    public ExpUnary(String s, Exp e) {
        sign = s;
        exp = e;
    }

    public Exp getExp() {
        return exp;
    }

    public String getSign() {
        return sign;
    }

    public <S, T> T visit(Visitor<S,T> v, S arg) throws VisitException {
        return v.visitExpUnary(this, arg);
    }

    public String toString() {
        if (sign.equals("+")) {
            return exp.toString();
        }
        return sign + exp.toString();
    }
}

