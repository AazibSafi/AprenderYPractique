package com.algorithms.aprenderypractique.VanHack.attempt1;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeltaDebugging extends BaseTest {

    @Test
    public void testDeltaDebug() throws IOException {
        String expected = "æ";
        String actual = minimize("java ", "");
        assertEquals(expected, actual);
    }

    public static String minimize(String command, String failingInputFilename)
            throws FileNotFoundException, IOException {

        List<String> lines = Arrays.asList("Up ↑ is the direction");
//        List<String> lines = Utilities.readFile(failingInputFilename);

        String minimizedVersion = "";

        for(String line : lines) {
            String encodedLine = encoded(line);
            if(encodedLine.contains("?")) {
                int ind = encodedLine.indexOf("?");
                minimizedVersion = String.valueOf(line.charAt(ind));
                break;
            }
        }

        return minimizedVersion;
    }

    public static String encoded(String text) {
        Charset charset = Charset.forName("US-ASCII");
        return new String(text.getBytes(charset),charset);
    }

}
