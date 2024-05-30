import javafx.util.Pair;
import org.jfree.data.xy.XYSeries;

public class XYSeriesWrapper {
    public interface SeriesDataProvider {
        Iterable<Pair<Number, Number>> getData();
    }

    private SeriesDataProvider provider;
    private XYSeries series;

    public XYSeriesWrapper(String title, SeriesDataProvider provider) {
        this.provider = provider;
        series = new XYSeries(title);
    }

    public XYSeries getSeries() {
        return series;
    }

    void fill() {
        series.setNotify(false);
        series.clear();
        for (Pair<Number, Number> pair : provider.getData())
            series.add(pair.getKey(), pair.getValue());
        series.setNotify(true);
        series.fireSeriesChanged();
    }
}