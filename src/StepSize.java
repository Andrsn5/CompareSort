import java.util.Iterator;

public class StepSize implements Iterable<Integer> {
    private int from, step, amount;

    public StepSize(int from, int step, int amount) throws IllegalArgumentException {
        if (from < 0 || step <= 0 || amount < 0)
            throw new IllegalArgumentException();
        this.from = from;
        this.step = step;
        this.amount = amount;
    }


    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int counter = 0;

            @Override
            public boolean hasNext() { return counter < amount; }

            @Override
            public Integer next() { return from + (counter++) * step; }
        };
    }
}