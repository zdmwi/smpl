package smpl.semantics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import smpl.builtin.ExpCreateList;
import smpl.builtin.ExpCreatePair;
import smpl.builtin.ExpGetSize;
import smpl.builtin.ExpIsEqual;
import smpl.builtin.ExpIsEqv;
import smpl.builtin.ExpIsPair;
import smpl.builtin.ExpPairCAR;
import smpl.builtin.ExpPairCDR;
import smpl.builtin.ExpSubstring;
import smpl.exceptions.SMPLException;

import smpl.semantics.Environment;

import smpl.types.SMPLValue;
import smpl.types.compound.SMPLPair;
import smpl.types.compound.SMPLTuple;
import smpl.types.compound.SMPLVector;
import smpl.types.SMPLProcedure;
import smpl.types.SMPLType;
import smpl.syntax.ast.core.Exp;
import smpl.syntax.ast.core.SMPLProgram;
import smpl.syntax.ast.Statement;
import smpl.syntax.ast.StmtAssignment;
import smpl.syntax.ast.StmtSequence;
import smpl.syntax.ast.StmtDefinition;
import smpl.syntax.ast.StmtLet;
import smpl.syntax.ast.StmtPrint;
import smpl.syntax.ast.StmtPrintLn;
import smpl.syntax.ast.StmtRead;
import smpl.syntax.ast.StmtReadInt;
import smpl.syntax.ast.MultiExp;
import smpl.syntax.ast.ExpProcedure;
import smpl.syntax.ast.ExpSequence;
import smpl.syntax.ast.ExpLit;
import smpl.syntax.ast.ExpId;
import smpl.syntax.ast.ExpIndexVector;
import smpl.syntax.ast.ExpLT;
import smpl.syntax.ast.ExpLTEQ;
import smpl.syntax.ast.ExpList;
import smpl.syntax.Binding;
import smpl.syntax.ast.ExpAdd;
import smpl.syntax.ast.ExpAnd;
import smpl.syntax.ast.ExpBAND;
import smpl.syntax.ast.ExpBNOT;
import smpl.syntax.ast.ExpBOR;
import smpl.syntax.ast.ExpSub;
import smpl.syntax.ast.ExpMul;
import smpl.syntax.ast.ExpNEQ;
import smpl.syntax.ast.ExpNot;
import smpl.syntax.ast.ExpOr;
import smpl.syntax.ast.ExpPow;
import smpl.syntax.ast.ExpCall;
import smpl.syntax.ast.ExpDiv;
import smpl.syntax.ast.ExpEQ;
import smpl.syntax.ast.ExpGT;
import smpl.syntax.ast.ExpGTEQ;
import smpl.syntax.ast.ExpMod;
import smpl.syntax.ast.ExpUnary;
import smpl.syntax.ast.ExpVector;

public class Evaluator implements Visitor<Environment<SMPLValue<?>>, SMPLValue<?>> {
    /* For this visitor, the argument passed to all visit
       methods will be the environment object that used to
       be passed to the eval method in the first style of
       implementation. */

    // allocate state here
    protected SMPLValue<?> result;	// result of evaluation

    public Evaluator() {
        // perform initialisations here
        result = SMPLValue.make(0);
    }

    public Environment getDefaultState() {
	    return Environment.makeGlobalEnv();
    }

    public SMPLValue<?> visitSMPLProgram(SMPLProgram p, 
        Environment<SMPLValue<?>> env) throws SMPLException {
        result = p.getSeq().visit(this, env);
        return result;
    }

    public SMPLValue<?> visitStatement(Statement s, 
        Environment<SMPLValue<?>> env) throws SMPLException {
	    return s.getExp().visit(this, env);
    }

    public SMPLValue<?> visitStmtSequence(StmtSequence sseq, 
        Environment<SMPLValue<?>> env) throws SMPLException {
        // remember that arg is the environment
        Statement s;
        ArrayList<Statement> seq = sseq.getSeq();
        Iterator<Statement> iter = seq.iterator();
        result = SMPLValue.make(0); // default result
        while(iter.hasNext()) {
            s = (Statement) iter.next();
            result = s.visit(this, env);
        }
        // return last value evaluated
        return result;
    }

    public SMPLValue<?> visitStmtDefinition(StmtDefinition sd, 
        Environment<SMPLValue<?>> env) throws SMPLException {
        result = sd.getExp().visit(this, env);
        env.put(sd.getVar(), result);
        return result;
    }

    public SMPLValue<?> visitStmtAssignment(StmtAssignment sa, 
        Environment<SMPLValue<?>> env) throws SMPLException {
        ArrayList<String> ids = sa.getIds();
        ArrayList<Exp> exps = sa.getExps().getSeq();

        int n, m;
        n = ids.size();
        m = exps.size();

        if (n > m) {
            throw new SMPLException("Unpacking error: Not enough values to unpack");
        } else if (n < m) {
            throw new SMPLException("Unpacking error: Too many values to unpack");
        } else {
            for (int i = 0; i < n; i++) {
                result = exps.get(i).visit(this, env);
                env.put(ids.get(i), result);
            }
        }
        return result;
    }

    public SMPLValue<?> visitStmtLet(StmtLet let, Environment<SMPLValue<?>> env) throws SMPLException {
        ArrayList<Binding> bindings = let.getBindings();
        Exp body = let.getBody();

        int size = bindings.size();
        String[] vars = new String[size];
        SMPLValue<?>[] vals = new SMPLValue<?>[size];
        Binding b;
        for (int i = 0; i < size; i++) {
            b = bindings.get(i);
            vars[i] = b.getVar();
            // evaluate each expression in bindings
            result = b.getValExp().visit(this, env);
            vals[i] = result;
        }
        // create new env as child of current
        Environment<SMPLValue<?>> newEnv = new Environment<>(vars, vals, env);
        return body.visit(this, newEnv);
    }

    public SMPLValue<?> visitMultiExp(MultiExp exp,
        Environment<SMPLValue<?>> env) throws SMPLException {
            ArrayList<Exp> exps = exp.getExps().getSeq();

            ArrayList<SMPLValue<?>> values = new ArrayList<>();

            for(Exp e: exps) {
                values.add(e.visit(this, env));
            }

            return new SMPLTuple(values);
        }

    public SMPLValue<?> visitStmtPrint(StmtPrint exp, 
        Environment<SMPLValue<?>> env) throws SMPLException {
        System.out.print(exp.getExp().visit(this, env));
        return null;
    }

    public SMPLValue<?> visitStmtPrintLn(StmtPrintLn exp, 
        Environment<SMPLValue<?>> env) throws SMPLException {
        System.out.println(exp.getExp().visit(this, env));
        return null;
    }

    public SMPLValue<?> visitStmtRead(StmtRead exp, 
        Environment<SMPLValue<?>> env) throws SMPLException {
        Scanner scan = new Scanner(System.in);
        try {
            result = SMPLValue.make(scan.nextLine());
            return result;
        } catch (Exception e) {
            throw new SMPLException();
        }
    }

    public SMPLValue<?> visitStmtReadInt(StmtReadInt exp, 
        Environment<SMPLValue<?>> env) throws SMPLException {
        Scanner scan = new Scanner(System.in);
        try {
            result = SMPLValue.make(scan.nextInt());
            return result;
        } catch(Exception e) {
            throw new SMPLException("Input Mismatch Error: Value is not an INT");
        }
    }

    public SMPLValue<?> visitExpSequence(ExpSequence exp,
        Environment<SMPLValue<?>> env) throws SMPLException {
        // remember that arg is the environment
        Exp s;
        ArrayList<Exp> seq = exp.getSeq();
        Iterator<Exp> iter = seq.iterator();
        result = SMPLValue.make(0); // default result
        while(iter.hasNext()) {
            s = iter.next();
            result = s.visit(this, env);
        }
        // return last value evaluated
        return result;
    }

    public SMPLValue<?> visitExpAdd(ExpAdd exp, 
        Environment<SMPLValue<?>> env) throws SMPLException {
        SMPLValue<?> val1, val2;
        val1 = exp.getExpL().visit(this, env);
        val2 = exp.getExpR().visit(this, env);
        return val1.add(val2);
    }

    public SMPLValue<?> visitExpSub(ExpSub exp, 
        Environment<SMPLValue<?>> env) throws SMPLException {
        SMPLValue<?> val1, val2;
        val1 = exp.getExpL().visit(this, env);
        val2 = exp.getExpR().visit(this, env);
        return val1.sub(val2);
    }

    public SMPLValue<?> visitExpMul(ExpMul exp, 
        Environment<SMPLValue<?>> env) throws SMPLException {
	    SMPLValue<?> val1, val2;
        val1 = exp.getExpL().visit(this, env);
        val2 = exp.getExpR().visit(this, env);
        return val1.mul(val2);
    }

    public SMPLValue<?> visitExpDiv(ExpDiv exp, 
        Environment<SMPLValue<?>> env) throws SMPLException {
        SMPLValue<?> val1, val2;
        val1 = exp.getExpL().visit(this, env);
        val2 = exp.getExpR().visit(this, env);
        return val1.div(val2);
    }

    public SMPLValue<?> visitExpMod(ExpMod exp, 
        Environment<SMPLValue<?>> env) throws SMPLException {
        SMPLValue<?> val1, val2;
        val1 = exp.getExpL().visit(this, env);
        val2 = exp.getExpR().visit(this, env);
        return val1.mod(val2);
    }

    public SMPLValue<?> visitExpPow(ExpPow exp,
        Environment<SMPLValue<?>> env) throws SMPLException {
        SMPLValue<?> val1, val2;

        val1 = exp.getExpL().visit(this, env);
        val2 = exp.getExpR().visit(this, env);
        return val1.pow(val2);
    }

    public SMPLValue<?> visitExpOr(ExpOr exp,
        Environment<SMPLValue<?>> env) throws SMPLException {
        SMPLValue<?> val1, val2;

        val1 = exp.getExpL().visit(this, env);
        val2 = exp.getExpR().visit(this, env);
        return val1.or(val2);
    }

    public SMPLValue<?> visitExpAnd(ExpAnd exp,
        Environment<SMPLValue<?>> env) throws SMPLException {
        SMPLValue<?> val1, val2;

        val1 = exp.getExpL().visit(this, env);
        val2 = exp.getExpR().visit(this, env);
        return val1.and(val2);
    }

    public SMPLValue<?> visitExpNot(ExpNot exp,
        Environment<SMPLValue<?>> env) throws SMPLException {
        SMPLValue<?> val1;

        val1 = exp.getExp().visit(this, env);
        return val1.not();
    }

    public SMPLValue<?> visitExpBAND(ExpBAND exp,
        Environment<SMPLValue<?>> env) throws SMPLException {
        SMPLValue<?> val1, val2;

        val1 = exp.getExpL().visit(this, env);
        val2 = exp.getExpR().visit(this, env);
        return val1.band(val2);
    }

    public SMPLValue<?> visitExpBOR(ExpBOR exp,
        Environment<SMPLValue<?>> env) throws SMPLException {
        SMPLValue<?> val1, val2;

        val1 = exp.getExpL().visit(this, env);
        val2 = exp.getExpR().visit(this, env);
        return val1.bor(val2);
    }

    public SMPLValue<?> visitExpBNOT(ExpBNOT exp,
        Environment<SMPLValue<?>> env) throws SMPLException {
        SMPLValue<?> val1;

        val1 = exp.getExp().visit(this, env);
        return val1.bnot();
    }

    public SMPLValue<?> visitExpEQ(ExpEQ exp,
        Environment<SMPLValue<?>> env) throws SMPLException {
        SMPLValue<?> val1, val2;

        val1 = exp.getExpL().visit(this, env);
        val2 = exp.getExpR().visit(this, env);
        return val1.eq(val2);
    }

    public SMPLValue<?> visitExpGT(ExpGT exp,
        Environment<SMPLValue<?>> env) throws SMPLException {
        SMPLValue<?> val1, val2;

        val1 = exp.getExpL().visit(this, env);
        val2 = exp.getExpR().visit(this, env);
        return val1.gt(val2);
    }

    public SMPLValue<?> visitExpGTEQ(ExpGTEQ exp,
        Environment<SMPLValue<?>> env) throws SMPLException {
        SMPLValue<?> val1, val2;

        val1 = exp.getExpL().visit(this, env);
        val2 = exp.getExpR().visit(this, env);
        return val1.gteq(val2);
    }

    public SMPLValue<?> visitExpLT(ExpLT exp,
        Environment<SMPLValue<?>> env) throws SMPLException {
        SMPLValue<?> val1, val2;

        val1 = exp.getExpL().visit(this, env);
        val2 = exp.getExpR().visit(this, env);
        return val1.lt(val2);
    }

    public SMPLValue<?> visitExpLTEQ(ExpLTEQ exp,
        Environment<SMPLValue<?>> env) throws SMPLException {
        SMPLValue<?> val1, val2;

        val1 = exp.getExpL().visit(this, env);
        val2 = exp.getExpR().visit(this, env);
        return val1.lteq(val2);
    }

    public SMPLValue<?> visitExpNEQ(ExpNEQ exp,
        Environment<SMPLValue<?>> env) throws SMPLException {
        SMPLValue<?> val1, val2;

        val1 = exp.getExpL().visit(this, env);
        val2 = exp.getExpR().visit(this, env);
        return val1.neq(val2);
    }

    public SMPLValue<?> visitExpUnary(ExpUnary exp,
        Environment<SMPLValue<?>> env) throws SMPLException {
        SMPLValue<?> val;
        String sign;

        val = exp.getExp().visit(this, env);
        sign = exp.getSign();
        return val.unary(sign);
    }

    public SMPLValue<?> visitExpLit(ExpLit exp, 
        Environment<SMPLValue<?>> env) throws SMPLException {
	    return exp.getVal();
    }

    public SMPLValue<?> visitExpId(ExpId exp, 
        Environment<SMPLValue<?>> env) throws SMPLException {
        return env.get(exp.getId());
    }

    public SMPLValue<?> visitExpCreatePair(ExpCreatePair exp,
        Environment<SMPLValue<?>> env) throws SMPLException {
            SMPLValue<?> v1 = env.get(exp.getHead());
            SMPLValue<?> v2 = env.get(exp.getTail());

            return new SMPLPair(v1, v2);
    }

    public SMPLValue<?> visitExpPairCAR(ExpPairCAR exp, 
        Environment<SMPLValue<?>> env) throws SMPLException {
            SMPLValue<?> p = env.get(exp.getParam());

            return ((SMPLPair) p).getObj1();
    }

    public SMPLValue<?> visitExpPairCDR(ExpPairCDR exp, 
        Environment<SMPLValue<?>> env) throws SMPLException {
            SMPLValue<?> p = env.get(exp.getParam());

            return ((SMPLPair) p).getObj2();
    }

    public SMPLValue<?> visitExpIsPair(ExpIsPair exp,
        Environment<SMPLValue<?>> env) throws SMPLException {
            SMPLValue<?> p = env.get(exp.getParam());

            return SMPLValue.make(p.getType() == SMPLType.PAIR);
    }

    public SMPLValue<?> visitExpGetSize(ExpGetSize exp,
        Environment<SMPLValue<?>> env) throws SMPLException {
            SMPLValue<?> p = env.get(exp.getParam());

            ArrayList<SMPLValue<?>> values = ((SMPLVector) p).getValues();

            return SMPLValue.make(values.size());
    }

    public SMPLValue<?> visitExpIsEqv(ExpIsEqv exp,
        Environment<SMPLValue<?>> env) throws SMPLException {
            SMPLValue<?> e1 = env.get(exp.getParam1());
            SMPLValue<?> e2 = env.get(exp.getParam2());

            return SMPLValue.make(e1.equals(e2));
    }

    public SMPLValue<?> visitExpIsEqual(ExpIsEqual exp,
        Environment<SMPLValue<?>> env) throws SMPLException {
            SMPLValue<?> e1 = env.get(exp.getParam1());
            SMPLValue<?> e2 = env.get(exp.getParam2());

            return e1.eq(e2);
    }

    public SMPLValue<?> visitExpSubstring(ExpSubstring exp,
        Environment<SMPLValue<?>> env) throws SMPLException {
            SMPLValue<?> e1 = env.get(exp.getParam1());
            SMPLValue<?> e2 = env.get(exp.getParam2());
            SMPLValue<?> e3 = env.get(exp.getParam3());

            String st = e1.stringValue();
            int start = e2.intValue();
            int end = e3.intValue();

            return SMPLValue.make(st.substring(start, end));
    }

    public SMPLValue<?> visitExpCreateList(ExpCreateList exp,
        Environment<SMPLValue<?>> env) throws SMPLException {
            SMPLValue<?> p = env.get(exp.getParam());
            SMPLPair rest = ((SMPLPair) env.get("prest"));

            SMPLPair head = new SMPLPair();
            head.setObj1(p);
            head.setObj2(rest);
            return head;
    }

    public SMPLValue<?> visitExpList(ExpList exp,
        Environment<SMPLValue<?>> env) throws SMPLException {
            ArrayList<Exp> content = exp.getContent().getSeq();

            SMPLPair head = new SMPLPair();
            SMPLPair current = head;
            SMPLPair next = new SMPLPair();
            for (int i = 0; i < content.size(); i++) {
                current.setObj1(content.get(i).visit(this, env));
                current.setObj2(next);
                current = next;
                next = new SMPLPair();
            }

            return head;
    }

    public SMPLValue<?> visitExpVector(ExpVector exp,
        Environment<SMPLValue<?>> env) throws SMPLException {
            ArrayList<Exp> content = exp.getContent().getSeq();

            ArrayList<SMPLValue<?>> values = new ArrayList<>();
            for (Exp e: content) {
                values.add(e.visit(this, env));
            }

            return new SMPLVector(values);
    }

    public SMPLValue<?> visitExpIndexVector(ExpIndexVector exp,
        Environment<SMPLValue<?>> env) throws SMPLException {
            SMPLValue<?> vector = exp.getVecExp().visit(this, env);

            SMPLValue<?> index = exp.getIndexExp().visit(this, env);

            ArrayList<SMPLValue<?>> values = ((SMPLVector) vector).getValues();

            return values.get(index.intValue());
    }

    @Override
    public SMPLValue<?> visitProcDefinition(ExpProcedure exp, Environment<SMPLValue<?>> env) throws SMPLException {
        return new SMPLProcedure(exp, env);
    }

    public SMPLValue<?> visitProcCall(ExpCall call, Environment<SMPLValue<?>> env) throws SMPLException {
        Exp exp = call.getCaller();
        SMPLValue<?> proc = exp.visit(this, env);

        // map arguments to function paramters
        ArrayList<Exp> arguments = call.getArguments().getSeq();

        Environment<SMPLValue<?>> closingEnv = ((SMPLProcedure) proc).getClosingEnv();

        String namedParam = ((SMPLProcedure) proc).getProcExp().getNamedParameter();

        Environment<SMPLValue<?>> xctx;
        
        if (namedParam != null) {
            // evaluate the expressions and create a linked list of pairs
            // that can be referenced by namedParam
            SMPLPair head = new SMPLPair();
            SMPLPair current = head;
            SMPLPair next = new SMPLPair();
            for (int i = 0; i < arguments.size(); i++) {
                current.setObj1(arguments.get(i).visit(this, env));
                current.setObj2(next);
                current = next;
                next = new SMPLPair();
            }

            xctx = new Environment<>(new String[] {namedParam}, new SMPLValue[] {head});

        } else {
            ArrayList<String> parameters = ((SMPLProcedure) proc).getProcExp().getParameters();

            ArrayList<SMPLValue<?>> values = new ArrayList<>();

            int numParams = parameters.size();
            int numArgs = arguments.size();

            if (numParams < numArgs) {
                for (int i = 0; i < numParams; i++) {
                    values.add(arguments.get(i).visit(this, env));
                }
                // place the remaining arguments in a list
                SMPLPair head = new SMPLPair();
                SMPLPair current = head;
                SMPLPair next = new SMPLPair();
                for (int i = numParams; i < numArgs; i++) {
                    current.setObj1(arguments.get(i).visit(this, env));
                    current.setObj2(next);
                    current = next;
                    next = new SMPLPair();
                }

                parameters.add("prest");
                values.add(head);

                xctx = new Environment<>(parameters, values, closingEnv);

            } else {
                // bind parameters to each argument
                for (int i = 0; i < arguments.size(); i++) {
                    values.add(arguments.get(i).visit(this, env));
                }
    
                xctx = new Environment<>(parameters, values, closingEnv);
            }
        }
        
        // evaluate the function with respect to new bindings
        return ((SMPLProcedure) proc).getProcExp().getBody().visit(this, xctx);

    }
}
