package smpl.syntax.ast;

import java.util.ArrayList;

import smpl.exceptions.SMPLException;
import smpl.semantics.Visitor;
import smpl.syntax.CaseClause;
import smpl.syntax.ast.core.Exp;

public class StmtCase extends Statement {
    ArrayList<CaseClause> clauses;

    public StmtCase(ArrayList<CaseClause> clauses) {
        super();
        this.clauses = clauses;
    }

    public ArrayList<CaseClause> getClauses() {
	    return clauses;
    }
    
    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SMPLException {
        return v.visitStmtCase(this, state);
    }

    @Override
    public String toString() {
        String st = "case { ";
        for (int i = 0; i < clauses.size() - 1; i++) {
            st += clauses.get(i).toString() + ", ";
        }
        st += clauses.get(clauses.size() - 1).toString() + " }";
        
        return st;
    }

}
