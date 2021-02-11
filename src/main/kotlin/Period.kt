package utils.date

import kotlin.math.absoluteValue

class Period(
    val milliseconds: Int
) {

    constructor(d1: SoDate, d2: SoDate) : this((d1.date.getTime() - d2.date.getTime()).toInt().absoluteValue)

    val seconds: Int
        get() = this.milliseconds / 1000
    val minutes: Int
        get() = this.seconds / 60
    val hours: Int
        get() = this.minutes / 60
    val day: Int
        get() = this.hours / 24
    val month: Int
        get() = this.day / 30
    val year: Int
        get() = this.month / 12

}