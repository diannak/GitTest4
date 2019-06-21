package comstudy.android.ex33_xmljsonex2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import comstudy.android.ex33_xmljsonex2.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    ImageView ivPicture;
    TextView tvHtml1;
    String filePath1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivPicture = findViewById(R.id.ivPicture);
        tvHtml1 = findViewById(R.id.tvHtml1);
        // 권한 체크 후 사용자에 의해 취소되었다면 다시 요청
        if ( ContextCompat.checkSelfPermission( this, Manifest.permission.READ_EXTERNAL_STORAGE )
                != PackageManager.PERMISSION_GRANTED )
        {
            ActivityCompat.requestPermissions(
                    this,
                    new String[] { Manifest.permission.READ_EXTERNAL_STORAGE },
                    1 );
        }

    }
    public void onBtnGetPicture(View v){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }
    public void onBtnUpload(View v){
        tvHtml1.setText("");

        HashMap<String, String> param1 = new HashMap<>();
        param1.put("userid","홍길동");
        param1.put("userpwd", "패스워드");

        HashMap<String, String> param2 = new HashMap<>();
        param2.put("filename", filePath1);

        UploadAsync networkTask = new UploadAsync(getApplicationContext(), param1, param2);
        networkTask.execute();
    }
    public void onBtnFinish(View v){
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                Uri selPhotoUri = data.getData();
                showCapturedImage(selPhotoUri);
            }
        }
    }
    private void showCapturedImage(Uri imageUri){
        // 절대경로를 획득한다!!! 중요~
        filePath1 = getRealPathFromURI(imageUri);
        //Log.d(TAG, "path1:"+filePath1);
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(filePath1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        int exifDegree = exifOrientationToDegrees(exifOrientation);

        //경로를 통해 비트맵으로 전환
        Bitmap bitmap = BitmapFactory.decodeFile(filePath1);
        Bitmap rotatedBitmap = rotate(bitmap, exifDegree);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(rotatedBitmap, 800, 800, false);
        bitmap.recycle();
        //이미지 뷰에 비트맵 넣기
        ivPicture.setImageBitmap(scaledBitmap);

    }
    private int exifOrientationToDegrees(int exifOrientation){
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;

    }
    private Bitmap rotate(Bitmap src, float degree){
        // Matrix 객체 생성
        Matrix matrix = new Matrix();
        // 회전 각도 셋팅
        matrix.postRotate(degree);
        // 이미지와 Matrix 를 셋팅해서 Bitmap 객체 생성
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(),
                src.getHeight(), matrix, true);

    }
    private String getRealPathFromURI(Uri contentUri){
        int column_index=0;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if(cursor.moveToFirst()){
            column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        }

        return cursor.getString(column_index);

    }
    public class UploadAsync extends AsyncTask<Object, Integer, JSONObject>{
        private Context mContext;
        private HashMap<String, String> param;
        private HashMap<String, String> files;

        public UploadAsync(Context context,
                           HashMap<String, String> param,
                           HashMap<String, String> files)
        {
            mContext = context;
            this.param = param;
            this.files = files;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(Object... params) {
            JSONObject rtn = null;
            try {
                String sUrl = getString(R.string.server_addr) + "/JspInServer/uploadOk.jsp";
                Log.d(TAG, "1:"+sUrl);
                comstudy.android.ex33_xmljsonex2.FileUpload multipartUpload = new comstudy.android.ex33_xmljsonex2.FileUpload(sUrl, "UTF-8");
                rtn = multipartUpload.upload(param, files);
                Log.d(TAG, "2:"+rtn.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return rtn;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);

            if (result != null) {
                tvHtml1.setText(result.toString());
                try {
                    if (result.getInt("success") == 1) {
                        Toast.makeText(mContext, "파일 업로드 성공!", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(mContext, "파일 업로드 실패!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}