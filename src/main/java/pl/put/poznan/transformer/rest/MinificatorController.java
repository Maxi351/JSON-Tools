package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.Minificator;

@RestController
@RequestMapping("/minify/{reqJson}")
public class MinificatorController {

    private static final Logger logger = LoggerFactory.getLogger(MinificatorController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get(@PathVariable String reqJson){
        //, @RequestParam(value="userJson", defaultValue="{     \"Hello\": \"world  \"}") String userJson
        // localhost:8080/minify/reqJson?userJson=tu jest userJson

        // log the parameters
        logger.debug(reqJson);
//        logger.debug(userJson);

        Minificator minificator = new Minificator();
        return minificator.minify(reqJson);
    }


    /*
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(@PathVariable String reqJson,
                       @RequestBody String userJson) {

        // log the parameters
        logger.debug(reqJson);
        logger.debug(userJson);

        Minificator minificator = new Minificator();
        return minificator.minify(userJson);
    }
    */
}
