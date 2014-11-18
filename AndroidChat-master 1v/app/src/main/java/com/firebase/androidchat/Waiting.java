package com.firebase.androidchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;

import java.util.Map;


public class Waiting extends Activity {

    // TODO: Change URL
    private static final String FIREBASE_URL = "https://scorching-inferno-4068.firebaseio.com";
    private String username;
    private Firebase ref, ref2;
    People p;
    private int flagJafoi = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        p = (People) intent.getSerializableExtra("obj");
        /*if (p.getClassification() == false){
            username = "Helper" + p.getId();
        }
        else
        {
            username = "Helped" + p.getId();
        }*/
        username = ""+p.whoAmI();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Make sure we have a username
        //setupUsername();
        ref = new Firebase(FIREBASE_URL).child("People");


        final Waiting t = this;
        ref.addChildEventListener(new ChildEventListener()
        {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildKey)
            {
                Map<String, Object> newPost = (Map<String, Object>) snapshot.getValue();

                if (flagJafoi==1 /*!newPost.get("classification").equals(p.whoAmI()) && newPost.get("other").equals(0)*/)
                {
                    Intent intent = new Intent(t, MainActivity.class);
                    Toast.makeText(Waiting.this, "Connected to Firebase", Toast.LENGTH_SHORT).show();
                    ref.push().setValue(p);
                    intent.putExtra("obj", (java.io.Serializable) p);
                    flagJafoi = 0;
                    startActivity(intent);

                }
                else if (flagJafoi == 2)
                {
                    flagJafoi = 1;
                }
            }

            @Override
            public void onChildChanged(DataSnapshot snapshot, String previousChildKey)
            {
                System.out.println();
            }

            @Override
            public void onChildMoved(DataSnapshot snapshot, String previousChildKey)
            {
                System.out.println();
            }

            @Override
            public void onChildRemoved(DataSnapshot snapshot)
            {
                System.out.println();
            }

            @Override
            public void onCancelled()
            {
                System.out.println();
            }


        });
        // Setup our input methods. Enter key on the keyboard or pushing the send button
        /*EditText inputText = (EditText)findViewById(R.id.messageInput);
        inputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_NULL && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    //sendMessage();
                }
                return true;
            }
        });*/

        findViewById(R.id.sendButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sendMessage();
            }
        });
        setContentView(R.layout.activity_waiting);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_waiting, menu);
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
