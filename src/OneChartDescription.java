import java.util.List;

public abstract class OneChartDescription {
    private AllSortsInfo info;
    private String title;
    private List<XYSeriesWrapper> wrappers = null;

    public OneChartDescription(AllSortsInfo info, String title) {
        this.info = info;
        this.title = title;
    }

    protected abstract List<XYSeriesWrapper> getSeriesWrappers(AllSortsInfo info);

    public List<XYSeriesWrapper> getWrappers() {
        if (wrappers == null)
            wrappers = getSeriesWrappers(info);
        return wrappers;
    }

    public String getTitle() {
        return title;
    }

    public void fillData() {
        for (XYSeriesWrapper w : getWrappers())
            w.fill();
    }

}