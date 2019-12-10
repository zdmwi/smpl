Release Notes

v1.1
This version takes the vanilla implementation using the Visitor design
pattern and creates a generic Repl that can use the command line to
determine which Visitor the user wants to use.
Specific changes:
- Visitor interface now includes a getDefaultState() method

- PersistentWalker class now provides for a visitor that will retain
  the same instance of a state across multiple traversals of ASTs.
  This allows for multiple user-entered expressions to interact with
  each other (e.g. previous statements can modify a global environment
  to affect future evaluations)

- Created VisitException class to better facilitate the separation of
  sources of errors that arise while lexing, parsing and walking user
  input.  (For an evaluator, WalkException is a runtime error).

- Renamed Repl to Main. Rewrote it to create a generic instance of
  PersistentWalker wrapped around a visitor of a class specified on
  the command line

- There are two known visitor classes at this time: Evaluator and
  ToScheme.
  
Example Usages:
- To use as a Repl for a standard interpretor (expression evaluator):
  > $(JAVA_EXEC) Main -w Evaluator -
  OR more simply:
  > $(JAVA_EXEC) Main -

- To use as a converter to Scheme (prefix notation for expressions):
  > $(JAVA_EXEC) Main -w ToScheme -

- To evaluate a file of expressions called foo.txt:
  > $(JAVA_EXEC) Main -w Evaluator foo.txt
  OR more simply:
  > $(JAVA_EXEC) Main foo.txt


