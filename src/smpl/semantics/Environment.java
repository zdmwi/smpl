package smpl.semantics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import smpl.builtin.ExpCreateList;
import smpl.builtin.ExpCreatePair;
import smpl.builtin.ExpGetSize;
import smpl.builtin.ExpIsEqual;
import smpl.builtin.ExpIsEqv;
import smpl.builtin.ExpIsPair;
import smpl.builtin.ExpPairCAR;
import smpl.builtin.ExpPairCDR;
import smpl.builtin.ExpSubstring;
import smpl.exceptions.UnboundVarException;
import smpl.syntax.ast.ExpProcedure;
import smpl.syntax.ast.core.Exp;
import smpl.types.SMPLValue;
import smpl.types.SMPLInt;
import smpl.types.SMPLProcedure;

/**
 * An instance of class <code>Environment</code> maintains a collection of
 * bindings from valid identifiers to integers. It supports storing and
 * retrieving bindings, just as would be expected in any dictionary.
 *
 * @author <a href="mailto:dcoore@uwimona.edu.jm">Daniel Coore</a>
 * @version 1.0
 */
public class Environment<T extends SMPLValue<?>> {

    HashMap<String, T> dictionary;
    Environment<T> parent = null;

    public Environment() {
        dictionary = new HashMap<>();
    }

    /**
     * Creates a new <code>Environment</code> instance that is initialised with the
     * given collection of bindings (presented as separate arrays of names and
     * values).
     *
     * @param ids    the collection of identifiers to be bound.
     * @param values the corresponding collection of values for the identifiers.
     *               Note that the two arrays must have the same length.
     */
    public Environment(String[] ids, T[] values) {
        dictionary = new HashMap<>();
        for (int i = 0; i < ids.length; i++) {
            put(ids[i], values[i]);
        }
    }

    /**
     * Creates a new environment that extends a given one with some new bindings
     * 
     * @param ids    the identifiers of the new bindings
     * @param values the values of the new bindings
     * @param p      The environment being extended, which will be the parent of the
     *               new environment that is created.
     */
    public Environment(String[] ids, T[] values, Environment<T> p) {
        parent = p;
        dictionary = new HashMap<>();
        for (int i = 0; i < ids.length; i++) {
            put(ids[i], values[i]);
        }
    }

    /**
     * Create a new environment that extends a given one with some new bindings
     * 
     * @param ids    The identifiers of the new bindings
     * @param values The values of the new bindings
     * @param env    The environment being extended, which will be the parent of the
     *               new environment that is created.
     */
    public Environment(ArrayList<String> ids, ArrayList<T> values, Environment<T> env) {
        parent = env;
        dictionary = new HashMap<>();
        for (int i = 0; i < ids.size(); i++) {
            put(ids.get(i), values.get(i));
        }
    }

    /**
     * Create an instance of a global environment suitable for evaluating an
     * program.
     *
     * @return the <code>Environment</code> created.
     */
    public static <T extends SMPLValue<T>> Environment<T> makeGlobalEnv() {
        Environment<T> result = new Environment<>();

        // add builtin functions
        ArrayList<String> params = new ArrayList<>();
        params.add(0, "e1");
        params.add(0, "e2");
        Exp body = new ExpCreatePair("e1", "e2");
        ExpProcedure procedure = new ExpProcedure(params, body);
        SMPLProcedure makePair = new SMPLProcedure(procedure, (Environment<SMPLValue<?>>) result);
        result.put("pair", (T) makePair);

        params = new ArrayList<>();
        params.add(0, "p");
        body = new ExpPairCAR("p");
        procedure = new ExpProcedure(params, body);
        SMPLProcedure carPair = new SMPLProcedure(procedure, (Environment<SMPLValue<?>>) result);
        result.put("car", (T) carPair);

        params = new ArrayList<>();
        params.add(0, "p");
        body = new ExpPairCDR("p");
        procedure = new ExpProcedure(params, body);
        SMPLProcedure cdrPair = new SMPLProcedure(procedure, (Environment<SMPLValue<?>>) result);
        result.put("cdr", (T) cdrPair);

        params = new ArrayList<>();
        params.add(0, "p");
        body = new ExpIsPair("p");
        procedure = new ExpProcedure(params, body);
        SMPLProcedure isPair = new SMPLProcedure(procedure, (Environment<SMPLValue<?>>) result);
        result.put("pair?", (T) isPair);

        params = new ArrayList<>();
        params.add(0, "p");
        body = new ExpCreateList("p");
        procedure = new ExpProcedure(params, body);
        SMPLProcedure makeList = new SMPLProcedure(procedure, (Environment<SMPLValue<?>>) result);
        result.put("list", (T) makeList);

        params = new ArrayList<>();
        params.add(0, "vec");
        body = new ExpGetSize("vec");
        procedure = new ExpProcedure(params, body);
        SMPLProcedure getSize = new SMPLProcedure(procedure, (Environment<SMPLValue<?>>) result);
        result.put("size", (T) getSize);

        params = new ArrayList<>();
        params.add(0, "e1");
        params.add(0, "e2");
        body = new ExpIsEqv("e1", "e2");
        procedure = new ExpProcedure(params, body);
        SMPLProcedure isEquivalent = new SMPLProcedure(procedure, (Environment<SMPLValue<?>>) result);
        result.put("eqv?", (T) isEquivalent);
        
        params = new ArrayList<>();
        params.add(0, "e1");
        params.add(0, "e2");
        body = new ExpIsEqual("e1", "e2");
        procedure = new ExpProcedure(params, body);
        SMPLProcedure isEqual = new SMPLProcedure(procedure, (Environment<SMPLValue<?>>) result);
        result.put("equal?", (T) isEqual);

        params = new ArrayList<>();
        params.add("e1");
        params.add("e2");
        params.add("e3");
        body = new ExpSubstring("e1", "e2", "e3");
        procedure = new ExpProcedure(params, body);
        SMPLProcedure getSubstring = new SMPLProcedure(procedure, (Environment<SMPLValue<?>>) result);
        result.put("substr", (T) getSubstring);

        return result;
    }

    /**
     * Store a binding for the given identifier to the given
     * int within this environment.
     *
     * @param id the name to be bound
     * @param value the value to which the name is bound.
     */
    public void put(String id, T value) {
	    dictionary.put(id, value);
    }

    /**
     * Return the int associated with the given identifier.
     *
     * @param id the identifier.
     * @return the int associated with the identifier in
     * this environment.
     * @exception Exception if <code>id</code> is unbound
     */
    public T get(String id) throws UnboundVarException {
        T result = dictionary.get(id);
        if (result == null) {
            if (parent == null)
                throw new UnboundVarException(id);
            else
                return parent.get(id);
        }
        else
            return result;
    }

    /**
     * Create a string representation of this environment.
     *
     * @return a string of all the names bound in this
     *         environment.
     */
    public String toString() {
        StringBuffer result = new StringBuffer();

        Iterator<String> iter = dictionary.keySet().iterator();
        while (iter.hasNext()) {
            result = result.append(iter.next());
        }
        return result.toString();
    }

}
