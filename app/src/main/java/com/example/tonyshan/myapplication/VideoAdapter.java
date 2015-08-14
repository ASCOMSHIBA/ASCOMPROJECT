package com.example.tonyshan.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * ビデオアダプター
 */
public class VideoAdapter extends BaseAdapter{
    /**
     * listViewのLayoutInflater
     */
    private final LayoutInflater mInflater;

    private ImageView imageView;

    private TextView textView;

    private Context context;

    private List<VideoData> list;

    /**
     * コンストラクタ
     * @param context コンテンツ
     * @param list リスとビュー
     */
    public VideoAdapter(Context context, List<VideoData> list){

        this.context = context;

        this.mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.list = list;

    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent){

        if (convertView == null){

            convertView = this.mInflater.inflate(R.layout.sample_row,parent,false);

            imageView = (ImageView)convertView.findViewById(R.id.imageView);

            textView = (TextView)convertView.findViewById(R.id.textView);

        }
        VideoData videoData = (VideoData) getItem(position);

        imageView.setImageResource(videoData.getId());

        textView.setText(videoData.getText());



        return convertView;
    }

    @Override
    public int getCount(){
        return list.size();
    }

    @Override
    public Object getItem(int position){
        return list.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

}
