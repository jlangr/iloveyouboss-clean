package iloveyouboss.questions.yesno;

import iloveyouboss.Answer;

import static iloveyouboss.questions.yesno.YesNo.No;
import static iloveyouboss.questions.yesno.YesNo.Yes;

public enum YesNoAnswers implements Answer<YesNo> {
   YesAnswer {
      @Override public YesNo value() { return Yes; }
   },
   NoAnswer {
      @Override public YesNo value() { return No; }
   };
}
