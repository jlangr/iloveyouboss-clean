package iloveyouboss;

import iloveyouboss.questions.yesno.YesNoQuestion;
import org.junit.jupiter.api.Test;
import java.util.List;

import static iloveyouboss.questions.yesno.YesNoQuestion.No;
import static iloveyouboss.questions.yesno.YesNoQuestion.Yes;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ACriteria {
   Criterion criterion1 = new Criterion(new YesNoQuestion(1, "?"), Yes);
   Criterion criterion2 = new Criterion(new YesNoQuestion(2, "?"), No);
   Criterion criterion3 = new Criterion(new YesNoQuestion(3, "?"), No);

   @Test
   void holdsACollectionOfCriterion() {
      var criteria = new Criteria(List.of(criterion1, criterion2));

      assertEquals(listOfCriterion(criteria), List.of(criterion1, criterion2));
   }

   @Test
   void canBeConstructedWithVarargs() {
      var criteria = new Criteria(criterion1, criterion2);

      assertEquals(listOfCriterion(criteria), List.of(criterion1, criterion2));
   }

   @Test
   void canAcceptAdditionalCriterion() {
      var criteria = new Criteria(criterion1);

      criteria.add(criterion2);
      criteria.add(criterion3);

      assertEquals(listOfCriterion(criteria), List.of(criterion1, criterion2, criterion3));
   }

   private static List<Criterion> listOfCriterion(Criteria criteria) {
      return criteria.stream().collect(toList());
   }
}