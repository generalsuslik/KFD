package hw4

import hw4.test_util.createRandomInstance

class InnerObj(val integer: Int, val double: Double, val string: String, val boolean: Boolean)

class Obj(val integer: Int, val double: Double, val string: String, val boolean: Boolean, val innerObj: InnerObj?)

fun main() {
    val obj: Obj? = createRandomInstance(Obj::class)
    if (obj != null) {
        println(obj.integer)
        println(obj.double)
        println(obj.string)
        println(obj.boolean)
        println(obj.innerObj)
    }
}

