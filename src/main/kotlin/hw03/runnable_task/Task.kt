package hw03.runnable_task

import java.lang.Thread.currentThread

class Task(private val num: Int, private val sleep: Long) : Runnable {
    override fun run() {
        Thread.sleep(sleep)
        println("${currentThread()}, Task: $num")
    }
}
