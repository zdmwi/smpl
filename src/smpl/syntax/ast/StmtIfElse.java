package smpl.syntax.ast;

import smpl.exceptions.SMPLException;
import smpl.semantics.Visitor;
import smpl.syntax.ast.core.Exp;

public class StmtIfElse extends Statement {
    Exp predicate;
    Statement then, alt;

    public StmtIfElse(Exp predicate, Statement then, Statement alt) {
        super();
        this.predicate = predicate;
        this.then = then;
        this.alt = alt;
    }

    public StmtIfElse(Exp predicate, Statement then) {
        super();
        this.predicate = predicate;
        this.then = then;
    }

    public Exp getPredicate() {
	    return predicate;
    }

    public Statement getThenClause() {
	    return then;
    }

    public Statement getElseClause() {
        return alt;
    }
    
    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SMPLException {
        return v.visitStmtIfElse(this, state);
    }

    @Override
    public String toString() {
        String st = "if (" + predicate.toString() + ") " + then.toString();
        if (alt != null) {
            st += "else " + alt.toString();
        }
        return st;
    }

}
