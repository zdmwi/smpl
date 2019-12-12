package smpl.semantics;

import smpl.exceptions.SMPLException;

import smpl.syntax.ast.core.SMPLProgram;
import smpl.syntax.ast.Statement;
import smpl.syntax.ast.StmtSequence;
import smpl.syntax.ast.StmtDefinition;
import smpl.syntax.ast.ExpLit;
import smpl.syntax.ast.ExpId;
import smpl.syntax.ast.ExpAdd;
import smpl.syntax.ast.ExpSub;
import smpl.syntax.ast.ExpMul;
import smpl.syntax.ast.ExpDiv;
import smpl.syntax.ast.ExpMod;
import smpl.syntax.ast.ExpPow;
import smpl.syntax.ast.ExpUnary;

/**
 * The generic Visitor interface for the Arithmetic parser
 * example.
 * @param <S> The type of the information needed by the visitor
 * @param <T> The type of result returned by the visitor 
 */
public interface Visitor<S, T> {

    public S getDefaultState();

    // program
    public T visitSMPLProgram(SMPLProgram p, S arg) throws SMPLException;

    // statements
    public T visitStatement(Statement exp, S arg) throws SMPLException ;
    public T visitStmtSequence(StmtSequence exp, S arg) throws SMPLException ;
    public T visitStmtDefinition(StmtDefinition sd, S arg) throws SMPLException;

    // expressions
    public T visitExpAdd(ExpAdd exp, S arg) throws SMPLException ;
    public T visitExpSub(ExpSub exp, S arg) throws SMPLException;
    public T visitExpMul(ExpMul exp, S arg) throws SMPLException;
    public T visitExpDiv(ExpDiv exp, S arg) throws SMPLException;
    public T visitExpMod(ExpMod exp, S arg) throws SMPLException;
    public T visitExpPow(ExpPow exp, S arg) throws SMPLException;
    public T visitExpUnary(ExpUnary exp, S arg) throws SMPLException;
    public T visitExpLit(ExpLit exp, S arg) throws SMPLException;
    public T visitExpId(ExpId exp, S arg) throws SMPLException;
}
