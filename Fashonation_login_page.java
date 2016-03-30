package com.example.dhruva.fashonation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.facebook.FacebookSdk;

/**
 * Created by dhruva on 27/2/16.
 */
public class Fashonation_login_page extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener
{

    int ans;
    private GoogleApiClient mGoogleApiClient;
    private LoginButton loginButton;
    private int RC_SIGN_IN = 0;
    CallbackManager callbackManager;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_fashonation_login_page);
        final EditText username = (EditText) findViewById(R.id.Login_username);
        final EditText password = (EditText) findViewById(R.id.Login_password);
        final Button login = (Button) findViewById(R.id.app_login_button);
        final TextView[] signupLink = {(TextView) findViewById(R.id.Login_notregister_button)};

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connection_class connection = new connection_class(Fashonation_login_page.this);
                connection.fasho_login_request(username.getText().toString(), password.getText().toString());
            }
        });

        signupLink[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), Fashonation_registration_page.class);
                startActivity(in);
            }
        });

        //Start of code for integrating google plus sign in button

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).addApi(Plus.API)
                .addApi(Plus.API, Plus.PlusOptions.builder().build())
                .addScope(new Scope(Scopes.PLUS_LOGIN))
                .addScope(new Scope(Scopes.PLUS_ME))
                .build();

        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setScopes(new Scope[]{Plus.SCOPE_PLUS_LOGIN});

        findViewById(R.id.sign_in_button).setOnClickListener(this);

        //End of code for integrating google plus sign in button

        //Start of code for integrating facebook sign in button

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu., menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            Intent it=new Intent(Fashonation_login_page.this,CartActivity.class);
            startActivity(it);
               /* return true;*/
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.sign_in_button:
                signIn();
                break;
        }
    }

    //Start of Overridden and other methods related to integration of google plus button

   /* protected void onStart()
    {
        super.onStart();
        // Add this line to initiate connection
        mGoogleApiClient.connect();
    }*/

    protected void onStop()
    {
        super.onStop();
        if (mGoogleApiClient.isConnected())
        {
            mGoogleApiClient.disconnect();
        }
    }

    private void signIn()
    {
        /*Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);*/
        Intent signin = new Intent(this,Fashonation_All_Products.class);
        startActivity(signin);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN)
        {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result)
    {
        if (result.isSuccess())
        {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Intent signin = new Intent(this,Fashonation_All_Products.class);
            //signin.putExtra("uname",acct.getDisplayName());
            //signin.putExtra("email_id",Plus.AccountApi.getAccountName(mGoogleApiClient));
            startActivity(signin);
            //mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
        }
        else
        {
            // Signed out, show unauthenticated UI.
            Toast.makeText(this,"Error in login!!! Please try again...",Toast.LENGTH_LONG).show();
        }
    }

    //End of Overridden and other methods related to integration of google plus button

    //Start of Overridden and other methods related to integration of facebook button

    @Override
    protected void onResume()
    {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    public void onConnected(Bundle bundle)
    {
        Intent signin = new Intent(this,Fashonation_All_Products.class);
        startActivity(signin);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult)
    {
        Toast.makeText(this,"Error in login!!! Please try again...",Toast.LENGTH_LONG).show();
    }

    //End of Overridden and other methods related to integration of facebook button
}
