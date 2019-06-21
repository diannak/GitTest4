package comstudy.android.ex19_list5;

public class SingerItem {

    private String name;
    private String age;
    private int reId;

    public SingerItem(String name, String age, int reId){
        this.name = name;
        this.age = age;
        this.reId = reId;
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

    public int getReId() {
        return reId;
    }

    public void setReId(int reId) {
        this.reId = reId;
    }
}