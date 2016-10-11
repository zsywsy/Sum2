package com.zsy.libsamples;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;
import mzs.libapp.ctrl.BaseActivity;
import mzs.libtools.adapter.CommAdapter;
import mzs.libtools.utils.depend.DebugUtils;
import mzs.libui.view.TimerView;

/**
 * Created by 24275 on 2016/9/28.
 */

public class TimerActivity extends BaseActivity {

    private String[] actions = {"count", "countDown", "pause", "continue", "stop"};
    private CommAdapter<String> adapter;

    private long period = 60 * 1000;

    @BindView(R.id.timerview) TimerView timerView;
    @BindView(R.id.et_period) EditText periodEt;
    @BindView(R.id.lv_action) ListView actionLv;

    @Override
    public void initUI() {
        setContentView(R.layout.activity_timer);
    }

    @Override
    public void initData() {
        List<String> data = Arrays.asList(actions);
        adapter = new CommAdapter<String>(data, R.layout.item_samples) {
            @Override
            public void bindView(ViewHolder holder, String obj) {
                holder.setText(R.id.tv_samples, obj);
            }
        };
    }

    @Override
    public void initView() {
        actionLv.setAdapter(adapter);
        timerView.setTimeFinishLsn(new TimerView.TimeFinishLsn() {
            @Override
            public void finish(int timeStyle) {
                DebugUtils.log("finish");
            }
        });
        periodEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = periodEt.getText().toString();
                if (text.matches("[\\d]+")) {
                    period = Long.parseLong(text);
                }
            }
        });
    }


    @OnItemClick(R.id.lv_action)
    @Override
    public void doItemClick(int position) {
        switch (position) {
            case 0:
                timerView.startTm(period);
                break;
            case 1:
                timerView.startCd(period);
                break;
            case 2:
                timerView.pauseTm();
                break;
            case 3:
                timerView.continueTm();
                break;
            case 4:
                timerView.stopTm();
                break;
        }
    }
}
