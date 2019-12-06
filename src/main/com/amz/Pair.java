package com.amz;

import java.util.Objects;

class Pair<T, R> {
    T x;
    R y;

    private Pair(T x, R y) {
        this.x = x;
        this.y = y;
    }

    public static <T, R> Pair<T, R> create(T x, R y) {
        return new Pair<>(x, y);
    }

    public static <T, R> Pair<T, R> of(T x, R y) {
        return new Pair<>(x, y);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(x, pair.x) &&
                Objects.equals(y, pair.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("{%s,%s}", x.toString(), y.toString());
    }
}
