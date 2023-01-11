package Part_2;

import java.util.concurrent.*;

public class CustomExecutor extends ThreadPoolExecutor {
    //Using a blocking queue so threads cannot leave queue if it's empty and waits until element is available
    //Has to be Runnable for threadPoolExecuter
    private PriorityBlockingQueue <Runnable> queue;

    private static final int availableProcessors = Runtime.getRuntime().availableProcessors();
    int maxNumThreads = availableProcessors -1;
    int minNumThreads = availableProcessors /2;
    private long keepAlive = 300;
    private  int maxPriority;

    private int [] prioritiesCounter = new int [10];


    public CustomExecutor() {

        super(availableProcessors/2 , availableProcessors-1,
                300 ,TimeUnit.MILLISECONDS,  new PriorityBlockingQueue<>());
    }


    public void updatePriorities(int newPriority){
        prioritiesCounter[newPriority]++;

    }

    public <T>Future<T> submit(Task<T> task)
    {
        CustomFuture futureTaskAdapter = new CustomFuture(task);
        this.execute(futureTaskAdapter);
        return futureTaskAdapter;
    }


    public <T>Future<T> submit(Callable<T> callable, TaskType type) {
        // create a new future task to save the task and the future result
        Task<T> task = Task.createTask(callable, type);
        return this.submit(task);
    }


    public <T>Future<T> submit(Callable<T> callable)
    {
        return this.submit(callable, TaskType.OTHER);
    }


    public int getCurrentMax() {
        for (int i = 0; i < 10; i++) {
            if (this.prioritiesCounter[i] != 0) {
                return i + 1;
            }
        }
        return 0;
    }


    @Override
    protected void beforeExecute(Thread thread, Runnable runnable) {
        CustomFuture myFuture = (CustomFuture) (runnable);
        prioritiesCounter[myFuture.getPriority()]--;
    }



    public void gracefullyTerminate() {
        super.shutdown();
    }




}
