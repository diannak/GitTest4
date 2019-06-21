package comstudy.android.ex23_fragment1;
//조금 복잡한데 구글에서 제공되는 것들이 다 이런식이라 쩔수.
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "lecture";

    MainFragment mainFragment;
    MenuFragment menuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = (MainFragment)getSupportFragmentManager().findFragmentById(R.id.mainFragment);
        menuFragment = new MenuFragment();

    }

    public void onFragmentChange(int index){
        if(index == 0) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, menuFragment).commit();
        }else if(index == 1){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, mainFragment).commit();
        }
    }
}
