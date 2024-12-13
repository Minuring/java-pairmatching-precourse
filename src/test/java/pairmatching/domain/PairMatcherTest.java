package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pairmatching.config.Course;
import pairmatching.config.Level;
import pairmatching.config.Mission;

class PairMatcherTest {

    Pairs pairs = Pairs.getInstance();
    List<Crew> frontCrews;
    List<Crew> backCrews;
    PairMatcher matcher;

    @BeforeEach
    void setUp() {
        frontCrews = Stream.of(
                "보노", "시저", "쉐리", "신디", "다비",
                "덴버", "이브", "제시", "라라", "린다",
                "리사", "니콜", "로드", "윌터", "제키")
            .map(name -> new Crew(Course.FRONTEND, name)).toList();

        backCrews = Stream.of(
                "백호", "태웅", "치수", "태섭", "대만",
                "준호", "대협", "덕규", "태산", "경태",
                "수겸", "현준", "준섭", "한나", "소연",
                "호열", "대남", "용팔", "구식", "달재")
            .map(name -> new Crew(Course.BACKEND, name)).toList();
        matcher = new PairMatcher(backCrews, frontCrews);
    }

    @DisplayName("백엔드 크루 20명 매칭시 10쌍, 마지막 쌍까지 2명")
    @Test
    void matchBackendCrews() {
        matcher.match(Course.BACKEND, Level.LEVEL1, Mission.BASEBALL);
        List<Pair> pairs = this.pairs.getPairs(Course.BACKEND, Level.LEVEL1, Mission.BASEBALL);

        assertThat(pairs.size()).isEqualTo(10);
        assertThat(pairs.getLast().getCrews().size()).isEqualTo(2);
    }

    @DisplayName("프론트엔드 크루 15명 매칭시 7쌍, 마지막 쌍은 3명")
    @Test
    void matchFrontendCrews() {
        matcher.match(Course.FRONTEND, Level.LEVEL1, Mission.BASEBALL);
        List<Pair> pairs = this.pairs.getPairs(Course.FRONTEND, Level.LEVEL1, Mission.BASEBALL);
        assertThat(pairs.size()).isEqualTo(7);
        assertThat(pairs.getLast().getCrews().size()).isEqualTo(3);
    }
}
