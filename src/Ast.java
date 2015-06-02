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
        keys = new TreeMap<String, Boolean>();
    }

    public SymbolTable(Map<String, Boolean> keyMap) {
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
}

class Json extends Ast {
    public String outputFileName;
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
        Map<String, Boolean> keyMap = new TreeMap<String, Boolean>(); // the value boolean is not used (I chose boolean to save space)

        for(int i = 0; i < objects.size(); ++i) {
            // convert JsonValue to JsonObject
            if(objects.get(i) instanceof JsonObjectValue) {
                objects.set(i, new JsonObject((JsonObjectValue)objects.get(i))); // replace
            }

            JsonObject obj = (JsonObject)objects.get(i);    

            // Get all keys in the document
            for(int j = 0; j < obj.pairList.size(); ++j) {
                JsonPair pair = (JsonPair)(obj.pairList.get(j));
                if(!keyMap.containsKey(pair.key))
                    keyMap.put(pair.key, true);
            }
        }

        this.table = new SymbolTable(keyMap);

        JSONArray complete = new JSONArray();
        complete.add(table.toJsonArray()); // add key array first

        // Then add array of values of each JSON object
        for(int i = 0; i < objects.size(); ++i) {
            JsonObject obj = (JsonObject)objects.get(i);
            obj.table = this.table;
            JSONArray result = obj.compile();

            complete.add(result);
        }

        String result = complete.toJSONString();
        JsonWriter.writeToFile(outputFileName, result);
        System.out.println("Objects = " + objects.size());
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
            Object compiledValue = pair.compile();

            // table is not null only when we are gethering all keys in the document
            // else we don't need to use table
            if(table != null) {
                if(table.contains(pair.key)) {
                    values.add(compiledValue);
                }
                else {
                    values.add(null);
                }
            }
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

    public JSONArray compile() {
        JSONArray complete = new JSONArray();

        for(int i = 0; i < valueList.size(); ++i) {
            JsonValue value = (JsonValue)valueList.get(i);
            Object result = value.compile();

            complete.add(result);
        }

        return complete;
    }
}

// **********************************************************************
// JsonValue
// **********************************************************************
abstract class JsonValue {
    protected String stringValue;

    public abstract Object compile();
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
        return obj.compile();
    }
}

class JsonArrayValue extends JsonValue {
    public JsonArray array;

    public JsonArrayValue(JsonArray array) {
        this.array = array;
    }

    public JSONArray compile() {
        //System.out.println(array.compile());
        return array.compile();
    }
}

