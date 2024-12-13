package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static pairmatching.config.Course.BACKEND;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PairTest {

    static Crew crew1 = new Crew(BACKEND, "크루1");
    static Crew crew2 = new Crew(BACKEND, "크루2");
    static Crew crew3 = new Crew(BACKEND, "크루3");
    static Crew crew4 = new Crew(BACKEND, "크루4");
    static Crew crew5 = new Crew(BACKEND, "크루5");

    @DisplayName("크루로 만난 적이 없음을 판단할 수 있다.")
    @ParameterizedTest
    @MethodSource("pairsNotOverlapped")
    void isNotOverlapped(Pair pair1, Pair pair2) {
        assertThat(pair1.isOverlapped(pair2)).isFalse();
    }

    static List<Arguments> pairsNotOverlapped() {
        return List.of(
            Arguments.of(new Pair(crew1, crew2), new Pair(crew1, crew3)),
            Arguments.of(new Pair(crew1, crew2), new Pair(crew2, crew3)),
            Arguments.of(new Pair(crew1, crew2, crew3), new Pair(crew4, crew5)),
            Arguments.of(new Pair(crew1, crew2, crew3), new Pair(crew1, crew5))
        );
    }

    @DisplayName("크루로 만난 적이 있음을 판단할 수 있다.")
    @ParameterizedTest
    @MethodSource("pairsOverlapped")
    void isOverlapped(Pair pair1, Pair pair2) {
        assertThat(pair1.isOverlapped(pair2)).isTrue();
    }

    static List<Arguments> pairsOverlapped() {
        return List.of(
            Arguments.of(new Pair(crew1, crew2), new Pair(crew1, crew2)),
            Arguments.of(new Pair(crew1, crew2), new Pair(crew2, crew1)),
            Arguments.of(new Pair(crew1, crew2, crew3), new Pair(crew3, crew1)),
            Arguments.of(new Pair(crew1, crew2, crew3), new Pair(crew2, crew3))
        );
    }
}