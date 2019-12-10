package smpl.semantics;

import java.util.ArrayList;
import java.util.Iterator;

import smpl.exceptions.VisitException;

import smpl.syntax.ast.core.ArithProgram;
import smpl.syntax.ast.Statement;
import smpl.syntax.ast.StmtSequence;
import smpl.syntax.ast.StmtDefinition;
import smpl.syntax.ast.ExpLit;
import smpl.syntax.ast.ExpVar;
import smpl.syntax.ast.ExpAdd;
import smpl.syntax.ast.ExpSub;
import smpl.syntax.ast.ExpMul;
import smpl.syntax.ast.ExpDiv;
import smpl.syntax.ast.ExpMod;

public class Evaluator implements Visitor<Environment, Integer> {
    /* For this visitor, the argument passed to all visit
       methods will be the environment object that used to
       be passed to the eval method in the first style of
       implementation. */

    // allocate state here
    protected Integer result;	// result of evaluation

    public Evaluator() {
        // perform initialisations here
        result = new Integer(0);
    }

    public Environment getDefaultState() {
	    return Environment.makeGlobalEnv();
    }

    public Integer visitArithProgram(ArithProgram p, Environment arg)
	throws VisitException {
        result = p.getSeq().visit(this, arg);
        return result;
    }

    public Integer visitStatement(Statement s, Environment arg)
    throws VisitException {
	    return s.getExp().visit(this, arg);
    }

    public Integer visitStmtSequence(StmtSequence sseq, Environment arg)
	throws VisitException {
        // remember that arg is the environment
        Statement s;
        ArrayList seq = sseq.getSeq();
        Iterator iter = seq.iterator();
        Integer result = new Integer(0); // default result
        while(iter.hasNext()) {
            s = (Statement) iter.next();
            result = s.visit(this, arg);
        }
        // return last value evaluated
        return result;
    }

    public Integer visitStmtDefinition(StmtDefinition sd, Environment env)
	throws VisitException {
        // Environment env = (Environment) arg;
        Integer result;
        result = (Integer) sd.getExp().visit(this, env);
        env.put(sd.getVar(), result.intValue());
        return result;
    }

    public Integer visitExpAdd(ExpAdd exp, Environment arg)
	throws VisitException {
        Integer val1, val2;
        val1 = (Integer) exp.getExpL().visit(this, arg);
        val2 = (Integer) exp.getExpR().visit(this, arg);
        return new Integer(val1.intValue() +
                val2.intValue());
    }

    public Integer visitExpSub(ExpSub exp, Environment arg)
	throws VisitException {
        Integer val1, val2;
        val1 = exp.getExpL().visit(this, arg);
        val2 = exp.getExpR().visit(this, arg);
        return new Integer(val1.intValue() -
                val2.intValue());
    }

    public Integer visitExpMul(ExpMul exp, Environment arg)
	throws VisitException {
	    Integer val1, val2;
        val1 = (Integer) exp.getExpL().visit(this, arg);
        val2 = (Integer) exp.getExpR().visit(this, arg);
        return new Integer(val1.intValue() *
                val2.intValue());
    }

    public Integer visitExpDiv(ExpDiv exp, Environment arg)
	throws VisitException {
        Integer val1, val2;
        val1 = (Integer) exp.getExpL().visit(this, arg);
        val2 = (Integer) exp.getExpR().visit(this, arg);
        return new Integer(val1.intValue() /
                val2.intValue());
    }

    public Integer visitExpMod(ExpMod exp, Environment arg)
	throws VisitException {
        Integer val1, val2;
        val1 = (Integer) exp.getExpL().visit(this, arg);
        val2 = (Integer) exp.getExpR().visit(this, arg);
        return new Integer(val1.intValue() %
                val2.intValue());
    }

    public Integer visitExpLit(ExpLit exp, Environment arg)
	throws VisitException {
	    return new Integer(exp.getVal());
    }

    public Integer visitExpVar(ExpVar exp, Environment env)
	throws VisitException {
        // remember that arg is really the environment
        //	Environment env = (Environment) arg;
        int val = env.get(exp.getVar());
        return new Integer(val);
    }
}
