package abanoub.johnny.development.moviesapp.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import abanoub.johnny.development.moviesapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Abanoub Maher on 6/11/17.
 */

public class ProgressDialogNew extends Dialog {


    public Activity activity;
    Context context;
    private final static int CHAT_SOUND_PERMISSION = 1;
    private final static int TRIP_SOUND_PERMISSION = 2;
    @BindView(R.id.progress_text)
    TextView textView;
    String textContent;

    public ProgressDialogNew(Activity activity, Context context, String text, int theme) {
        super(activity,theme);
        this.activity = activity;
        this.context = context;
        this.textContent = text;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.progress_dialog_new);
        ButterKnife.bind(this);
        textView.setText(textContent);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        dismiss();
    }
}