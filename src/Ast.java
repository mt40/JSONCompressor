import java.io.*;
import java.util.*;

// **********************************************************************
// Ast class (base class for all other kinds of nodes)
// **********************************************************************
abstract class Ast {
}

class Json extends Ast {
    private LinkedList objects;

    public Json(JsonObject obj) {
        this.objects = new LinkedList();
        objects.add(obj);
    }

    public Json(JsonArray array) {
        this.objects = array.valueList;
    }

    // Compile
    public void compile() {
        for(int i = 0; i < objects.size(); ++i) {
            // convert JsonValue to JsonObject
            if(objects.get(i) instanceof JsonObjectValue) {
                objects.set(i, new JsonObject((JsonObjectValue)objects.get(i))); // replace
            }
            JsonObject obj = (JsonObject)objects.get(i);
            obj.compile();
        }

        System.out.println("Objects = " + objects.size());
    }
}

// **********************************************************************
// JsonObject
// **********************************************************************
class JsonObject extends Ast {
    private LinkedList pairList;

    public JsonObject(LinkedList pairList) {
        this.pairList = pairList;
    }

    public JsonObject(JsonObjectValue objValue) {
        this.pairList = objValue.obj.pairList;
    }

    public void compile() {
        for(int i = 0; i < pairList.size(); ++i) {
            JsonPair pair = (JsonPair)pairList.get(i);
            //stmt.table = this.table;
            pair.compile();
        }

        //System.out.println("Pairs = " + pairList.size());
    }
}

// **********************************************************************
// JsonPair
// **********************************************************************
class JsonPair extends Ast {
    private String key;
    private JsonValue value;

    public JsonPair(String key, JsonValue value) {
        this.key = key;
        this.value = value;
    }

    public void compile() {
        //exp.table = table;
        //value.compile();

        //table.enterVariable(id, exp.value);
    }
}

// **********************************************************************
// JsonArray
// **********************************************************************
class JsonArray extends Ast {
    public LinkedList valueList;

    public JsonArray(LinkedList valueList) {
        this.valueList = valueList;
    }

    public void compile() {
        System.out.println("Values = " + valueList.size());
    }
}

// **********************************************************************
// JsonValue
// **********************************************************************
abstract class JsonValue {
    protected String stringValue;

    public abstract void compile();
}

abstract class JsonBasicValue extends JsonValue {
    private int lineNum;
    private int charNum;
    
    public JsonBasicValue(int lineNum, int charNum) {
        this.lineNum = lineNum;
        this.charNum = charNum;
    }
    
    public int getLine() {
        return lineNum;
    }
    public int getChar() {
        return charNum;
    }
}

class NumberLit extends JsonBasicValue {
    private double numberVal;

    public NumberLit(int lineNum, int charNum, double numberVal) {
        super(lineNum, charNum);
        this.numberVal = numberVal;
    }

    public void compile() {
        stringValue = Double.toString(numberVal);
    }
}

class StringLit extends JsonBasicValue {
    private String strVal;

    public StringLit(int lineNum, int charNum, String strVal) {
        super(lineNum, charNum);
        this.strVal = strVal;
    }

    public String str() {
        return strVal;
    }

    public void compile() {
        stringValue = strVal;
    }
}

class BoolLit extends JsonBasicValue {
    private boolean boolVal;

    public BoolLit(int lineNum, int charNum, boolean val) {
        super(lineNum, charNum);
        this.boolVal = val;
    }

    public void compile() {
        stringValue = "" + boolVal;
    }
}

class NullLit extends JsonBasicValue {
    private Object val;

    public NullLit(int lineNum, int charNum) {
        super(lineNum, charNum);
        this.val = null;
    }

    public void compile() {
        stringValue = "null";
    }
}

class JsonObjectValue extends JsonValue {
    public JsonObject obj;

    public JsonObjectValue(JsonObject obj) {
        this.obj = obj;
    }

    public void compile() {
        obj.compile();
    }
}

class JsonArrayValue extends JsonValue {
    public JsonArray array;

    public JsonArrayValue(JsonArray array) {
        this.array = array;
    }

    public void compile() {
        array.compile();
    }
}

