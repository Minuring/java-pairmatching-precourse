package pairmatching.view;

public class OutputView {

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printWelcome() {
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

    public static void printReset() {
        System.out.println("\n초기화 되었습니다.");
    }
}
