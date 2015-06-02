import java.io.*;
import java_cup.runtime.*;

public class JSONCompressor {
    public static void main(String[] args) throws IOException {
        // SimpleC file
        String fileJson = "";
        if (args.length >= 2) {
            fileJson = args[0];
        } else {
            System.err.println("usage: JSONCompressor <Input_file> <Output_file>");
            System.exit(-1);
        }

        // Open input file
        FileReader reader = null;
        try {
            reader = new FileReader(fileJson);
        } catch (FileNotFoundException ex) {
            System.err.println("File " + fileJson + " not found!");
            System.exit(-1);
        }

        parser P = new parser(new Yylex(reader));

        Json program = null;
        try {
            program = (Json) P.parse().value;
            program.outputFileName = args[1];
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