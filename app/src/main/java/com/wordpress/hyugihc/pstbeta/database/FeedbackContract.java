package com.wordpress.hyugihc.pstbeta.database;

import android.provider.BaseColumns;

/**
 * Created by hyugihc on 3/18/2016.
 */
public class FeedbackContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public FeedbackContract() {}

    /* Inner class that defines the table contents */
    public static abstract class FeedbackEntry implements BaseColumns {
        public static final String TABLE_NAME = "feedback";
        public static final String COLUMN_NAME_TIMESTAMP = "timestamp";
        public static final String COLUMN_NAME_RESPONSE = "response";

        public static final String[] TABLE_COLUMN= {FeedbackContract.FeedbackEntry.COLUMN_NAME_TIMESTAMP,
                FeedbackEntry.COLUMN_NAME_RESPONSE};
        //content
        public static final String COLUMN_CONTENT_SANGATPUAS = "S";
        public static final String COLUMN__CONTENT_TIDAKPUAS = "U";
        public static final String COLUMN_CONTENT_PUAS = "P";

    }

}
