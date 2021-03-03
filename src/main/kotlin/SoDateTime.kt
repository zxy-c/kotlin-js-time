package utils.date

import kotlin.js.Date

class SoDateTime(
    val date: Date
) {

    private val soDate = SoDate(date)
    private val soTime = SoTime(date)

    companion object {
        fun now(): SoDateTime {
            return SoDateTime(Date())
        }
    }

    constructor(date: String) : this(Date(date))
    constructor(date: Number) : this(Date(date))

    val year: Int = soDate.year
    val month: Int = soDate.month
    val day: Int = soDate.day
    val week: Week = soDate.week

    fun format(template: String = "yyyy-MM-dd HH:mm:ss"): String {
        return soTime.format(soDate.format(template))
    }

    fun toISOString(): String {
        return this.date.toISOString()
    }

    fun clone(): SoDate {
        return SoDate(this.date.getTime())
    }

    override fun toString(): String {
        return this.format()
    }

}