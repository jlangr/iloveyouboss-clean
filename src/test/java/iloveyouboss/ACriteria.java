package iloveyouboss;

import iloveyouboss.questions.yesno.YesNo;
import iloveyouboss.questions.yesno.YesNoQuestion;
import org.junit.jupiter.api.Test;

import java.util.List;

import static iloveyouboss.questions.yesno.YesNoAnswers.NoAnswer;
import static iloveyouboss.questions.yesno.YesNoAnswers.YesAnswer;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ACriteria {
   Criterion<YesNo> criterion1 = new Criterion<>(new YesNoQuestion(1, "?"), YesAnswer);
   Criterion<YesNo> criterion2 = new Criterion<>(new YesNoQuestion(2, "?"), NoAnswer);
   Criterion<YesNo> criterion3 = new Criterion<>(new YesNoQuestion(3, "?"), NoAnswer);

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

   private static List<Criterion<?>> listOfCriterion(Criteria criteria) {
      return criteria.stream().collect(toList());
   }
}