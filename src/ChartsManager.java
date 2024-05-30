import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;

public class ChartsManager {
    private JFreeChart chart;
    public JFreeChart getChart() { return chart; }
    private XYSeriesCollection series = new XYSeriesCollection();
    public ChartsManager() {
        chart = ChartFactory.createXYLineChart(
                "CpasHenme copTuposok", "Pasmep maccusa", "3wauenme",
                series
        );
        XYPlot plot = chart.getXYPlot();
        var renderer = new XYLineAndShapeRenderer();
        renderer.setDefaultStroke(new BasicStroke(0.1f));
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinesVisible(true);
        plot.setDomainGridlinesVisible(false);
    }
    public void apply(OneChartDescription description) {
        chart.setTitle(description.getTitle());
        series.removeAllSeries();
        for (XYSeriesWrapper wrapper : description.getWrappers()) {
            XYSeries s = wrapper.getSeries();
            int counter = 1;
            String baseKey = s.getKey().toString();
            String key = baseKey;
            while (series.getSeriesIndex(key) != -1)
                key = baseKey + " " + (counter++);
            s.setKey(key);
            series.addSeries(s);
        }
        description.fillData();
    }

}