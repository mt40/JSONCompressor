
//----------------------------------------------------
// The following code was generated by CUP v0.10k
// Mon Jun 01 08:00:23 ICT 2015
//----------------------------------------------------

import java_cup.runtime.*;
import java.util.*;

/** CUP v0.10k generated parser.
  * @version Mon Jun 01 08:00:23 ICT 2015
  */
public class parser extends java_cup.runtime.lr_parser {

  /** Default constructor. */
  public parser() {super();}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s) {super(s);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\023\000\002\002\004\000\002\003\003\000\002\003" +
    "\003\000\002\004\006\000\002\004\004\000\002\005\005" +
    "\000\002\006\005\000\002\006\002\000\002\007\006\000" +
    "\002\007\004\000\002\010\003\000\002\010\003\000\002" +
    "\010\003\000\002\010\003\000\002\010\003\000\002\010" +
    "\003\000\002\010\003\000\002\011\005\000\002\011\002" +
    "" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\037\000\006\021\007\023\004\001\002\000\022\014" +
    "\031\015\027\016\025\017\030\020\022\021\007\023\004" +
    "\024\034\001\002\000\004\002\000\001\002\000\004\002" +
    "\032\001\002\000\006\015\011\022\013\001\002\000\004" +
    "\002\uffff\001\002\000\004\032\021\001\002\000\006\022" +
    "\ufffa\027\014\001\002\000\012\002\ufffd\022\ufffd\024\ufffd" +
    "\027\ufffd\001\002\000\004\015\011\001\002\000\004\022" +
    "\016\001\002\000\012\002\ufffe\022\ufffe\024\ufffe\027\ufffe" +
    "\001\002\000\006\022\ufffa\027\014\001\002\000\004\022" +
    "\ufffb\001\002\000\020\014\031\015\027\016\025\017\030" +
    "\020\022\021\007\023\004\001\002\000\010\022\ufff1\024" +
    "\ufff1\027\ufff1\001\002\000\006\022\ufffc\027\ufffc\001\002" +
    "\000\010\022\ufff5\024\ufff5\027\ufff5\001\002\000\010\022" +
    "\ufff3\024\ufff3\027\ufff3\001\002\000\010\022\ufff4\024\ufff4" +
    "\027\ufff4\001\002\000\010\022\ufff7\024\ufff7\027\ufff7\001" +
    "\002\000\010\022\ufff2\024\ufff2\027\ufff2\001\002\000\010" +
    "\022\ufff6\024\ufff6\027\ufff6\001\002\000\004\002\001\001" +
    "\002\000\006\024\uffef\027\035\001\002\000\012\002\ufff8" +
    "\022\ufff8\024\ufff8\027\ufff8\001\002\000\020\014\031\015" +
    "\027\016\025\017\030\020\022\021\007\023\004\001\002" +
    "\000\004\024\037\001\002\000\012\002\ufff9\022\ufff9\024" +
    "\ufff9\027\ufff9\001\002\000\006\024\uffef\027\035\001\002" +
    "\000\004\024\ufff0\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\037\000\010\003\005\004\004\007\007\001\001\000" +
    "\010\004\023\007\025\010\032\001\001\000\002\001\001" +
    "\000\002\001\001\000\004\005\011\001\001\000\002\001" +
    "\001\000\002\001\001\000\004\006\014\001\001\000\002" +
    "\001\001\000\004\005\016\001\001\000\002\001\001\000" +
    "\002\001\001\000\004\006\017\001\001\000\002\001\001" +
    "\000\010\004\023\007\025\010\022\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\004\011\035\001\001" +
    "\000\002\001\001\000\010\004\023\007\025\010\037\001" +
    "\001\000\002\001\001\000\002\001\001\000\004\011\040" +
    "\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$parser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}




public void syntax_error(Symbol currToken) {
    if (currToken.value == null) {
       Errors.fatal(0,0, "Syntax error at end of file");
    }
    else {
       Errors.fatal(((TokenVal)currToken.value).linenum,
	            ((TokenVal)currToken.value).charnum,
		    "Syntax error");
    }
    System.exit(-1);
}

}

/** Cup generated class to encapsulate user supplied action code.*/
class CUP$parser$actions {
  private final parser parser;

  /** Constructor */
  CUP$parser$actions(parser parser) {
    this.parser = parser;
  }

  /** Method with the actual generated action code. */
  public final java_cup.runtime.Symbol CUP$parser$do_action(
    int                        CUP$parser$act_num,
    java_cup.runtime.lr_parser CUP$parser$parser,
    java.util.Stack            CUP$parser$stack,
    int                        CUP$parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$parser$result;

      /* select the action based on the action number */
      switch (CUP$parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // valueList ::= 
            {
              LinkedList RESULT = null;
		 RESULT = new LinkedList(); 
              CUP$parser$result = new java_cup.runtime.Symbol(7/*valueList*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // valueList ::= COMMA val valueList 
            {
              LinkedList RESULT = null;
		int vleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		JsonValue v = (JsonValue)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		int vlleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left;
		int vlright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right;
		LinkedList vl = (LinkedList)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-0)).value;
		 vl.addFirst(v); RESULT = vl; 
              CUP$parser$result = new java_cup.runtime.Symbol(7/*valueList*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // val ::= NULLLITERAL 
            {
              JsonValue RESULT = null;
		int nleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left;
		int nright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right;
		NullTokenVal n = (NullTokenVal)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-0)).value;
		 RESULT = new NullLit(n.linenum, n.charnum); 
              CUP$parser$result = new java_cup.runtime.Symbol(6/*val*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // val ::= FALSELITERAL 
            {
              JsonValue RESULT = null;
		int bleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left;
		int bright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right;
		BoolTokenVal b = (BoolTokenVal)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-0)).value;
		 RESULT = new BoolLit(b.linenum, b.charnum, false); 
              CUP$parser$result = new java_cup.runtime.Symbol(6/*val*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // val ::= TRUELITERAL 
            {
              JsonValue RESULT = null;
		int bleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left;
		int bright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right;
		BoolTokenVal b = (BoolTokenVal)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-0)).value;
		 RESULT = new BoolLit(b.linenum, b.charnum, true); 
              CUP$parser$result = new java_cup.runtime.Symbol(6/*val*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // val ::= array 
            {
              JsonValue RESULT = null;
		int oleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left;
		int oright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right;
		JsonArray o = (JsonArray)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-0)).value;
		 RESULT = new JsonArrayValue(o); 
              CUP$parser$result = new java_cup.runtime.Symbol(6/*val*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // val ::= object 
            {
              JsonValue RESULT = null;
		int oleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left;
		int oright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right;
		JsonObject o = (JsonObject)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-0)).value;
		 RESULT = new JsonObjectValue(o); 
              CUP$parser$result = new java_cup.runtime.Symbol(6/*val*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // val ::= INTLITERAL 
            {
              JsonValue RESULT = null;
		int ileft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left;
		int iright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right;
		IntLitTokenVal i = (IntLitTokenVal)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-0)).value;
		 RESULT = new IntLit(i.linenum, i.charnum, i.intVal); 
              CUP$parser$result = new java_cup.runtime.Symbol(6/*val*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // val ::= STRINGLITERAL 
            {
              JsonValue RESULT = null;
		int sleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left;
		int sright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right;
		StringTokenVal s = (StringTokenVal)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-0)).value;
		 RESULT = new StringLit(s.linenum, s.charnum, s.strVal); 
              CUP$parser$result = new java_cup.runtime.Symbol(6/*val*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // array ::= LSQBRACKET RSQBRACKET 
            {
              JsonArray RESULT = null;
		 RESULT = new JsonArray(new LinkedList()); 
              CUP$parser$result = new java_cup.runtime.Symbol(5/*array*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // array ::= LSQBRACKET val valueList RSQBRACKET 
            {
              JsonArray RESULT = null;
		int vleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		JsonValue v = (JsonValue)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int vlleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int vlright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		LinkedList vl = (LinkedList)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		 vl.addFirst(v); RESULT = new JsonArray(vl); 
              CUP$parser$result = new java_cup.runtime.Symbol(5/*array*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // pairList ::= 
            {
              LinkedList RESULT = null;
		 RESULT = new LinkedList(); 
              CUP$parser$result = new java_cup.runtime.Symbol(4/*pairList*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // pairList ::= COMMA pair pairList 
            {
              LinkedList RESULT = null;
		int pleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int pright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		JsonPair p = (JsonPair)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		int plleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left;
		int plright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right;
		LinkedList pl = (LinkedList)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-0)).value;
		 pl.addFirst(p); RESULT = pl; 
              CUP$parser$result = new java_cup.runtime.Symbol(4/*pairList*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // pair ::= STRINGLITERAL COLON val 
            {
              JsonPair RESULT = null;
		int sleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int sright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		StringTokenVal s = (StringTokenVal)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int vleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right;
		JsonValue v = (JsonValue)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-0)).value;
		 RESULT = new JsonPair(s.strVal, v); 
              CUP$parser$result = new java_cup.runtime.Symbol(3/*pair*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // object ::= LCURLY RCURLY 
            {
              JsonObject RESULT = null;
		 RESULT = new JsonObject(new LinkedList()); 
              CUP$parser$result = new java_cup.runtime.Symbol(2/*object*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // object ::= LCURLY pair pairList RCURLY 
            {
              JsonObject RESULT = null;
		int pleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int pright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		JsonPair p = (JsonPair)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int plleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int plright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		LinkedList pl = (LinkedList)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		 pl.addFirst(p); RESULT = new JsonObject(pl); 
              CUP$parser$result = new java_cup.runtime.Symbol(2/*object*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // json ::= array 
            {
              Json RESULT = null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right;
		JsonArray a = (JsonArray)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-0)).value;
		RESULT = new Json(a); 
              CUP$parser$result = new java_cup.runtime.Symbol(1/*json*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // json ::= object 
            {
              Json RESULT = null;
		int oleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left;
		int oright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right;
		JsonObject o = (JsonObject)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-0)).value;
		RESULT = new Json(o); 
              CUP$parser$result = new java_cup.runtime.Symbol(1/*json*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= json EOF 
            {
              Object RESULT = null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Json start_val = (Json)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		RESULT = start_val;
              CUP$parser$result = new java_cup.runtime.Symbol(0/*$START*/, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-0)).right, RESULT);
            }
          /* ACCEPT */
          CUP$parser$parser.done_parsing();
          return CUP$parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number found in internal parse table");

        }
    }
}

