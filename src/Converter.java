public interface Converter<TI,TO> {
    TI convert(TO value);
}
