package pl.put.poznan.transformer.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinificatorTest {
    private Minificator minificator;

    @BeforeEach
    void setUp(){
        minificator = new Minificator();
    }

    @Test
    void testMinifyValid() throws JsonProcessingException {
        String newJson = minificator.minify("{\"Hello\": \"REST\"  }");
        assertEquals("{\"Hello\":\"REST\"}", newJson);
    }
    
    @Test
    void testMinifyValidMultipleLines() throws JsonProcessingException {
        String newJson = minificator.minify("{\n" +
                "\n" +
                "  \"Param1\"   : \"Text1\",\n" +
                "        \"Param2\":         \"Tekst2\",\n" +
                "\"Param3\"    :    \"Tekst3\"  \n" +
                "        }");
        assertEquals("{\"Param1\":\"Text1\",\"Param2\":\"Tekst2\",\"Param3\":\"Tekst3\"}", newJson);
    }

    @Test
    void testMinifyInvalid() {
        assertThrows(JsonProcessingException.class, ()->minificator.minify("{\"Hello\": \"REST  }"));
    }

}