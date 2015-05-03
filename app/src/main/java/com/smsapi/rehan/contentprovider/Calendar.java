package com.smsapi.rehan.contentprovider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Calendar extends ActionBarActivity {
//    public static final String[] EVENT_PROJECTION = new String[] {
//            CalendarContract.Calendars._ID,                           // 0
//            CalendarContract.Calendars.ACCOUNT_NAME,                  // 1
//            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,         // 2
//            CalendarContract.Calendars.OWNER_ACCOUNT                  // 3
//    };
//
//    // The indices for the projection array above.
//    private static final int PROJECTION_ID_INDEX = 0;
//    private static final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
//    private static final int PROJECTION_DISPLAY_NAME_INDEX = 2;
//    private static final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;
    TextView calender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calender = (TextView)findViewById(R.id.calender);
//        long calID = 0;
//        String displayName = null;
//        String accountName = null;
//        String ownerName = null;
        String data = "";
        Cursor cur = getCalender();
        cur.moveToFirst();
        do {
            data += cur.getString(0)+cur.getString(1)+cur.getString(2)+cur.getString(3)+cur.getString(4)+cur.getString(5);
            data +="\n";
        }while (cur.moveToNext());
        calender.setText(data);
//        while (cur.moveToNext()) {
//            // Get the field values
//            calID = cur.getLong(PROJECTION_ID_INDEX);
//            displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
//            accountName = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX);
//            ownerName = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX);
//            Log.d("CalID",String.valueOf(calID));
//            Log.d("DisplayNames",String.valueOf(calID));
//            Log.d("CalID",String.valueOf(calID));
//            Log.d("CalID",String.valueOf(calID));
    }

    private Cursor getCalender() {
        Cursor cursor = null;
        ContentResolver cr = getContentResolver();
        Uri uri = Uri.parse("content://com.android.calendar/events");
        String[] projection = new String[]{
                "calendar_id","title","description","dtstart","dtend","eventLocation"
        };
       // String selection = "((" + CalendarContract.Calendars.ACCOUNT_NAME + " = ?) AND ("
         //       + CalendarContract.Calendars.ACCOUNT_TYPE + " = ?) AND ("
           //     + CalendarContract.Calendars.OWNER_ACCOUNT + " = ?))";
//        String selectionArgs = null;
        cursor = cr.query(uri,projection, null, null, null);
        return cursor;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calendar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
