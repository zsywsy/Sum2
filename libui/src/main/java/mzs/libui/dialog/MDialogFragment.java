package mzs.libui.dialog;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * Created by 24275 on 2016/6/29.
 */
public class MDialogFragment extends DialogFragment {

    private CallBack callBack;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        View dialogView = new DialogView.Builder(getActivity())
//                .setTitle("title")
////                .setMsg("msg")
//                .setleftBtn("leftBnt", null)
//                .setRight("right", null)
//                .create();
//
//        return new AlertDialog.Builder(getActivity()).setView(dialogView).create();

//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
////        DebugUtils.log(getArguments().get("test").toString());
////        View dialogView = new DialogView.Builder(inflater)
////                .setTitle("title")
////                .setMsg("msg")
////                .setleftBtn("leftBnt", null)
////                .setRight("right", null)
////                .create();
//        return dialogView;
        return callBack == null ? null : callBack.createView();
    }

    public interface CallBack {
        View createView();
    }

}
