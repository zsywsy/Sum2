package com.zsy.test;

import android.content.Intent;
import android.telecom.TelecomManager;
import android.widget.TextView;

import butterknife.BindView;
import mzs.libapp.ctrl.BaseActivity;
import mzs.libtools.utils.TempRepo;
import mzs.libtools.utils.depend.DebugUtils;

/**
 * Created by 24275 on 2016/9/29.
 */

public class SecActivity extends BaseActivity {

    @BindView(R.id.tv_show_intent) TextView showIntentTv;
    @BindView(R.id.tv_show_map) TextView showMapTv;

    @Override
    public void initUI() {
        setContentView(R.layout.activity_sec);
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        DebugUtils.log("initView");
        String intentText = "intentText";
        String mapText = "mapText";
        if (intent != null) {
            String text1 = intent.getStringExtra("test");
            DebugUtils.log("intent:" + text1);
            if (text1 != null) {
                intentText = text1;
            }
        }

        String text2 = (String) TempRepo.getInstance().get("test");
        DebugUtils.log("map:" + text2);
        if (text2 != null) {
            mapText = text2;
        }

        showIntentTv.setText(intentText);
        showMapTv.setText(mapText);


        DebugUtils.log("temp:" + TempRepo.getInstance().get("test"));

    }
}
