package com.example.dhruva.fashonation;

import android.app.Dialog;
import android.preference.PreferenceActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.mikepenz.iconics.context.IconicsLayoutInflater;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.mikepenz.materialdrawer.DrawerBuilder;

public class Fashonation_login_page extends AppCompatActivity
{
    Button login,login_fb,btn_register;
    EditText username, password;
    CheckBox remember_me;
    public String usrnm, pswd;
    public int success;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        //LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));

        setContentView(R.layout.activity_fashonation_login_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        new DrawerBuilder().withActivity(this).build();
        login = (Button) findViewById(R.id.Login_button);
        login_fb = (Button)findViewById(R.id.Login_fb_button);
        username = (EditText) findViewById(R.id.Login_username);
        password = (EditText) findViewById(R.id.Login_password);
        remember_me = (CheckBox) findViewById(R.id.Login_remember_me);
        btn_register = (Button)findViewById(R.id.Login_notregister_button);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(Fashonation_login_page.this, Fashonation_signup_page.class);
                startActivity(it);
            }
        });

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.i("log", "after pressing login");
                usrnm = username.getText().toString().trim();
                pswd = password.getText().toString();

                if (usrnm.equals("") || pswd.equals(""))
                {
                    Log.i("Empty fields", "fields are empty");
                    Toast.makeText(Fashonation_login_page.this, "Email or Password may be empty. ", Toast.LENGTH_LONG).show();
                }
                else if (usrnm.matches(emailPattern))
                {
                    Log.i("email ", "valid email id");
                    JSONObject obj = new JSONObject();
                    StringEntity mStringEntity = null;
                    try
                    {
                        obj.put("email_id", username.getText() + "");
                        obj.put("password", password.getText() + "");
                       /* if(e1!=null)
                        {
                            return Patterns.EMAIL_ADDRESS.matcher(e1).matches();
                        }*/
                        Log.i("inside 1st try", "");
                        try
                        {

                            mStringEntity = new StringEntity(obj.toString());
                        }
                        catch (UnsupportedEncodingException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                    ConnectivityManager cm = (ConnectivityManager)Fashonation_login_page.this.getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo activeNetwork=cm.getActiveNetworkInfo();
                    boolean isconnected=activeNetwork!=null&&activeNetwork.isConnectedOrConnecting();
                    if(isconnected)
                    {
                        AsyncHttpClient client = new AsyncHttpClient();
                        client.addHeader("Accept", "application/json");
                        client.addHeader("Content-Type", "application/json");
                        client.post(Fashonation_login_page.this, "http://fashonation.com/api/v1/nx9_user.php", mStringEntity, "application/json", new JsonHttpResponseHandler() {
                            ProgressDialog mProgressDialog;

                            @Override
                            public void onSuccess(int statusCode, Header[] headers, JSONObject response)
                            {
                                super.onSuccess(statusCode, headers, response);
                                try
                                {

                                    // JSONArray mJsonArray=response.getJSONArray("d");
                                    success = response.getInt("user_info");
                                    Log.i("success", success + "");


                                    //         Log.i("in success","1");
                                    if (success == 1)
                                    {
                                        Fashonation_login_class.l = 1;
                                        Log.i("result found", "1");
                                        Fashonation_login_class.email = username.getText().toString();
                                        Fashonation_login_class.password = password.getText().toString();
                                        Intent it = new Intent(Fashonation_login_page.this, CartActivity.class);
                                        Toast.makeText(Fashonation_login_page.this, "Login successfully.", Toast.LENGTH_LONG).show();
                                        startActivity(it);
                                    }
                                    else
                                    {
                                        Fashonation_login_class.l = 0;
                                        Log.i("result not found", "0");
                                        Intent it = new Intent(Fashonation_login_page.this, Fashonation_signup_page.class);
                                        Toast.makeText(Fashonation_login_page.this, "No records found.", Toast.LENGTH_LONG).show();
                                        startActivity(it);

                                    }
                                }
                                catch (JSONException e)
                                {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse)
                            {
                                super.onFailure(statusCode, headers, throwable, errorResponse);
                                Log.i("in failure", "1");
                            }

                            @Override
                            public void onStart()
                            {
                                super.onStart();
                                mProgressDialog = ProgressDialog.show(Fashonation_login_page.this, "Loading", "Please Wait", true, false);
                            }

                            @Override
                            public void onFinish()
                            {
                                super.onFinish();
                                if (mProgressDialog.isShowing())
                                {
                                    mProgressDialog.dismiss();
                                }
                            }
                        });
                    }
                    else
                    {
                        AlertDialog.Builder builder=new AlertDialog.Builder(Fashonation_login_page.this);
                        builder.setTitle("Warning!");
                        builder.setMessage("No Data Connection Found,Please Turn On Data Connection From Settings And Try Again .");
                        builder.setNeutralButton("Ok",new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                dialog.dismiss();
                            }
                        });
                        builder.setIcon(R.mipmap.ic_launcher);
                        builder.setCancelable(false);
                        AlertDialog dialog=builder.create();
                        dialog.show();
                    }
                }
                else
                {
                    Log.i("email id","invalid type");
                    Toast.makeText(Fashonation_login_page.this,"Email id should be in proper format.",Toast.LENGTH_LONG).show();
                }
            }

        });


    }
}






