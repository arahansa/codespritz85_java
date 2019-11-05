package com.arahansa;

import java.util.function.Consumer;
import java.util.function.Function;


public class Continuation {
    final static Continuation STOP = new Continuation();
    final static Continuation PASS = new Continuation();
    int key;
    Context context;
    Object value;
    Continuation next;
    Consumer<Continuation> block;

    private Continuation() {
    }

    public Continuation(int key, Consumer<Continuation> block) {
        this.key = key;
        this.block = block;
    }

    public void set(String key, Object value){
        this.context.set(key, value);
    }
    public Object get(String key){
        return this.context.get(key);
    }

    public void setContext(Context context) {
        this.context = context;
        context.setCont(this.key, this);
    }


    public void setNext(Continuation cont) {
        this.next = cont;
    }
    public Continuation getNext() {
        return this.next;
    }

    public Object getValue(){
        return value;
    }

    public boolean isStop(){
        return this.value == Continuation.STOP;
    }

    public boolean isPass(){
        return this.value == Continuation.PASS;
    }

    public void suspended(){
        this.value = Continuation.STOP;
        block.accept(this);
    }

    public void resume(Object v, Integer next){
        this.value =  v == null ? Continuation.PASS : v;
        if(next != null){
            this.next = this.context.getCont(next);
        }
    }

    public static void main(String[] args) {
    }

    public void stop() {
        this.value = STOP;
    }
}
