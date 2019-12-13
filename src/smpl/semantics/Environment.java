package smpl.semantics;

import java.util.HashMap;
import java.util.Iterator;

import smpl.exceptions.UnboundVarException;

import smpl.types.SMPLValue;
import smpl.types.SMPLInt;

/**
 * An instance of class <code>Environment</code> maintains a
 * collection of bindings from valid identifiers to integers.
 * It supports storing and retrieving bindings, just as would
 * be expected in any dictionary.
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
     * Creates a new <code>Environment</code> instance that is
     * initialised with the given collection of bindings
     * (presented as separate arrays of names and values).
     *
     * @param ids the collection of identifiers to be bound.
     * @param values the corresponding collection of values
     * for the identifiers.  Note that the two arrays must
     * have the same length.
     */
    public Environment(String[] ids, T[] values) {
        dictionary = new HashMap<>();
        for (int i = 0; i < ids.length; i++) {
            put(ids[i], values[i]);
        }
    }

    /**
     * Creates a new environment that extends a given one with some new bindings
     * @param ids the identifiers of the new bindings
     * @param values the values of the new bindings
     * @param p The environment being extended, which will be the parent of the
     * new environment that is created.
     */
    public Environment(String[] ids, T[] values, Environment<T> p) {
        parent = p;
        dictionary = new HashMap<>();
        for (int i = 0; i < ids.length; i++) {
            put(ids[i], values[i]);
        }
    }

    /**
     * Create an instance of a global environment suitable for
     * evaluating an program.
     *
     * @return the <code>Environment</code> created.
     */
    public static <T extends SMPLValue<T>> Environment<T> makeGlobalEnv() {
        Environment<T> result =  new Environment<>();

        // add builtin functions
        result.put("pair", null);
        result.put("car", null);
        result.put("cdr", null);
        result.put("pair?", null);

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
