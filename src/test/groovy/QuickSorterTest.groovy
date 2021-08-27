import spock.lang.Specification

import static QuickSorter.QSortTypes.*

class QuickSorterTest extends Specification {
    private final random = new Random()
    private final size = 1000

    final supplier(int size) {
        final arrays = new int[size + 1][1000]
        (0..size).each {
            arrays[it] = random.ints(1000, 20000, 50000).toArray()
        }
        return arrays
    }

    private static final sorted(int[][] arrays, int size) {
        (0..size).forEach(index -> Arrays.sort(arrays[index]))
        return arrays
    }

    def "Simple Quick Sort Algorithm"() {
        given:
        final arrays = supplier(size)
        when:
        final sorted = sorted(arrays, size)
        (0..size).each { QuickSorter.apply(SIMPLE_FIRST_PIVOTED, arrays[it]) }
        then:
        (0..size).each { sorted[it] == arrays[it] }
    }

    def "Best Quick Sort Algorithm"() {
        given:
        final arrays = supplier(size)
        when:
        final sorted = sorted(arrays, size)
        (0..size).each { QuickSorter.apply(BEST_FIRST_PIVOTED, arrays[it]) }
        then:
        (0..size).each { sorted[it] == arrays[it] }
    }

    def "Middle Pivoted Quick Sort"() {
        given:
        final arrays = supplier(size)
        when:
        final sorted = sorted(arrays, size)
        (0..size).each { QuickSorter.apply(MIDDLE_PIVOTED, arrays[it]) }
        then:
        (0..size).each { sorted[it] == arrays[it] }
    }
}
