import java.util.List;
import java.util.ArrayList;

public class Hero {
    private String mName;
    private Int mAge;
    private String mPower;
    private String mWeakness;

    public Hero(String name, Int age ,String power,String weakness) {
        mName = name;
        mAge = age;
        mPower = power;
        mWeakness = weakness;
    }
    public String getName() {
        return mName;
    }
}
