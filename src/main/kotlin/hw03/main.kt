package hw03

import hw03.runnable_task.Task
import hw03.threadpool.ThreadPool

fun main() {
    val threadPool = ThreadPool(3)
    val task1 = Task(1, 10)
    val task2 = Task(2, 1000)
    val task3 = Task(3, 1000)
    val task4 = Task(4, 1000)

    threadPool.execute(task1)
    threadPool.execute(task2)
    threadPool.execute(task3)
    threadPool.execute(task4)

    threadPool.shutdown(false)
}