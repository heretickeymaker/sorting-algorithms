import spock.lang.Specification

import static QuickSorter.QSortTypes.BEST_FIRST_PIVOTED
import static QuickSorter.QSortTypes.MIDDLE_PIVOTED
import static QuickSorter.QSortTypes.SIMPLE_FIRST_PIVOTED
import static java.util.Comparator.reverseOrder

class QuickSorterTest extends Specification {
    final random = new Random()

    def "Simple Quick Sort Algorithm"() {
        given:
        final list = random.ints(100, 100, 800).toArray()
        when:
        final sorted = list.toList().stream().sorted(reverseOrder()).toList()
        QuickSorter.apply(SIMPLE_FIRST_PIVOTED, list)
        then:
        list.toList() == sorted
    }

    def "Best Quick Sort Algorithm"() {
        given:
        final list = random.ints(100, 100, 800).toArray()
        when:
        final sorted = list.toList().stream().sorted().toList()
        QuickSorter.apply(BEST_FIRST_PIVOTED, list)
        then:
        list.toList() == sorted
    }

    def "Middle Pivoted Quick Sort"() {
        given:
        final list = random.ints(100, 100, 800).toArray()
        when:
        final sorted = list.toList().stream().sorted().toList()
        QuickSorter.apply(MIDDLE_PIVOTED, list)
        then:
        list.toList() == sorted
    }
}
