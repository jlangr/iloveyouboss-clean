package iloveyouboss;

import iloveyouboss.questions.Question;

import static iloveyouboss.Answer.NotProvided;

public record Criterion<T>(Question<T> question, Answer<T> expectedAnswer, boolean isOptional) {
   public Criterion(Question<T> question, Answer<T> expectedAnswer) {
      this(question, expectedAnswer, false);
   }

   // TODO warning around ? wildcard bind type for Answer
   public boolean isMetBy(Answer<?> answer) {
      if (answer == NotProvided) return false;
      if (!question.options().contains(answer.value()))
         throw new InvalidAnswerException();
      return expectedAnswer().value().equals(answer.value());
   }
}
