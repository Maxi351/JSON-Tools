package pl.put.poznan.transformer.logic;

import org.apache.catalina.User;

public class Node {

    private String UserJson;

    public Node(){
        UserJson="";
    }

    public Node (String s){
        UserJson = s;
    }

    public void set(String s){
        UserJson = s;
    }

    public String get(){
        return UserJson;
    }



}
