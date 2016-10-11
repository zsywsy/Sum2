package mzs.libapp.ctrl;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import mzs.libapp.api.ClickLsn;
import mzs.libapp.api.CtrlApi;
import mzs.libtools.utils.depend.DebugUtils;

/**
 * Created by 24275 on 2016/6/14.
 */
public abstract class BaseFragment extends Fragment implements CtrlApi, ClickLsn {

    private LayoutInflater inflater;
    private ViewGroup container;
    private View layout;
    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        init();
        return layout;
    }


    @Override
    public void init() {
        initUI();
        initData();
        unbinder = ButterKnife.bind(this, layout);
        initView();
        initEvent();
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            return;
        }
        boolean isHidden = savedInstanceState.getBoolean("isHidden");
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if (isHidden) {
            DebugUtils.log("onRestore:hide:" + getClass().getSimpleName());
            fragmentTransaction.hide(this);
        } else {
            DebugUtils.log("onRestore:show:" + getClass().getSimpleName());
            fragmentTransaction.show(this);
        }
        fragmentTransaction.commit();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isHidden", isHidden());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected void setContentView(int layoutResID) {
        layout = inflater.inflate(layoutResID, container, false);
    }

    @Override
    public void doClick(View v) {

    }

    @Override
    public void doItemClick(int position) {
    }


    //for test
    public void pushIntoStack(int resId, Fragment fragment) {
        if (fragment == null) {
            throw new NullPointerException("fragment == null");
        }
        getFragmentManager().beginTransaction().replace(resId, fragment).addToBackStack(fragment.getClass().getName()).commit();
    }

    public void popBackStack() {
        getFragmentManager().popBackStack();
    }

}
