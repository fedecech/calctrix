public interface Operations<T> {
    T multiply(T other) throws Exception;
    T multiply(double other) throws Exception;
    T add(T other) throws Exception;
    T inverse();
}
