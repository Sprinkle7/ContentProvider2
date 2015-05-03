package com.smsapi.rehan.contentprovider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Messages extends ActionBarActivity {
    TextView Msgs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        Msgs = (TextView)findViewById(R.id.msg);
        String DisplayName = "";
        Cursor cur = getMessages();
        cur.moveToFirst();
        do {
            DisplayName += cur.getString(0) + cur.getString(1) + cur.getString(2);
            DisplayName +="\n";
        }while (cur.moveToNext());
        Msgs.setText(DisplayName);
    }
    private Cursor getMessages() {
        Uri Inbox = Uri.parse("content://sms/inbox");
        String[] requiredColumns = new  String[]{"address","body","type","date"};
        ContentResolver cr = getContentResolver();
        Cursor cur  = cr.query(Inbox,requiredColumns,null,null,null);
        return cur;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_messages, menu);
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
