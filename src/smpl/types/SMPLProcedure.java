package smpl.types;

import smpl.exceptions.SMPLException;
import smpl.semantics.Environment;
import smpl.syntax.ast.ExpProcedure;
import java.util.ArrayList;

public class SMPLProcedure extends SMPLValue<SMPLProcedure> {
    ExpProcedure procExp;
    Environment<SMPLValue<?>> closingEnv;

    /**
     * Create a new instance of a user-defined procedure.
     * @param procExp The function expression that was evaluated
     * @param closingEnv The environment over which this function is closed
     */
    public SMPLProcedure(ExpProcedure procExp, Environment<SMPLValue<?>> closingEnv) {
        this.procExp = procExp;
        this.closingEnv = closingEnv;
    }
    
    public SMPLBoolean eq(SMPLValue<?> arg) throws SMPLException {
        if (arg.getType() != getType()) {
            return make(false);
        }
        return make(this.equals(arg));
    }

    public SMPLBoolean neq(SMPLValue<?> arg) throws SMPLException {
        if (arg.getType() != getType()) {
            return make(true);
        }
        return make(!this.equals(arg));
    }
    
    @Override
    public SMPLType getType() {
        return SMPLType.PROCEDURE;
    }
    
    public SMPLProcedure procValue() {
        return this;
    }

    public ExpProcedure getProcExp() {
        return procExp;
    }

    public Environment<SMPLValue<?>> getClosingEnv() {
        return closingEnv;
    }
    
    @Override
    public String toString() {
        String params;
        ArrayList<String> paramList = procExp.getParameters();
        int n = paramList.size();
        switch (n) {
            case 0: params = ""; break;
            case 1: params = paramList.get(0); break;
            default: 
                params = paramList.get(0);
                for (int i = 1; i < n; i++) {
                    params += ", " + paramList.get(i);
                }
        }
        String body = procExp.getBody().toString();
        return String.format("[Procedure: (%s) -> %s]", params, body);
    }
    
}
