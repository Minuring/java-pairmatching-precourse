package pairmatching.view.input;

import pairmatching.error.Error;

public class RematchInputView extends InputViewTemplate<Boolean>{

    @Override
    protected void printHeader(Object... args) {
        System.out.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?");
        System.out.println("네 | 아니오");
    }

    @Override
    protected Boolean bind(String userInput) {
        String input = userInput.trim();
        if (input.equals("네")) {
            return true;
        }
        if (input.equals("아니오")) {
            return false;
        }
        throw new IllegalArgumentException(Error.BAD_INPUT.message());
    }
}
