package utils.date

fun String.fill0(length: Int): String {
    return this.padStart(length, '0')
}