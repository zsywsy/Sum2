package mzs.libapp.ctrl;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import mzs.libapp.api.ClickLsn;
import mzs.libapp.app.AppManager;
import mzs.libapp.api.CtrlApi;
import mzs.libapp.state.AppState;
import mzs.libapp.utils.AppUtils;
import mzs.libtools.utils.Lg;
import mzs.libtools.utils.depend.DebugUtils;


public abstract class BaseActivity extends FragmentActivity implements CtrlApi, ClickLsn {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Lg.i("1");
        if (AppManager.getInstance().getActivityStack().size() == 0) {
            onFirstActivityCreate();
        }
        AppManager.getInstance().addActivity(this);

        Lg.i("2");
        init();
    }


    @Override
    public void init() {
        Lg.i("ui");
        initUI();

        Lg.i("data");
        initData();
        ButterKnife.bind(this);
        Lg.i("view");
        initView();
        Lg.i("event");
        initEvent();
        Lg.i("end");
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        AppState.getInstance().setAppRunningForeground(AppUtils.isAppOnForeground(this));
    }

    @Override
    protected void onStop() {
        super.onStop();
        AppState.getInstance().setAppRunningForeground(AppUtils.isAppOnForeground(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Lg.i("size1:" + AppManager.getInstance().getActivityStack().size());
        AppManager.getInstance().removeActivity(this);
        Lg.i("size2:" + AppManager.getInstance().getActivityStack().size());
        if (AppManager.getInstance().getActivityStack().size() == 0) {
            onAllActivityDestroy();
        }
    }

    private void onFirstActivityCreate() {
    }

    private void onAllActivityDestroy() {
    }

    @Override
    public void doClick(View v) {

    }

    @Override
    public void doItemClick(int position) {
        DebugUtils.log("position:" + position);
    }
}
