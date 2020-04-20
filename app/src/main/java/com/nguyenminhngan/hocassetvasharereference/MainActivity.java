package com.nguyenminhngan.hocassetvasharereference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.nguyenminhngan.hocassetvasharereference.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayAdapter<String>fontAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadFontFromAssets();
        addEvents();
    }

    private void addEvents() {
        binding.lvFonts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Typeface typeface= Typeface.createFromAsset(getAssets(),"fontChu/"+fontAdapter.getItem(position));
                binding.textView.setTypeface(typeface);
            }
        });
    }

    private void loadFontFromAssets() {
        try {
            AssetManager assetManager=getAssets();
            String []fonts = assetManager.list("fontChu");
            fontAdapter = new ArrayAdapter<>(
                    MainActivity.this,
                    android.R.layout.simple_list_item_1,
                    fonts
            );
            binding.lvFonts.setAdapter(fontAdapter);
        }
        catch (Exception ex){
            Toast.makeText(MainActivity.this, ex.toString(), Toast.LENGTH_SHORT);
        }
    }

    public void moManHinhNhac(View view) {
        Intent intent = new Intent(MainActivity.this,moManHinhNhacActivity.class);
        startActivity(intent);
    }
}
