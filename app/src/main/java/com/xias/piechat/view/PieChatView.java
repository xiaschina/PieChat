package com.xias.piechat.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.xias.piechat.entry.PieChatEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XIAS on 2017/8/16.
 */

public class PieChatView extends View {

    private List<PieChatEntry> list;
    private int width;
    private int height;

    private int minRadius;
    private int maxRadius;

    private Paint arcPaint;
    private Paint textPaint;
    private Paint linePaint;
    private RectF minRectF;
    private RectF maxRectF;

    public PieChatView(Context context) {
        this(context,null);
    }

    public PieChatView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        list = new ArrayList<>();

        arcPaint = new Paint();
        arcPaint.setAntiAlias(true);
        arcPaint.setStyle(Paint.Style.FILL);

        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setColor(Color.BLACK);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(32);
    }

    public void setData(List<PieChatEntry> list){
        this.list = list;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPicChat(canvas);
        drawTextView(canvas);
    }

    private void drawPicChat(Canvas canvas){
        if(list != null && list.size() >= 1) {
            canvas.translate(width / 2, height / 2);
            for (PieChatEntry p : list) {
                arcPaint.setColor(p.getColor());
                if (p.isSelected())
                    canvas.drawArc(maxRectF, p.getStartAngle(), p.getEndAngle() - p.getStartAngle() - 1, true, arcPaint);
                else
                    canvas.drawArc(minRectF, p.getStartAngle(), p.getEndAngle() - p.getStartAngle() - 1, true, arcPaint);
            }
        }
    }

    private void drawTextView(Canvas canvas){
        if(list != null && list.size() >= 1) {
            float sX = 0, sY = 0, eX = 0, eY = 0;

            for (PieChatEntry p : list) {
                if(p.isSelected()) {
                    sX = (float) (maxRadius * Math.cos(Math.toRadians(p.getStartAngle() + (p.getEndAngle() - p.getStartAngle()) / 2)));
                    sY = (float) (maxRadius * Math.sin(Math.toRadians(p.getStartAngle() + (p.getEndAngle() - p.getStartAngle()) / 2)));

                    eX = (float) ((maxRadius+20) * Math.cos(Math.toRadians(p.getStartAngle() + (p.getEndAngle() - p.getStartAngle()) / 2)));
                    eY = (float) ((maxRadius+20) * Math.sin(Math.toRadians(p.getStartAngle() + (p.getEndAngle() - p.getStartAngle()) / 2)));
                }
                else{
                    sX = (float) (minRadius * Math.cos(Math.toRadians(p.getStartAngle() + (p.getEndAngle() - p.getStartAngle()) / 2)));
                    sY = (float) (minRadius * Math.sin(Math.toRadians(p.getStartAngle() + (p.getEndAngle() - p.getStartAngle()) / 2)));

                    eX = (float) ((minRadius+20) * Math.cos(Math.toRadians(p.getStartAngle() + (p.getEndAngle() - p.getStartAngle()) / 2)));
                    eY = (float) ((minRadius+20) * Math.sin(Math.toRadians(p.getStartAngle() + (p.getEndAngle() - p.getStartAngle()) / 2)));
                }

                canvas.drawLine(sX,sY,eX,eY,linePaint);
                if (p.getStartAngle() >= 90.0 && p.getStartAngle() <= 180.0) {
                    canvas.drawText(p.getName(), eX-30, eY+30, textPaint);
                }
                else
                    canvas.drawText(p.getName(),eX,eY,textPaint);

                }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w - getPaddingLeft() - getPaddingRight();
        height = h - getPaddingBottom() - getPaddingTop();

        minRadius = Math.min(width,height)*7/20;
        maxRadius = Math.min(width,height)*2/5;

        minRectF = new RectF(-minRadius,-minRadius,minRadius,minRadius);
        maxRectF = new RectF(-maxRadius,-maxRadius,maxRadius,maxRadius);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();
                if(list == null || list.size() < 1)
                    return false;
                float temp = (x-width/2)*(x-width/2)+(y-height/2)*(y-height/2);
                if(temp <= minRadius * minRadius){
                    float sweep = getSweep(x,y);
                    for(PieChatEntry p:list){
                        if(p.getStartAngle()<= sweep && sweep <= p.getEndAngle()) {
                            if (!p.isSelected())
                                p.setSelected(true);
                        }
                        else{
                                if(p.isSelected())
                                    p.setSelected(false);
                            }
                    }
                }
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    private float getSweep(float touchX, float touchY) {
        float xZ = touchX - width/2;
        float yZ = touchY - height/2;
        float a = Math.abs(xZ);
        float b = Math.abs(yZ);
        double c = Math.toDegrees(Math.atan(b / a));
        if (xZ >= 0 && yZ >= 0) {//第一象限
            return (float) c;
        } else if (xZ <= 0 && yZ >= 0) {//第二象限
            return 180 - (float) c;
        } else if (xZ <= 0 && yZ <= 0) {//第三象限
            return (float) c + 180;
        } else {//第四象限
            return 360 - (float) c;
        }
    }
}
