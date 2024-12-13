package pairmatching;

import static pairmatching.view.input.Choice.QUIT;
import static pairmatching.view.input.Choice.RESET;

import pairmatching.domain.Pairs;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;
import pairmatching.view.input.Choice;

public class ApplicationFacade {

    private static final Pairs PAIRS = Pairs.getInstance();

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
        if (choice == RESET) {
            PAIRS.reset();
            OutputView.printReset();
        }
    }
}
