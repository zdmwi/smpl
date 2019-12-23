package smpl.syntax;

import smpl.syntax.ast.core.Exp;

public class Predicate {

    Exp pred;
    Exp result;

    public Predicate(Exp pred, Exp result) {
        this.pred = pred;
        this.result = result;
    }

    public Exp getPred() {
	    return pred;
    }

    public Exp getResult() {
	    return result;
    }

}
