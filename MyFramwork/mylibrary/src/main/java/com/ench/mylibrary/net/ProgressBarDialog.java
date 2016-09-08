package com.ench.mylibrary.net;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import com.ench.mylibrary.R;

/**
 * Created by ench on 16/7/6.
 */
public class ProgressBarDialog {

    private final Window window;
    private final Dialog dialog;

    public ProgressBarDialog(Context context) {
        dialog = new Dialog(context, R.style.progressDialog);
        window = dialog.getWindow();
        window.setContentView(R.layout.progress_bar_dialog);


    }

    public void show() {
        dialog.show();
//        window.findViewById(R.id.avloadingIndicatorView).setVisibility(View.VISIBLE);
    }

    public void dismiss() {
        dialog.dismiss();
//        window.findViewById(R.id.avloadingIndicatorView).setVisibility(View.GONE);
    }
}
