package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TryCountTest {

    @DisplayName("시도할 횟수를 입력 안한 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void 시도_횟수_입력_X(String value) {
        assertThatThrownBy(() -> new TryCount(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("값을 입력해주세요.");
    }

    @DisplayName("시도할 횟수가 숫자 일 때 성공한다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "1", "4", "6", "7", "9", "15"})
    void 시도_횟수_숫자_성공(String value) {
        assertThatCode(() -> new TryCount(value))
                .doesNotThrowAnyException();
    }

    @DisplayName("시도할 횟수가 숫자가 아닐 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "c"})
    void 시도_횟수_숫자_실패(String value) {
        assertThatThrownBy(() -> new TryCount(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자를 입력해주세요.");
    }

    @DisplayName("시도할 횟수가 음수 일 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"-1", "-2", "-5"})
    void 시도_횟수_음수_실패(String value) {
        assertThatThrownBy(() -> new TryCount(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 시도 회수입니다.");
    }

}