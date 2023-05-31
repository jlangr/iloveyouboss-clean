package iloveyouboss;

import iloveyouboss.questions.yesno.YesNoQuestion;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static iloveyouboss.questions.Question.AnswerNotProvided;
import static iloveyouboss.questions.yesno.YesNoQuestion.No;
import static iloveyouboss.questions.yesno.YesNoQuestion.Yes;
import static org.junit.jupiter.api.Assertions.*;

class AProfile {
   Profile profile = new Profile();
   YesNoQuestion hasRelo = new YesNoQuestion(1, "Has relocation package?");
   YesNoQuestion has401K = new YesNoQuestion(2, "Has 401K?");
   YesNoQuestion hasSmelt = new YesNoQuestion(3, "got smelt?");

   Criterion mustHaveRelo = new Criterion(hasRelo, Yes);
   Criterion mustHave401K = new Criterion(has401K, Yes);
   Criterion optionallyHasSmeltShouldBeTrue = new Criterion(hasSmelt, Yes, true);

   @Nested
   class WhenDeterminingMatches {
      @Test
      void doesNotMatchWhenProfileHasNoAnswerForCriterion() {
         var criteria = new Criteria(new Criterion(hasRelo, Yes));

         assertFalse(profile.matches(criteria));
      }

      @Test
      void doesNotMatchWhenAllCriteriaNotMet() {
         profile.answer(hasRelo, Yes);
         profile.answer(has401K, No);

         assertFalse(profile.matches(new Criteria(mustHaveRelo, mustHave401K)));
      }

      @Nested
      class WithAllQuestionsAnsweredYes {
         @Test
         void matchesWhenAllCriteriaMet() {
            profile.answer(hasRelo, Yes);
            profile.answer(has401K, Yes);

            assertTrue(profile.matches(new Criteria(mustHaveRelo, mustHave401K)));
         }

         @Test
         void matchesDespiteUnmetOptionalCriterion() {
            var optionalCriterion = new Criterion(hasSmelt, Yes, true);
            var criteria = new Criteria(mustHaveRelo, optionalCriterion);
            profile.answer(hasSmelt, No);
            profile.answer(hasRelo, Yes);

            assertTrue(profile.matches(criteria));
         }

         @Test
         void stillMatchesWithOnlyMismatchedOptionalCriteria() {
            var criteria = new Criteria(optionallyHasSmeltShouldBeTrue);
            profile.answer(hasSmelt, No);

            assertTrue(profile.matches(criteria));
         }
      }
   }

   @Nested
   class WhenManagingAnswers {
      @Test
      void returnsNullWhenAskedForNonexistentAnswer() {
         assertSame(AnswerNotProvided, profile.answerFor(mustHave401K));
      }

      @Test
      void returnsAnswerForCorrespondingCriterionQuestion() {
         profile.answer(has401K, Yes);
         var criterion = new Criterion(has401K, Yes);

         var answer = profile.answerFor(criterion);

         assertEquals(answer, Yes);
      }

      @Test
      void throwsWhenAddingDuplicateAnswer() {
         profile.answer(has401K, Yes);
         var questionWithDuplicateId = new YesNoQuestion(has401K.id(), "?");

         assertThrows(DuplicateQuestionException.class,
            () -> profile.answer(questionWithDuplicateId, No));
      }
   }
}