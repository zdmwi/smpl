package smpl.syntax;

import smpl.syntax.ast.core.Exp;

public class CaseClause {

    Exp pred;
    Exp action;

    public CaseClause(Exp pred, Exp action) {
        this.pred = pred;
        this.action = action;
    }

    public Exp getPred() {
	    return pred;
    }

    public Exp getAction() {
	    return action;
    }

    public String toString() {
        return pred.toString() + " : " + action.toString();
    }
}
