package jurrians.restaurant;

import android.content.Context;
import android.util.Log;

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


public class MenuItemsRequest implements Response.Listener<JSONObject>, Response.ErrorListener{

    private Context context;
    private Callback activity;
    private String categoryGet;

    public interface Callback {
        void gotMenuItems(ArrayList<MenuItem> arrayList);
        void gotMenuItemsError(String message);
    }

    public MenuItemsRequest(Context context, String categoryGet) {
        this.context = context;
        this.categoryGet = categoryGet;
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            ArrayList<MenuItem> arrayList = new ArrayList<>();
            JSONArray menuItems = response.getJSONArray("items");

            for (int i = 0; i < menuItems.length(); i++) {

                JSONObject menuItemsJSONObject = menuItems.getJSONObject(i);

                String category = menuItemsJSONObject.getString("category");
                Log.d("Request", category);

                if (category.equals(this.categoryGet)){

                    String description = menuItemsJSONObject.getString("description");
                    String price = menuItemsJSONObject.getString("price");
                    String imageUrl = menuItemsJSONObject.getString("image_url");
                    String name = menuItemsJSONObject.getString("name");

                    MenuItem menuItem = new MenuItem(category, description, price, imageUrl, name );
                    arrayList.add(menuItem);
                    activity.gotMenuItems(arrayList);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d("gotMenuItemsError", error.getMessage());
        activity.gotMenuItemsError(error.getMessage());
    }

    public void getMenuItems(final Callback activity) {

        String url = "https://resto.mprog.nl/menu";
        this.activity = activity;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);

        RequestQueue queue = Volley.newRequestQueue(this.context);
        queue.add(jsonObjectRequest);
    }

}
