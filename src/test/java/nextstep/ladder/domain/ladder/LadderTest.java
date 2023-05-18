package nextstep.ladder.domain.ladder;

import nextstep.ladder.domain.user.Participants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.assertAll;

class LadderTest {

    @Test
    @DisplayName("참여자 및 높이 정보를 가지고 사다리를 생성한다.")
    void test01() {
        Participants participants = Participants.of("pobi,honux,crong,jk");
        LadderHeight ladderHeight = LadderHeight.from(5);

        assertThatNoException()
                .isThrownBy(() -> Ladder.create(ladderHeight.getHeight(), participants.count()));
    }

    @Test
    @DisplayName("사용자 수보다 하나작은 points 를 생성한다.")
    void test02() {
        int height = 5;
        int userCount = 5;
        Ladder ladder = Ladder.create(height, userCount);

        assertThat(ladder.getRows().get(0).getConnectionStatuses()).hasSize(userCount - 1);
    }

    @Test
    @DisplayName("크기를 반환한다.")
    void test03() {
        int height = 5;
        int userCount = 5;
        Ladder ladder = Ladder.create(height, userCount);
        assertThat(ladder.getRows()).hasSize(height);
    }

    @Test
    @DisplayName("참여자 위치를 입력하면 실행결과를 반환한다.")
    void test04() {
        Points points = Points.initialize(2, () -> true);
        Ladder ladder = new Ladder(List.of(points));

        assertAll(
                () -> assertThat(ladder.getResult(0)).isEqualTo(1),
                () -> assertThat(ladder.getResult(1)).isEqualTo(0)
        );
    }

}
