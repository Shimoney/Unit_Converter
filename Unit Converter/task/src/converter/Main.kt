package converter

enum class Units(val type: String, val unitList: List<String>, val convertFac: Double) {
    KM("D", listOf("km", "kilometer", "kilometers"), 1000.0),
    MET("D", listOf("m", "meter", "meters"), 1.0),
    CM("D", listOf("cm", "centimeter", "centimeters"), 0.01),
    MM("D", listOf("mm", "millimeter", "millimeters"), 0.001),
    MILE("D", listOf("mi", "mile", "miles"), 1609.35),
    YARD("D", listOf("yd", "yard", "yards"), 0.9144),
    FEET("D", listOf("ft", "foot", "feet"), 0.3048),
    INCH("D", listOf("in", "inch", "inches"), 0.0254),
    KG("W", listOf("kg", "kilogram", "kilograms"), 1000.0),
    GRAM("W", listOf("g", "gram", "grams"), 1.0),
    MG("W", listOf("mg", "milligram", "milligrams"), 0.001),
    LB("W", listOf("lb", "pound", "pounds"), 453.592),
    OZ("W", listOf("oz", "ounce", "ounces"), 28.3495),
    DC("T", listOf("dc", "degree Celsius", "degrees Celsius", "celsius", "c"), 1.0),
    DF("T", listOf("df", "degree Fahrenheit", "degrees Fahrenheit", "fahrenheit", "f"), 1.0),
    KE("T", listOf("k", "kelvin", "kelvins"), 1.0),
    NULL("", listOf(""), 0.0);

    companion object {
        fun getFactor(unit: String): Double {
            var factor = 0.0
            for (enum in values()) {
                if (unit in enum.unitList)
                    factor = enum.convertFac
            }
            return factor
        }

        fun getName(input: String, value: Double = 0.0): String {
            var name = "???"
            for (enum in values()) {
                if (input in enum.unitList && value == 1.0)
                    name = enum.unitList[1]
                else if (input in enum.unitList && value != 1.0)
                    name = enum.unitList[2]
            }
            return name
        }

        fun getType(input: String): String? {
            for (enum in values()) {
                if (input in enum.unitList) return enum.type
            }
            return null
        }
    }

}

fun getTargetValue(startValue: Double, startUnit: String, targetUnit: String): Double {
    val targetValue =
        if (startUnit in Units.DC.unitList && targetUnit in Units.DF.unitList) {
            (startValue * 9.0 / 5.0) + 32.0
        } else if (startUnit in Units.DC.unitList && targetUnit in Units.KE.unitList) {
            startValue + 273.15
        } else if (startUnit in Units.DF.unitList && targetUnit in Units.DC.unitList) {
            (startValue - 32.0) * 5.0 / 9.0
        } else if (startUnit in Units.KE.unitList && targetUnit in Units.DC.unitList) {
            startValue - 273.15
        } else if (startUnit in Units.DF.unitList && targetUnit in Units.KE.unitList) {
            (startValue + 459.67) * 5.0 / 9.0
        } else if (startUnit in Units.KE.unitList && targetUnit in Units.DF.unitList) {
            startValue * 9.0 / 5.0 - 459.67
        } else {
            Units.getFactor(startUnit) * startValue / Units.getFactor(targetUnit)
        }
    return targetValue
}

fun isNegative(unit: String, value: Double) {
    if (Units.getType(unit) == "W" && value < 0.0)
        println("Weight shouldn't be negative")
    else if (Units.getType(unit) == "L" && value < 0.0)
        println("Weight shouldn't be negative")
}


fun main() {
    while (true) {
        println("Enter what you want to convert (or exit):")
        val input = readLine()!!.split(" ").map { it.lowercase() }.toMutableList()
        if (input.contains("exit")) break
        val startValue = try {
            input[0].toDouble()
        } catch (e: NumberFormatException) {
            println("Parse error")
            continue
        }
        var startUnit = ""
        var targetUnit = ""
        if (input[1] == "degree" || input[1] == "degrees") {
            startUnit = input[2]
            targetUnit = if (input[4] == "degree" || input[4] == "degrees") {
                input[5]
            } else {
                input[4]
            }
        } else {
            startUnit = input[1]
            targetUnit = if (input[3] == "degree" || input[3] == "degrees") {
                input[4]
            } else {
                input[3]
            }
        }
        if (Units.getType(startUnit) == "W" && startValue < 0.0) {
            println("Weight shouldn't be negative")
            continue
        }
        if (Units.getType(startUnit) == "D" && startValue < 0.0) {
            println("Length shouldn't be negative")
            continue
        }
        val targetValue = getTargetValue(startValue as Double, startUnit, targetUnit)
        if (Units.getType(startUnit) == Units.getType(targetUnit) && Units.getType(startUnit) != null &&
            Units.getType(targetUnit) != null
        ) {
            println(
                "$startValue ${Units.getName(startUnit, startValue)} is $targetValue ${
                    Units.getName(
                        targetUnit,
                        targetValue
                    )
                }"
            )
        } else if (Units.getType(startUnit) != Units.getType(targetUnit) || Units.getType(startUnit) == null ||
            Units.getType(targetUnit) == null
        ) {
            println(
                "Conversion from ${
                    Units.getName(
                        startUnit
                    )
                } to ${Units.getName(targetUnit)} is impossible"
            )
        }
        println()
    }
}
