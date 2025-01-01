package stream.collector;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.ArrayList;

public interface CustomCollector<T, R> {
    Supplier<R> supplier();
    BiConsumer<R, T> accumulator();
    Function<R, R> finisher();

    static <T> CustomCollector<T, List<T>> toList() {
        return new CustomCollector<>() {
            @Override
            public Supplier<List<T>> supplier() {
                return ArrayList::new;
            }

            @Override
            public BiConsumer<List<T>, T> accumulator() {
                return List::add;
            }

            @Override
            public Function<List<T>, List<T>> finisher() {
                return Function.identity();
            }
        };
    }
}
