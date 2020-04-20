package com.nguyenminhngan.hocassetvasharereference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.nguyenminhngan.hocassetvasharereference.databinding.ActivityMainBinding;
import com.nguyenminhngan.hocassetvasharereference.databinding.ActivityMoManHinhNhacBinding;

public class moManHinhNhacActivity extends AppCompatActivity {

    ActivityMoManHinhNhacBinding binding;
    MediaPlayer mediaPlayer = new MediaPlayer();
    ArrayAdapter<String> nhacAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMoManHinhNhacBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadFromAssets();
        addEvents();
    }

    private void addEvents() {
        binding.lvNhac.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tenNhac = nhacAdapter.getItem(position).toString();
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
                startSound("nhac/"+tenNhac);
            }
        });
    }
    private void startSound(String filename) {
        try {
            AssetFileDescriptor descriptor = getAssets().openFd(filename);
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadFromAssets() {
        try{
            AssetManager assetManager= getAssets();
            String []listnhac = assetManager.list("nhac");
            nhacAdapter = new ArrayAdapter<>(
                    moManHinhNhacActivity.this,
                    android.R.layout.simple_list_item_1,
                    listnhac
            );
            binding.lvNhac.setAdapter(nhacAdapter);
        }
        catch (Exception ex){
            Toast.makeText(moManHinhNhacActivity.this,ex.toString(),Toast.LENGTH_SHORT);
        }
    }

}
