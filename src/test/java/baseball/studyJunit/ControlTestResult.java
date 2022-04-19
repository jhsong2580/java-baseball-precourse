package baseball.studyJunit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ControlTestResult {

    @Test
    @DisplayName(value = "As 로 발생되는 오류 Customize")
    public void testAs (){
        //given
        testObject person = new testObject(100, "sjh");
        //when
        Assertions.assertThat(person.age)
                .as("%s's age shoud be equal to 100",person.name)
                .isEqualTo(100)
                ;
        //then
    }


    class testObject{
        public int age;
        public String name;

        public testObject(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }
}
