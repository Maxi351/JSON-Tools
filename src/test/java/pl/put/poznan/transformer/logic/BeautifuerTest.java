package pl.put.poznan.transformer.logic;

import static org.mockito.Mockito.*;
import org.mockito.InOrder;

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
        Node newJson = new Node("{\"Param1\":\"Text1\",\"Param2\":\"Tekst2\",\"Param3\":\"Tekst3\"}");
        newJson = TB.Beautify(newJson);
        String result = newJson.get();
        assertEquals("{\r\n" +
                "  \"Param1\" : \"Text1\",\r\n" +
                "  \"Param2\" : \"Tekst2\",\r\n" +
                "  \"Param3\" : \"Tekst3\"\r\n" +
                "}", result);
    }

    @Test
    void testBeautifyAlreadyBeautified() throws JsonProcessingException {
        Node newJson = new Node("{\r\n" +
                "  \"Param1\" : \"Text1\",\r\n" +
                "  \"Param2\" : \"Tekst2\",\r\n" +
                "  \"Param3\" : \"Tekst3\"\r\n" +
                "}");
        newJson = TB.Beautify(newJson);
        String result = newJson.get();
        assertEquals("{\r\n" +
                "  \"Param1\" : \"Text1\",\r\n" +
                "  \"Param2\" : \"Tekst2\",\r\n" +
                "  \"Param3\" : \"Tekst3\"\r\n" +
                "}",result);
    }

    @Test
    void testBeautifyInvalid() {
    Node newJson = new Node("{\"Hello\": \"REST  }");
    assertThrows(JsonProcessingException.class, ()->TB.Beautify(newJson));
    }

    @Test
    void testBeautifyEmpty() throws JsonProcessingException {
        Node newJson = new Node("{}");
        newJson = TB.Beautify(newJson);
        String result = newJson.get();
        assertEquals("{ }",result);
    }

    @Test
    void testBeautifyComplicated() throws JsonProcessingException {
    Node newJson = new Node("{\"pi\": \"3.14159265359\", \"e\": \"2.7182818284\", \"prime\": [2, 3, 5, 7, 11, 13, 17, 19], \"1+6\": 7}");
    newJson = TB.Beautify(newJson);
    String result = newJson.get();
    assertEquals("{\r\n" +
            "  \"pi\" : \"3.14159265359\",\r\n" +
            "  \"e\" : \"2.7182818284\",\r\n" +
            "  \"prime\" : [ 2, 3, 5, 7, 11, 13, 17, 19 ],\r\n" +
            "  \"1+6\" : 7\r\n" +
            "}",result);
    }

    @Test
    void testMockedString() throws  JsonProcessingException{
        Node workingJson  = mock(Node.class);
        Node newJson = TB.Beautify(workingJson);
        verify(workingJson).get();
        verify(workingJson).set(any(String.class));
    }

}