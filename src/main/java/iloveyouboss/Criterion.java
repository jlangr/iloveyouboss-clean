package iloveyouboss;

import iloveyouboss.questions.Question;
import static iloveyouboss.questions.Question.AnswerNotProvided;

public record Criterion(Question question, String expectedAnswer, boolean isOptional) {
   public Criterion(Question question, String expectedAnswer) {
      this(question, expectedAnswer, false);
   }

   public boolean isMetBy(String answer) {
      if (answer.equals(AnswerNotProvided)) return false;
      if (!question.options().contains(answer))
         throw new InvalidAnswerException();
      return expectedAnswer.equals(answer);
   }
}
