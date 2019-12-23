package smpl.syntax.ast;

import java.util.ArrayList;

import smpl.exceptions.SMPLException;

import smpl.syntax.ast.core.Exp;

import smpl.semantics.Visitor;

public class ExpCall extends Exp {
    Exp caller;
    ExpSequence arguments;

    public ExpCall() {
        super();
    }

    public ExpCall(Exp caller, ExpSequence arguments) {
        this.caller = caller;
        this.arguments = arguments;
    }

    public ExpSequence getArguments() {
        return this.arguments;
    }

    public Exp getCaller() {
        return this.caller;
    }
    
    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SMPLException {
        return v.visitProcCall(this, state);
    }

    @Override
    public String toString() {
        String argStr = "";
        ArrayList<Exp> expSeq = arguments.getSeq();
        if (expSeq.size() > 0) {
            argStr = expSeq.get(0).toString();
        }
        for (int i = 1; i < expSeq.size(); i++) {
            argStr = argStr + ", " + expSeq.get(i);
        }
        return String.format("%s(%s)", caller.toString(), argStr);
    }

}
