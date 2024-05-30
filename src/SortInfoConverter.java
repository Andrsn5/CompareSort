public abstract class SortInfoConverter implements Converter<SortInfo, Number> {


    public Number convert(SortInfo value) {
        return value == null ? null : actualConvert(value);
    }

    protected abstract Number actualConvert( SortInfo value);

    public static final SortInfoConverter BY_CMP = new SortInfoConverter() {
        protected Number actualConvert( SortInfo value) {
            return value.getCmpCount();
        }
    };

    public static final SortInfoConverter BY_CHG = new SortInfoConverter() {
        @Override
        protected Number actualConvert( SortInfo value) {
            return value.getchangeCount();
        }
    };

    public static final SortInfoConverter BY_TIME = new SortInfoConverter() {
        @Override
        protected Number actualConvert( SortInfo value) {
            return value.getTimeMs();
        }
    };
}