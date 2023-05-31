package iloveyouboss.questions.yesno;

import iloveyouboss.Answer;

public enum YesNoAnswers implements Answer {
   Yes {
      @Override public String value() { return Yes.toString(); }
   },
   No {
      @Override public String value() { return No.toString(); }
   };
}
