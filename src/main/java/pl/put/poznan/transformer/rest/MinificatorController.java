package pl.put.poznan.transformer.rest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.Minificator;

@RestController
@RequestMapping("/minify")
public class MinificatorController {

    private static final Logger logger = LoggerFactory.getLogger(MinificatorController.class);

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(@RequestBody String userJson) {
        System.out.println("POST");

        // log the parameters
        logger.debug(userJson);

        try{
            Minificator minificator = new Minificator();
            return minificator.minify(userJson);
        }
        catch (
            JsonProcessingException e){
            System.out.println(e.toString());
            return "Invalid JSON: " + userJson;
        }
    }
}
