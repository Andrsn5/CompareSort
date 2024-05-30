import java.util.*;
import javafx.util.Pair;


public class AllSortsInfo {
    private Map<Sorting, List<SortInfo>> data = new HashMap<>();
    private List<Integer> sizes = new ArrayList<>();

    public AllSortsInfo(List<Sorting> all) {
        for (Sorting s : all)
            data.put(s, new ArrayList<>());
    }

    public void putData(Integer size, Map<Sorting, SortInfo> item) {
        sizes.add(size);
        for (Sorting key : data.keySet()) {
            data.get(key).add(item.getOrDefault(key, null));
        }
    }
    public Iterable<Sorting> getSorts() {
        return data.keySet();
    }

    public Iterable<Pair<Number, Number>> getDataSequence(Sorting sorting, SortInfoConverter converter) {
        Iterator<Integer> sizeIterator = sizes.iterator();
        Iterator<SortInfo> infoIterator = data.getOrDefault(sorting, new LinkedList<>()).iterator();

        class IteratorOverData implements Iterator<Pair<Number, Number>> {

            public boolean hasNext() {
                return sizeIterator.hasNext() && infoIterator.hasNext();
            }


            public Pair<Number, Number> next() {
                return new Pair<>(sizeIterator.next(), converter.convert(infoIterator.next()));
            }
        }

        return new Iterable<Pair<Number, Number>>() {
            public Iterator<Pair<Number, Number>> iterator() {
                return new IteratorOverData();
            }
        };
    }
}