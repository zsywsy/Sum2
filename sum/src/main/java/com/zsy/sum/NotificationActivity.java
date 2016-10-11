package com.zsy.sum;

import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;
import mzs.libapp.ctrl.BaseActivity;
import mzs.libtools.adapter.CommAdapter;
import mzs.libtools.utils.depend.DebugUtils;

/**
 * Created by 24275 on 2016/9/19.
 */
public class NotificationActivity extends BaseActivity {


    private final String[] Datas = {"no", "simpleRetrofit", "simpleSum"};

    private CommAdapter<String> adapter;

    @BindView(R.id.lv_notification) ListView notificationLv;

    @Override
    public void initUI() {
        setContentView(R.layout.activity_notification);
    }

    @Override
    public void initData() {
        List<String> datas = Arrays.asList(Datas);
        adapter = new CommAdapter<String>(datas, R.layout.item_samples) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setText(R.id.tv_samples, obj);
            }
        };
    }

    @Override
    public void initView() {
        notificationLv.setAdapter(adapter);
    }


    @OnItemClick(R.id.lv_notification)
    @Override
    public void doItemClick(int position) {
        DebugUtils.log("position:" + position);
    }

}
