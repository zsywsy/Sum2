package com.zsy.libsamples.TabLayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.TextView;

import com.zsy.libsamples.R;

import butterknife.BindView;
import mzs.libapp.ctrl.BaseActivity;
import mzs.libtools.utils.depend.DebugUtils;
import mzs.libui.view.TabLayout;

/**
 * Created by 24275 on 2016/9/21.
 */

public class TabLayoutActivity extends BaseActivity {


//    private final Fragment[] fragments = {new TabFragment1(), new TabFragment2(), new TabFragment3(), new TabFragment4()};

    private final Fragment[] fragments = new Fragment[4];

    private int tempFragmentPosition = -1;

    @BindView(R.id.tl_bottom) TabLayout bottomTl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getSupportFragmentManager();
        if (savedInstanceState == null) {
            DebugUtils.log("init");
            fragments[0] = new TabFragment1();
            fragments[1] = new TabFragment2();
            fragments[2] = new TabFragment3();
            fragments[3] = new TabFragment4();
            FragmentUtils.loadMultipleRootTransaction(fm, R.id.fl_container, -1, fragments);
        } else {
            DebugUtils.log("onRestore");
            fragments[0] = FragmentUtils.findStackFragment(fm, TabFragment1.class);
            fragments[1] = FragmentUtils.findStackFragment(fm, TabFragment2.class);
            fragments[2] = FragmentUtils.findStackFragment(fm, TabFragment3.class);
            fragments[3] = FragmentUtils.findStackFragment(fm, TabFragment4.class);
        }
        if (savedInstanceState == null || !savedInstanceState.getBoolean("test", false)) {
            DebugUtils.log("setDefault");
            bottomTl.setSelected(0);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("test", true);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            DebugUtils.log(getSupportFragmentManager().getFragments().toString());
//            return true;
//        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void initUI() {
        setContentView(R.layout.activity_tablayout);
    }

    @Override
    public void initView() {
        bottomTl.setOnSelectedChangedLsn(new TabLayout.onSelectedChangedLsn() {
            @Override
            public void onSelected(int position) {
                if (position == tempFragmentPosition) {
                    return;
                }
                Fragment from = null;
                Fragment to = fragments[position];
                if (tempFragmentPosition >= 0) {
                    from = fragments[tempFragmentPosition];
                }
                tempFragmentPosition = position;
                FragmentUtils.showHideFragment(getSupportFragmentManager(), to, from);
            }

            @Override
            public void onUnSelected(int position) {

            }
        });
        bottomTl.setTextColor(0xff8A94AD, 0xff60A4E5);
        bottomTl.setData(new TabLayout.Entity[]{bottomTl.new Entity("首页", R.mipmap.icon_tab_home_normal, R.mipmap.icon_tab_home_press), bottomTl.new Entity("功能介绍", R.mipmap.icon_tab_annc_normal, R.mipmap.icon_tab_annc_press), bottomTl.new Entity("相关公告", R.mipmap.icon_tab_intro_normal, R.mipmap.icon_tab_intro_press), bottomTl.new Entity("关于我们", R.mipmap.icon_tab_about_normal, R.mipmap.icon_tab_about_press)});

    }
}
