package iloveyouboss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public record Criteria(List<Criterion> criteria) {
   public Criteria(Criterion... criteria) {
      this(new ArrayList<>(Arrays.asList(criteria)));
   }

   public Stream<Criterion> stream() {
      return criteria.stream();
   }

   public void add(Criterion criterion) {
      criteria.add(criterion);
   }
}
