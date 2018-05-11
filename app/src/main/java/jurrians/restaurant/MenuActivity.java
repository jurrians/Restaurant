package jurrians.restaurant;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements  MenuItemsRequest.Callback {

    public ListView listView;
    public String categoryGet;

    public ArrayList<MenuItem> MenuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intentGet = getIntent();
        categoryGet = intentGet.getStringExtra("categoryGet");

        MenuItemsRequest menuItemsRequest = new MenuItemsRequest(getApplicationContext(), categoryGet);
        menuItemsRequest.getMenuItems(this);

        listView = findViewById(R.id.listView2);
        listView.setOnItemClickListener(listener);
    }

    @Override
    public void gotMenuItems(ArrayList<MenuItem> menuItems) {
        MenuItemAdapter adapter = new MenuItemAdapter(this, menuItems);
        MenuItems = menuItems;
        listView = findViewById(R.id.listView2);
        listView.setAdapter(adapter);
    }

    @Override
    public void gotMenuItemsError(String message) {
        Toast.makeText(this, "gotMenuItemsError", Toast.LENGTH_LONG).show();
    }

    private AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            MenuItem menuItem = MenuItems.get(position);

            String itemCategory = menuItem.category;
            String itemDescription = menuItem.description;
            String itemName = menuItem.name;
            String itemImageUrl = menuItem.imageUrl;
            String itemPrice = menuItem.price;

            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);

            intent.putExtra("itemCategory", itemCategory);
            intent.putExtra("itemDescription", itemDescription);
            intent.putExtra("itemName", itemName);
            intent.putExtra("itemImageUrl", itemImageUrl);
            intent.putExtra("itemPrice", itemPrice);

            startActivity(intent);
        }
    };
}

