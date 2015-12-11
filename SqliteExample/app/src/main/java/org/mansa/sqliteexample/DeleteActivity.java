package org.mansa.sqliteexample;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by mansa on 12/11/15.
 */
public class DeleteActivity extends Activity {

   String USER_NAME;
   Bundle bn;
    Button mDelBtn;
    EditText mPassword;
    String password;
     DatabaseOPerations DOP;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete);

        mPassword = (EditText) findViewById(R.id.delete_password_txt);
        mDelBtn = (Button) findViewById(R.id.delete_btn);

        bn = getIntent().getExtras();
        USER_NAME = bn.getString("user_name");

        mDelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               password = mPassword.getText().toString();

                DOP = new DatabaseOPerations(context);
                Cursor CR = DOP.getUSerPassword(DOP, USER_NAME);
                CR.moveToFirst();
                boolean loginstatus = false;

                do {
                    if(password.equals(CR.getString(0))){

                        loginstatus = true;
                    }
                }while(CR.moveToNext());


                if (loginstatus){

                    //delete user here
                    DOP.deleteUser(DOP, USER_NAME, password);
                    Toast.makeText(getBaseContext(), "Removed User " + USER_NAME, Toast.LENGTH_LONG).show();
                    finish();

                }else{

                    Toast.makeText(getBaseContext(), "Invalid User", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

    }
}
