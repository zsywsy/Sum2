package com.zsy.sum;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import mzs.libapp.ctrl.BaseActivity;
import mzs.libtools.utils.IterateIntent;
import mzs.libtools.utils.depend.DebugUtils;

/**
 * Created by 24275 on 2016/9/21.
 */

public class GcSaveActivity extends BaseActivity {

    private static final String staticConstData = "staticConstData";
    private final String constData = "constData";

    private static String staticData = "staticData";
    private String data = "constData";

    private int count = 0;

    @BindView(R.id.et) EditText et;
    @BindView(R.id.cb) CheckBox cb;
    @BindView(R.id.btn) Button btn;

    @Override
    public void initUI() {
        DebugUtils.log("onCreate");
        setContentView(R.layout.activity_gc_save);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        DebugUtils.log("onSave");
        if (cb.isChecked()) {
            DebugUtils.log("set count");
            outState.putInt("count", count);
        } else {
            DebugUtils.log("not save");
        }
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        DebugUtils.log(IterateIntent.iterateBundle(savedInstanceState, new StringBuilder()).toString());
        DebugUtils.log("onRestore");
        if (savedInstanceState != null) {
            DebugUtils.log("not null");
            DebugUtils.log("get count");
            count = savedInstanceState.getInt("count");
        } else {
            DebugUtils.log("null");
        }
    }


    @OnClick(R.id.btn)
    void logData() {
        count++;
        DebugUtils.log(this.toString());
    }

    @Override
    public String toString() {
        return "GcSaveActivity{" +
                "staticConstData=" + staticConstData + '\'' +
                "，staticData=" + staticData + '\'' +
                "，constData='" + constData + '\'' +
                ", data='" + data + '\'' +
                ", et=" + et.getText() +
                ", count=" + (count) +
                '}';
    }
}
