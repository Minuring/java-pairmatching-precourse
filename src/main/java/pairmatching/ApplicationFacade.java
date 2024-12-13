package pairmatching;

import static pairmatching.view.input.Choice.QUIT;

import pairmatching.view.InputView;
import pairmatching.view.input.Choice;

public class ApplicationFacade {

    public static void run() {
        while (true) {
            Choice choice = InputView.readChoice();
            if (choice == QUIT) {
                break;
            }

            handleChoice(choice);
        }
    }

    private static void handleChoice(Choice choice) {
        //todo : 구현
    }
}
