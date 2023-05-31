package iloveyouboss.questions.choice;

import iloveyouboss.questions.Question;

import java.util.List;

public record ChoiceQuestion(int id, String text, List<String> options) implements Question {
}
