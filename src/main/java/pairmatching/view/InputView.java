package pairmatching.view;

import pairmatching.view.input.Choice;
import pairmatching.view.input.ChoiceInputView;

public class InputView {

    public static Choice readChoice() {
        return new ChoiceInputView().readUntilSuccess();
    }
}
