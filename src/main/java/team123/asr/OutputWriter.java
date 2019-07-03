package team123.asr;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class OutputWriter {
    public static void writeToTxtFile(String fileName, List<String> content) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fileName, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (int i=0; i<content.size(); i++) {
            writer.println(content.get(i));
        }
        writer.close();
    }
}
