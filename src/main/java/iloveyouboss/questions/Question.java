package iloveyouboss.questions;

import java.util.List;

public interface Question<T> {
   int id();
   List<T> options();
}
