package com.step.steplibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


public class StepView extends View {

    /*attr value*/
    private static int dotCount;
    private int defaultStepNum = 0;
    private int   stepNum;
    private int   defaultNormalLineColor;
    private int   normalLineColor;
    private int   defaultPassLineColor;
    private int   passLineColor;
    private float defaultLineStikeWidth;
    private float lineStikeWidth;
    private int   defaultTextColor;
    private int   textColor;
    private float defaultTextSize;
    private float textSize;
    private float defaultText2DotMargin;
    private int   text2DotMargin;
    private int   defalutMarginLeft;
    private int   defalutMarginRight;
    private int   marginLeft;
    private int   marginright;
    private float defaultText2BottomMargin;
    private int   text2BottomMargin;
    /*view messured size*/
    private int   width, height;
    private int    perLineLength;
    private int    perLineHeight;
    private Paint  linePaint;
    private Paint  squarPaint;
    private Paint  textPaint;
    private Rect   bounds;
    private Bitmap normal_pic;
    private Bitmap target_pic;
    private Bitmap passed_pic;
    private static String[] texts = {"确认身份信息", "确认入住信息", "选择房型", "支付押金", "完成入住"};
    private float mX;
    private int   mZ;
    private int[] mPassWH;
    private int[] mNormalWH;
    private int[] mTargetWH;

    public StepView(Context context) {
        this(context, null);
    }

    public StepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        defaultNormalLineColor = Color.parseColor("#545454");
        defaultPassLineColor = Color.WHITE;
        defaultTextColor = Color.WHITE;
        defaultLineStikeWidth = dp2px(context, 1);
        defaultTextSize = sp2px(context, 80);
        defaultText2DotMargin = dp2px(context, 20);
        defalutMarginLeft = dp2px(context, 27);
        defalutMarginRight = dp2px(context, 23);
        defaultText2BottomMargin = dp2px(context, 6);
        normal_pic = BitmapFactory.decodeResource(getResources(), R.drawable.box2);
        target_pic = BitmapFactory.decodeResource(getResources(), R.drawable.box1);
        passed_pic = BitmapFactory.decodeResource(getResources(), R.drawable.box_white);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StepView, defStyleAttr, 0);
        stepNum = a.getInt(R.styleable.StepView_step, defaultStepNum);
        normalLineColor = a.getColor(R.styleable.StepView_normal_line_color, defaultNormalLineColor);
        passLineColor = a.getColor(R.styleable.StepView_passed_line_color, defaultPassLineColor);
        lineStikeWidth = a.getDimension(R.styleable.StepView_line_height, defaultLineStikeWidth);
        textColor = a.getColor(R.styleable.StepView_text_color, defaultTextColor);
        textSize = a.getDimension(R.styleable.StepView_text_size, defaultTextSize);
        text2DotMargin = (int) a.getDimension(R.styleable.StepView_line_bottom_margin, defaultText2DotMargin);
        marginLeft = (int) a.getDimension(R.styleable.StepView_margin_left, defalutMarginLeft);
        marginright = (int) a.getDimension(R.styleable.StepView_margin_right, defalutMarginRight);
        text2BottomMargin = (int) a.getDimension(R.styleable.StepView_text_bottom_margin, defaultText2BottomMargin);
        a.recycle();

        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(lineStikeWidth);

        squarPaint = new Paint();
        squarPaint.setAntiAlias(true);
        squarPaint.setStyle(Paint.Style.FILL);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);

        bounds = new Rect();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        String text = texts[4];
        textPaint.getTextBounds(text, 0, text.length(), bounds);
        int textWidth = bounds.width();
        mPassWH = calculateWidthAndHeight(passed_pic);
        mNormalWH = calculateWidthAndHeight(normal_pic);
        mTargetWH = calculateWidthAndHeight(target_pic);

        width = w - marginLeft - marginright - textWidth;
        height = h;
        perLineLength = width / (5 - 1);
        perLineHeight = (h - text2DotMargin) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawConnectLine(canvas, stepNum);
        drawNormalSquar(canvas, stepNum);
        drawTargetSquar(canvas, stepNum);
        drawDescText(canvas);
    }

    private void drawConnectLine(Canvas canvas, int stepNum) {
        float startX = 0;
        float stopX = 0;
        for (int i = 0; i < dotCount - 1; i++) {
            if (i == stepNum) {
                startX = marginLeft + perLineLength * i + mTargetWH[0];
            } else if (i > stepNum) {
                startX = marginLeft + perLineLength * i + mNormalWH[0];
            } else {
                startX = marginLeft + perLineLength * i + mPassWH[0];
            }
            stopX = marginLeft + perLineLength * (i + 1);

            if (stepNum > i) {
                linePaint.setColor(passLineColor);
            } else {
                linePaint.setColor(normalLineColor);
            }
            canvas.drawLine(startX, perLineHeight, stopX, perLineHeight, linePaint);
        }
    }

    private void drawNormalSquar(Canvas canvas, int stepNum) {
        for (int i = 0; i < dotCount; i++) {
            if (stepNum == i) {
                continue;
            }
            if (stepNum > i) {
                float left = marginLeft + perLineLength * i;
                float top = perLineHeight - mPassWH[1]/ 2;
                canvas.drawBitmap(passed_pic, left, top, squarPaint);
            } else {
                float left = marginLeft + perLineLength * i;
                float top = perLineHeight -  mNormalWH[1]/ 2;
                canvas.drawBitmap(normal_pic, left, top, squarPaint);
            }
        }
    }

    private int[] calculateWidthAndHeight(Bitmap bitmap) {
        int[] wh = new int[2];
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        wh[0] = width;
        wh[1] = height;
        return wh;
    }

    private void drawTargetSquar(Canvas canvas, int i) {
        float left = marginLeft + perLineLength * i;
        float top = perLineHeight - mTargetWH[1] / 2;
        canvas.drawBitmap(target_pic, left, top, squarPaint);
    }

    private void drawDescText(Canvas canvas) {
        for (int i = 0; i < dotCount; i++) {
            String text = texts[i];
            float y = height - text2BottomMargin;
            mX = marginLeft + perLineLength * i;
            canvas.drawText(text, mX, y, textPaint);
        }
    }

    public void setDotCount(int count, String[] descs) {
        if (count < 2) {
            throw new IllegalArgumentException("dot count can't be less than 2.");
        }
        dotCount = count;
        setDescription(descs);
        invalidate();
    }

    public void setDescription(String[] descs) {
        if (descs == null || descs.length < dotCount) {
            throw new IllegalArgumentException("Descriptions can't be null or its length must maore than dot count");
        }
        if (descs.length == 5) {
            texts = descs;
        } else if (descs.length < 5) {
            for (int i = 0; i < descs.length; i++) {
                texts[i] = descs[i];
            }
        } else {
            throw new IllegalArgumentException("需要更改setDescription方法中的参数");
        }
    }

    public void setStep(Step step) {
        switch (step) {
            case ONE:
                stepNum = 0;
                break;
            case TWO:
                stepNum = 1;
                break;
            case THREE:
                stepNum = 2;
                break;
            case FOUR:
                stepNum = 3;
                break;
            case FIVE:
                stepNum = 4;
                break;
            default:
                break;
        }
        if (stepNum + 1 > dotCount) {
            throw new IllegalArgumentException("dotCount can't be less than Steps");
        }

        invalidate();
    }

    public enum Step {
        ONE, TWO, THREE, FOUR, FIVE
    }

    private int dp2px(Context context, int value) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (density * value + 0.5f);
    }

    private int sp2px(Context context, int value) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (value / scaledDensity + 0.5f);
    }
}
