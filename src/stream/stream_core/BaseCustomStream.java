package stream.stream_core;

import stream.collector.CustomCollector;
import stream.iterator.CustomStreamIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class BaseCustomStream<T> implements CustomStream<T> {
    private final List<T> elements;

    public BaseCustomStream(T[] array) {
        this.elements = new ArrayList<>(Arrays.asList(array));
    }

    protected BaseCustomStream(List<T> elements) {
        this.elements = elements;
    }

    @Override
    public <R> CustomStream<R> map(Function<? super T, ? extends R> mapper) {
        List<R> mapped = new ArrayList<>();
        CustomStreamIterator<T> iterator = new CustomStreamIterator<>(elements);
        while (iterator.hasNext()) {
            T element = iterator.next();
            mapped.add(mapper.apply(element));
        }
        return new BaseCustomStream<>(mapped);
    }

    @Override
    public CustomStream<T> filter(Predicate<? super T> predicate) {
        List<T> filtered = new ArrayList<>();
        CustomStreamIterator<T> iterator = new CustomStreamIterator<>(elements);
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (predicate.test(element)) {
                filtered.add(element);
            }
        }
        return new BaseCustomStream<>(filtered);
    }

    @Override
    public <R> R collect(CustomCollector<? super T, R> collector) {
        R result = collector.supplier().get();
        CustomStreamIterator<T> iterator = new CustomStreamIterator<>(elements);
        while (iterator.hasNext()) {
            T element = iterator.next();
            collector.accumulator().accept(result, element);
        }
        return collector.finisher().apply(result);
    }
}
