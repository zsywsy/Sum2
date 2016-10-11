package com.zsy.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

/**
 * Created by 24275 on 2016/9/29.
 */

public class FDialog extends DialogFragment {

//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        Activity activity = getActivity();
//        if (activity == null) {
//            return null;
//        }
//        AlertDialog.Builder builder = new AlertDialog.
//                Builder(activity)
//                .setTitle("title")
//                .setMessage("msg")
//                .setIcon(R.mipmap.ic_launcher)
//                .setPositiveButton("pos", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        DebugUtils.log("pos click");
//                    }
//                });
//        return builder.create();
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View rootView = inflater.inflate(R.layout.dialog_view,null);
        TextView titleTv = (TextView) rootView.findViewById(R.id.tv_title);
        TextView msgTv = (TextView) rootView.findViewById(R.id.tv_msg);
        titleTv.setText("I'm title");
        msgTv.setText("I'm msg");
        return rootView;
    }
}
