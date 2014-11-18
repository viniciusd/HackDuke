package com.firebase.androidchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firebase.client.Firebase;
//import android.app.ListActivity;
//import android.content.SharedPreferences;
//import android.database.DataSetObserver;
//import android.os.Bundle;
//import android.view.KeyEvent;
//import android.view.View;
//import android.view.inputmethod.EditorInfo;


public class StressBuddy extends Activity {

    private Firebase ref;
    private static final String FIREBASE_URL = "https://scorching-inferno-4068.firebaseio.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stress_buddy);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stress_buddy, menu);
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

    public void someHelpChat(View view)
    {
        Intent intent = new Intent(this, Waiting.class);
       // EditText editText = (EditText) findViewById(R.id.edit_message); // maybe R.id.messageInput?, message?, textView? importei um negocio pra corrigir o Edittext
       // String message = editText.getText().toString();
        People p = new People(false);
        ref = new Firebase(FIREBASE_URL).child("People");
        ref.push().setValue(p);

        intent.putExtra("obj", (java.io.Serializable) p); //tem que definir a string EXTRA_MESSAGE,
        startActivity(intent);
    }

    public void toHelpChat(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        // EditText editText = (EditText) findViewById(R.id.edit_message); // maybe R.id.messageInput?, message?, textView? importei um negocio pra corrigir o Edittext
        // String message = editText.getText().toString();
        People p = new People(true);
        ref = new Firebase(FIREBASE_URL).child("People");
        ref.push().setValue(p);

        intent.putExtra("obj", (java.io.Serializable) p); //tem que definir a string EXTRA_MESSAGE,
        startActivity(intent);
    }
}
