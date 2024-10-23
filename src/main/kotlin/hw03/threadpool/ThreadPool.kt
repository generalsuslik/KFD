package hw03.threadpool

import hw03.exception.UnableToExecuteException
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
                lock.lock()
                try {
                    while (queue.isEmpty()) {
                        if (!running) {
                            return@Thread
                        }
                        condition.await()
                    }
                    val task = queue.removeFirst()
                    task.run()
                } finally {
                    lock.unlock()
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
                throw UnableToExecuteException("Thread pool is not running")
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
        } finally {
            lock.unlock()
        }

        if (wait) {
            threads.forEach { it.join() }
        } else {
            threads.forEach { it.interrupt() }
        }
        queue.clear()
    }
}

