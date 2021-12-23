package com.furniturecombiner;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.furniturecombiner.model.Item;
import com.furniturecombiner.model.arrayAdapter;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private List<Item> items = new ArrayList<>();
    private com.furniturecombiner.model.arrayAdapter arrayAdapter;
    private final static String url = "http://10.0.2.2:8080/v1";
    private final static String URL_ITEMS = url + "/items";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getData();

        arrayAdapter = new arrayAdapter(this, R.layout.item, items);

        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                items.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                Toast.makeText(MainActivity.this, "Unlike!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(MainActivity.this, "Like!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                int i = 0;
                items.add(new Item(null, "Office chair", "An amazing office chair", "100.00", "chair_1"));
                arrayAdapter.notifyDataSetChanged();
                i++;
            }

            @Override
            public void onScroll(float scrollProgressPercent) {

            }
        });

        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(MainActivity.this, "Clicked!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getData() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(URL_ITEMS)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                items.add(new Item(1L, "Office chair", "An amazing office chair", "100.00", "chair_1"));
                items.add(new Item(2L, "Gaming chair", "The best chair for gaming", "150.00", "chair_2"));
                items.add(new Item(3L, "Regular chair", "A low cost chair", "30.00", "chair_3"));
                items.add(new Item(4L, "Top chair", "The best chair ever", "999.00", "chair_4"));
                items.add(new Item(5L, "Gaming chair", "The best chair for gaming", "150.00", "chair_2"));
                items.add(new Item(6L, "Regular chair", "A low cost chair", "30.00", "chair_3"));
                items.add(new Item(7L, "Top chair", "The best chair ever", "999.00", "chair_4"));
                items.add(new Item(8L, "Gaming chair", "The best chair for gaming", "150.00", "chair_2"));
                items.add(new Item(9L, "Regular chair", "A low cost chair", "30.00", "chair_3"));
                items.add(new Item(10L, "Top chair", "The best chair ever", "999.00", "chair_4"));
                items.add(new Item(11L, "Gaming chair", "The best chair for gaming", "150.00", "chair_2"));
                items.add(new Item(12L, "Regular chair", "A low cost chair", "30.00", "chair_3"));
                items.add(new Item(13L, "Top chair", "The best chair ever", "999.00", "chair_4"));
                items.add(new Item(14L, "Top chair", "The best chair ever", "999.00", "chair_4"));
                items.add(new Item(15L, "Gaming chair", "The best chair for gaming", "150.00", "chair_2"));
                items.add(new Item(16L, "Regular chair", "A low cost chair", "30.00", "chair_3"));
                items.add(new Item(17L, "Top chair", "The best chair ever", "999.00", "chair_4"));
                items.add(new Item(18L, "Gaming chair", "The best chair for gaming", "150.00", "chair_2"));
                items.add(new Item(19L, "Regular chair", "A low cost chair", "30.00", "chair_3"));
                items.add(new Item(20L, "Top chair", "The best chair ever", "999.00", "chair_4"));
                items.add(new Item(21L, "Gaming chair", "The best chair for gaming", "150.00", "chair_2"));
                items.add(new Item(22L, "Regular chair", "A low cost chair", "30.00", "chair_3"));
                items.add(new Item(23L, "Office chair", "An amazing office chair", "100.00", "chair_1"));
                items.add(new Item(24L, "Office chair", "An amazing office chair", "100.00", "chair_1"));
                items.add(new Item(25L, "Office chair", "An amazing office chair", "100.00", "chair_1"));
                items.add(new Item(26L, "Office chair", "An amazing office chair", "100.00", "chair_1"));
                items.add(new Item(27L, "Office chair", "An amazing office chair", "100.00", "chair_1"));
                items.add(new Item(28L, "Office chair", "An amazing office chair", "100.00", "chair_1"));

                items.forEach(
                        i -> i.setImageResource(
                                getResources().getIdentifier("@drawable/" + i.getPicture(), null, getPackageName())));
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    ObjectMapper mapper = new ObjectMapper();
                    String bodyResponse = Objects.requireNonNull(response.body()).string();
                    List<Item> itemList = Arrays.asList(mapper.readValue(bodyResponse, Item[].class));
                    itemList.forEach(
                            i -> i.setImageResource(
                                    getResources().getIdentifier("@drawable/" + i.getPicture(), null, getPackageName())));
                    items.addAll(itemList);
                }
            }
        });
    }

}