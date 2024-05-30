import org.jfree.data.xy.XYSeries;

import java.util.*;

public class Experiment {
    private static Integer[] generate(int size) {
        Integer[] array = new Integer[size];
        Random r = new Random();
        for (int i = 0; i < size; i++){array[i]=r.nextInt(size)+1;}
        return array;
    }

    public static AllSortsInfo experiment(
            List<Sorting> sorts, DataGenerator gen,
            Iterable<Integer> sizes, InfoSmoothing smoothing) throws Exception {
        AllSortsInfo result = new AllSortsInfo(sorts);
        for (Integer i : sizes) {
            if (i < 0)
                throw new NegativeArraySizeException();
            Map<Sorting, List<SortInfo>> repeats = new HashMap<>();
            for (Sorting s : sorts)
                repeats.put(s, new LinkedList<>());
            for (int cnt = smoothing.getRepeatTime(); cnt > 0; cnt--) {
                Integer[] arr = gen.create(i);
                for (Sorting s : sorts)
                    repeats.get(s).add(s.sort(Arrays.copyOf(arr, arr.length)));
            }

            Map<Sorting, SortInfo> item = new HashMap<>();
            for (Map.Entry<Sorting, List<SortInfo>> kv : repeats.entrySet())
                item.put(kv.getKey(), smoothing.process(kv.getValue()));
            result.putData(i, item);
        }

        return result;
    }
    public static Integer[] cloneIntegerArray(Integer[] arr){
        Integer[] arr2 = new Integer[arr.length];
        for (int i =0 ; i< arr.length;i++){
            arr2[i]=arr[i];
        }
        return arr2;
    }

    public static void applyShakeChgLine(ExperimentInfo info, XYSeries series) throws CloneNotSupportedException {
        series.clone();
        int size = info.getSize();
        for (int i = 0; i < size; i++) {
            series.add(i, info.getShakerResult(i).getchangeCount());
        }
    }
    public static void applyBubbleChgLine(ExperimentInfo info, XYSeries series) throws CloneNotSupportedException {
        series.clone();
        int size = info.getSize();
        for (int i = 0; i < size; i++) {
            series.add(i, info.getBubbleResult(i).getchangeCount());
        }
    }

    public static void applyShakeCmpLine(ExperimentInfo info, XYSeries series) throws CloneNotSupportedException {
        series.clone();
        int size = info.getSize();
        for (int i = 0; i < size; i++) {
            series.add(i, info.getShakerResult(i).getCmpCount());
        }
    }
    public static void applyBubbleCmpLine(ExperimentInfo info, XYSeries series) throws CloneNotSupportedException {
        series.clone();
        int size = info.getSize();
        for (int i = 0; i < size; i++) {
            series.add(i, info.getBubbleResult(i).getCmpCount());
        }
    }}
