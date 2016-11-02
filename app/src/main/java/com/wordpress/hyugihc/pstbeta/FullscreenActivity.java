package com.wordpress.hyugihc.pstbeta;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.wordpress.hyugihc.pstbeta.database.FeedbackContract;
import com.wordpress.hyugihc.pstbeta.database.FeedbackDbHelper;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    private View mControlsView;
    private ImageButton imageButtonSangatPuas, imageButtonTidakPuas, imageButtonPuas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        imageButtonPuas = (ImageButton) findViewById(R.id.plain_image_button);
        imageButtonSangatPuas = (ImageButton) findViewById(R.id.satisfied_image_button);
        imageButtonTidakPuas = (ImageButton) findViewById(R.id.unsatisfied_image_button);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        final FeedbackDbHelper feedbackDbHelper = new FeedbackDbHelper(getApplicationContext());
        final SQLiteDatabase db = feedbackDbHelper.getWritableDatabase();

        imageButtonPuas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(FeedbackContract.FeedbackEntry.COLUMN_NAME_RESPONSE, FeedbackContract.FeedbackEntry.COLUMN_CONTENT_PUAS);
                db.insert(FeedbackContract.FeedbackEntry.TABLE_NAME,
                        null,
                        values);
                Snackbar.make(v, "Anda puas, Terima kasih atas masukan anda", Snackbar.LENGTH_SHORT).show();
            }
        });

        imageButtonTidakPuas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(FeedbackContract.FeedbackEntry.COLUMN_NAME_RESPONSE, FeedbackContract.FeedbackEntry.COLUMN__CONTENT_TIDAKPUAS);
                db.insert(FeedbackContract.FeedbackEntry.TABLE_NAME,
                        null,
                        values);
                Snackbar.make(v, "Anda tidak puas, Terima kasih atas masukan anda", Snackbar.LENGTH_SHORT).show();
            }
        });

        imageButtonSangatPuas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(FeedbackContract.FeedbackEntry.COLUMN_NAME_RESPONSE, FeedbackContract.FeedbackEntry.COLUMN_CONTENT_SANGATPUAS);
                db.insert(FeedbackContract.FeedbackEntry.TABLE_NAME,
                        null,
                        values);
                Snackbar.make(v, "Anda sangat puas, Terima kasih atas masukan anda", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }


}
