package iloveyouboss;

public interface Answer {
   Answer NotProvided = () -> null;
   String value();
}
