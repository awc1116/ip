package Doopies.util;

public class Pair<T, U> {
    private final T t;
    private final U u;

    public Pair(T t, U u) {
        this.t = t;
        this.u = u;
    }

    public T getFirst() {
        return this.t;
    }

    public U getSecond() {
        return this.u;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", this.t, this.u);
    }
}
