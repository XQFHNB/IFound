package com.example.anif.base;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * @author XQF
 * @created 2017/4/13
 */
public class FragBase extends Fragment {

    protected AppCompatActivity mAty = (AppCompatActivity) getActivity();

    /**
     * 显示进度条
     *
     * @return
     */
    public ProgressDialog showSpinnerDialog() {
        //activity = modifyDialogContext(activity);
        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(true);
        dialog.setMessage("正在努力加载。。。");
        if (!getActivity().isFinishing()) {
            dialog.show();
        }
        return dialog;
    }
}
