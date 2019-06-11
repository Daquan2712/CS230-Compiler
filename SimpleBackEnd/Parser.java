/* Generated By:JavaCC: Do not edit this line. Parser.java */
//This part of the code is just plain java.
//The "Parser" class is an executable class with a main method.
//It creates a Parser object named "parser" and invokes its "Input()" method.
//The Input() method is defined in the grammar as a "nonterminal symbol."

import java.io.*;
import java.util.*;

public class Parser implements ParserConstants {

  public static void main(String args[]) throws ParseException, FileNotFoundException {
    Parser parser = new Parser(new FileInputStream (args[0]));
    HashSet<String> st = new HashSet<String>();
    SymbolTable table = new SymbolTable(st);
    BinaryExpression parsedTree = parser.Input(table);
    System.out.println(parsedTree.toString());
    table.Print();
  }

/* Specification of Language */

//The Input() symbol is just a wrapper for the Program() symbol.
  static final public BinaryExpression Input(SymbolTable table) throws ParseException {
 BinaryExpression result;
    result = Program(table);
    jj_consume_token(0);
   {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

//Program() is the legit start symbol for this grammar. At this point,
//we just regard it as a string of tokens, as specified above. The
//statements in brackets are executed when the tokens are
//recognized.
//Rule: program -> void main() <block>
  static final public BinaryExpression Program(SymbolTable table) throws ParseException {
 Token t; BinaryExpression res;
    t = jj_consume_token(keyw);
    //return type; only void for now
        t = jj_consume_token(keyw);
    //main
        t = jj_consume_token(openparen);
    t = jj_consume_token(closeparen);
    t = jj_consume_token(begin);
    res = Block(table);
     {if (true) return res;}
    throw new Error("Missing return statement in function");
  }

//Rule: <block> -> { <declarations> <optional_statements>?}
  static final public BinaryExpression Block(SymbolTable table) throws ParseException {
 Token t; BinaryExpression left = null; BinaryExpression right = null;
    //t = <begin> //symbol "{"
      left = Declarations(table);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case keywIf:
    case keywLoop:
    case identifier:
    case begin:
      right = Optional_Statement(table);
      break;
    default:
      jj_la1[0] = jj_gen;
      ;
    }
    t = jj_consume_token(end);
   {if (true) return new BinaryExpression("program", left, right);}
    throw new Error("Missing return statement in function");
  }

//Rule: <declarations> -> (<declaration>)*
  static final public BinaryExpression Declarations(SymbolTable table) throws ParseException {
 BinaryExpression left = null; BinaryExpression temp = null;
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case type:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_1;
      }
      temp = Declaration(table);
    if (left == null) {
      left = temp;
    } else {
      left = new BinaryExpression(";", left, temp);
    }
    }
   {if (true) return left;}
    throw new Error("Missing return statement in function");
  }

//Rule: <declaration> -> <type> <identifier_list>;
  static final public BinaryExpression Declaration(SymbolTable table) throws ParseException {
 Token t; BinaryExpression left = null; BinaryExpression right = null;
    t = jj_consume_token(type);
                    left = new BinaryExpression(t.image, null, null);
    right = Identifier_List(table);
    t = jj_consume_token(semicolon);
   {if (true) return new BinaryExpression(":", left, right);}
    throw new Error("Missing return statement in function");
  }

//Rule: <identifier_list> -> <id> (, <id>)*
  static final public BinaryExpression Identifier_List(SymbolTable table) throws ParseException {
 Token t; BinaryExpression left = null; BinaryExpression right = null;
    t = jj_consume_token(identifier);
         table.Add(t.image);
   left = new BinaryExpression(t.image, null, null);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case comma:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_2;
      }
      t = jj_consume_token(comma);
      t = jj_consume_token(identifier);
                      table.Add(t.image);
    right = new BinaryExpression(t.image, null, null); left = new BinaryExpression(",", left, right);
    }
   {if (true) return left;}
    throw new Error("Missing return statement in function");
  }

//Rule: <optional_statements> -> <statement_list>
  static final public BinaryExpression Optional_Statement(SymbolTable table) throws ParseException {
 BinaryExpression res = null;
    res = Statement_List(table);
   {if (true) return res;}
    throw new Error("Missing return statement in function");
  }

//Rule: <statement_list> -> <statement> (<statement>)*
  static final public BinaryExpression Statement_List(SymbolTable table) throws ParseException {
 Token t; BinaryExpression left = null; BinaryExpression right = null;
    left = Statement(table);
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case keywIf:
      case keywLoop:
      case identifier:
      case begin:
        ;
        break;
      default:
        jj_la1[3] = jj_gen;
        break label_3;
      }
      right = Statement(table);
                             left = new BinaryExpression(";", left, right);
    }
   {if (true) return left;}
    throw new Error("Missing return statement in function");
  }

//Rule: <statement> -> <variable><assignop><expression>; 
//						| <block>
//						| if (<expression>) <statement> <else_clause>
//						| while (<expression>) <statement>
  static final public BinaryExpression Statement(SymbolTable table) throws ParseException {
 Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case identifier:
      t = jj_consume_token(identifier);
                    {if (true) return Statement_Expression(t, table);}
      break;
    case keywIf:
      t = jj_consume_token(keywIf);
                  {if (true) return Statement_If(table);}
      break;
    case keywLoop:
      t = jj_consume_token(keywLoop);
                    {if (true) return Statement_While(table);}
      break;
    case begin:
      t = jj_consume_token(begin);
                 {if (true) return Block(table);}
      break;
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

//if it reads a variable for the rule <statement>, goes to this function
//Rule: <statement> -> <variable><assignop><expression>;
  static final public BinaryExpression Statement_Expression(Token var, SymbolTable table) throws ParseException {
 Token t; BinaryExpression left = null; BinaryExpression right = null; String op;
    t = jj_consume_token(assignop);
                  left = new BinaryExpression(var.image, null, null); op = t.image;
    right = Expression();
    t = jj_consume_token(semicolon);
   table.Add(var.image); {if (true) return new BinaryExpression(op, left, right);}
    throw new Error("Missing return statement in function");
  }

//if it reads an "if" keyword, goes to this function
//Rule: <statement> -> if (<expression>) <statement> <else_clause>
  static final public BinaryExpression Statement_If(SymbolTable table) throws ParseException {
 Token t; BinaryExpression left = null; BinaryExpression right = null; BinaryExpression ifTrue = null;
    t = jj_consume_token(openparen);
    left = Expression();
    //this is the condition
            t = jj_consume_token(closeparen);
    ifTrue = Statement(table);
    //If the condition is true
            right = Statement_Else(ifTrue, table);
   {if (true) return new BinaryExpression("If", left, right);}
    throw new Error("Missing return statement in function");
  }

//An If statement has to be followed with an Else
//Rule: <else_clause> -> (else <statement>)?
  static final public BinaryExpression Statement_Else(BinaryExpression ifTrue, SymbolTable table) throws ParseException {
 Token t; BinaryExpression left = null; BinaryExpression right = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case keywElse:
      t = jj_consume_token(keywElse);
      right = Statement(table);
   left = ifTrue; {if (true) return new BinaryExpression("Else", left, right);}
      break;
    default:
      jj_la1[5] = jj_gen;
      ;
    }
   left = ifTrue; {if (true) return new BinaryExpression("Else", left, null);}
    throw new Error("Missing return statement in function");
  }

//if it reads a While keyword, goes to this function
//Rule: <statement> -> while (<expression>) <statement>
  static final public BinaryExpression Statement_While(SymbolTable table) throws ParseException {
 Token t; BinaryExpression left = null; BinaryExpression right = null;
    t = jj_consume_token(openparen);
    left = Expression();
    //condition
            t = jj_consume_token(closeparen);
    right = Statement(table);
   {if (true) return new BinaryExpression("while", left, right);}
    throw new Error("Missing return statement in function");
  }

//Rule: <expression> -> <simple_expression> <relopclause>
  static final public BinaryExpression Expression() throws ParseException {
 Token t; BinaryExpression left = null; BinaryExpression right = null;
    left = Simple_Expression();
    right = Relop_Clause();
   if (right == null)
      {if (true) return left;}
    else
      {if (true) return new BinaryExpression(right.getOp(), left, right.getExpr());}
    throw new Error("Missing return statement in function");
  }

//Rule: <relopclause> -> <relop> <simple_expression> | empty
  static final public BinaryExpression Relop_Clause() throws ParseException {
 Token t; BinaryExpression expr = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case relop:
      t = jj_consume_token(relop);
      expr = Simple_Expression();
                                     {if (true) return new BinaryExpression(t.image, expr, null);}
      break;
    default:
      jj_la1[6] = jj_gen;
      ;
    }
   {if (true) return null;}
    throw new Error("Missing return statement in function");
  }

//Rule: <simple_expression> -> <term> (<addop><term>)*
  static final public BinaryExpression Simple_Expression() throws ParseException {
 Token t; BinaryExpression left = null; BinaryExpression right = null;
    left = Term();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case addop:
        ;
        break;
      default:
        jj_la1[7] = jj_gen;
        break label_4;
      }
      t = jj_consume_token(addop);
                right = Term(); left = new BinaryExpression(t.image, left, right);
    }
   {if (true) return left;}
    throw new Error("Missing return statement in function");
  }

//Rule: <term> -> <factor> (<mulop><factor>)*
//It seems like we don't need the option | <addop> <term> as it is considered in 
//simple_expression already
  static final public BinaryExpression Term() throws ParseException {
 Token t; BinaryExpression left = null; BinaryExpression right = null;
    left = Factor();
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case mulop:
        ;
        break;
      default:
        jj_la1[8] = jj_gen;
        break label_5;
      }
      t = jj_consume_token(mulop);
                right = Factor(); left = new BinaryExpression(t.image, left, right);
    }
   {if (true) return left;}
    throw new Error("Missing return statement in function");
  }

//Rule: <factor> -> <id> | (<expression>) | <num> | !<factor>
  static final public BinaryExpression Factor() throws ParseException {
 Token t; BinaryExpression temporaryAST = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case identifier:
      t = jj_consume_token(identifier);
                    {if (true) return new BinaryExpression(t.image, null, null);}
      break;
    case openparen:
      t = jj_consume_token(openparen);
                     temporaryAST = Expression(); Close_Paren(); {if (true) return temporaryAST;}
      break;
    case num:
      t = jj_consume_token(num);
               {if (true) return new BinaryExpression(t.image, null, null);}
      break;
    case negop:
      t = jj_consume_token(negop);
                 temporaryAST = Factor(); {if (true) return new BinaryExpression(t.image, temporaryAST, null);}
      break;
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

//This function is to check if a close parenthesis exists after an open parenthesis
//and it is invoked in Factor() in case a token read <openparen>
  static final public void Close_Paren() throws ParseException {
 Token t;
    t = jj_consume_token(closeparen);
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public ParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[10];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x20a80,0x400,0x400000,0x20a80,0x20a80,0x100,0x4000,0x20,0x10000,0x83800,};
   }

  /** Constructor with InputStream. */
  public Parser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 10; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[23];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 10; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 23; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}