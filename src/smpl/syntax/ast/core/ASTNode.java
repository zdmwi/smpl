package smpl.syntax.ast.core;

import smpl.semantics.Visitor;

import smpl.exceptions.VisitException;

public abstract class ASTNode {

    /** Method to support Visitor design pattern. A call to visit on
     * the ASTNode results in a specific method call in the provided
     * visitor.  This way, each subclass of ASTNode will make a direct
     * call to its associated method in the visitor.
     */
    public abstract <S, T> T visit(Visitor<S, T> v, S arg)
	throws VisitException ;

    /** Return a string representation of this node and its subtree.
     * The returned string should bear some resemblance to the user's
     * input that would have given rise to this subtree being
     * constructed in the first place, since this method is likely to
     * be called during debugging, and the returned value is likely to
     * be shown to the end user.
     */
    public abstract String toString();
}
