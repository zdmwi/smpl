package smpl.syntax.ast;

import java.util.ArrayList;

import smpl.exceptions.SMPLException;

import smpl.syntax.ast.core.Exp;

import smpl.semantics.Visitor;

public class ExpIndexVector extends Exp {
    Exp vecExp;
    Exp indexExp;

    public ExpIndexVector() {
        super();
    }

    public ExpIndexVector(Exp vecExp, Exp indexExp) {
        this.vecExp = vecExp;
        this.indexExp = indexExp;
    }

    public Exp getVecExp() {
        return this.vecExp;
    }

    public Exp getIndexExp() {
        return this.indexExp;
    }
    
    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SMPLException {
        return v.visitExpIndexVector(this, state);
    }

    @Override
    public String toString() {
        return String.format("%s[%s]", vecExp.toString(), indexExp.toString());
    }

}
