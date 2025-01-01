package stream.decorator;

import stream.collector.CustomCollector;
import stream.stream_core.CustomStream;
import java.util.function.Function;
import java.util.function.Predicate;

public class StreamDecorator<T> implements CustomStream<T> {
    private final CustomStream<T> stream;

    public StreamDecorator(CustomStream<T> stream) {
        this.stream = stream;
    }

    @Override
    public <R> CustomStream<R> map(Function<? super T, ? extends R> mapper) {
        return stream.map(mapper);
    }

    @Override
    public CustomStream<T> filter(Predicate<? super T> predicate) {
        return stream.filter(predicate);
    }

    @Override
    public <R> R collect(CustomCollector<? super T, R> collector) {
        return stream.collect(collector);
    }
}
