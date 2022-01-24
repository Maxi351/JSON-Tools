package pl.put.poznan.transformer.logic;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Test;

import java.io.File;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class File_comparatorTest {

    @Test
    void test_Merge_strings_lines_almostEqual()
    {
        File_comparator myComp = new File_comparator();

        String text_1 = "1\n" +
                "2\n" +
                "3\n" +
                "j\n" +
                "4\n" +
                "5\n" +
                "6\n" +
                "\n" +
                "\n" +
                "fg\n" +
                "\n" +
                "hg";

        String text_2 = "1\n" +
                "2\n" +
                "3\n" +
                "4\n" +
                "5\n" +
                "6\n" +
                "\n" +
                "\n" +
                "fgfd\n" +
                "\n" +
                "hg";

        String output_valid = " :1\n" +
                " :2\n" +
                " :3\n" +
                "-:j\n" +
                " :4\n" +
                " :5\n" +
                " :6\n" +
                " :\n" +
                " :\n" +
                "+:fgfd\n" +
                "-:fg\n" +
                " :\n" +
                " :hg\n";

        String output = myComp.merge_strings_lines(text_1,text_2);


        assertEquals(output,output_valid);

    }

    @Test
    void test_Merge_strings_lines_fullDiffrent()
    {
        File_comparator myComp = new File_comparator();

        String text_1 = "1\n" +
                "2\n" +
                "3\n" +
                "4\n" +
                "5";

        String text_2 = "6\n" +
                "7\n" +
                "8\n" +
                "9\n" +
                "10";

        String output_valid = "+:6\n" +
                "+:7\n" +
                "+:8\n" +
                "+:9\n" +
                "+:10\n" +
                "-:1\n" +
                "-:2\n" +
                "-:3\n" +
                "-:4\n" +
                "-:5\n";

        String output = myComp.merge_strings_lines(text_1,text_2);

        assertEquals(output,output_valid);

    }

    @Test
    void test_Merge_strings_lines_oneEmpty()
    {
        File_comparator myComp = new File_comparator();

        String text_1 = "1\n" +
                "2\n" +
                "3\n" +
                "j\n" +
                "4\n" +
                "5\n" +
                "6\n" +
                "fg\n" +
                "hg\n";

        String text_2 = "";

        String output_valid = "+:\n" +
                "-:1\n" +
                "-:2\n" +
                "-:3\n" +
                "-:j\n" +
                "-:4\n" +
                "-:5\n" +
                "-:6\n" +
                "-:fg\n" +
                "-:hg\n";

        String output = myComp.merge_strings_lines(text_1,text_2);

        assertEquals(output,output_valid);
    }

    @Test
    void test_Merge_strings_lines_twoEmpty()
    {
        File_comparator myComp = new File_comparator();

        String text_1 = "";

        String text_2 = "";

        String output_valid = " :\n";

        String output = myComp.merge_strings_lines(text_1,text_2);

        assertEquals(output,output_valid);
    }




}