package com.zsy.test;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import javax.xml.transform.Templates;

import butterknife.BindView;
import butterknife.OnClick;
import mzs.libapp.ctrl.BaseActivity;
import mzs.libtools.utils.CtrlHelper;
import mzs.libtools.utils.TempRepo;
import mzs.libtools.utils.ThreadUtils;
import mzs.libtools.utils.depend.DebugUtils;

public class MainActivity extends BaseActivity {

    private int count = 100;

    @BindView(R.id.tv) TextView tv;
    @BindView(R.id.btn_normal) Button normalBtn;
    @BindView(R.id.btn_pressed) Button pressedBtn;
    @BindView(R.id.btn_selected) Button selectedBtn;
    @BindView(R.id.btn_start_intent) Button startIntentBtn;
    @BindView(R.id.btn_start_map) Button startMapBtn;

    @Override
    public void initUI() {
        DebugUtils.log("main");
        setContentView(R.layout.activity_main);
    }

    @OnClick(R.id.btn_normal)
    void setTvNormal() {
        tv.setSelected(false);
        tv.setPressed(false);
        TempRepo.getInstance().put("name", "mao");
    }


    @OnClick(R.id.btn_pressed)
    void setTvPressed() {
        TempRepo.getInstance().put("gender", "female");
        tv.setPressed(true);
    }


    @OnClick(R.id.btn_selected)
    void setTvSelected() {
        tv.setSelected(true);
        count++;
        DebugUtils.log("count:" + count);
        DebugUtils.log(TempRepo.getInstance().toString());
    }

    @OnClick(R.id.btn_start_intent)
    void startIntent() {
        Intent intent = new Intent(this, SecActivity.class);
        intent.putExtra("test", "intent:" + count);
        startActivity(intent);
    }

    @OnClick(R.id.btn_start_map)
    void startMap() {
        Intent intent = new Intent(this, SecActivity.class);
        TempRepo.getInstance().put("test", "map:" + count);
        startActivity(intent);
    }

}
