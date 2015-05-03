package com.smsapi.rehan.contentprovider;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
public class MainActivity extends ActionBarActivity {
    TextView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (TextView) findViewById(R.id.NAME);
        String name="";
        Cursor cur = getContacts();
        cur.moveToFirst();
        do{
            name += cur.getString(1);
//          name += "";
//          name += cur.getString(cur.getColumnIndex(ContactsContract.Data.HAS_PHONE_NUMBER));
            name += "\n";
            String hasPhone = cur.getString(2);
            String id = cur.getString(0);
            if (hasPhone.equalsIgnoreCase("1")) {
                Cursor phones = getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id,
                        null, null);
                phones.moveToFirst();
                name += phones.getString(phones.getColumnIndex("data1"));
                name +="\n\n";
            }
        }while (cur.moveToNext());
        list.setText(name);
    }
    private Cursor getContacts() {
        Cursor cursor;
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String[] projection = new String[] {
              ContactsContract.Contacts._ID,
              ContactsContract.Contacts.DISPLAY_NAME,
              ContactsContract.Contacts.HAS_PHONE_NUMBER
        };
        String selection = ContactsContract.Contacts.IN_VISIBLE_GROUP + " = '"
                + ("1") + "'";
        String[] selectionArgs = null;
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME
                + " COLLATE LOCALIZED ASC";
        cursor = managedQuery(uri, projection, selection, selectionArgs,sortOrder);
        return   cursor;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        // no inspection Simplifiable If Statement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
