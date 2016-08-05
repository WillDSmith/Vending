package com.guhring.vending;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.guhring.vending.utilities.Utility;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends Activity {
    ProgressDialog prgDialog;
    TextView errorMsg;
    EditText usernameET;
    EditText pwdET;
    /*CheckBox rememberCHK;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //code for hide title bar.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_login);

        errorMsg = (TextView)findViewById(R.id.login_error);
        usernameET = (EditText)findViewById(R.id.loginUsername);
        pwdET = (EditText)findViewById(R.id.loginPassword);
        /*rememberCHK = (CheckBox)findViewById(R.id.remember);*/

        prgDialog = new ProgressDialog(this);

        prgDialog.setMessage("Please wait...");
        prgDialog.setCancelable(false);

        /*logout();*/
    }

   /* public void logout(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        alertDialog.setView(inflater.inflate(R.layout.dialog_signin, null));

    *//* When positive (yes/ok) is clicked *//*
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                dialog.cancel(); // Your custom code
            }
        });

    *//* When negative (No/cancel) button is clicked*//*
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish(); // Your custom code
            }
        });
        alertDialog.show();
    }*/


    /**
     * Method gets triggered when Login button is clicked
     *
     * @param view
     */
    public void loginUser(View view){
        String username = usernameET.getText().toString();
        String password = pwdET.getText().toString();
        String rememberme = "false";
        /*Boolean result = rememberCHK.isChecked();*/
        /*String rememberme = result.toString();*/

        // Instantiate Http Request Param Object
        RequestParams params = new RequestParams();

        // When Username Edit View and Password Edit View have values other than Null
        if(Utility.isNotNull(username) && Utility.isNotNull(password)){
                // Put Http parameter username with value of Email Edit View control
                params.put("username", username);
                // Put Http parameter password with value of Password Edit Value control
                params.put("password", password);
                params.put("rememberme", rememberme);

                // Invoke RESTful Web Service with Http parameters
                invokeWS(params);

        }
        // When any of the Edit View control left blank
        else{
            Toast.makeText(getApplicationContext(), "Please fill the form, don't leave any field blank", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Method that performs RESTful webservice invocations
     *
     * @param params
     */
    public void invokeWS(RequestParams params){
        // Show Progress Dialog
        prgDialog.show();
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();

        client.get("http://staging.guhring.com/Account/LoginJson", params ,new AsyncHttpResponseHandler() {
            // When the response returned by REST has Http response code '200'
            @Override
            public void onSuccess(String response) {
                // Hide Progress Dialog
                prgDialog.hide();

                try {
                    // JSON Object
                    JSONObject obj = new JSONObject();
                    obj.put("status", response);
                    obj.put("error_msg", "Incorrect Username or Password");
                    // When the JSON response has status boolean value assigned with true
                    if(obj.getBoolean("status")){
                        Toast.makeText(getApplicationContext(), "You are successfully logged in!", Toast.LENGTH_LONG).show();
                        // Navigate to Home screen
                        navigateToNextActivity();
                    }
                    // Else display error message
                    else{
                        errorMsg.setText(obj.getString("error_msg"));
                        Toast.makeText(getApplicationContext(), obj.getString("error_msg"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                    Log.e("ERROR", String.valueOf(response));

                }
            }
            // When the response returned by REST has Http response code other than '200'
            @Override
            public void onFailure(int statusCode, Throwable error,
                                  String content) {
                // Hide Progress Dialog
                prgDialog.hide();
                // When Http response code is '404'
                if(statusCode == 404){
                    Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                }
                // When Http response code is '500'
                else if(statusCode == 500){
                    Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                }
                // When Http response code other than 404, 500
                else{
                    Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * Method which navigates from Login Activity to Home Activity
     */
    public void navigateToNextActivity(){
        Intent homeIntent = new Intent(getApplicationContext(),MachineDataActivity.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }
}
