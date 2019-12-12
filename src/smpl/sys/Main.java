package smpl.sys;

import java_cup.runtime.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

import smpl.exceptions.TokenException;
import smpl.exceptions.SMPLException;

import smpl.syntax.lexer.SMPLLexer;
import smpl.syntax.parser.SMPLParser;
import smpl.syntax.ast.core.Exp;
import smpl.syntax.ast.core.SMPLProgram;

import smpl.semantics.Environment;
import smpl.semantics.Visitor;
import smpl.semantics.Evaluator;
import smpl.semantics.PersistentWalker;

import smpl.types.SMPLValue;

public class Main {

    public static final String PROMPT = ">>>";

    private static final String MESSAGE = "SMPL Version 1.0.0 :: " +
                                          "COMP3652 Project 1";

    public static void usage() {
        String[] usageMsg = new String[]{
            String.format("Usage: <javaexec> %s [-w <walker-class-name>] " +
                "[file ...]", Main.class.getName()),
            "walker-class-name must be the name of a class that subclasses ",
            "the class PersistentWalker.  Defaults to Evaluator.",
            "The sequence of filenames provided afterwards is optional.  Each",
            "will be read and traversed in the order given.  If a '-' is",
            "specified, input will be read from stdin.  If no files are given,",
            "input willl be read from stdin."
        };
        for (String line : usageMsg) {
            System.out.println(line);
        }
    }

    public static <S, T> void main(String args[]) {
        int n = args.length;
        String walkerName = "";
        ArrayList<String> filenames = new ArrayList<>();

        if (n == 0) {
            usage();
            System.exit(0);
        }
        
        for (int i = 0; i  < n; i++) {
            String arg = args[i];
            if (arg.equals("-w")) {
                walkerName = args[i+1];
                i += 1;
            } else {
                filenames.add(arg);
            }
        }

        try {
            PersistentWalker<?, ?> walker;	// to be set by switch statement
            if (walkerName.equals("")) {
                walker = new PersistentWalker<Environment<SMPLValue<?>>, SMPLValue<?>>
                    (new Evaluator());
            } else {
                Class<? extends Visitor<S, T>> wclass =
                    (Class<? extends Visitor<S, T>>) Class.forName(walkerName);
                walker = new PersistentWalker<S, T>(wclass.newInstance());
            }

            for (String fname : filenames) {
                Reader fr = null;
                try {
                    if (fname.equals("-")) {
                        fr = new InputStreamReader(System.in);
                        System.out.println(MESSAGE);
                    } else {
                        fr = new FileReader(fname);
                        System.out.println("Processing " + fname + "...");
                    }
                    readLines(fr, walker);
                } catch (FileNotFoundException fnfe) {
                    System.err.println(fnfe.getMessage());
                } finally {
                    try { 
                        if (fr != null)
                            fr.close();
                    } catch (IOException ioe) {
                        System.err.println(ioe.getMessage());
                    }
                }
            }
        } catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe.getMessage());
            System.exit(1);
        } catch (InstantiationException ie) {
            System.err.println(ie.getMessage());
            System.exit(1);
        } catch (IllegalAccessException iae) {
            System.err.println(iae.getMessage());
            System.exit(1);
        }
    }

    public static <S, T> void readLines(Reader in, 
                                        PersistentWalker<S, T> walker) {
        LineNumberReader scanner = null;
        try {
            StringBuilder input = new StringBuilder(256);
            scanner = new LineNumberReader(in);
            while (true) {
                try {
                    System.out.print(PROMPT);
                    String line = scanner.readLine();
                    while (line != null && !line.equals(".")) {
                        // we add a newline character so the lexer can see it
                        input.append(line + "\n");
                        line = scanner.readLine();
                    }
                    if (input.toString().equals("")) {
                        break;
                    } else {
                        parseWalkShow(new StringReader(input.toString()),
                                walker);
                        input.delete(0, input.length());
                    }
                    if (line == null) {
                        break;
                    }
                } catch (IOException ioe) {
                    System.err.println(ioe.getMessage());
                }
            }
        } finally {
            try { 
                scanner.close();
            } catch(IOException ioe) {
                System.err.println(ioe.getMessage());
            }
        }
    }

    public static <S, T> void parseWalkShow(Reader reader,
					                        PersistentWalker<S, T> walker) {
        SMPLParser parser;
        Exp ast = null; // ArithProgram extends Exp
        try {
            parser = new SMPLParser(new SMPLLexer(reader));
            // now parse the input to produce an AST for the program
            ast = (SMPLProgram) parser.parse().value;
        } catch (TokenException te) {
            System.out.println("Lexing Error: "+ te.getMessage());
        } catch (Exception e) {
            System.out.println("Parse Error: " + e.getMessage());
            // e.printStackTrace();
        }
	
        T result = null;	// type should be same as output of interp
        if (ast != null) {
            try {
                // now walk the AST to evaluate the program.
                result = walker.walk(ast);
                if (result != null) {
                    System.out.println(result);
                }
            } catch (SMPLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
