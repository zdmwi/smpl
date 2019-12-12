package smpl.semantics;

import java.util.ArrayList;
import java.util.Iterator;

import smpl.exceptions.SMPLException;

import smpl.semantics.Environment;

import smpl.types.SMPLValue;

import smpl.syntax.ast.core.SMPLProgram;
import smpl.syntax.ast.Statement;
import smpl.syntax.ast.StmtSequence;
import smpl.syntax.ast.StmtDefinition;
import smpl.syntax.ast.ExpLit;
import smpl.syntax.ast.ExpId;
import smpl.syntax.ast.ExpAdd;
import smpl.syntax.ast.ExpSub;
import smpl.syntax.ast.ExpMul;
import smpl.syntax.ast.ExpPow;
import smpl.syntax.ast.ExpDiv;
import smpl.syntax.ast.ExpMod;
import smpl.syntax.ast.ExpUnary;

public class Evaluator implements Visitor<Environment<SMPLValue<?>>, SMPLValue<?>> {
    /* For this visitor, the argument passed to all visit
       methods will be the environment object that used to
       be passed to the eval method in the first style of
       implementation. */

    // allocate state here
    protected SMPLValue<?> result;	// result of evaluation

    public Evaluator() {
        // perform initialisations here
        result = SMPLValue.make(0);
    }

    public Environment getDefaultState() {
	    return Environment.makeGlobalEnv();
    }

    public SMPLValue<?> visitSMPLProgram(final SMPLProgram p, 
        final Environment<SMPLValue<?>> env) throws SMPLException {
        result = p.getSeq().visit(this, env);
        return result;
    }

    public SMPLValue<?> visitStatement(final Statement s, 
        final Environment<SMPLValue<?>> env) throws SMPLException {
	    return s.getExp().visit(this, env);
    }

    public SMPLValue<?> visitStmtSequence(final StmtSequence sseq, 
        final Environment<SMPLValue<?>> env) throws SMPLException {
        // remember that arg is the environment
        Statement s;
        final ArrayList<Statement> seq = sseq.getSeq();
        final Iterator<Statement> iter = seq.iterator();
        result = SMPLValue.make(0); // default result
        while(iter.hasNext()) {
            s = (Statement) iter.next();
            result = s.visit(this, env);
        }
        // return last value evaluated
        return result;
    }

    public SMPLValue<?> visitStmtDefinition(final StmtDefinition sd, 
        final Environment<SMPLValue<?>> env) throws SMPLException {
        result = sd.getExp().visit(this, env);
        env.put(sd.getVar(), result);
        return result;
    }

    public SMPLValue<?> visitExpAdd(final ExpAdd exp, 
        final Environment<SMPLValue<?>> env) throws SMPLException {
        SMPLValue<?> val1, val2;
        val1 = exp.getExpL().visit(this, env);
        val2 = exp.getExpR().visit(this, env);
        return val1.add(val2);
    }

    public SMPLValue<?> visitExpSub(final ExpSub exp, 
        final Environment<SMPLValue<?>> env) throws SMPLException {
        SMPLValue<?> val1, val2;
        val1 = exp.getExpL().visit(this, env);
        val2 = exp.getExpR().visit(this, env);
        return val1.sub(val2);
    }

    public SMPLValue<?> visitExpMul(final ExpMul exp, 
        final Environment<SMPLValue<?>> env) throws SMPLException {
	    SMPLValue<?> val1, val2;
        val1 = exp.getExpL().visit(this, env);
        val2 = exp.getExpR().visit(this, env);
        return val1.mul(val2);
    }

    public SMPLValue<?> visitExpDiv(final ExpDiv exp, 
        final Environment<SMPLValue<?>> env) throws SMPLException {
        SMPLValue<?> val1, val2;
        val1 = exp.getExpL().visit(this, env);
        val2 = exp.getExpR().visit(this, env);
        return val1.div(val2);
    }

    public SMPLValue<?> visitExpMod(final ExpMod exp, 
        final Environment<SMPLValue<?>> env) throws SMPLException {
        SMPLValue<?> val1, val2;
        val1 = exp.getExpL().visit(this, env);
        val2 = exp.getExpR().visit(this, env);
        return val1.mod(val2);
    }

    public SMPLValue<?> visitExpPow(final ExpPow exp,
        final Environment<SMPLValue<?>> env) throws SMPLException {
        SMPLValue<?> val1, val2;

        val1 = exp.getExpL().visit(this, env);
        val2 = exp.getExpR().visit(this, env);
        return val1.pow(val2);
    }

    public SMPLValue<?> visitExpUnary(final ExpUnary exp,
        final Environment<SMPLValue<?>> env) throws SMPLException {
        SMPLValue<?> val;
        String sign;

        val = exp.getExp().visit(this, env);
        sign = exp.getSign();
        return val.unary(sign);
    }

    public SMPLValue<?> visitExpLit(final ExpLit exp, 
        final Environment<SMPLValue<?>> env) throws SMPLException {
	    return exp.getVal();
    }

    public SMPLValue<?> visitExpId(final ExpId exp, 
        final Environment<SMPLValue<?>> env) throws SMPLException {
        return env.get(exp.getId());
    }
}
