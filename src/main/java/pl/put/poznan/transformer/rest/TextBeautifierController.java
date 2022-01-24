package pl.put.poznan.transformer.rest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.TextBeautifier;

@RestController
public class TextBeautifierController {

    private static final Logger logger = LoggerFactory.getLogger(TextBeautifierController.class);

    @GetMapping("/beautify")
    public Object post(@RequestParam(value = "userJson", defaultValue = "{\"name\":\"mkyong\",\"age\":38,\"skills\":[\"java\",\"python\",\"node\",\"kotlin\"]}") String userJson) {
        System.out.println("POST");

        // log the parameters
        logger.debug(userJson);

        try{
            TextBeautifier TB = new TextBeautifier();
            return TB.Beautify(userJson);
        }
        catch (
                JsonProcessingException e){
            e.printStackTrace();
            return "Invalid JSON: " + userJson;
        }
    }
}

