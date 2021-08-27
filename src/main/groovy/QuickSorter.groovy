import static QuickSorter.QSortTypes.*

class QuickSorter {

    private static quickSortSimple(int begin, int end, int ... array) {
        if (begin < end) {
            int pivot = partitionSimple(begin, end, array)
            quickSortSimple(begin, pivot - 1, array)
            quickSortSimple(pivot + 1, end, array)
        }
    }

    private static partitionSimple(int low, int high, int ... array) {
        final pivot = array[low]
        def P = low - 1, Q = high + 1
        while (P < Q) {
//            while (true) {
//                P = P + 1
//                if (P > high) break
//                if (array[P] <= pivot) break
//            }
//
//            while (true) {
//                Q = Q - 1
//                if (Q < low) {
//                    Q = low
//                    break
//                }
//
//                if (array[Q] > pivot) break
//            }
//
            do {
                if (++P > high)
                    break
            } while (array[P] <= pivot)

            do {
                if (--Q < low) {
                    Q = low
                    break
                }
            } while (array[Q] > pivot)

            if (P < Q)
                swap(P, Q, array)
        }

        swap(low, Q, array)
        return Q
    }

    private static void quickSortBest(int low, int high, int ... array) {
        if (low < high) {
            int pivot = partitionBest(low, high, array)
            quickSortBest(low, pivot - 1, array)
            quickSortBest(pivot + 1, high, array)
        }
    }

    private static int partitionBest(int begin, int end, int ... array) {
        int pivot = array[end]
        int P = begin - 1
        for (int Q = begin; Q < end; Q++) if (array[Q] <= pivot) swap(++P, Q, array)
        swap(P + 1, end, array)
        return P + 1
    }

    private static void quickSortMiddle(int low, int high, int ... array) {
        if (array == null || array.length == 0) return
        if (low >= high) return

        int middle = (int) (low + (high - low) / 2)
        int pivot = array[middle]

        int P = low
        int Q = high

        while (P <= Q) {
            while (array[P] < pivot) P++
            while (array[Q] > pivot) Q--
            if (P <= Q) {
                swap(P, Q, array)
                P++
                Q--
            }
        }

        if (low < Q) quickSortMiddle(low, Q, array)
        if (high > P) quickSortMiddle(P, high, array)
    }

    static void apply(int begin, int end, QSortTypes type, int ... array) {
        if (begin > end) throw new IndexOutOfBoundsException("begin is greater than end")
        switch (type) {
            case MIDDLE_PIVOTED: quickSortMiddle(begin, end, array); break
            case BEST_FIRST_PIVOTED: quickSortBest(begin, end, array); break
            case SIMPLE_FIRST_PIVOTED: quickSortSimple(begin, end, array)
        }
    }

    static void apply(QSortTypes type, int ... array) {
        apply(0, type, array)
    }

    static void apply(int begin, QSortTypes type, int ... array) {
        apply(begin, array.length - 1, type, array)
    }

    enum QSortTypes {
        SIMPLE_FIRST_PIVOTED,
        MIDDLE_PIVOTED,
        BEST_FIRST_PIVOTED,
    }

    private static swap(int index1, int index2, int ... items) {
        final temp = items[index1]
        items[index1] = items[index2]
        items[index2] = temp
    }
}
