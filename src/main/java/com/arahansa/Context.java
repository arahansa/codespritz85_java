package com.arahansa;

import java.util.HashMap;
import java.util.Map;

public class Context {

    Map<String, Object> lexicalContext = new HashMap<>();
    Map<Integer, Continuation> contTable = new HashMap<>();
    Continuation start = null;
    Continuation end = null;

    public Context set(String key, Object value){
        lexicalContext.put(key, value);
        return this;
    }

    public Object get(String key){
        return lexicalContext.get(key);
    }

    public void setCont(int key, Continuation cont) {
        contTable.put(key, cont);
    }
    public Continuation getCont(int key){
        return contTable.get(key);
    }

    public Context next(Continuation cont){
        if(this.start == null){
            this.start = cont;
            this.end = cont;
        }else{
            this.end.setNext(cont);
            this.end = cont;
        }
        cont.setContext(this);
        return this;
    }

    public CIterator iterator(){
        return new CIterator(this.start);
    }
}
