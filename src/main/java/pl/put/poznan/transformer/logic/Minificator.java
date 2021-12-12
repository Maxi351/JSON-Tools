package pl.put.poznan.transformer.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Minificator {

    public Minificator(){}

    public String minify(String userJson) throws JsonProcessingException{
        /** Minifies json
         * @param userJson JSON to minify
         * @return Minified JSON
         **/
        System.out.println("Your JSON: \n" + userJson + "\n");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readValue(userJson, JsonNode.class);
        System.out.println("Minified JSON: \n" + jsonNode.toString());
        return jsonNode.toString();
    }
}
