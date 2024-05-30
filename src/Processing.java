import java.util.List;

public interface Processing<T> {
    T process(List<T> data);
}
