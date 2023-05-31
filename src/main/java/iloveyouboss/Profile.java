package iloveyouboss;

import iloveyouboss.questions.Question;

import java.util.HashMap;
import java.util.Map;

import static iloveyouboss.questions.Question.AnswerNotProvided;

public class Profile {
   Map<Integer, String> answers = new HashMap<>();

   public boolean matches(Criteria criteria) {
      return criteria.stream()
         .filter(criterion -> !criterion.isOptional())
         .allMatch(criterion -> criterion.isMetBy(answerFor(criterion)));
   }

   public void answer(Question question, String answer) {
      if (answers.containsKey(question.id()))
         throw new DuplicateQuestionException();
      answers.put(question.id(), answer);
   }

   public String answerFor(Criterion criterion) {
      return answers.getOrDefault(criterion.question().id(), AnswerNotProvided);
   }
}
