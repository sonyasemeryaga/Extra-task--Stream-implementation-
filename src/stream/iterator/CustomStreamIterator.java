package stream.iterator;

import java.util.Iterator;
import java.util.List;

public class CustomStreamIterator<T> implements Iterator<T> {
    private final List<T> elements;
    private int currentPosition = 0;

    public CustomStreamIterator(List<T> elements) {
        this.elements = elements;
    }

    @Override
    public boolean hasNext() {
        return currentPosition < elements.size();
    }

    @Override
    public T next() {
        return elements.get(currentPosition++);
    }
}
