package utils.date

import kotlin.js.Date

class SoDate(date: Date) {

    val date: Date =
        Date(date.getTime() - date.getMilliseconds() - date.getSeconds() * 1000 - date.getMinutes() * 1000 * 60 - date.getHours() * 1000 * 60 * 60)

    companion object {
        fun now(): SoDate {
            return SoDate(Date())
        }
    }

    constructor(date: String) : this(Date(date))

    constructor(date: Number) : this(Date(date))

    val year: Int
        get() = this.date.getFullYear()

    val month: Int
        get() = this.date.getMonth() + 1

    val day: Int
        get() = this.date.getDate()

    val week: Week
        get() {
            return Week.values()[this.date.getDay()]
        }

    fun format(template: String = "yyyy-MM-dd"): String {
        val patterns = mapOf(
            "yyyy" to this.year.toString(),
            "MM" to this.month.toString().fill0(2),
            "M" to this.month.toString(),
            "WW" to this.week.toString(),
            "W" to this.week.toString(),
            "dd" to this.day.toString().fill0(2),
            "d" to this.day.toString(),
        )
        var result = template
        patterns.forEach {
            result = result.replace(Regex(it.key), it.value)
        }
        return result
    }


    fun toISOString(): String {
        return this.date.toISOString()
    }

    fun clone(): SoDate {
        return SoDate(this.date.getTime())
    }

    fun plusDay(day: Int): SoDate {
        return SoDate(this.date.getTime() + 24 * 60 * 60 * 1000 * day)
    }

    override fun toString(): String {
        return this.format()
    }

    fun toStartAtDay(): SoDateTime {
        return SoDateTime.of(this,SoTime.of())
    }

}

enum class Week {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
}