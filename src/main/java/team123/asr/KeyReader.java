package team123.asr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class KeyReader {
    public static String readKeyFromTextFile() {
        String txt = "";
        try {
            txt = new String(Files.readAllBytes(Paths.get("D:\\Uni\\2019\\Sem1\\Software project\\SubscriptionKey.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return txt;
    }
}
