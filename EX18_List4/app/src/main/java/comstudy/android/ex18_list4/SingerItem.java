package comstudy.android.ex18_list4;
//DTO = Bean과 같은 모양
public class SingerItem {

    private String name;
    private String age;
    private int ResId;

    public  SingerItem(String name, String age, int resId){
        this.name = name;
        this.age = age;
        this.ResId = resId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getResId() {
        return ResId;
    }

    public void setResId(int imgNum) {
        this.ResId = ResId;
    }
}
