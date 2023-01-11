package Part_2;

import java.util.concurrent.Callable;

public class Task<T> implements  Callable<T>, Comparable<Task<T>>{
    private final TaskType tType;
    private final Callable<T> operation;
    private  int priority;


    /**
     * Constractor to create a new Task given two parameters
     * @param operation
     * @param tType
     */

    protected Task(Callable operation, TaskType tType){
        this.operation = operation;
        this.tType = tType;
        this.priority = tType.getPriorityValue();
    }

    /**
     * Creates a task only from operation, note that we chose the default priority to be 0.
     * @param operation
     */


    protected Task(Callable operation){
        this.operation = operation;
        this.tType = TaskType.OTHER;
        this.priority = 3;
    }


    public TaskType getTaskType() {
        return tType;
    }

    /**
     * Using Factory with default task type
     * @param operation what operation should take place.
     * @return a new task
     */

    public static Task createTask(Callable operation){

        return new Task(operation, TaskType.OTHER);
    }


    /**
     * Factory method for creating a task
     * @param operation
     * @param taskType
     * @return a new task.
     */
    public static <T> Task<T> create(Callable<T> operation, TaskType taskType) {
        return new Task<>(operation, taskType);
    }

    /**
     * This gives the priority
     * @return
     */

    public int getPriority(){
        return this.priority;
    }

    /**
     * Creates the task "Safely".
     * @param operation what operation should take place.
     * @param tType of the task
     * @return a new task
     */

    public static Task createTask(Callable operation, TaskType tType){

        return new Task(operation, tType);
    }

    /**
     * This function checks if the operation can take place.
     * @return the operation's result
     * @throws Exception if the operation isn't successful
     */

    @Override
    public T call() throws Exception {
        return operation.call();
    }




    /**
     * The compareTo functions compares two tasks according to their priorities
     * @param o the object to be compared.
     * @return which one is smaller.
     */

    @Override
    public int compareTo(Task<T> o) {
        if (this.priority > o.priority) return 1;
        else if (this.priority < o.priority) return -1;
        return 0;
    }
}
