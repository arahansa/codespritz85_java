package com.arahansa;

public class CIterator {

    public static class Result{
        boolean done;
        Object value;

        public Result(boolean done) {
            this.done = done;
        }

        public Result(boolean done, Object value) {
            this.done = done;
            this.value = value;
        }

        public Object getValue() {
            return value;
        }

        public boolean isDone() {
            return done;
        }
        public void setDone(boolean done) {
            this.done = done;
        }
    }

    final static Result DONE = new Result(true);

    Continuation target;

    public CIterator(Continuation cont) {
        this.target = cont;
    }

    public Result next(){
        Continuation target = this.target;
        if(target == null)
            return DONE;
        target.suspended();
        if(target.isStop()) return CIterator.DONE;
        if(target.isPass()){
            this.target = target.next;
            return this.next();
        }else{
            Result result = new Result(false, target.value);
            this.target = target.getNext();
            return result;
        }
    }
}
