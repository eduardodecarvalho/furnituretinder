package com.furniturecombiner;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.furniturecombiner.model.Item;
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

    private ArrayList<String> items = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;
    private final static String url = "http://10.0.2.2:8080/v1";
    private final static String URL_ITEMS = url + "/items";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getData();

        arrayAdapter = new ArrayAdapter<>(this, R.layout.item, R.id.itemName, items);

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
                items.add("Chair ".concat(String.valueOf(i)));
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
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()) {
                    ObjectMapper mapper = new ObjectMapper();
                    String bodyResponse = Objects.requireNonNull(response.body()).string();
                    List<Item> itemList = Arrays.asList(mapper.readValue(bodyResponse, Item[].class));
                    itemList.forEach(i -> items.add(i.getName()));
                }
            }
        });
    }

}