package comstudy.android.ex24_fragment2;
//화면 부분마다에 액티비티를 사용할때에 사용하는 프레그먼트를 겹쳐쓰면 안된다.
//겹쳐쓰면 에러 많다.
//나중에 플젝할때 액티비티 나눠서 개발 못할때 프래그먼트를 이용
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ImageSelectionCallback

    {
        private static String TAG = "lecture";

        ListFragment listFragment;
        ViewerFragment viewerFragment;

        int[] images = {R.drawable.dream01, R.drawable.dream02, R.drawable.dream03};

        @Override
        protected void onCreate (Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            FragmentManager manager = getSupportFragmentManager();
            listFragment = (ListFragment) manager.findFragmentById(R.id.listFragment);
            viewerFragment = (ViewerFragment) manager.findFragmentById(R.id.viewerFragment);
        }

        @Override
        public void onImageSelected ( int position) {
            viewerFragment.setImage(images[position]);
        }
    }
