package com.wordpress.hyugihc.pstbeta;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Toast;

import com.wordpress.hyugihc.pstbeta.database.FeedbackContract;
import com.wordpress.hyugihc.pstbeta.database.FeedbackDbHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import au.com.bytecode.opencsv.CSVWriter;


public class ReportActivity extends AppCompatActivity {

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private final int MAX_YEAR = 25;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    /**
     * Checks if the app has permission to write to device storage
     * <p/>
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permission2 = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }

        if (permission2 != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        int year = Calendar.getInstance().get(Calendar.YEAR);
        int base = 2016;
        mViewPager.setCurrentItem(year - base, false);


        verifyStoragePermissions(ReportActivity.this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_report, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_exportCSV) {
            exportToCSV();
            return true;
        } else if (id == R.id.action_importCSV) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("file/csv");
            startActivityForResult(intent, 88);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 88) {
            if (resultCode == RESULT_OK) {
                FeedbackDbHelper feedbackDbHelper = new FeedbackDbHelper(getApplicationContext());
                SQLiteDatabase db = feedbackDbHelper.getWritableDatabase();
                Uri uri = data.getData();
                try {
                    //prompt user
                    deleteAllFeedback();
                    File fileCSV = new File(uri.getPath());
                    FileReader file = new FileReader(fileCSV);
                    BufferedReader buffer = new BufferedReader(file);
                    ContentValues contentValues = new ContentValues();
                    String line = "";
                    String tableName = FeedbackContract.FeedbackEntry.TABLE_NAME;
                    db.beginTransaction();
                    while ((line = buffer.readLine()) != null) {
                        String[] str = line.split(",");
                        contentValues.put(FeedbackContract.FeedbackEntry.COLUMN_NAME_TIMESTAMP, str[0].replaceAll("\"", ""));
                        contentValues.put(FeedbackContract.FeedbackEntry.COLUMN_NAME_RESPONSE, str[1].replaceAll("\"", ""));
                        db.insert(tableName, null, contentValues);
                    }
                    db.setTransactionSuccessful();
                    db.endTransaction();
                    Toast.makeText(getApplicationContext(), "Berhasil meload CSV \n silahkan tutup dan buka lagi aplikasi PST report", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), " Gagal meload CSV \n dengan pesan error " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }
    }

    public void deleteAllFeedback() {
        FeedbackDbHelper feedbackDbHelper = new FeedbackDbHelper(ReportActivity.this);
        feedbackDbHelper.deleteAllFeedback();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void exportToCSV() {
        File dbFile = getDatabasePath("Feedback.db");
        FeedbackDbHelper dbhelper = new FeedbackDbHelper(getApplicationContext());
        File exportDir = new File(Environment.getExternalStorageDirectory() + File.separator + "PST", "");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }
        File file = new File(exportDir, "Feedback.csv");
        try {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            Cursor curCSV = db.rawQuery("SELECT * FROM " + FeedbackContract.FeedbackEntry.TABLE_NAME, null);
            csvWrite.writeNext(curCSV.getColumnNames());
            while (curCSV.moveToNext()) {
                String arrStr[] = {curCSV.getString(0), curCSV.getString(1)};
                csvWrite.writeNext(arrStr);
            }
            csvWrite.close();
            curCSV.close();
            Toast.makeText(getApplicationContext(), "Backup database CSV berhasil \n lihat di folder PST", Toast.LENGTH_SHORT).show();
        } catch (IOException sqlEx) {
            sqlEx.printStackTrace();
            Toast.makeText(getApplicationContext(), "Backup database CSV gagal \n karena " + sqlEx.getMessage(), Toast.LENGTH_SHORT).show();
            sqlEx.printStackTrace();
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a ReportFragment (defined as a static inner class below).
            int year = 2016;
            return ReportFragment.newInstance(year + position);
        }

        @Override
        public int getCount() {
            return MAX_YEAR;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }


    }
}
