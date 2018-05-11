package jurrians.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MenuItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        Intent intentGet = getIntent();

        String itemImageUrl = intentGet.getStringExtra("itemImageUrl");
        String itemName = intentGet.getStringExtra("itemName");
        String itemCategory = intentGet.getStringExtra("itemCategory");
        String itemDescription = intentGet.getStringExtra("itemDesciption");
        String itemPrice = intentGet.getStringExtra("itemPrice");

        ImageView imageMenuItem = findViewById(R.id.imageMenuItem);
        TextView textItemName = findViewById(R.id.textItemName);
        TextView textItemCategory = findViewById(R.id.textItemCategory);
        TextView textItemDescription = findViewById(R.id.textItemDescription);
        TextView textItemPrice = findViewById(R.id.textItemPrice);

        Picasso.get().load(itemImageUrl).into(imageMenuItem);
        textItemName.setText(itemName);
        textItemCategory.setText(itemCategory);
        textItemDescription.setText(itemDescription);
        textItemPrice.setText(itemPrice);
    }
}

