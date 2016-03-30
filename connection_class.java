package  com.example.dhruva.fashonation;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dhruva on 9/3/16.
 */
public class connection_class
{

    Context context;

    //MAIN URL
    String main_url="http://fashonation.com/api/v1/";
    public connection_class(Context con)
    {
        context=con;
    }


    public void fasho_login_request(String email,String password)
    {
        class  login extends AsyncTask<String , Void ,String>
        {
            private Context con;
            private Dialog loading;
            private String email,password;
            public login(Context context)
            {
                con = context;
            }

            public void onPreExecute()
            {
                super.onPreExecute();
                loading = ProgressDialog.show(con, "Please Wait", "login in..");
            }

            public String doInBackground(String... params)
            {
                email=params[0];
                password=params[1];
                Log.d("email",email);
                String data = null;
                String login_url=main_url+"user.php";
                String result="";
                try
                {
                    data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                    data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                    Log.d("login_url",login_url);
                    URL url = new URL(login_url);
                    URLConnection connection = url.openConnection();
                    connection.setDoOutput(true);
                    //Writting
                    OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                    writer.write(data);
                    writer.flush();
                    //getting the input
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;

                    while ((line = reader.readLine()) != null)
                    {
                        result = result + line;
                    }

                }
                catch (UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                }
                catch (MalformedURLException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                Log.d("result", result);
                return result;

            }
            public void onPostExecute(String result)
            {
                if (loading != null)
                {
                    loading.dismiss();
                }
                json_parse json_parse=new json_parse(con);
                String response_message=json_parse.parse_json(result);
                Log.d("response_msg",response_message);
                if(response_message.equals("Success"))
                {
                    Intent intent=new Intent(con,CartActivity.class);
                    con.startActivity(intent);
                }
                Toast.makeText(con,response_message,Toast.LENGTH_SHORT).show();

            }
        }
        login login=new login(context);
        login.execute(email, password);
    }

    public void fasho_regis_request(String uname,String email,String password)
    {
        class  regis extends AsyncTask<String , Void ,String>
        {
            private Context con;
            private Dialog loading;
            private String uname,email,password;
            public regis(Context context)
            {
                con = context;
            }

            public void onPreExecute()
            {
                super.onPreExecute();
                loading = ProgressDialog.show(con, "Please Wait", "loading..");
            }

            public String doInBackground(String... params)
            {
                uname = params[0];
                email = params[1];
                password = params[2];
                Log.d("email",email);
                String data = null;
                String regis_url=main_url+"add_user.php";
                String result="";
                try
                {
                    data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(uname, "UTF-8");
                    data += "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                    data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                    Log.d("regis_url",regis_url);
                    URL url = new URL(regis_url);
                    URLConnection connection = url.openConnection();
                    connection.setDoOutput(true);
                    //Writing
                    OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                    writer.write(data);
                    writer.flush();
                    //getting the input
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;

                    while ((line = reader.readLine()) != null)
                    {
                        result = result + line;
                    }

                }
                catch (UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                }
                catch (MalformedURLException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                Log.d("result", result);
                return result;

            }
            public void onPostExecute(String result)
            {
                if (loading != null)
                {
                    loading.dismiss();
                }
                json_parse json_parse=new json_parse(con);
                String response_message=json_parse.parse_json(result);
                Log.d("response_msg",response_message);
                if(response_message.equals("Success"))
                {
                    Intent intent=new Intent(con,CartActivity.class);
                    con.startActivity(intent);
                }
                Toast.makeText(con,response_message,Toast.LENGTH_SHORT).show();

            }
        }
        regis regis=new regis(context);
        regis.execute(uname,email, password);
    }

}
