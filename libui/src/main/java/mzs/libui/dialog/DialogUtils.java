package mzs.libui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import mzs.libtools.utils.depend.DebugUtils;
import mzs.libui.R;

/**
 * Created by 24275 on 2016/6/3.
 */
public class DialogUtils {

    private static ProgressDialog progressDialog;

    public static synchronized void showProgressDialog(Context context, String title, String msg) {
        progressDialog = ProgressDialog.show(context, title, msg, false, false, new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                DebugUtils.log("haha");
                hideProgressDialog();
            }
        });
        progressDialog.show();
    }

    public static synchronized void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public static AlertDialog getADialog(Context context, View dialogView) {
        return new AlertDialog.Builder(context)
                .setView(dialogView)
                .create();
    }

    public static void dismiss(Dialog dialog) {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public static void show(Dialog dialog) {
        if (dialog != null) {
            dialog.show();
        }
    }

}
