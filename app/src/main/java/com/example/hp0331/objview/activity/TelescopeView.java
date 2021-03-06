package com.example.hp0331.objview.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.hp0331.objview.R;

/**
 * Created by Aaron.zhang
 * at  2017/3/9.
 */

public class TelescopeView extends View {
    private Paint mPaint;
    private Bitmap mBitmap,mBitmapBG;
    private int mDx = -1, mDy = -1;
    public TelescopeView(Context context) {
        super(context);
        initview();
    }

    public TelescopeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initview();
    }

    public TelescopeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initview();
    }
    private void initview(){
        mPaint=new Paint();
        mBitmap= BitmapFactory.decodeResource(getResources(), R.mipmap.timg);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mDx=(int) event.getX();
                mDy=(int)event.getY();
                postInvalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                mDx = (int) event.getX();
                mDy = (int) event.getY();

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mDx = -1;
                mDy = -1;
                break;
        }
        postInvalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mBitmapBG==null){
            mBitmapBG=Bitmap.createBitmap(getWidth(),getHeight(),Bitmap.Config.ARGB_8888);
            Canvas canvasbg=new Canvas(mBitmapBG);
            canvasbg.drawBitmap(mBitmap,null,new Rect(0,0,getWidth(),getHeight()),mPaint);
        }
        if (mDx!=-1&&mDy!=-1){
            mPaint.setShader(new BitmapShader(mBitmapBG, Shader.TileMode.REPEAT,Shader.TileMode.REPEAT));
            canvas.drawCircle(mDx,mDy,150,mPaint);
        }

    }
}
