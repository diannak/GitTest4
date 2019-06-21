package comstudy.android.ex17_list3;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SingerItemView extends LinearLayout {

    TextView textView1;
    TextView textView2;
    ImageView imageView1;

    public SingerItemView(Context context) {
        super(context);

        //동적 화면 구성에 쓰이는 inflater
        //부분적으로만 화면 변화를 주고 싶을때!
        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.singer_item_view, this, true);

            textView1 = findViewById(R.id.textView1);
            textView2 = findViewById(R.id.textView2);
            imageView1 = findViewById(R.id.imageView1);
        }

        public void setName(String name){
            textView1.setText(name);
        }

        public void setAge(String age){
            textView2.setText(age);
        }
        public void setImage(int imgNum){
           imageView1.setImageResource(imgNum);
        }

}

