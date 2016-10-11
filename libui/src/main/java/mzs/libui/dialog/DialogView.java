package mzs.libui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import mzs.libui.R;

/**
 * Created by 24275 on 2016/6/24.
 */
public class DialogView {
    public static class Builder {

        private LayoutInflater inflater;

        private View dialogView;
        private TextView titleTv;
        private TextView msgTv;
        private Button leftBtn;
        private Button rightBtn;

        private View.OnClickListener leftLsn;
        private View.OnClickListener rightLsn;


        public Builder(Context context) {
            inflater = LayoutInflater.from(context);
            dialogView = inflater.inflate(R.layout.dialog_view, null);
        }

        public Builder(LayoutInflater inflater) {
            dialogView = inflater.inflate(R.layout.dialog_view, null);
        }


        public Builder setTitle(CharSequence title) {
            titleTv = (TextView) dialogView.findViewById(R.id.id_tv_title);
            titleTv.setVisibility(View.VISIBLE);
            titleTv.setText(title);
            return this;
        }

        public Builder setMsg(CharSequence msg) {
            msgTv = (TextView) dialogView.findViewById(R.id.id_tv_msg);
            msgTv.setVisibility(View.VISIBLE);
            msgTv.setText(msg);
            return this;
        }

        public Builder setleftBtn(CharSequence text, View.OnClickListener onClickListener) {
            leftBtn = (Button) dialogView.findViewById(R.id.id_btn_left);
            leftBtn.setVisibility(View.VISIBLE);
            leftBtn.setText(text);
            leftBtn.setOnClickListener(onClickListener);
            return this;
        }

        public Builder setRight(CharSequence text, View.OnClickListener onClickListener) {
            rightBtn = (Button) dialogView.findViewById(R.id.id_btn_right);
            rightBtn.setVisibility(View.VISIBLE);
            rightBtn.setText(text);
            rightBtn.setOnClickListener(onClickListener);
            return this;
        }

        public View create() {
            return dialogView;
        }
    }
}
