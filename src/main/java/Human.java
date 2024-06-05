import java.util.ArrayList;
import java.util.List;

public class Human {
    // 이름   글자 저장
    String name;
    // 나이   숫자 저장
    Integer age;
    // 과자목록   과자를 여러개 저장
    List<Snack> snackList;

    public Human(String name, Integer age) {
        this.name = name;
        this.age = age;
        this.snackList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Snack> getSnackList() {
        return snackList;
    }

    public void setSnackList(List<Snack> snackList) {
        this.snackList = snackList;
    }
}
