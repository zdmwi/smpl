package smpl.semantics;

import smpl.exceptions.SMPLException;

import smpl.syntax.ast.core.ASTNode;
/**
  * A PersistentWalker instance is a wrapper for a visitor.  The
  * constructor will establish an instance of the default state which
  * will be used with visits of the wrapped visitor.
  */
public class PersistentWalker<S, T> {

    private Visitor<S, T> visitor;
    private S state;
    
    public PersistentWalker(Visitor<S, T> visitor) {
        this.visitor = visitor;
        this.state = visitor.getDefaultState();
    }

    public Visitor<S, T> getVisitor() {
	    return visitor;
    }

    /** Return the state used by this AST walker. */
    public S getState() {
	    return state;
    }

    /** Traverse the given expression (AST) using the visitor with its 
	associated state. 
    */
    public T walk(ASTNode expr) throws SMPLException {
	    return expr.visit(visitor, state);
    }
}
