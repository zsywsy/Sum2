package mzs.libapp.api;

/**
 * Created by 24275 on 2016/5/24.
 */
public interface CtrlApi {

    void init();

    /**
     * 设置布局
     */
    void initUI();

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 初始化组件
     */
    void initView();

    /**
     * 初始化事件
     */
    void initEvent();

}
