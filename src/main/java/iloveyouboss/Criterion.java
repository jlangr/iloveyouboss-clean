package iloveyouboss;

import iloveyouboss.questions.Question;

import static iloveyouboss.Answer.NotProvided;

public record Criterion(Question question, Answer expectedAnswer, boolean isOptional) {
   public Criterion(Question question, Answer expectedAnswer) {
      this(question, expectedAnswer, false);
   }

   public boolean isMetBy(Answer answer) {
      if (answer == NotProvided) return false;
      if (!question.options().contains(answer.value()))
         throw new InvalidAnswerException();
      return expectedAnswer().value().equals(answer.value());
   }
}
