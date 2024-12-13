package pairmatching.view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.config.Course;
import pairmatching.config.Level;
import pairmatching.config.Mission;
import pairmatching.config.NameSupplier;

public class OutputView {

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printReset() {
        System.out.println("\n초기화 되었습니다.");
    }

    public static void printBoard() {
        System.out.println("#############################################");
        System.out.println("과정: " + parseNames(Course.values()));
        System.out.println("미션:");
        Arrays.stream(Level.values()).forEach(level ->
            System.out.printf("  - %s: %s\n", level.getName(), parseNames(Mission.ofLevel(level))))
        ;
        System.out.println("#############################################");
    }

    private static <T extends NameSupplier> String parseNames(List<T> enumValues) {
        NameSupplier[] array = enumValues.toArray(NameSupplier[]::new);
        return parseNames(array);
    }

    private static <T extends NameSupplier> String parseNames(T[] enumValues) {
        return "" + Arrays.stream(enumValues)
            .map(NameSupplier::getName)
            .collect(Collectors.joining(" | "));
    }
}
