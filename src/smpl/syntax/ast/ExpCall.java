package smpl.syntax.ast;

import java.util.ArrayList;

import smpl.exceptions.SMPLException;

import smpl.syntax.ast.core.Exp;

import smpl.semantics.Visitor;

public class ExpCall extends Exp {
    Exp caller;
    ArrayList<Exp> arguments;

    public ExpCall() {
        super();
    }

    public ExpCall(Exp caller, ArrayList<Exp> arguments) {
        this.caller = caller;
        this.arguments = arguments;
    }

    public ArrayList<Exp> getArguments() {
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
        
        if (arguments.size() > 0) {
            argStr = arguments.get(0).toString();
        }
        for (int i = 1; i < arguments.size(); i++) {
            argStr = argStr + ", " + arguments.get(i);
        }
        return String.format("%s(%s)", caller.toString(), argStr);
    }

}
