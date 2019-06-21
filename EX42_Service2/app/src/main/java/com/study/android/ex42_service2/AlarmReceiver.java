package com.study.android.ex42_service2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "지정한 시간입니다.",
                Toast.LENGTH_LONG).show();
    }
}
//[ 리피트 사용시 주의사항 ]
//see the 60000 argument pass there? Before kitkat you can set that
// to what ever milliseconds you like, but on kitkat onwards,
// the minimum is 1 min (60000 milliseconds).
// This is due to battery saving feature introduce on kitkat version.
//
//kitkat을 사용하기 전에 원하는 것을 밀리 초 단위로 설정할 수 있지만
// kitkat 이후에는 최소값이 1 분 (60000 밀리 초)입니다.
// 이것은 배터리 절약 기능이 kitkat 버전에서 소개 되었기 때문입니다.
