package jurrians.restaurant;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MenuItemAdapter extends ArrayAdapter<MenuItem> {

    private Context context;
    private List<MenuItem> menuItemList;


    public MenuItemAdapter(@NonNull Context context, ArrayList<MenuItem> list) {
        super(context, 0, list);
        this.context = context;
        this.menuItemList = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null){
            listItem = LayoutInflater.from(context).inflate(R.layout.row_menuitems,parent,false);
        }

        MenuItem currentMenuItem = menuItemList.get(position);

        ImageView image = listItem.findViewById(R.id.imageMenuItem);
        String url = currentMenuItem.imageUrl;
        Picasso.get().load(url).into(image);

        TextView name = listItem.findViewById(R.id.textMenuItemName);
        name.setText(currentMenuItem.name);

        TextView price = listItem.findViewById(R.id.textMenuItemPrice);
        price.setText(currentMenuItem.price);

       return listItem;
    }

}
