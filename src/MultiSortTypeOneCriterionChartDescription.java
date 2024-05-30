import java.util.ArrayList;
import java.util.List;

public abstract class MultiSortTypeOneCriterionChartDescription extends OneChartDescription {

    public interface BySortingWrapperFiller {
        void addWrappers(List<XYSeriesWrapper> list, Sorting s, AllSortsInfo info);
    }

    private BySortingWrapperFiller filler;

    public MultiSortTypeOneCriterionChartDescription(AllSortsInfo info, String title, BySortingWrapperFiller filler) {
        super(info, title);
        this.filler = filler;
    }
    protected List<XYSeriesWrapper> getSeriesWrappers(AllSortsInfo info) {

        Iterable<Sorting> sorts = info.getSorts();

        List<XYSeriesWrapper> wrappers = new ArrayList<>();

        for (Sorting s : sorts) {
            filler.addWrappers(wrappers, s, info);
        }
        return wrappers;
    }
    public static final BySortingWrapperFiller CHANGES = new BySortingWrapperFiller() {
        @Override
        public void addWrappers(List<XYSeriesWrapper> list, Sorting s, AllSortsInfo info) {
            list.add(new XYSeriesWrapper(s.getName(),
                    new SeriesProvider(info, s, SortInfoConverter.BY_CHG)));
        }
    };

    public static final BySortingWrapperFiller COMPARISONS = new BySortingWrapperFiller() {
        @Override
        public void addWrappers(List<XYSeriesWrapper> list, Sorting s, AllSortsInfo info) {
            list.add(new XYSeriesWrapper(s.getName(),
                    new SeriesDataProviderFromInfo(info, s, SortInfoConverter.BY_CMP)));
        }
    };

    public static final BySortingWrapperFiller TIMES = new BySortingWrapperFiller() {
        @Override
        public void addWrappers(List<XYSeriesWrapper> list, Sorting s, AllSortsInfo info) {
            list.add(new XYSeriesWrapper(s.getName(),
                    new SeriesDataProviderFromInfo(info, s, SortInfoConverter.BY_TIME)));
        }
    };
}