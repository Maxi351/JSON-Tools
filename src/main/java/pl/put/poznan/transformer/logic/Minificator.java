package pl.put.poznan.transformer.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Minificator {

    public Minificator(){};

    public String minify(String userJson){
        /**
         * @param userJson JSON to minify
         * @return Minified JSON
        * */
        System.out.println("Your JSON: \n" + userJson + "\n");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readValue(userJson, JsonNode.class);
            System.out.println("Minified JSON: \n" + jsonNode.toString());
            return jsonNode.toString();
        }
        catch (JsonProcessingException e){
            System.out.println(e.toString());
        }
        return userJson;


    }

    public static void main(String[] args){
        String sampleJson = "{\n"+
                " \"fact1\": \"Java is verbose.\", \n" +
                " \"fact2\" : \"C has pointers\" \n" +
                " }";
        Minificator minificator = new Minificator();
        minificator.minify(sampleJson);
    }
}
