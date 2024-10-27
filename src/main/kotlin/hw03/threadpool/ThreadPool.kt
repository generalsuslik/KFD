package hw03.threadpool

import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.locks.ReentrantLock

class ThreadPool(threadCount: Int) : Executor {

    private val queue = LinkedList<Runnable>()
    private val threads = mutableListOf<Thread>()
    private val lock = ReentrantLock()
    private val condition = lock.newCondition()
    private var running = true

    init {
        repeat(threadCount) {
            val thread = Thread {
                while (true) {
                    val task: Runnable
                    lock.lock()
                    try {
                        while (queue.isEmpty() && running) {
                            condition.await()
                        }

                        if (queue.isEmpty() && !running) {
                            return@Thread
                        }

                        task = queue.removeFirst()
                    } finally {
                        lock.unlock()
                    }
                    try {
                        task.run()
                    } catch (_: InterruptedException) {}
                }
            }
            threads.add(thread)
            thread.start()
        }
    }

    override fun execute(command: Runnable) {
        lock.lock()
        try {
            if (!running) {
                throw RuntimeException("Thread pool is not running")
            }
            queue.add(command)
            condition.signal()
        } finally {
            lock.unlock()
        }
    }

    fun shutdown(wait: Boolean) {
        lock.lock()
        try {
            running = false
            condition.signalAll()
            if (!wait) {
                queue.clear()
                threads.forEach(Thread::interrupt)
            }
        } finally {
            lock.unlock()
        }

        if (wait) {
            threads.forEach(Thread::join)
        }
    }
}

