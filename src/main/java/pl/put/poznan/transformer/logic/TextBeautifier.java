package pl.put.poznan.transformer.logic;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TextBeautifier {

    public TextBeautifier(){}


    /** Beautifies json
     * @param userJson JSON to beautify
     * @return Beautified JSON
     * @throws JsonProcessingException If given Json is wrong
     **/
    public String Beautify(String userJson) throws JsonProcessingException{
        System.out.println("Your JSON: \n" + userJson + "\n");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readValue(userJson, JsonNode.class);
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
        System.out.println("Beautified JSON: \n" + json);
        return json;
    }

}
