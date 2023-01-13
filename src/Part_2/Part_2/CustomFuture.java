package Part_2;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CustomFuture<T> extends FutureTask implements Comparable <CustomFuture<T>> {
    private Task<T> myTask;


    /**
     * This constructor takes a Task object and calls the FutureTask and passing the task as callable.
     * Assigns Task object to myTask
     * @param task is to be wrapped by CustomFuture object
     */
    public CustomFuture(Task<T> task) {
        super(task);
        this.myTask = task;
    }

    /**
     * This constructor takes a Callable object and calls the FutureTask and passing the task.
     * Assigns Callable object to myTask
     * @param call is to be wrapped by CustomFuture object
     */
    public CustomFuture(Callable<T> call) {
        super(call);
        this.myTask = (Task<T>) call;
    }

    /**
     * @return the priority of myTask object
     */

    public int getPriority(){

        return this.myTask.getPriority();
    }


    /**
     * Overrides the original FutureTask comparable.
     * @param o the object to be compared.
     * @return
     */

    @Override
    public int compareTo(CustomFuture o) {
        if(this.myTask.getPriority() > o.myTask.getPriority()) return 1;
        else if(this.myTask.getPriority() < o.myTask.getPriority()) return -1;
        return 0;
    }
}
