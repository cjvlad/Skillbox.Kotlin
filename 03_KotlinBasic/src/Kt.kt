package com.skillbox.kotlin39
import java.lang.Math.abs

fun main() {
    println("Введите любое положительно число: ") // Пункт 1. Ввести число N с клавиатуры.
    while (true) {
        val writeList = readLine()?.toIntOrNull() // Пункт 2. После этого ввести следующие N чисел каждое с новой строки
        if (writeList !is Int || writeList < 0) {
            println("Вы не ввели число или ваше число отрицательное. Повторите попытку")
            continue
        } else {
            val list = intList(writeList) // Пункт 3. Сохранить числа в список.
            println("Вы ввели список чисел: $list")
            println(
                "Количество натуральных чисел в массиве: ${positiveNumbers(
                    list
                )}"
            )
            println("Четные числа в массиве: ${evenNumbers(list)}")
            println("Количество уникальных чисел в массиве: ${uniqueNumbers(list)}") // Пункт 7. Вывод количества уникальных чисел через Set
            println("Сумма чисел в массиве: " + list.sum())

            val uniq = list.toSet();
            val mapAndNod = mutableMapOf<Int, Int>() // Пункт 10. Создать отображение число - НОД  (Использовать map)
            list.forEach {
                val temp = CalculateNod(
                    it,
                    uniq.sum()
                ) // Пункт 8. Ввести следующие N чисел каждое с новой строки
                mapAndNod[it] = temp
            }
            mapAndNod.forEach { (key, value) -> println("Число $key, сумма ${uniq.sum()}, НОД $value") } // Пункт 11. Вывести все числа с НОД в формате
            break
        }
    }
}

fun intList(items: Int): MutableList<Int> {
    val list: MutableList<Int> = mutableListOf()
    for (item in 1..items) { // Пункт 3. Сохранить числа в список.
        while (true) {
            println("Введите $item число:")
            val number = readLine()?.toIntOrNull()
            if (number !is Int || number < 0) {
                println("Вы не ввели число. Повторите попытку")
                continue
            } else list.add(number)
            break
        }
    }
    return list // Пункт 4. Возвращаем список из N чисел
}
fun positiveNumbers(list: List<Int>) = list.filter { it > 0 }.size // Пункт 5. Количество введенных положительных чисел
fun evenNumbers(list: List<Int>) = list.filter { it % 2 == 0 }.size // Пункт 6. Кол-во четных чисел в массиве
fun uniqueNumbers(list: List<Int>): Int = list.toSet().size // Пункт 7. Уникальные введенные числа

tailrec fun CalculateNod(a: Int = 0, b:Int): Int {  // Пункт 9. Наибольший общий делитель (НОД)
    return if (b == 0) {
        abs(a)
    } else {
        CalculateNod(b, a % b)
    }
}