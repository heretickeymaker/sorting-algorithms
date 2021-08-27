final random = new Random()

final supplier = () -> {
    final array = new List<Integer>[16]
    (0..15).each {
        array[it] = random.ints(1000, 20000, 50000).collect { it }
    }
    return array
}

print supplier()
