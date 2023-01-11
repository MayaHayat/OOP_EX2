package Part_2;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CustomFuture<T> extends FutureTask implements Comparable <CustomFuture> {
    private Task<T> myTask;

    public CustomFuture(Task<T> task) {
        super(task);
        this.myTask = task;
    }

    public CustomFuture(Callable<T> call) {
        super(call);
        this.myTask = (Task<T>) call;
    }

    public int getPriority(){
        return this.myTask.getPriority();
    }



    @Override
    public int compareTo(CustomFuture o) {
        if(this.myTask.getPriority() > o.myTask.getPriority()) return 1;
        else if(this.myTask.getPriority() < o.myTask.getPriority()) return -1;
        return 0;
    }
}
