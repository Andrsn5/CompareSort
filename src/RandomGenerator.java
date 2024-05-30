import java.util.Random;


public class RandomGenerator implements DataGenerator {
    private static Random rnd = new Random();
    private int from, len;

    public RandomGenerator(int from, int to) {
        this.from = Math.min(from, to);
        this.len = Math.abs(to - from) + 1;
    }


    @Override
    public Integer[] create(int size) {
        Integer[] data = new Integer[size];
        for (int i = 0; i < data.length; i++) {
            data[i] = from + rnd.nextInt(len);
        }
        return data;
    }
    public class OrderedGenerator implements DataGenerator {
        @Override
        public Integer[] create(int size) {
            Integer[] data = new Integer[size];
            for (int i = 0; i < data.length; i++) {
                data[i] = i;
            }

            return data;
        }
    }
    public class ReverseOrderedGenerator implements DataGenerator {
        @Override
        public Integer[] create(int size) {
            Integer[] data = new Integer[size];
            for (int i = 0; i < data.length; i++) {
                data[i] = data.length - i;
            }

            return data;
        }
    }
}

