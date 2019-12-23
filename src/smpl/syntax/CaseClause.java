package smpl.syntax;

import smpl.syntax.ast.Statement;
import smpl.syntax.ast.core.Exp;

public class CaseClause {

    Exp pred;
    Statement action;

    public CaseClause(Exp pred, Statement action) {
        this.pred = pred;
        this.action = action;
    }

    public Exp getPred() {
	    return pred;
    }

    public Statement getAction() {
	    return action;
    }

    public String toString() {
        return pred.toString() + " : " + action.toString();
    }
}
