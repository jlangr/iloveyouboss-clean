package iloveyouboss;

import iloveyouboss.questions.choice.ChoiceQuestion;
import iloveyouboss.questions.yesno.YesNoQuestion;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static iloveyouboss.questions.yesno.YesNoQuestion.No;
import static iloveyouboss.questions.yesno.YesNoQuestion.Yes;
import static org.junit.jupiter.api.Assertions.*;

class ACriterion {
   @Nested
   class WithABooleanQuestion {
      YesNoQuestion question = new YesNoQuestion(1, "?");

      @Test
      void isMetByAnswerMatchingItsExpectedAnswer() {
         var criterion = new Criterion(question, Yes);

         assertTrue(criterion.isMetBy(Yes));
      }

      @Test
      void isNotMetByAnswerMismatchingItsExpectedAnswer() {
         var criterion = new Criterion(question, Yes);

         assertFalse(criterion.isMetBy(No));
      }
   }

   @Nested
   class WithAChoiceQuestion {
      ChoiceQuestion question = new ChoiceQuestion(1, "?", List.of("eeny", "meeny", "miny", "moe"));

      @Test
      void isMetByAnswerMatchingItsExpectedAnswer() {
         var criterion = new Criterion(question, "eeny");

         assertTrue(criterion.isMetBy("eeny"));
      }

      @Test
      void isNotMetByAnswerMismatchingItsExpectedAnswer() {
         var criterion = new Criterion(question, "meeny");

         assertFalse(criterion.isMetBy("moe"));
      }

      @Test
      void throwsWhenAnswerDoesNotMatchAvailableChoices() {
         var criterion = new Criterion(
            new ChoiceQuestion(1, "?", Collections.singletonList("correct")),
            "correct");
         var answerOutOfRange = "anything else";

         assertThrows(InvalidAnswerException.class, () -> criterion.isMetBy(answerOutOfRange));
      }
   }
}
