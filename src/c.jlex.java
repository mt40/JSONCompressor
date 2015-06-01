import java_cup.runtime.*; // defines the Symbol class
// The generated scanner will return a Symbol for each token that it finds.
// A Symbol contains an Object field named value; that field will be of type
// TokenVal, defined below.
//
// A TokenVal object contains the line number on which the token occurs as
// well as the number of the character on that line that starts the token.
// Some tokens (e.g., literals) also include the value of the token.
class TokenVal {
  int linenum;
  int charnum;
  TokenVal(int l, int c) {
    linenum = l;
    charnum = c;
  }
}
class NullTokenVal extends TokenVal {
 // the value of null
  Object val;
  NullTokenVal(int l, int c) {
    super(l,c);
    val = null;
  }
}
class BoolTokenVal extends TokenVal {
 // the value of the boolean literal
  boolean boolVal;
  BoolTokenVal(int l, int c, boolean val) {
    super(l,c);
    boolVal = val;
  }
}
class IntLitTokenVal extends TokenVal {
 // the value of the integer literal
  int intVal;
  IntLitTokenVal(int l, int c, int val) {
    super(l,c);
    intVal = val;
  }
}
class BadEscapedChar extends Exception {
}
//TokenVal for both STRINGLITERAL and ID
class StringTokenVal extends TokenVal {
  String strVal;
  StringTokenVal(int l, int c, String s) {
    super(l, c);
    strVal = s;
  }
  //process string s and return a String with all the escaped characters expanded
  //throws BadEscapedChar if a bad escaped character is found
  public static String checkEscapedChars(String s) throws BadEscapedChar {
    // index 0 is the opening quote, so don't include it.
    int start = 1;
    int slash = s.indexOf("\\");
    String strVal = "";
    while (slash != -1) {
      strVal = strVal + s.substring(start, slash);
      // if the slash is the last character in the string then we are done.
      if (slash == s.length() - 1) throw new BadEscapedChar();
      char c = s.charAt(slash + 1);
      if (c == 'n') {
        strVal = strVal + "\n";
      } else if (c == 't') {
        strVal = strVal + "\t";
      } else if (c == '"') {
        strVal = strVal + "\"";
      } else if (c == '\\') {
        strVal = strVal + "\\";
      } else if (c == '\'') {
        strVal = strVal + "'";
      } else {
        throw new BadEscapedChar();
      }
      start = slash + 2;
      slash = s.indexOf("\\", slash + 2);
    }
    //the last character is the closing quote, so don't include.
    if (start < s.length() - 1)
      strVal = strVal + s.substring(start, s.length() - 1);
    return strVal;
  }
}
// The following class is used to keep track of the character number at which
// the current token starts on its line.
class CharNum {
  static int num=1;
}


class Yylex implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final char YY_EOF = '\uFFFF';
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yyline;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yyline = 0;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private char yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_start () {
		if ((byte) '\n' == yy_buffer[yy_buffer_start]) {
			++yyline;
		}
		++yy_buffer_start;
	}
	private void yy_pushback () {
		--yy_buffer_end;
	}
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ((byte) '\n' == yy_buffer[i]) {
				++yyline;
			}
		}
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
private int [][] unpackFromString(int size1, int size2, String st)
    {
      int colonIndex = -1;
      String lengthString;
      int sequenceLength = 0;
      int sequenceInteger = 0;
      int commaIndex;
      String workString;
      int res[][] = new int[size1][size2];
      for (int i= 0; i < size1; i++)
	for (int j= 0; j < size2; j++)
	  {
	    if (sequenceLength == 0) 
	      {	
		commaIndex = st.indexOf(',');
		if (commaIndex == -1)
		  workString = st;
		else
		  workString = st.substring(0, commaIndex);
		st = st.substring(commaIndex+1);
		colonIndex = workString.indexOf(':');
		if (colonIndex == -1)
		  {
		    res[i][j] = Integer.parseInt(workString);
		  }
		else 
		  {
		    lengthString = workString.substring(colonIndex+1);  
		    sequenceLength = Integer.parseInt(lengthString);
		    workString = workString.substring(0,colonIndex);
		    sequenceInteger = Integer.parseInt(workString);
		    res[i][j] = sequenceInteger;
		    sequenceLength--;
		  }
	      }
	    else 
	      {
		res[i][j] = sequenceInteger;
		sequenceLength--;
	      }
	  }
      return res;
    }
	private int yy_acpt[] = {
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NOT_ACCEPT,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR,
		YY_NO_ANCHOR
	};
	private int yy_cmap[] = {
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 1, 2, 0, 0, 3, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0,
		1, 0, 4, 5, 0, 6, 0, 0,
		7, 8, 9, 10, 11, 12, 0, 13,
		14, 14, 14, 14, 14, 14, 14, 14,
		14, 14, 15, 16, 0, 17, 0, 0,
		0, 18, 18, 18, 18, 18, 18, 18,
		18, 18, 18, 18, 18, 18, 18, 18,
		18, 18, 18, 18, 18, 18, 18, 18,
		18, 18, 18, 19, 20, 21, 0, 18,
		0, 22, 18, 18, 23, 24, 25, 18,
		18, 26, 18, 18, 27, 18, 28, 29,
		18, 18, 30, 31, 32, 33, 34, 18,
		18, 18, 18, 35, 0, 36, 0, 0
		
	};
	private int yy_rmap[] = {
		0, 1, 2, 1, 1, 3, 1, 1,
		1, 1, 1, 1, 1, 4, 5, 1,
		1, 1, 6, 1, 1, 7, 1, 1,
		7, 6, 6, 6, 6, 6, 8, 8,
		9, 10, 11, 7, 12, 13, 14, 15,
		16, 17, 18, 19, 20, 21, 22, 23,
		24, 25 
	};
	private int yy_nxt[][] = unpackFromString(26,37,
"1,2,3,4,31,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,1,20,18:3,49,40,18,45,18:3,46,18,47,21,22,-1:38,2,-1:35,5:2,-1,5:34,-1:13,5,-1:37,14,-1:36,18,-1:3,18,-1:3,18:13,-1:2,35:2,-1,35,-1,35:15,37,35:15,24,30:4,23,30:15,33,30:16,-1:14,18,-1:3,18,-1:3,18:10,25,18:2,-1:2,30:2,-1,30:34,-1:14,18,-1:3,18,-1:3,18:5,26,18:7,-1:16,18,-1:3,18,-1:3,18:2,27,18:10,-1:2,35:2,-1,35:34,-1:14,18,-1:3,18,-1:3,18,28,18:11,-1:16,18,-1:3,18,-1:3,18:2,29,18:10,-1:16,18,-1:3,18,-1:3,18:6,32,18:6,-1:16,18,-1:3,18,-1:3,18:5,34,18:7,-1:16,18,-1:3,18,-1:3,18:11,36,18,-1:16,18,-1:3,18,-1:3,18:4,38,18:8,-1:16,18,-1:3,18,-1:3,18:9,39,18:3,-1:16,18,-1:3,18,-1:3,18:11,41,18,-1:16,18,-1:3,18,-1:3,18:8,42,18:4,-1:16,18,-1:3,18,-1:3,18:7,43,18:5,-1:16,18,-1:3,18,-1:3,18:5,44,18:7,-1:16,18,-1:3,18,-1:3,48,18:12,-1:2");
	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		char yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			if (YY_EOF != yy_lookahead) {
				yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_EOF == yy_lookahead && true == yy_initial) {

return new Symbol(sym.EOF);
				}
				else if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_to_mark();
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_pushback();
					}
					if (0 != (YY_START & yy_anchor)) {
						yy_move_start();
					}
					switch (yy_last_accept_state) {
					case 1:
						{ Errors.fatal(yyline+1, CharNum.num, "ignoring illegal character: " + yytext());
    CharNum.num++; }
					case -2:
						break;
					case 2:
						{CharNum.num += yytext().length(); }
					case -3:
						break;
					case 3:
						{CharNum.num = 1;}
					case -4:
						break;
					case 4:
						{CharNum.num = 1;}
					case -5:
						break;
					case 5:
						{ CharNum.num += yytext().length(); }
					case -6:
						break;
					case 6:
						{ Symbol s = new Symbol(sym.PERCENT, new TokenVal(yyline+1, CharNum.num));
      CharNum.num += 1;
      return s; }
					case -7:
						break;
					case 7:
						{ Symbol s = new Symbol(sym.LPAREN, new TokenVal(yyline+1, CharNum.num));
      CharNum.num += 1;
      return s; }
					case -8:
						break;
					case 8:
						{ Symbol s = new Symbol(sym.RPAREN, new TokenVal(yyline+1, CharNum.num));
      CharNum.num += 1;
      return s; }
					case -9:
						break;
					case 9:
						{ Symbol s = new Symbol(sym.TIMES, new TokenVal(yyline+1, CharNum.num));
      CharNum.num += 1;
      return s; }
					case -10:
						break;
					case 10:
						{ Symbol s = new Symbol(sym.PLUS, new TokenVal(yyline+1, CharNum.num));
      CharNum.num += 1;
      return s; }
					case -11:
						break;
					case 11:
						{ Symbol s = new Symbol(sym.COMMA, new TokenVal(yyline+1, CharNum.num));
    CharNum.num += 1;
    return s; }
					case -12:
						break;
					case 12:
						{ Symbol s = new Symbol(sym.MINUS, new TokenVal(yyline+1, CharNum.num));
    CharNum.num += 1;
    return s; }
					case -13:
						break;
					case 13:
						{ Symbol s = new Symbol(sym.DIVIDE, new TokenVal(yyline+1, CharNum.num));
    CharNum.num += 1;
    return s; }
					case -14:
						break;
					case 14:
						{
   int val;
   try {
     val = (new Integer(yytext())).intValue();
   } catch (NumberFormatException e) {
     Errors.warn(yyline+1, CharNum.num, "integer literal too large; using max value");
     val = Integer.MAX_VALUE;
   }
   Symbol s = new Symbol(sym.INTLITERAL, new IntLitTokenVal(yyline+1, CharNum.num, val));
   CharNum.num += yytext().length();
   return s;
}
					case -15:
						break;
					case 15:
						{ Symbol s = new Symbol(sym.COLON, new TokenVal(yyline+1, CharNum.num));
    CharNum.num += 1;
    return s; }
					case -16:
						break;
					case 16:
						{ Symbol s = new Symbol(sym.SEMICOLON, new TokenVal(yyline+1, CharNum.num));
    CharNum.num += 1;
    return s; }
					case -17:
						break;
					case 17:
						{ Symbol s = new Symbol(sym.ASSIGN, new TokenVal(yyline+1, CharNum.num));
    CharNum.num += 1;
    return s; }
					case -18:
						break;
					case 18:
						{
    Symbol s = new Symbol(sym.ID, new StringTokenVal(yyline+1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return s;
}
					case -19:
						break;
					case 19:
						{ Symbol s = new Symbol(sym.LSQBRACKET, new TokenVal(yyline+1, CharNum.num));
    CharNum.num += 1;
    return s; }
					case -20:
						break;
					case 20:
						{ Symbol s = new Symbol(sym.RSQBRACKET, new TokenVal(yyline+1, CharNum.num));
    CharNum.num += 1;
    return s; }
					case -21:
						break;
					case 21:
						{ Symbol s = new Symbol(sym.LCURLY, new TokenVal(yyline+1, CharNum.num));
    CharNum.num += 1;
    return s; }
					case -22:
						break;
					case 22:
						{ Symbol s = new Symbol(sym.RCURLY, new TokenVal(yyline+1, CharNum.num));
    CharNum.num += 1;
    return s; }
					case -23:
						break;
					case 23:
						{
    try {
      String str = StringTokenVal.checkEscapedChars(yytext());
      //Symbol s = new Symbol(sym.STRINGLITERAL, new StringTokenVal(yyline+1, CharNum.num, str));
      Symbol s = new Symbol(sym.STRINGLITERAL, new StringTokenVal(yyline+1, CharNum.num, yytext()));
      CharNum.num += yytext().length();
      return s;
    } catch (BadEscapedChar e) {
      Errors.fatal(yyline+1, CharNum.num, "ignoring string literal with bad escaped character");
      CharNum.num += yytext().length();
    }
}
					case -24:
						break;
					case 24:
						{
    CharNum.num += yytext().length();
}
					case -25:
						break;
					case 25:
						{ Symbol s = new Symbol(sym.INT, new TokenVal(yyline+1, CharNum.num));
      CharNum.num += 3;
      return s; }
					case -26:
						break;
					case 26:
						{ Symbol s = new Symbol(sym.NULLLITERAL, new NullTokenVal(yyline+1, CharNum.num));
       CharNum.num += 4;
       return s; }
					case -27:
						break;
					case 27:
						{ Symbol s = new Symbol(sym.TRUELITERAL, new BoolTokenVal(yyline+1, CharNum.num, true));
       CharNum.num += 4;
       return s; }
					case -28:
						break;
					case 28:
						{ Symbol s = new Symbol(sym.VOID, new TokenVal(yyline+1, CharNum.num));
       CharNum.num += 4;
       return s; }
					case -29:
						break;
					case 29:
						{ Symbol s = new Symbol(sym.FALSELITERAL, new BoolTokenVal(yyline+1, CharNum.num, false));
       CharNum.num += 5;
       return s; }
					case -30:
						break;
					case 31:
						{ Errors.fatal(yyline+1, CharNum.num, "ignoring illegal character: " + yytext());
    CharNum.num++; }
					case -31:
						break;
					case 32:
						{
    Symbol s = new Symbol(sym.ID, new StringTokenVal(yyline+1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return s;
}
					case -32:
						break;
					case 34:
						{
    Symbol s = new Symbol(sym.ID, new StringTokenVal(yyline+1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return s;
}
					case -33:
						break;
					case 36:
						{
    Symbol s = new Symbol(sym.ID, new StringTokenVal(yyline+1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return s;
}
					case -34:
						break;
					case 38:
						{
    Symbol s = new Symbol(sym.ID, new StringTokenVal(yyline+1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return s;
}
					case -35:
						break;
					case 39:
						{
    Symbol s = new Symbol(sym.ID, new StringTokenVal(yyline+1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return s;
}
					case -36:
						break;
					case 40:
						{
    Symbol s = new Symbol(sym.ID, new StringTokenVal(yyline+1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return s;
}
					case -37:
						break;
					case 41:
						{
    Symbol s = new Symbol(sym.ID, new StringTokenVal(yyline+1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return s;
}
					case -38:
						break;
					case 42:
						{
    Symbol s = new Symbol(sym.ID, new StringTokenVal(yyline+1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return s;
}
					case -39:
						break;
					case 43:
						{
    Symbol s = new Symbol(sym.ID, new StringTokenVal(yyline+1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return s;
}
					case -40:
						break;
					case 44:
						{
    Symbol s = new Symbol(sym.ID, new StringTokenVal(yyline+1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return s;
}
					case -41:
						break;
					case 45:
						{
    Symbol s = new Symbol(sym.ID, new StringTokenVal(yyline+1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return s;
}
					case -42:
						break;
					case 46:
						{
    Symbol s = new Symbol(sym.ID, new StringTokenVal(yyline+1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return s;
}
					case -43:
						break;
					case 47:
						{
    Symbol s = new Symbol(sym.ID, new StringTokenVal(yyline+1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return s;
}
					case -44:
						break;
					case 48:
						{
    Symbol s = new Symbol(sym.ID, new StringTokenVal(yyline+1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return s;
}
					case -45:
						break;
					case 49:
						{
    Symbol s = new Symbol(sym.ID, new StringTokenVal(yyline+1, CharNum.num, yytext()));
    CharNum.num += yytext().length();
    return s;
}
					case -46:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
					}
				}
			}
		}
	}
}