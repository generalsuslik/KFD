package hw03.runnable_task

class Task(private val num: Int, private val sleep: Long) : Runnable {
    override fun run() {
        Thread.sleep(sleep)
        println("Task $num")
    }
}
