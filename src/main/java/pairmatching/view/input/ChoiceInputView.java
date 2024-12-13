package pairmatching.view.input;

import pairmatching.view.ViewUtils;

public class ChoiceInputView extends InputViewTemplate<Choice> {

    @Override
    protected void printHeader(Object... args) {
        System.out.println(
            """
                기능을 선택하세요.
                1. 페어 매칭
                2. 페어 조회
                3. 페어 초기화
                Q. 종료
                """
        );
    }

    @Override
    protected Choice bind(String userInput) {
        String input = userInput.trim();
        ViewUtils.validateMatchesPattern(input, "[123Q]");
        return Choice.of(input);
    }
}
