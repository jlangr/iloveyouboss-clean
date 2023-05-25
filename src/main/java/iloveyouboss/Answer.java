package iloveyouboss;

public interface Answer<T> {
   public static final Answer<Object> NotProvided = () -> null;
   T value();
}
