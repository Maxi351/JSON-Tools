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
    public Node Beautify(Node userJson) throws JsonProcessingException{
        String result;
        String workingJson= userJson.get();
        if(workingJson==null) workingJson="{}";
        System.out.println("Your JSON: \n" + workingJson + "\n");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readValue(workingJson, JsonNode.class);
        result = jsonNode.toPrettyString();
        userJson.set(result);
        //String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
        System.out.println("Beautified JSON: \n" + result);
        return userJson;
    }

}
