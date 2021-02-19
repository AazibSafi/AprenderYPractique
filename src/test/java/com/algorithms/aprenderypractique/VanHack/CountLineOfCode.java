package com.algorithms.aprenderypractique.VanHack;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CountLineOfCode extends BaseTest {

    @Test
    public void test() {
        String code = "/*\n" +
                " /****//*\n" +
                " A hello world program\n" +
                " *\\/\n" +
                "*/// -----------------\n" +
                "class Example5 {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out./*  */println(\"/*\\\"Hello world\")\n" +
                "        ;\n" +
                "///*\n" +
                "    }\n" +
                "    /* // */ }";

        int lines = count(code);
        System.out.println(lines);
        Assert.assertEquals(6,lines);
    }

    public static int count(String text) {
        List<String> lines = getLines(text);
        int count=0;
        for(int i=0; i<lines.size(); i++) {
            if(isEmpty(lines.get(i)) || isSingleLineComment(lines.get(i))) {
                continue;
            }
            else if( lines.get(i).trim().contains("/*") ) {
                if (lines.get(i).trim().contains("\"")) {
                    int qoutation = lines.get(i).trim().indexOf("\"");
                    int commentStart = lines.get(i).trim().indexOf("/*");
                    if (qoutation < commentStart) {
                        count++;
                    }
                    continue;
                }
                else if (lines.get(i).trim().contains("*/")) {
                    if(!lines.get(i).trim().startsWith("/*") || !lines.get(i).trim().endsWith("*/")) {
                        count++;
                    }
                    continue;
                }

                if (!lines.get(i).trim().startsWith("/*")) {
                    count++;
                }

                do {
                    i++;
                } while (i < lines.size() && !(lines.get(i).trim().contains("*/")));

                if (i < lines.size() && !lines.get(i).trim().endsWith("*/") && lines.get(i).trim().contains("/*")) {
                    int commentStart = lines.get(i).trim().lastIndexOf("/*");
                    int commentEnd = lines.get(i).trim().lastIndexOf("*/");
                    if (commentEnd < commentStart) {
                        do {
                            i++;
                        } while (i < lines.size() && !(lines.get(i).trim().contains("*/")));
                    }
                }
                if (i < lines.size() && !lines.get(i).trim().endsWith("*/")) {
                    count++;
                }

            }
            else {
                count++;
            }
        }
        return count;
    }

    private static boolean isEmpty(String line) {
        return line.trim().isEmpty();
    }

    private static boolean isSingleLineComment(String line) {
        return line.trim().startsWith("//");
    }

    public static List getLines(String text) {
        String separator = System.getProperty("line.separator");
        String[] items = text.split(separator);
        return Arrays.asList(items);
    }

}
