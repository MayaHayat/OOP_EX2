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

    /**
     * A constructor that sets corePoolSize and MaximumPoolSize said in project.
     * The work queue is a priorityBlockingQueue.
     */
    public CustomExecutor() {

        super(availableProcessors/2 , availableProcessors-1,
                300 ,TimeUnit.MILLISECONDS,  new PriorityBlockingQueue<>());
    }

    /**
     * This function updates the priority queue so the max priority can be returned at O(1).
     * @param newPriority
     */

    public void updatePriorities(int newPriority){
        prioritiesCounter[newPriority]++;

    }

    /**
     * Submit a new task to the ThreadPool
     * @param task is the new task added to the pool
     * @return CustomFuture object which can be used to retrieve the result of the task
     * @param <T>
     */
    public <T>Future<T> submit(Task<T> task)
    {
        CustomFuture futureTaskAdapter = new CustomFuture(task);
        this.execute(futureTaskAdapter);
        return futureTaskAdapter;
    }

    /**
     * Submit a Callable object to the ThreadPool.
     * @param callable used to create the new task
     * @param type is also used to create the new task
     * @return submitted task into ThreadPool
     * @param <T>
     */

    public <T>Future<T> submit(Callable<T> callable, TaskType type) {
        // create a new future task to save the task and the future result
        Task<T> task = Task.createTask(callable, type);
        return this.submit(task);
    }

    /**
     * Submits task into ThreadPool, however it uses the default taskType which is 'OTHER' in our case.
     * @param callable the task to submit
     * @return
     * @param <T>
     */

    public <T>Future<T> submit(Callable<T> callable)
    {

        return this.submit(callable, TaskType.OTHER);
    }

    /**
     * Goes through array
     * @return the highest priority (which is the smallest number)
     */

    public int getCurrentMax() {
        for (int i = 0; i < 10; i++) {
            if (this.prioritiesCounter[i] != 0) {
                return i + 1;
            }
        }
        return 0;
    }

    /**
     * This method is used to do all "finishings" before the task is executed.
     * In our case the method first casts the passed in Runnable object to a CustomFuture object, so it can access the getPriority() method.
     * When a task is about to be executed, it is decremented from the counter, since that the task is no longer in the queue, therefore one less task with that priority remains.
     * @param thread the thread that will run task {@code r}
     * @param runnable the task that will be executed
     */

    @Override
    protected void beforeExecute(Thread thread, Runnable runnable) {
        CustomFuture myFuture = (CustomFuture) (runnable);
        prioritiesCounter[myFuture.getPriority()]--;
    }

    /**
     * This method is calling the shutdown from the ThreadPoolExecutor class to shut the thread pool down.
     */

    public void gracefullyTerminate() {
        super.shutdown();
    }




}
