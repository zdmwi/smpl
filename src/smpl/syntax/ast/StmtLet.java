package smpl.syntax.ast;

import java.util.ArrayList;

import smpl.exceptions.SMPLException;
import smpl.semantics.Visitor;
import smpl.syntax.Binding;
import smpl.syntax.ast.core.Exp;

public class StmtLet extends Statement {
    ArrayList<Binding> bindings;
    Exp body;

    public StmtLet(ArrayList<Binding> bs, Exp bod) {
        bindings = bs;
        body = bod;
    }

    public ArrayList<Binding> getBindings() {
	    return bindings;
    }

    public Exp getBody() {
	    return body;
    }
    
    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SMPLException {
        return v.visitStmtLet(this, state);
    }

    @Override
    public String toString() {
	    return "let " + bindings + " in " + body;
    }

}
