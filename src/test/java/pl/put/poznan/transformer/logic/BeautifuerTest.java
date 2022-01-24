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
        assertEquals("{\n" +
                "  \"Param1\" : \"Text1\",\n" +
                "  \"Param2\" : \"Tekst2\",\n" +
                "  \"Param3\" : \"Tekst3\"\n" +
                "}", result);
    }

    @Test
    void testBeautifyAlreadyBeautified() throws JsonProcessingException {
        Node newJson = new Node("{\n" +
                "  \"Param1\" : \"Text1\",\n" +
                "  \"Param2\" : \"Tekst2\",\n" +
                "  \"Param3\" : \"Tekst3\"\n" +
                "}");
        newJson = TB.Beautify(newJson);
        String result = newJson.get();
        assertEquals("{\n" +
                "  \"Param1\" : \"Text1\",\n" +
                "  \"Param2\" : \"Tekst2\",\n" +
                "  \"Param3\" : \"Tekst3\"\n" +
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
    assertEquals("{\n" +
            "  \"pi\" : \"3.14159265359\",\n" +
            "  \"e\" : \"2.7182818284\",\n" +
            "  \"prime\" : [ 2, 3, 5, 7, 11, 13, 17, 19 ],\n" +
            "  \"1+6\" : 7\n" +
            "}",result);
    }

    @Test
    void testMockedString() throws  JsonProcessingException{
        Node workingJson  = mock(Node.class);
        when(workingJson.get()).thenReturn("{\"Param1\":\"Text1\",\"Param2\":\"Tekst2\",\"Param3\":\"Tekst3\"}");
        Node newJson = TB.Beautify(workingJson);
        verify(workingJson).get();
        verify(workingJson).set(any(String.class));
    }

}