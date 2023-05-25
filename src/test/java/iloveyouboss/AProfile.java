package iloveyouboss;

import iloveyouboss.questions.yesno.YesNo;
import iloveyouboss.questions.yesno.YesNoQuestion;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static iloveyouboss.questions.yesno.YesNoAnswers.NoAnswer;
import static iloveyouboss.questions.yesno.YesNoAnswers.YesAnswer;
import static org.junit.jupiter.api.Assertions.*;

class AProfile {
   Profile profile = new Profile();
   YesNoQuestion hasRelo = new YesNoQuestion(1, "Has relocation package?");
   YesNoQuestion has401K = new YesNoQuestion(2, "Has 401K?");
   YesNoQuestion hasSmelt = new YesNoQuestion(3, "got smelt?");

   Criterion<YesNo> mustHaveRelo = new Criterion<>(hasRelo, YesAnswer);
   Criterion<YesNo> mustHave401K = new Criterion<>(has401K, YesAnswer);
   Criterion<YesNo> optionallyHasSmeltShouldBeTrue = new Criterion<>(hasSmelt, YesAnswer, true);

   @Nested
   class WhenDeterminingMatches {
      @Test
      void doesNotMatchWhenProfileHasNoAnswerForCriterion() {
         var criteria = new Criteria(new Criterion<>(hasRelo, YesAnswer));

         assertFalse(profile.matches(criteria));
      }

      @Test
      void doesNotMatchWhenAllCriteriaNotMet() {
         profile.answer(hasRelo, YesAnswer);
         profile.answer(has401K, NoAnswer);

         assertFalse(profile.matches(new Criteria(mustHaveRelo, mustHave401K)));
      }

      @Nested
      class WithAllQuestionsAnsweredYes {
         @Test
         void matchesWhenAllCriteriaMet() {
            profile.answer(hasRelo, YesAnswer);
            profile.answer(has401K, YesAnswer);

            assertTrue(profile.matches(new Criteria(mustHaveRelo, mustHave401K)));
         }

         @Test
         void matchesDespiteUnmetOptionalCriterion() {
            var optionalCriterion = new Criterion<>(hasSmelt, YesAnswer, true);
            var criteria = new Criteria(mustHaveRelo, optionalCriterion);
            profile.answer(hasSmelt, NoAnswer);
            profile.answer(hasRelo, YesAnswer);

            assertTrue(profile.matches(criteria));
         }

         @Test
         void stillMatchesWithOnlyMismatchedOptionalCriteria() {
            var criteria = new Criteria(optionallyHasSmeltShouldBeTrue);
            profile.answer(hasSmelt, NoAnswer);

            assertTrue(profile.matches(criteria));
         }
      }
   }

   @Nested
   class WhenManagingAnswers {
      @Test
      void returnsNullWhenAskedForNonexistentAnswer() {
         assertSame(Answer.NotProvided, profile.answerFor(mustHave401K));
      }

      @Test
      void returnsAnswerForCorrespondingCriterionQuestion() {
         profile.answer(has401K, YesAnswer);
         var criterion = new Criterion<>(has401K, YesAnswer);

         var answer = profile.answerFor(criterion);

         assertEquals(answer, YesAnswer);
      }

      @Test
      void throwsWhenAddingDuplicateAnswer() {
         profile.answer(has401K, YesAnswer);
         var questionWithDuplicateId = new YesNoQuestion(has401K.id(), "?");

         assertThrows(DuplicateQuestionException.class,
            () -> profile.answer(questionWithDuplicateId, NoAnswer));
      }
   }
}