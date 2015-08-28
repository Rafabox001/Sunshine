package com.example.rafael.sunshine.app.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.rafael.sunshine.app.R;

/**
 * Created by Developer-I on 02/07/2015.
 */
public class Compass extends View {

    private float direction = 0;
    private String directionString = "N";
    private Paint paintRect = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint circle = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint line = new Paint(Paint.ANTI_ALIAS_FLAG);
    private boolean firstDraw;
    private int mExternal = getResources().getColor(R.color.primary);
    private int mInternal = Color.WHITE;
    private int mDirectional = Color.RED;
    private int mText = Color.BLACK;

    public Compass(Context context) {
        super(context);
    // TODO Auto-generated constructor stub
        init();
    }

    public Compass(Context context, AttributeSet attrs) {
        super(context, attrs);
    // TODO Auto-generated constructor stub
        TypedArray values = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Compass, 0,0);
        try{
            direction = values.getInteger(R.styleable.Compass_labelDirection, 0);
            mExternal = values.getColor(R.styleable.Compass_extColor, getResources().getColor(R.color.primary));
            mInternal = values.getColor(R.styleable.Compass_innerColor, Color.WHITE);
            mDirectional = values.getColor(R.styleable.Compass_directionColor, Color.RED);
            mText = values.getColor(R.styleable.Compass_textColor, Color.BLACK);
        }finally {
            values.recycle();
        }
        init();
    }

    public Compass(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    // TODO Auto-generated constructor stub
        init();
    }

    private void init(){

        paintRect.setStyle(Paint.Style.FILL_AND_STROKE);
        paintRect.setStrokeWidth(3);
        paintRect.setColor(mExternal);
        paintRect.setTextSize(30);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setColor(mText);
        paint.setTextSize(30);

        line.setStyle(Paint.Style.STROKE);
        line.setStrokeWidth(5);
        line.setColor(mDirectional);
        line.setTextSize(30);

        circle.setStyle(Paint.Style.FILL);
        circle.setStrokeWidth(1);
        circle.setColor(mInternal);
        circle.setTextSize(30);



        firstDraw = true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    // TODO Auto-generated method stub
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
    // TODO Auto-generated method stub

        int cxCompass = getMeasuredWidth()/2;
        int cyCompass = getMeasuredHeight()/2;
        float radiusCompass;
        float extCompass;

        if(cxCompass > cyCompass){
            radiusCompass = (float) (cyCompass * 0.75);
            extCompass = (float) (cyCompass *1);
        }
        else{
            radiusCompass = (float) (cxCompass * 0.75);
            extCompass = (float) (cxCompass *1);
        }

        canvas.drawCircle(cxCompass, cyCompass, extCompass, paintRect);
        canvas.drawCircle(cxCompass, cyCompass, radiusCompass, circle);
        canvas.drawText("N", cxCompass, cyCompass - radiusCompass , paint);
        canvas.drawText("S", cxCompass, cyCompass + extCompass, paint);
        canvas.drawText("E", cxCompass + radiusCompass, cyCompass, paint);
        canvas.drawText("W", cxCompass - extCompass , cyCompass, paint);

        if(!firstDraw){

            canvas.drawLine(cxCompass, cyCompass,
                    (float)(cxCompass + radiusCompass * Math.sin((double)(+direction) * 3.14/180)),
                    (float)(cyCompass - radiusCompass * Math.cos((double)(+direction) * 3.14/180)),
                    line);

            canvas.drawText(directionString, cxCompass, cyCompass, paint);
        }

    }

    public void updateDirection(float dir)
    {
        firstDraw = false;
        direction = dir;
        //Determine de direction of the wind with the degrees
        if (dir >= 337.5 || dir < 22.5) {
            directionString = "N";
        } else if (dir >= 22.5 && dir < 67.5) {
            directionString = "NE";
        } else if (dir >= 67.5 && dir < 112.5) {
            directionString = "E";
        } else if (dir >= 112.5 && dir < 157.5) {
            directionString = "SE";
        } else if (dir >= 157.5 && dir < 202.5) {
            directionString = "S";
        } else if (dir >= 202.5 && dir < 247.5) {
            directionString = "SW";
        } else if (dir >= 247.5 && dir < 292.5) {
            directionString = "W";
        } else if (dir >= 292.5 && dir < 337.5) {
            directionString = "NW";
        }
        invalidate();
    }
}
