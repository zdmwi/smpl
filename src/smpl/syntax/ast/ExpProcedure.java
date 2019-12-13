package smpl.syntax.ast;

import java.util.ArrayList;

import smpl.exceptions.SMPLException;

import smpl.semantics.Visitor;

import smpl.syntax.ast.core.Exp;

public class ExpProcedure extends Exp {
    
    ArrayList<String> parameters;
    Exp body;

    public ExpProcedure() {
        super();
    }

    public ExpProcedure(ArrayList<String> parameters, Exp body) {
        this.parameters = parameters;
        this.body = body;
    }

    public ArrayList<String> getParameters() {
        return parameters;
    }    

    public Exp getBody() {
        return body;
    }
    
    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SMPLException {
        return v.visitProcDefinition(this, state);
    }

    @Override
    public String toString() {
        String paramStr = "";
        if (parameters.size() > 0) {
            paramStr = parameters.get(0);
        }
        for (int i = 1; i < parameters.size(); i++) {
            paramStr = paramStr + ", " + parameters.get(i);
        }
        return String.format("(proc (%s) -> %s)", paramStr, body);
    }

}
