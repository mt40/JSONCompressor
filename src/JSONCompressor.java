import java.io.*;
import java_cup.runtime.*;

public class JSONCompressor {
    public static void main(String[] args) throws IOException {
        // SimpleC file
        String fileSimpleC = "";
        if (args.length >= 1) {
            fileSimpleC = args[0];
        } else {
            System.err.println("usage: JSONCompressor <JSON_file>");
            System.exit(-1);
        }

        // Open input file
        FileReader reader = null;
        try {
            reader = new FileReader(fileSimpleC);
        } catch (FileNotFoundException ex) {
            System.err.println("File " + fileSimpleC + " not found!");
            System.exit(-1);
        }

        parser P = new parser(new Yylex(reader));

        Json program = null;
        try {
            program = (Json) P.parse().value;
        } catch (Exception ex) {
            System.err.println("Exception occured during parse: " + ex);
            System.exit(-1);
        }

        if (Errors.fatalError) {
            System.err.println("Confused by earlier errors: aborting");
            System.exit(0);
        }

        // Compile
        program.compile();

        // Close and save files
        reader.close();

        System.out.println("Finished!");
    }
}