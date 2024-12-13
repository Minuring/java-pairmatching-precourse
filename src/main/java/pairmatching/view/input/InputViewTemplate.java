package pairmatching.view.input;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.Execution;

public abstract class InputViewTemplate<T> {

    public T readOnce(Object... headerArgs) {
        printHeader(headerArgs);
        String input = Console.readLine();
        return bind(input);
    }

    public T readUntilSuccess(Object... headerArgs) {
        return Execution.supplyUntilSuccess(() -> readOnce(headerArgs));
    }

    protected abstract void printHeader(Object... args);

    protected abstract T bind(String userInput);
}