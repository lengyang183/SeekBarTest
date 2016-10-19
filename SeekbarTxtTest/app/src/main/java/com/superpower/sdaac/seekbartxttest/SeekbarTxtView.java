package com.superpower.sdaac.seekbartxttest;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by pc on 2016/10/18.
 */
public class SeekbarTxtView extends FrameLayout implements SeekBar.OnSeekBarChangeListener {

    private TextView textView;
    private ImageView imageView;
    private SeekBar seekBar;
    private RelativeLayout rel_main;

    public SeekbarTxtView(Context context) {
        super(context);
        init(context);
    }

    public SeekbarTxtView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SeekbarTxtView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        View view= LayoutInflater.from(context).inflate(R.layout.seekbarlayout,this);
        textView= (TextView) view.findViewById(R.id.txt);
        imageView= (ImageView) view.findViewById(R.id.img);
        seekBar= (SeekBar) view.findViewById(R.id.seekbar);
        rel_main= (RelativeLayout) findViewById(R.id.rel_main);
        textView.setText("50");
        seekBar.setOnSeekBarChangeListener(this);
        textView.setVisibility(GONE);
        imageView.setVisibility(GONE);
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        changeDate();
        Message msg=new Message();
        msg.what=1;
        msg.obj=progress;
        handler.sendMessage(msg);

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        textView.setVisibility(VISIBLE);
        imageView.setVisibility(VISIBLE);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        textView.setVisibility(GONE);
        imageView.setVisibility(GONE);
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                textView.setText(msg.obj.toString());
            }
        }
    };

    private void changeDate(){
        Rect bounds = seekBar.getProgressDrawable().getBounds();
        int xFloat=(int)bounds.width()*seekBar.getProgress()/seekBar.getMax();
        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.leftMargin=xFloat+3;
        params.topMargin=10;
        rel_main.setLayoutParams(params);
    }
}
