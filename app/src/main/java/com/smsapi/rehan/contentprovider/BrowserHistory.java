package com.smsapi.rehan.contentprovider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.Browser;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BrowserHistory extends ActionBarActivity {
    TextView history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser_history);
        history = (TextView) findViewById(R.id.history);
        String History = "";
//        SimpleDateFormat sp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Cursor cur = getHistory();
        cur.moveToFirst();
//        String array[]=cur.getColumnNames();
//        for(String temp:array){
//            history.append(temp);
//            history.append("\n");
//
//        }
        do {
            History += cur.getString(0)+ cur.getString(1)+ cur.getString(2);
            History +="\n";
        }while (cur.moveToNext());
        history.setText(History);
    }
    private Cursor getHistory() {

        ContentResolver cr = getContentResolver();
        String[] proj = new String[]
        {
                Browser.BookmarkColumns.TITLE,
                Browser.BookmarkColumns.URL,
                Browser.BookmarkColumns.DATE,
        };
        String sel = Browser.BookmarkColumns.BOOKMARK + " = 0";

        Cursor mCur = cr.query(Browser.BOOKMARKS_URI, proj, sel, null, null);

        return mCur;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_browser_history, menu);
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
