import java.io.*;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

class JsonWriter {
    public static void writeToFile(String fileName, String json) {
        try {
            FileWriter file = new FileWriter(fileName);
            file.write(json);
            file.flush();
            file.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class SymbolTable {
    private Map<String, Boolean> keys;

    public SymbolTable() {
        keys = new LinkedHashMap<String, Boolean>();
    }

    public SymbolTable(LinkedHashMap<String, Boolean> keyMap) {
        keys = keyMap;
    }

    public void addKey(String key) {
        if(!keys.containsKey(key)) {
            keys.put(key, true);
        }
    }

    public boolean contains(String key) {
        return keys.containsKey(key);
    }

    public int indexOf(String key) {
        int index = 0;
        for(String item : keys.keySet()) {
            if(item.equals(key)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public int size() {
        return keys.size();
    }

    public JSONArray toJsonArray() {
        JSONArray array = new JSONArray();
        for(String k : keys.keySet()) {
            array.add(k);
        }

        return array;
    }
}

// **********************************************************************
// Ast class (base class for all other kinds of nodes)
// **********************************************************************
abstract class Ast {
    protected SymbolTable table;

    public abstract Object compile();
}

class Json extends Ast {
    public String outputFileName;
    private Ast content;

    public Json(JsonObject obj) {
        content = obj;
    }

    public Json(JsonArray array) {
        content = array;
    }

    // Compile
    public Object compile() {
        table = new SymbolTable();
        content.table = table;

        JSONArray result = new JSONArray();
        Object compiledContent = content.compile();

        //if(table.size() > 0) {
            result.add(table.toJsonArray());
        //}

        if(compiledContent instanceof LinkedList) {
            LinkedList list = (LinkedList)compiledContent;
            for(int i = 0; i < list.size(); ++i) {
                result.add(list.get(i));
            }
        }
        else {
            result.add(compiledContent);
        }

        JsonWriter.writeToFile(outputFileName, result.toJSONString());

        return null;
    }
}

// **********************************************************************
// JsonObject
// **********************************************************************
class JsonObject extends Ast {
    public LinkedList pairList;

    public JsonObject(LinkedList pairList) {
        this.pairList = pairList;
    }

    public JsonObject(JsonObjectValue objValue) {
        this.pairList = objValue.obj.pairList;
    }

    public JSONArray compile() {
        JSONArray values = new JSONArray();

        for(int i = 0; i < pairList.size(); ++i) {
            JsonPair pair = (JsonPair)pairList.get(i);

            table.addKey(pair.key);

            pair.table = table;
            Object compiledValue = pair.compile();

            int pos = table.indexOf(pair.key);
            for(int j = 0; j < pos - i; ++j)
                values.add(null);

            values.add(compiledValue);
            
        }

        return values;
        //System.out.println("Pairs = " + pairList.size());
    }
}

// **********************************************************************
// JsonPair
// **********************************************************************
class JsonPair extends Ast {
    public String key;
    public JsonValue value;

    public JsonPair(String key, JsonValue value) {
        this.key = key;
        this.value = value;
    }

    public Object compile() {
        value.table = new SymbolTable(); // new scope
        Object compiledValue = value.compile();
        return compiledValue;
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

    public LinkedList compile() {
        LinkedList complete = new LinkedList();

        for(int i = 0; i < valueList.size(); ++i) {
            JsonValue value = (JsonValue)valueList.get(i);
            value.table = table;

            Object result = value.compile();

            complete.add(result);
        }

        return complete;
    }
}

// **********************************************************************
// JsonValue
// **********************************************************************
abstract class JsonValue extends Ast {
    protected String stringValue;
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

    public String compile() {
        return toString();
    }

    public String toString() {
        Double eps = 0.0000000000001;
        long l = (long)numberVal;
        if(numberVal - 1.0 * l < eps)
            return "" + l;
        return Double.toString(numberVal);
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

    public String compile() {
        return toString();
    }

    public String toString() {
        return strVal;
    }
}

class BoolLit extends JsonBasicValue {
    private boolean boolVal;

    public BoolLit(int lineNum, int charNum, boolean val) {
        super(lineNum, charNum);
        this.boolVal = val;
    }

    public Boolean compile() {
        return boolVal;
    }

    public String toString() {
        return "" + boolVal;
    }
}

class NullLit extends JsonBasicValue {
    private Object val;

    public NullLit(int lineNum, int charNum) {
        super(lineNum, charNum);
        this.val = null;
    }

    public String compile() {
        return toString();
    }

    public String toString() {
        return null;
    }
}

class JsonObjectValue extends JsonValue {
    public JsonObject obj;

    public JsonObjectValue(JsonObject obj) {
        this.obj = obj;
    }

    public JSONArray compile() {
        obj.table = table;
        return obj.compile();
    }
}

class JsonArrayValue extends JsonValue {
    public JsonArray array;

    public JsonArrayValue(JsonArray array) {
        this.array = array;
    }

    public LinkedList compile() {
        //System.out.println(array.compile());
        table = new SymbolTable();
        array.table = table;

        LinkedList result = new LinkedList();
        LinkedList compiledContent = array.compile();

        //if(table.size() > 0) {
            result.add(table.toJsonArray());
        //}

        result.addAll(compiledContent);

        return result;
    }
}

