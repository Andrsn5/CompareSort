import java.util.List;

public abstract class AvgInfoSmoothing implements InfoSmoothing {
    private int times;

    public AvgInfoSmoothing(int times) { this.times = times; }


    public int getRepeatTimes() { return times; }


    public SortInfo process(List<SortInfo> data) {
        int n = data.size();

        if (n == 0)
            return null;

        long t = 0;
        int chg = 0, cmp = 0;

        for (SortInfo inf : data) {
            t += inf.getTimeMs();
            chg += inf.getchangeCount();
            cmp += inf.getCmpCount();
        }

        return new SortInfo(cmp / n, chg / n, t / n);
    }
}