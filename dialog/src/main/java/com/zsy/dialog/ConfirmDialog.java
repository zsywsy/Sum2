package com.zsy.dialog;

import android.app.DialogFragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by 24275 on 2016/9/30.
 */

public class ConfirmDialog extends DialogFragment {


    public static class Builder {

        private LayoutInflater inflater;
        private int layoutResId = R.layout.dialog_view;

        private String title;
        private String msg;
        private String leftText;
        private String rightText;

        private View rootView;

        private View.OnClickListener leftLsn;
        private View.OnClickListener rightLsn;


        public Builder(Context context) {
            inflater = LayoutInflater.from(context);
        }

        public void setLayout(int layoutResId) {
            this.layoutResId = layoutResId;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public void setLeftText(String leftText, View.OnClickListener leftLsn) {
            this.leftText = leftText;
            this.leftLsn = leftLsn;
        }

        public void setRightText(String rightText, View.OnClickListener rightLsn) {
            this.rightText = rightText;
            this.rightLsn = rightLsn;
        }

        public void build() {
            rootView = inflater.inflate(layoutResId, null);
            initTitleTv();
            initMsgTv();
            initLeftTv();
            initRightTv();
        }

        private void initTitleTv() {
            if (leftText == null) {
                return;
            }


        }

        private void initMsgTv() {

        }

        private void initLeftTv() {

        }

        private void initRightTv() {
        }


    }

}
