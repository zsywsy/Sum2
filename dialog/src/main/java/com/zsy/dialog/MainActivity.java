package com.zsy.dialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.OnClick;
import mzs.libapp.app.AppManager;
import mzs.libapp.ctrl.BaseActivity;
import mzs.libtools.utils.Lg;
import mzs.libtools.utils.depend.DebugUtils;

public class MainActivity extends BaseActivity {


    private LinearLayout ll;

    @Override
    public void initUI() {
        setContentView(R.layout.activity_main);
        ll = (LinearLayout) findViewById(R.id.activity_main);
        Button btn = new Button(this);
//        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        btn.setText("button");
        ll.addView(btn);
        Lg.i("ll:" + ll.getTag());
    }


    @BindView(R.id.btn_show) Button showBtn;
    @BindView(R.id.btn_hide) Button hideBtn;
    //
    FDialog fDialog;


    @Override
    public void initData() {
        fDialog = new FDialog();
        if (root != null) {
            params = root.generateLayoutParams(attrs);
            if (!attachToRoot) {
                temp.setLayoutParams(params);
            }
        }

    }

    @OnClick(R.id.btn_show)
    void showDialog() {
        RelativeLayout parent = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.parent, null);

        Lg.i("child:" + parent.getChildCount());
        View child = LayoutInflater.from(this).inflate(R.layout.child, parent, false);
        Lg.i("eq:" + (child == parent));
        Lg.i("child:" + parent.getChildCount());
//        fDialog.show(getSupportFragmentManager(), "test");
    }

    @OnClick(R.id.btn_hide)
    void hideDialog() {
        RelativeLayout parent = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.parent, null);
        Lg.i("child:" + parent.getChildCount());
        View child = LayoutInflater.from(this).inflate(R.layout.child, parent, true);
        Lg.i("eq:" + (child == parent));
        Lg.i("child:" + parent.getChildCount());

//        View view = LayoutInflater.from(this).inflate(R.layout.view, null);
//        ll.addView(view);
    }

}
