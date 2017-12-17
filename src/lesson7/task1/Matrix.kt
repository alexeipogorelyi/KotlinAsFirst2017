@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson7.task1

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E

    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)

    operator fun set(cell: Cell, value: E)
}

/**
 * Простая
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> {
    if ((width <= 0) || (height <= 0))
        throw IllegalArgumentException()
    else
        return MatrixImpl(height, width, e)
}

/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val height: Int, override val width: Int, e: E) : Matrix<E> {

    private val list = mutableListOf<E>()

    init {
        for (i in 0..width * height) {
            list.add(e)
        }
    }

    override fun get(row: Int, column: Int): E = list[height * column + row]

    override fun get(cell: Cell): E = list[height * cell.column + cell.row]

    override fun set(row: Int, column: Int, value: E) {
        list[height * column + row] = value
    }

    override fun set(cell: Cell, value: E) {
        list[height * cell.column + cell.row] = value
    }

    override fun equals(other: Any?): Boolean =
            other is MatrixImpl<*> &&
                    width == other.width &&
                    height == other.height

    override fun toString(): String {
        val res = StringBuilder()
        for (row in 0 until height) {
            res.append("{")
            for (column in 0 until width) {
                res.append("<", this[row, column], ">")
            }
            res.append("}", "\n")
        }
        return "$res"
    }
}
