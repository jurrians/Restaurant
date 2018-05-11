package jurrians.restaurant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {

    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        CategoriesRequest categoriesRequest = new CategoriesRequest(getApplicationContext());
        categoriesRequest.getCategories(this);

        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(listener);
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.row_categories, R.id.textCategories, categories);
        String length = String.valueOf(categories.size());
        Log.d("arraylist", length);
        listView = findViewById(R.id.listView);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this,  "gotCategoriesError",Toast.LENGTH_LONG).show();
    }

    private AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String categoryGet = (String) parent.getItemAtPosition(position);

            Log.d("CategoriesCLick", categoryGet);
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("categoryGet", categoryGet);
            startActivity(intent);
        }
    };










}
