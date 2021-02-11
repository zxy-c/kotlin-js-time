package utils.date

import kotlin.js.Date

class SoTime(val date: Date) {

    companion object {
        fun of(hours: Int = 0, minutes: Int = 0, seconds: Int = 0, milliseconds: Int = 0): SoTime {
            return SoTime(
                Date(
                    Date().getFullYear(),
                    Date().getMonth(),
                    Date().getDate(),
                    hours,
                    minutes,
                    seconds,
                    milliseconds
                )
            )
        }

        fun ofTimeString(timeString: String): SoTime {
            val now = SoDate.now()
            return SoTime(Date("${now.format("yyyy-MM-dd")}T$timeString"))
        }

        fun now(): SoTime {
            return SoTime(Date())
        }

    }

    constructor(date: String) : this(Date(date))
    constructor(date: Number) : this(Date(date))

    var hours: Int
        get() = this.date.getHours()
        set(value) {
            this.date.asDynamic().setHours(value)
        }

    val clock: Int
        get() = if (this.hours < 12) this.hours else this.hours - 12

    var minutes: Int
        get() = this.date.getMinutes()
        set(value) {
            this.date.asDynamic().setMinutes(value)
        }

    var seconds: Int
        get() = this.date.getSeconds()
        set(value) {
            this.date.asDynamic().setSeconds(value)
        }

    var milliseconds: Int
        get() = this.date.getMilliseconds()
        set(value) {
            this.date.asDynamic().setMilliseconds(value)
        }

    fun format(template: String = "HH:mm:ss"): String {
        val patterns = mapOf(
            "HH" to this.hours.toString().fill0(2),
            "H" to this.hours.toString(),
            "hh" to this.clock.toString().fill0(2),
            "h" to this.clock.toString(),
            "mm" to this.minutes.toString().fill0(2),
            "m" to this.minutes.toString(),
            "ss" to this.seconds.toString().fill0(2),
            "s" to this.seconds.toString(),
            "SSS" to this.milliseconds.toString().fill0(3),
            "SS" to this.milliseconds.toString().fill0(2),
            "S" to this.milliseconds.toString(),
        )
        var result = template
        patterns.forEach {
            result = result.replace(Regex(it.key), it.value)
        }
        return result
    }

    override fun toString(): String {
        return this.format()
    }

}