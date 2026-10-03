data class Expense(
    val name: String?, val amount: Double?, val category: String?
)

fun calculateCategorySums(expenses: List<Expense>): Map<String, Double> {
    val categorySums = mutableMapOf<String, Double>()

    for (item in expenses) {
        val finalAmount = item.amount ?: 0.0
        
        if (finalAmount < 0.0) {
            println("Обнаружена отрицательная сумма (${finalAmount}) для расхода '${item.name}'. Расход пропущен!")
            continue
        }
        
        if (finalAmount == 0.0) continue

        val finalCategory = item.category ?: "other"

        val currentSum = categorySums[finalCategory] ?: 0.0
        categorySums[finalCategory] = currentSum + finalAmount
    }

    return categorySums
}

fun findMostExpensiveCategory(categorySums: Map<String, Double>): String? {
    var mostExpensiveCategory: String? = null
    var maxCategorySum = 0.0

    for (entry in categorySums) {
        if (entry.value > maxCategorySum) {
            maxCategorySum = entry.value
            mostExpensiveCategory = entry.key
        }
    }
    
    return mostExpensiveCategory
}

fun runTest(testName: String, expenses: List<Expense>) {
    println("\n=== ЗАПУСК ТЕСТА: $testName ===")
    
    val sums = calculateCategorySums(expenses)
    
    println("Итоги по категориям:")
    if (sums.isEmpty()) {
        println("Пусто (Итоги нулевые)")
    } else {
        for (entry in sums) {
            println("${entry.key}: ${entry.value}")
        }
    }

    val mostExpensive = findMostExpensiveCategory(sums)
    if (mostExpensive != null) {
    println("Самая дорогая категория: $mostExpensive (Сумма: ${sums[mostExpensive]})")
    } else {
    println("Самая дорогая категория: Нет категорий с расходами больше 0.")
    }
}

fun main() {
    val expenseList = mutableListOf<Expense>()
    expenseList.add(Expense("коммунальные услуги", 12000.0, "fixed payment"))
    expenseList.add(Expense("бензин", 5000.0, "transport"))
    expenseList.add(Expense("такси", 2400.0, "transport"))
    expenseList.add(Expense("продукты", 20000.0, "food"))
    expenseList.add(Expense("фастфуд", 3000.0, "food"))
    expenseList.add(Expense("абонемент в спортзал", 0.0, "sport"))
    expenseList.add(Expense("абонемент в спортзал", 4000.0, "sport"))
    expenseList.add(Expense(null, 1150.0, null)) 
    expenseList.add(Expense("билет", -500.0, null))

    println("Входные данные:")
    for (e in expenseList) {
        println("${e.name}: ${e.amount} - ${e.category}")
    }

    runTest("Основной список расходов", expenseList)

    val emptyList = emptyList<Expense>()
    runTest("Пустой список", emptyList)
}