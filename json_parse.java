package  com.example.dhruva.fashonation;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dhruva on 9/3/16.
 */
public class json_parse
{
    Context context;
    public json_parse(Context context)
    {
        context=this.context;
    }

    //returns the success value returned from the response
    public String parse_json(String result)
    {
        int success;
        String message="Error in connecting with app.";
        try
        {
            JSONObject json_object=new JSONObject(result);

            Log.d("success", String.valueOf(json_object.getInt("success")));
            success=json_object.getInt("success");
            if(success<0)
            {
                message=json_object.getString("message");
            }
            if(success==1)
            {
                message="Success";
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        //returns 0 means there is an error connecting to server
        return message;
    }
}
