package com.dog.android.happy_dog;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//지도 넣은 부분
public class ReportFragment0 extends Fragment {
    private static final String TAG = "happy";

    TextView txt_rName, txt_rId, txt_rSort, txt_rTel, txt_rPlace, txt_rKind,
            txt_rGender, txt_rColor, txt_rContent, txt_rMdate;
    ImageView imageView1_img;
    String rRNUM;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle bundle ) {
        ViewGroup rootView =
                (ViewGroup) inflater.inflate(R.layout.report_fragment0, container, false);

        bundle = this.getArguments();
        if (bundle != null) {
            rRNUM = bundle.getString("key", rRNUM);
        }

        Log.d("happy", "-------------------------rRNUM--2 : " + rRNUM);
/*        new ReportSelect().execute();*/
        return rootView;
    }
/*

    public class ReportSelect extends AsyncTask<String, Void, ReportSelectItem> {

        //진행바표시
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //진행다일로그 시작
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("잠시 기다려 주세요.");
            progressDialog.show();

        }

        @Override
        protected ReportSelectItem doInBackground( String... params ) {
            try {
                Log.d("lecture", "rRNUM3333333333333333 : " + rRNUM);

                Document doc = Jsoup.connect("http://192.168.219.106:8081/guest/view_a?rNum=" + rRNUM).get();


                String my_imgUrl = doc.select(".tdimg img").attr("src");
                String rSort1 = doc.select("td #RSort").text();
                String rSort = "";

                if (rSort1.equals("0")) {
                    rSort = "실종";
                } else if (rSort1.equals("1")) {
                    rSort = "제보";
                } else {
                    rSort = "완료";
                }

                String rKind = doc.select("td #RKind").text();
                String rGender = doc.select("td #RGender").text();
                String rColor = doc.select("td #RColor").text();
                String rContent = doc.select("td #RContent").text();
                String rMdate = doc.select("td #RMdate").text();
                String rPlace = doc.select("td #RPlace").text();
                String rName = doc.select("td #RName").text();
                String rId = doc.select("td #RId").text();
                String rTel = doc.select("td #RTel").text();

                return new ReportSelectItem("http://192.168.219.106:8081" + my_imgUrl, rSort, rKind, rGender, rColor,
                        rContent, rMdate, rPlace, rName, rId, rTel);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute( ReportSelectItem result ) {
            super.onPostExecute(result);
            progressDialog.dismiss();

            txt_rSort.setText(result.getrSort());
            txt_rKind.setText(result.getrKind());
            txt_rGender.setText(result.getrGender());
            txt_rColor.setText(result.getrColor());
            txt_rContent.setText(result.getrContent());
            txt_rMdate.setText(result.getrMdate());
            txt_rPlace.setText(result.getrPlace());
            txt_rName.setText(result.getrName());
            txt_rId.setText(result.getrId());
            txt_rTel.setText(result.getrTel());

            Picasso.with(imageView1_img.getContext()).load(result.getImg_url()).into(imageView1_img);
        }
    }
*/

}

