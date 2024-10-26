package hw4.test_util

import kotlin.random.Random
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

fun <T : Any> createRandomInstance(cls: KClass<T>): T? {
    val constructor = cls.primaryConstructor ?: return null
    val params = constructor.parameters.map { param ->
        val type = param.type.classifier
        when (type) {
            Int::class -> Random.nextInt()
            String::class -> Random.nextString()
            Double::class -> Random.nextDouble()
            Boolean::class -> Random.nextBoolean()
            else -> null
        }
    }

    return constructor.call(*params.toTypedArray())
}

fun Random.nextString(): String {
    val length = Random.nextInt(1, 100)
    val charPool = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..length)
        .map { charPool.random(this) }
        .joinToString("")
}
