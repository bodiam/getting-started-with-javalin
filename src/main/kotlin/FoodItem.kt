// FoodItem.kt
data class FoodItem(val name: String, val calories: Int)

val foodItems = hashMapOf(
        0 to FoodItem("Pizza", 1000),
        1 to FoodItem("Soup", 10),
        2 to FoodItem("Apple", 50)
)