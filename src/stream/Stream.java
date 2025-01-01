package stream;

import stream.decorator.StreamDecorator;
import stream.stream_core.BaseCustomStream;
import stream.stream_core.CustomStream;

public class Stream {
    public static <T> CustomStream<T> of(T[] array) {
        return new StreamDecorator<>(new BaseCustomStream<>(array));
    }
}
