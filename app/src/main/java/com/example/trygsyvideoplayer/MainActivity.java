package com.example.trygsyvideoplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.trygsyvideoplayer.Dao.VideoStub;
import com.example.trygsyvideoplayer.Pojo.Video;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    StandardGSYVideoPlayer videoPlayer;

    OrientationUtils orientationUtils;

    List<Video> mVideoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView videoRecyclerView = (RecyclerView)findViewById(R.id.videoRecyclerView);
        mVideoList = new ArrayList<>();
        for(int index = 0;index < VideoStub.getLength();index++){
            mVideoList.add(VideoStub.getVideo(index));
            if(index>255){
                break;
            }
        }

//        Video video = VideoStub.getVideo(0);
//        TextView textView=findViewById(R.id.text);
//        View btn = findViewById(R.id.watch);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, GSYPlayerActivity.class);
//                intent.putExtra("videoUrl",video.videoUrl);
//                startActivity(intent);
//            }
//        });
//        textView.setText(video.toString());


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        videoRecyclerView.setLayoutManager(layoutManager);
        VideoAdapter adapter = new VideoAdapter(mVideoList);
        videoRecyclerView.setAdapter(adapter);

        adapter.setOnitemClickLintener(new VideoAdapter.OnitemClick() {
            @Override
            public void onItemClick(String videoURL) {
                Intent it = new Intent(MainActivity.this, GSYPlayerActivity.class);
                it.putExtra("videoUrl", videoURL);
                startActivity(it);
            }
        });
    }

}