import spock.lang.Specification

import static QuickSorter.QSortTypes.*

class QuickSorterTest extends Specification {
    final random = new Random()

    final supplier() {
        final arrays = new int[16][1000]
        (0..15).each {
            arrays[it] = random.ints(1000, 20000, 50000).toArray()
        }
        return arrays
    }

    private static final sorted(int[][] arrays) {
        (0..15).forEach(index -> Arrays.sort(arrays[index]))
        return arrays
    }

    def "Simple Quick Sort Algorithm"() {
        given:
        final arrays = supplier()
        println sorted(arrays)
        when:
        final sorted = sorted(arrays)
        (0..15).each { QuickSorter.apply(SIMPLE_FIRST_PIVOTED, arrays[it]) }
        then:
        (0..15).each { sorted[it] == arrays[it] }
    }

    def "Best Quick Sort Algorithm"() {
        given:
        final arrays = supplier()
        when:
        final sorted = sorted(arrays)
        (0..15).each { QuickSorter.apply(BEST_FIRST_PIVOTED, arrays[it]) }
        then:
        (0..15).each { sorted[it] == arrays[it] }
    }

    def "Middle Pivoted Quick Sort"() {
        given:
        final arrays = supplier()
        println sorted(arrays)
        when:
        final sorted = sorted(arrays)
        (0..15).each { QuickSorter.apply(MIDDLE_PIVOTED, arrays[it]) }
        then:
        (0..15).each { sorted[it] == arrays[it] }
    }
}
