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

public class ToScheme implements Visitor<Void, String> {

    String result;

    public ToScheme() {
	    result = "";
    }

    public String getResult() {
	    return result;
    }

    public Void getDefaultState() {
	    return null;
    }

    // program
    public String visitArithProgram(ArithProgram p, Void arg)
	throws VisitException {
        result = (String) p.getSeq().visit(this, arg);
        return result;
    }

    // statements
    public String visitStatement(Statement stmt, Void arg)
	throws VisitException {
	    return stmt.getExp().visit(this, arg);
    }

    public String visitStmtSequence(StmtSequence exp, Void arg)
	throws VisitException {
        ArrayList stmts = exp.getSeq();
        if (stmts.size() == 1)
            return ((Statement) stmts.get(0)).visit(this,
                                arg);
        else {
            Iterator iter = stmts.iterator();
            String result = "(begin ";
            Statement stmt;
            while (iter.hasNext()) {
                stmt = (Statement) iter.next();
                result += (String) stmt.visit(this, arg) +
                    "\n";
            }
            result += ")";
            return result;
        }
    }

    public String visitStmtDefinition(StmtDefinition sd, Void arg)
	throws VisitException {
        String valExp = (String) sd.getExp().visit(this,
                            arg);
        return "(define " + sd.getVar() + " " +
            valExp + ")";
    }

    // expressions
    public String visitExpAdd(ExpAdd exp, Void arg)
	throws VisitException {
        String left = exp.getExpL().visit(this, arg);
        String right = exp.getExpR().visit(this, arg);
        return "(+ " + left + " " + right + ")";
    }
    public String visitExpSub(ExpSub exp, Void arg)
	throws VisitException {
        String left = exp.getExpL().visit(this, arg);
        String right = exp.getExpR().visit(this, arg);
        return "(- " + left + " " + right + ")";
    }

    public String visitExpMul(ExpMul exp, Void arg)
	throws VisitException {
        String left = exp.getExpL().visit(this, arg);
        String right = exp.getExpR().visit(this, arg);
        return "(* " + left + " " + right + ")";
    }

    public String visitExpDiv(ExpDiv exp, Void arg)
	throws VisitException {
        String left = exp.getExpL().visit(this, arg);
        String right = exp.getExpR().visit(this, arg);
        return "(/ " + left + " " + right + ")";
    }

    public String visitExpMod(ExpMod exp, Void arg)
	throws VisitException {
        String left = exp.getExpL().visit(this, arg);
        String right = exp.getExpR().visit(this, arg);
        return "(mod " + left + " " + right + ")";
    }

    public String visitExpLit(ExpLit exp, Void arg)
	throws VisitException {
	    return "" + exp.getVal();
    }

    public String visitExpVar(ExpVar exp, Void arg)
	throws VisitException {
	    return exp.getVar();
    }
}
