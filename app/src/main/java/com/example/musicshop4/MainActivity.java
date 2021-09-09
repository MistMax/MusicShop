package com.example.musicshop4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int quantity = 0;
    Spinner spinner;
    ArrayList<String> spinnerArrayList;
    ArrayAdapter<String> spinnerAdapter;
    String goodsName;
    double price;
    HashMap<String, Double> goodsMap;
    EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userNameEditText = findViewById(R.id.nameEditText);
        createSpinner();
        createMap();
    }
    void createSpinner() {
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinner.setPrompt("Musical instruments");
        spinnerArrayList = new ArrayList<>();
        spinnerArrayList.add("guitar");
        spinnerArrayList.add("drums");
        spinnerArrayList.add("keyboard");

        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }
    void createMap () {
        goodsMap = new HashMap<String, Double>();
        goodsMap.put("guitar", 500.0);
        goodsMap.put("drums", 1500.0);
        goodsMap.put("keyboard", 1000.0);
    }

    public void increaseQuantity(View view) {
        TextView textnull1 = findViewById(R.id.textnull1);
        quantity++;
        textnull1.setText("" + quantity);
        TextView textnull = findViewById(R.id.textnull);
        textnull.setText(""+quantity * price);
    }

    public void decreaseQuantity(View view) {
        TextView textnull1 = findViewById(R.id.textnull1);
        quantity--;
        if(quantity < 0) quantity = 0;
        textnull1.setText("" + quantity);
        TextView textnull = findViewById(R.id.textnull);
        textnull.setText(""+quantity * price);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        goodsName = spinner.getSelectedItem().toString();
        price = goodsMap.get(goodsName);
        TextView textnull = findViewById(R.id.textnull);
        textnull.setText(""+quantity * price);

        ImageView goodsImageView = findViewById(R.id.imageView2);

        switch (goodsName) {
            case"guitar":
                goodsImageView.setImageResource(R.drawable.guitar);
                break;
            case"drums":
                goodsImageView.setImageResource(R.drawable.drums);
                break;
            case"keyboard":
                goodsImageView.setImageResource(R.drawable.pianino);
                break;
            default:
                goodsImageView.setImageResource(R.drawable.guitar);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void addToCard(View view) {

        Order order = new Order();
        order.userName = userNameEditText.getText().toString();
        order.goodsName = goodsName;
        order.quantity = quantity;
        order.orderPrice = quantity * price;
        order.price = price;
        Intent orderIntent = new Intent(this, OrderActivity.class);
        orderIntent.putExtra("userName", order.userName);
        orderIntent.putExtra("goodsName", order.goodsName);
        orderIntent.putExtra("quantity", order.quantity);
        orderIntent.putExtra("orderPrice", order.orderPrice);
        orderIntent.putExtra("price", order.price);

        startActivity(orderIntent);
    }
}