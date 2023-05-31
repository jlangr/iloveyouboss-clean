package iloveyouboss;

import iloveyouboss.questions.Question;

import java.util.HashMap;
import java.util.Map;

import static iloveyouboss.Answer.NotProvided;

public class Profile {
   Map<Integer, Answer> answers = new HashMap<>();

   public boolean matches(Criteria criteria) {
      return criteria.stream()
         .filter(criterion -> !criterion.isOptional())
         .allMatch(criterion -> criterion.isMetBy(answerFor(criterion)));
   }

   public void answer(Question question, Answer answer) {
      if (answers.containsKey(question.id()))
         throw new DuplicateQuestionException();
      answers.put(question.id(), answer);
   }

   public Answer answerFor(Criterion criterion) {
      return answers.getOrDefault(criterion.question().id(), NotProvided);
   }
}
