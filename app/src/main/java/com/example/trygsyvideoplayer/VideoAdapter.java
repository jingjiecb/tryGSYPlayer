package com.example.trygsyvideoplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.trygsyvideoplayer.Pojo.Video;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>{

    private List<Video> mVideoList;
    private OnitemClick onitemClick;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView videoImage;
        TextView videoText;

        public ViewHolder (View view)
        {
            super(view);
            videoImage = (ImageView) view.findViewById(R.id.video_img);
            videoText = (TextView) view.findViewById(R.id.video_text);
        }

    }

    public VideoAdapter(List<Video> videoList){
        mVideoList = videoList;
    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder holder, int position) {

        Video video = mVideoList.get(position);
        URL picUrl = null;

//        try {
//            String[] tmp = video.pictureUrl.split(":");
//            picUrl = new URL("https:"+tmp[1]);
////            Bitmap pngBM = BitmapFactory.decodeStream(picUrl.openStream());
////            holder.videoImage.setImageBitmap(pngBM);
//
//            Glide.with(new View(null))
//                    .load(picUrl)
//                    .into(holder.videoImage);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        String[] tmp = video.pictureUrl.split(":");
        try {
            picUrl = new URL("https:"+tmp[1]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Glide.with(holder.videoImage)
                .load(picUrl)
                .into(holder.videoImage);

        holder.videoText.setText(video.author);

        if (onitemClick != null) {
            holder.videoImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //在imageView的地方进行监听点击事件，并且实现接口
                    onitemClick.onItemClick(video.videoUrl);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }

    public interface OnitemClick {
        void onItemClick(String videoURL);
    }

    public void setOnitemClickLintener (OnitemClick onitemClick) {
        this.onitemClick = onitemClick;
    }

}
