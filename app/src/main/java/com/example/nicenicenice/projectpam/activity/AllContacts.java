package com.example.nicenicenice.projectpam.activity;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.nicenicenice.projectpam.model.*;
import com.example.nicenicenice.projectpam.adapter.AllContactsAdapter;

import com.example.nicenicenice.projectpam.R;

import java.util.ArrayList;
import java.util.List;

public class AllContacts extends AppCompatActivity {

    RecyclerView rvContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_contacts);

        rvContacts = (RecyclerView) findViewById(R.id.rvContacts);

        getAllContacts();
    }

    private void getAllContacts() {
        List<ContactVO> contactVOList = new ArrayList();
        ContactVO contactVO;

        Cursor cursor = getContacts();

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                if (hasPhoneNumber > 0) {
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                    contactVO = new ContactVO();
                    contactVO.setContactName(name);

                    Cursor phoneCursor = getContentResolver().query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id},
                            null);
                    if (phoneCursor.moveToNext()) {
                        String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        contactVO.setContactNumber(phoneNumber);
                    }

                    phoneCursor.close();

                    Cursor emailCursor = getContentResolver().query(
                            ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (emailCursor.moveToNext()) {
                        String emailId = emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                    }
                    contactVOList.add(contactVO);
                }
            }

            AllContactsAdapter contactAdapter = new AllContactsAdapter(contactVOList, getApplicationContext());
            rvContacts.setLayoutManager(new LinearLayoutManager(this));
            rvContacts.setAdapter(contactAdapter);
        }
    }

    private Cursor getContacts() {
/* Run query */
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String[] projection = new String[]{ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.HAS_PHONE_NUMBER};
        String selection = ContactsContract.Contacts.IN_VISIBLE_GROUP + " = '" +
                ("1") + "'";
        String[] selectionArgs = null;
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC ";
        return managedQuery(uri, projection, selection, selectionArgs,
                sortOrder);
    }
}
