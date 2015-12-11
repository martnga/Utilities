package org.mansa.sqliteexample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by mansa on 12/11/15.
 */
public class LoginActivity  extends Activity{

    Button mLoginBtn;
    EditText mLoginUserText, mLoginUserPasswordText;
    String username, password;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mLoginUserText = (EditText)findViewById(R.id.login_username);
        mLoginUserPasswordText = (EditText)findViewById(R.id.login_password);
        mLoginBtn = (Button)findViewById(R.id.login_btn);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = getIntent().getExtras();
                int status = b.getInt("status");

                if (status == 2){

                   username = mLoginUserText.getText().toString();
                   password = mLoginUserPasswordText.getText().toString();

                    DatabaseOPerations DOP = new DatabaseOPerations(context);
                    Cursor CR = DOP.getInformation(DOP);
                    CR.moveToFirst();
                    boolean loginstatus = false;
                    String Name = "";

                    do {

                        if(username.equals(CR.getString(0)) && (password.equals(CR.getString(1))));{
                            loginstatus = true;
                            Name = CR.getString(0);
                        }

                    }while(CR.moveToNext());

                    if (loginstatus){

                        Toast.makeText(getBaseContext(), "Success " + Name, Toast.LENGTH_LONG ).show();
                        finish();
                    }else{
                        Toast.makeText(getBaseContext(), "Errrrrror", Toast.LENGTH_LONG ).show();
                        finish();
                    }

                }else if(status == 3){

                    username = mLoginUserText.getText().toString();
                    password = mLoginUserPasswordText.getText().toString();

                    DatabaseOPerations DOP = new DatabaseOPerations(context);
                    Cursor CR = DOP.getInformation(DOP);
                    CR.moveToFirst();
                    boolean loginstatus = false;
                    String Name = "";

                    do {

                        if(username.equals(CR.getString(0)) && (password.equals(CR.getString(1))));{
                            loginstatus = true;
                            Name = CR.getString(0);
                        }

                    }while(CR.moveToNext());

                    if (loginstatus){

                        Toast.makeText(getBaseContext(), "Success " + Name, Toast.LENGTH_LONG ).show();

                        Intent i = new Intent(LoginActivity.this, DeleteActivity.class);
                        Bundle B = new Bundle();
                        B.putString("user_name", Name);
                        i.putExtras(B);
                        startActivity(i);

                        finish();
                    }else{
                        Toast.makeText(getBaseContext(), "Errrrrror", Toast.LENGTH_LONG ).show();
                        finish();
                    }


                }
            }
        });
    }
}
