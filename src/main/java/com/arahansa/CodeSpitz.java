package com.arahansa;

import java.util.function.Consumer;
import java.util.function.Function;

public class CodeSpitz {

    public static void main(String[] args) {
        Function<Object, CIterator> aaa =  a -> new Context()
                .set("a", a)
                .set("b", null)
                .next(new Continuation(0, cont ->{
                    if(1!=1) cont.stop();
                    cont.resume(null, null);
                })).next(new Continuation(1, cont ->{
                    cont.set("a", ((Integer)cont.get("a"))+1);
                    cont.set("b", cont.get("a"));
                    cont.resume(cont.get("b"), 0);
                })).iterator();

        CIterator iterator = aaa.apply(0);
        CIterator.Result result;

        for(int i=0;i<10;i++){
            result = iterator.next();
            System.out.println("iter value : "+ result.getValue());
        }
    }
}
