package iloveyouboss.questions.yesno;

import iloveyouboss.questions.Question;

import java.util.List;

import static java.util.Arrays.asList;

public record YesNoQuestion(int id, String text) implements Question<YesNo> {
   @Override
   public List<YesNo> options() {
      return asList(YesNo.values());
   }
}
