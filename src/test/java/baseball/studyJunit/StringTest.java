package baseball.studyJunit;



import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StringTest {
    @Test
    @DisplayName("문자열 split TEST - \"1,2\"")
    public void splitStringTest (){
        //given
        String target = "1,2";

        //when
        String[] targetResult = target.split(",");

        //then
        Assertions.assertThat(targetResult).contains("1");
        Assertions.assertThat(targetResult).contains("2");
        Assertions.assertThat(targetResult).containsExactly("1", "2");
        Assertions.assertThat(targetResult[0]).isEqualTo("1");
        Assertions.assertThat(targetResult[1]).isEqualTo("2");
    }
    @Test
    @DisplayName("문자열 split TEST - \"1\"")
    public void splitSingleStringTest (){
        //given
        String singleTarget = "1";
        //when
        String[] singleTargetResult = singleTarget.split(",");
        //then
        Assertions.assertThat(singleTargetResult).containsExactly("1"); //순서를 포함하여 정확히 일치
        Assertions.assertThat(singleTargetResult).containsOnly("1");// 순서, 중복을 무시하는 대신 원소값과 갯수가 정확히 일치

        Assertions.assertThat(singleTargetResult.length).isEqualTo(1);
        Assertions.assertThat(singleTargetResult[0]).isEqualTo("1");
    }

    @Test
    @DisplayName("문자열 Substring Test")
    public void substringTest (){
        //given
        String target = "(1,2)";
        //when
        String result = target.substring(1, target.length() - 1);
        //then
        Assertions.assertThat(result).isEqualTo("1,2");
    }

    @Test
    @DisplayName("Charat 테스트 및 OutOfBound Exception 테스트")
    public void charAtTest() {
        //given
        String target = "abc";
        //when
        char first = target.charAt(0);
        char second = target.charAt(1);
        char third = target.charAt(2);
        //then
        Assertions.assertThat(first).isEqualTo('a');
        Assertions.assertThat(second).isInstanceOf(Character.class);
        Assertions.assertThat(third).isIn('a', 'c');

        Assertions.assertThatThrownBy(() -> target.charAt(4))
                .isInstanceOf(StringIndexOutOfBoundsException.class)
                .hasMessageMatching("String index out of range: \\d+")
                .hasMessageContaining("String index out of range: 4");

    }

}
