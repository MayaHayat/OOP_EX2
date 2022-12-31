# Second exercise OOP
## First part
In the first part of this project we had to create 4 different functions. The first function creates n different text files with the names "File_x.txt", each file contains a random number of lines, the number of lines in each line is created using a seed and a bound.

This class is used to compare 3 different methods to calculate the total number of lines in all files. Each function receives an array of strings where each cell contains a name of a different file.

The first function "getNumOfLines" goes file by file using the BufferedReader and calculate the number of lines in the first file, then second, etc.. until it finishes going through all files. Each time it adds the number of lines in each file to the totalLines counter which is returned as the final answer.

The second function "getNumOfLinesThreads" uses a different thread for each file, once the thread is created it starts and calculates the number of lines in each file (once again using BufferedRead until it has nothing to read meaning it reached the end of the file). Note that MyThread class used for this function extends the Thread class and we used the "run" function to calculate the number of lines in each file. Once all threads are terminated the function returns the total number of lines and the function is also being terminated.

The third and last function "getNumOfLinesThreadPool" once again receives the array of names of files created in the first function and calculates the total number of lines of all files using the same method as above. The difference between this function and "getNumOfLinesThread" is that this function uses the class MyThreadCallable which implements the Callable interface. In this class we use the call method to calculate the number of lines in each file. 
In the "getNumOfLinesThreadPool" function a thread pool is created with a fixed size (the length of our fileNames array), for each file whose name is found in the array a new MyThreadCollable is created and added to the thread pool.
After having calculated the number of lines in a specific file that number is being added to the 'totalLines' counter and when all thread are done the threads are shut down and the totalLines is returned as the answer.

### Comparing running times
We've been asked to compare all three methods, note we created a large number of files so we can see a clear difference. The calculation can be found in the "Main" class.
We created 50 files with seed number 2 and bound = 100. This created 50 files and the total number of lines in all files are 2418. 

Each time we run our main we get different numbers as the running times does not only depend on our function however it is noticeable that the second function is takes at least 0.01S less than the first function.
The third function's running time is better than the second one by also around 0.005S.
This change might not look too significant, however we've only 2418 lines, some companies/ project will have way more than 100 lines per file and will have to work with a larger number of files.

This order makes sense to us as threads are used to help multiple tasks take place at the same time, we also know that thread pool maintains all threads found in it and therefore increases performance.

