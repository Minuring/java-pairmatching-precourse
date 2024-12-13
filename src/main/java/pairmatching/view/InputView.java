package pairmatching.view;

import pairmatching.view.input.Choice;
import pairmatching.view.input.ChoiceInputView;
import pairmatching.view.input.RematchInputView;
import pairmatching.view.input.TargetInputView;
import pairmatching.view.input.TargetInputView.TriInput;

public class InputView {

    public static Choice readChoice() {
        return new ChoiceInputView().readUntilSuccess();
    }

    public static TriInput readTarget() {
        return new TargetInputView().readUntilSuccess();
    }

    public static boolean readRematch() {
        return new RematchInputView().readUntilSuccess();
    }
}
