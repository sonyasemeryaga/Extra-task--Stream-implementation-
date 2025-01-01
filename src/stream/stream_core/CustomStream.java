package stream.stream_core;

import stream.collector.CustomCollector;
import java.util.function.Function;
import java.util.function.Predicate;

public interface CustomStream<T> {
    <R> CustomStream<R> map(Function<? super T, ? extends R> mapper);
    CustomStream<T> filter(Predicate<? super T> predicate);
    <R> R collect(CustomCollector<? super T, R> collector);

}
