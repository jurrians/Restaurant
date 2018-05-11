package jurrians.restaurant;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener{

    private Context context;
    private Callback activity;

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    public CategoriesRequest(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            ArrayList<String> arrayList = new ArrayList<>();

            JSONArray categories = response.getJSONArray("categories");

            for (int i = 0; i < categories.length(); i++) {
                String string = categories.getString(i);
                arrayList.add(string);
                activity.gotCategories(arrayList);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d("gotCategoriesError", error.getMessage());
        activity.gotCategoriesError(error.getMessage());
    }

    public void getCategories(final Callback activity) {
        String url = "https://resto.mprog.nl/categories";
        this.activity = activity;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);

        RequestQueue queue = Volley.newRequestQueue(this.context);
        queue.add(jsonObjectRequest);
    }

}
