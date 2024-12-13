package pairmatching;

import static pairmatching.config.Course.BACKEND;
import static pairmatching.config.Course.FRONTEND;
import static pairmatching.view.input.Choice.MATCH;
import static pairmatching.view.input.Choice.QUERY;
import static pairmatching.view.input.Choice.QUIT;
import static pairmatching.view.input.Choice.RESET;

import java.util.List;
import pairmatching.domain.Crew;
import pairmatching.domain.Pair;
import pairmatching.domain.PairMatcher;
import pairmatching.domain.Pairs;
import pairmatching.error.Error;
import pairmatching.file.CourseCrewLoader;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;
import pairmatching.view.input.Choice;
import pairmatching.view.input.TargetInputView.TriInput;

public class ApplicationFacade {

    private static final Pairs PAIRS = Pairs.getInstance();
    private static final List<Crew> BACKEND_CREWS;
    private static final List<Crew> FRONTEND_CREWS;

    private static final PairMatcher matcher;

    static {
        CourseCrewLoader backendLoader = new CourseCrewLoader(BACKEND,
            "src/main/resources/backend-crew.md");
        CourseCrewLoader frontendLoader = new CourseCrewLoader(FRONTEND,
            "src/main/resources/frontend-crew.md");
        BACKEND_CREWS = backendLoader.read();
        FRONTEND_CREWS = frontendLoader.read();
        matcher = new PairMatcher(BACKEND_CREWS, FRONTEND_CREWS);
    }

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
            return;
        }
        OutputView.printBoard();
        if (choice == MATCH) {
            handleMatch();
        }
        if (choice == QUERY) {
            handleQuery();
        }
    }

    private static void handleQuery() {
        TriInput triInput = InputView.readTarget();

        if (PAIRS.hasMatched(triInput.course(), triInput.level(), triInput.mission())) {
            List<Pair> pairs = PAIRS.getPairs(triInput.course(), triInput.level(), triInput.mission());
            OutputView.printResult(pairs);
            return;
        }
        OutputView.printErrorMessage(Error.MATCH_NOT_FOUND.message());
    }

    private static void handleMatch() {
        TriInput triInput = InputView.readTarget();

        if (PAIRS.hasMatched(triInput.course(), triInput.level(), triInput.mission())) {
            if (!InputView.readRematch()) {
                handleMatch();
                return;
            }
        }
        matcher.match(triInput.course(), triInput.level(), triInput.mission());
        List<Pair> pairs = PAIRS.getPairs(triInput.course(), triInput.level(), triInput.mission());
        OutputView.printResult(pairs);
    }
}
