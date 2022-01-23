package pl.put.poznan.transformer.logic;

//import static org.mockito.Mockito.*;
//import org.mockito.InOrder;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BeautifierTest {
    private TextBeautifier TB;

    @BeforeEach
    void setUp(){
        TB = new TextBeautifier();
    }

    @Test
    void testBeautifyValid() throws JsonProcessingException {
        String newJson = TB.Beautify("{\"Param1\":\"Text1\",\"Param2\":\"Tekst2\",\"Param3\":\"Tekst3\"}");
        assertEquals("{\r\n" +
                "  \"Param1\" : \"Text1\",\r\n" +
                "  \"Param2\" : \"Tekst2\",\r\n" +
                "  \"Param3\" : \"Tekst3\"\r\n" +
                "}", newJson);
    }

    @Test
    void testBeautifyAlreadyBeautified() throws JsonProcessingException {
        String newJson = TB.Beautify("{\r\n" +
                "  \"Param1\" : \"Text1\",\r\n" +
                "  \"Param2\" : \"Tekst2\",\r\n" +
                "  \"Param3\" : \"Tekst3\"\r\n" +
                "}");
        assertEquals("{\r\n" +
                "  \"Param1\" : \"Text1\",\r\n" +
                "  \"Param2\" : \"Tekst2\",\r\n" +
                "  \"Param3\" : \"Tekst3\"\r\n" +
                "}",newJson);
    }

    @Test
    void testBeautifyInvalid() {
    assertThrows(JsonProcessingException.class, ()->TB.Beautify("{\"Hello\": \"REST  }"));
    }

    @Test
    void testBeautifyEmpty() throws JsonProcessingException {
        assertEquals("{ }",TB.Beautify("{}"));
    }

    @Test
    void testBeautifyComplicated() throws JsonProcessingException {
    String newJson = TB.Beautify("{\"pi\": \"3.14159265359\", \"e\": \"2.7182818284\", \"prime\": [2, 3, 5, 7, 11, 13, 17, 19], \"1+6\": 7}");
    assertEquals("{\r\n" +
            "  \"pi\" : \"3.14159265359\",\r\n" +
            "  \"e\" : \"2.7182818284\",\r\n" +
            "  \"prime\" : [ 2, 3, 5, 7, 11, 13, 17, 19 ],\r\n" +
            "  \"1+6\" : 7\r\n" +
            "}",newJson);
    }

   /* @Test
    void testMockedString() throws  JsonProcessingException{
        TB  = mock(TextBeautifier.class);
        String newJson = TB.Beautify("{\"Param1\":\"Text1\",\"Param2\":\"Tekst2\",\"Param3\":\"Tekst3\"}");
        verify(TB).Beautify("{\"Param1\":\"Text1\",\"Param2\":\"Tekst2\",\"Param3\":\"Tekst3\"}");
        assertEquals("{\r\n" +
                "  \"Param1\" : \"Text1\",\r\n" +
                "  \"Param2\" : \"Tekst2\",\r\n" +
                "  \"Param3\" : \"Tekst3\"\r\n" +
                "}", newJson);
    }*/

}