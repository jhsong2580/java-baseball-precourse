package baseball.studyJunit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.entry;

public class ObjectTest {
    private  Dog fido;
    private Dog fidosClone;

    @BeforeEach
    public void init(){
        fido = new Dog("Fido", 5.25f);
        fidosClone = new Dog("Fido", 5.25f);
    }

    @Test
    @DisplayName("Object 동등성 비교")
    public void isEqual (){
        Assertions.assertThat(fido).isNotEqualTo(fidosClone); //class끼리 비교는 객체 가 다르기때문에 동일성비교는 맞지 않는다.
//        Assertions.assertThat(fido).isEqualToComparingFieldByFieldRecursively(fidosClone);
//isEqualToComparingFieldByFieldRecursively는 Deprecate 된 method이므로 아래 usingRecursiveComparison 함수로 대체한다.         
        Assertions.assertThat(fido)
                .usingRecursiveComparison()
                .isEqualTo(fidosClone);
    }


    @Test
    @DisplayName("Interface인지 여부 ")
    public void checkInterface (){
        Assertions.assertThat(Human.class).isInterface();
        Assertions.assertThat(adult.class).isNotInterface();
    }

    @Test
    @DisplayName(value = "Map 검증")
    public void testMap (){
        //given
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");

        //then
        Assertions.assertThat(map)
                .isNotEmpty()
                .containsKey("2")
                .doesNotContainKey("4")
                .contains(entry("2", "b"))
                .doesNotContainEntry("3", "d")
                .containsEntry("3", "c");

    }
    
    @Test
    @DisplayName("Filter을 사용하여 부분 값 검증")
    public void testWithFilter (){
        //given
        ArrayList<adult> adults = new ArrayList<>();

        adult man1 = new adult(20, "man1");
        adult man2 = new adult(30, "man2");
        adult man3 = new adult(20, "man3");
        adult man4 = new adult(20, "man4");
        adult man5 = new adult(30, "man5");
        adult man6 = new adult(20, "man6");

        adults.add(man1);
        adults.add(man2);
        adults.add(man3);
        adults.add(man4);
        adults.add(man5);
        adults.add(man6);

        //then
        Assertions.assertThat(adults)
                .filteredOn(adult -> adult.getAge() == 20)
                .containsOnly(man1, man3, man4, man6);
    }

    static class Dog{
        private String name;
        private  Float weight;

        public Dog(String name, Float weight) {
            this.name = name;
            this.weight = weight;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Float getWeight() {
            return weight;
        }

        public void setWeight(Float weight) {
            this.weight = weight;
        }
    }

    static interface Human{

        public void walk();
        public void eat();
    }
    static class adult implements Human{
        private int age;
        private String name;

        public adult(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public void walk() {
            System.out.println("walking");
        }

        @Override
        public void eat() {
            System.out.println("eating");
        }
    }
}
