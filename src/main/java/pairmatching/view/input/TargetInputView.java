package pairmatching.view.input;

import pairmatching.config.Course;
import pairmatching.config.Level;
import pairmatching.config.Mission;
import pairmatching.view.ViewUtils;
import pairmatching.view.input.TargetInputView.TriInput;

public class TargetInputView extends InputViewTemplate<TriInput> {

    public record TriInput(
        Course course,
        Level level,
        Mission mission
    ) {

    }

    @Override
    protected void printHeader(Object... args) {
        System.out.println("과정, 레벨, 미션을 선택하세요.");
        System.out.println("ex) 백엔드, 레벨1, 자동차경주");
    }

    @Override
    protected TriInput bind(String userInput) {
        ViewUtils.validateMatchesPattern(userInput, "^\\S+,\\s?\\S+,\\s?\\S+");

        String[] split = userInput.split(",");

        Course course = Course.ofName(split[0].trim());
        Level level = Level.ofName(split[1].trim());
        Mission mission = Mission.ofName(split[2].trim());
        return new TriInput(course, level, mission);
    }
}
