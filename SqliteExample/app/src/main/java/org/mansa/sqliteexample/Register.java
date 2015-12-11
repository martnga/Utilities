package org.mansa.sqliteexample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by mansa on 12/11/15.
 */
public class Register extends Activity{

    EditText mUsername, mUserPassword, mConfirmPassword;
    String usernametext, userpassword_txt, confirmpassword_txt;
    Button REG_BTN;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        mUsername = (EditText)findViewById(R.id.username);
        mUserPassword = (EditText)findViewById(R.id.password);
        mConfirmPassword = (EditText)findViewById(R.id.confirm_password);
        REG_BTN = (Button) findViewById(R.id.register_btn);

        REG_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usernametext = mUsername.getText().toString();
                userpassword_txt = mUserPassword.getText().toString();
                confirmpassword_txt = mConfirmPassword.getText().toString();

                if(!userpassword_txt.equals(confirmpassword_txt)){

                    mUserPassword.setText("");
                    mConfirmPassword.setText("");
                }else{


                    DatabaseOPerations DB = new DatabaseOPerations(context);
                    DB.saveInfo(DB, usernametext, userpassword_txt);
                    Toast.makeText(getBaseContext(), "Registration Success", Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        });
    }
}
