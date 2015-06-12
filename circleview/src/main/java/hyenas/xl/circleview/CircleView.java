package hyenas.xl.circleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import hyenas.xl.circleview.model.TextValueObject;

/**
 * Created by xl on 2015/6/11.
 */
public class CircleView extends View {

    private int circleRadius;
    private int circleBorderWidth;

    Paint paint = new Paint();
    Paint circleLightPaint = new Paint();
    Paint circleDarkPaint = new Paint();
    Paint circleLineLightPaint = new Paint();
    Paint circleLineDarkPaint = new Paint();
    private int progressValue = 0;
    private String weatherText = null;
    private String viewTitle = null;
    private TextValueObject[] centerTextValueObjects = null;

    public TextValueObject[] getCenterTextValueObjects() {
        return centerTextValueObjects;
    }

    /**
     * center circle texts<br>
     *  TextValueObject:<br>
     *      textValue   : text message<br>
     *      isHighlight : Set the font highlighted
     * @param centerTextValueObjects    text array
     */
    public void setCenterTextValueObjects(TextValueObject[] centerTextValueObjects) {
        this.centerTextValueObjects = centerTextValueObjects;
    }

    public String getViewTitle() {
        return viewTitle;
    }
    /**
     *  view  title
     * @param viewTitle eg: ""
     */
    public void setViewTitle(String viewTitle) {
        this.viewTitle = viewTitle;
    }

    public String getWeatherText() {
        return weatherText;
    }
    /**
     *  view title description
     * @param weatherText   eg: ""
     */
    public void setWeatherText(String weatherText) {
        this.weatherText = weatherText;
    }

    public int getProgressValue() {
        return progressValue;
    }

    /**
     * @param _progressValue 1 to 100
     */
    public void setProgressValue(final int _progressValue) {
//        this.progressValue = progressValue;
        new Thread(){
            @Override
            public void run() {
                while(progressValue <  _progressValue){
                    try {
                        sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressValue++;
                    postInvalidate();
                }
            }
        }.start();
    }

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        loadAttrs(context, attrs, defStyleAttr);
    }

    /**
     * load attrs
     *
     * @param context
     * @param attrs
     * @param styleAttr
     */
    private void loadAttrs(Context context, AttributeSet attrs, int styleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BackgroudView, styleAttr, 0);

        circleRadius = typedArray.getDimensionPixelSize(R.styleable.BackgroudView_circle_radius, 0);
        circleBorderWidth = typedArray.getDimensionPixelSize(R.styleable.BackgroudView_circle_border_width, 0);
        typedArray.recycle();

        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAlpha(140);
        paint.setTextSize(14);

        circleLightPaint.setAntiAlias(true);
        circleLightPaint.setColor(Color.WHITE);
        circleLightPaint.setStyle(Paint.Style.STROKE);
        circleLightPaint.setStrokeWidth(circleBorderWidth);
        circleLightPaint.setAlpha(100);

        circleDarkPaint.setAntiAlias(true);
        circleDarkPaint.setColor(Color.WHITE);
        circleDarkPaint.setStyle(Paint.Style.STROKE);
        circleDarkPaint.setStrokeWidth(circleBorderWidth);
        circleDarkPaint.setAlpha(30);

        circleLineLightPaint.setAntiAlias(true);
        circleLineLightPaint.setColor(Color.WHITE);
        circleLineLightPaint.setStyle(Paint.Style.STROKE);
        circleLineLightPaint.setAlpha(100);

        circleLineDarkPaint.setAntiAlias(true);
        circleLineDarkPaint.setColor(Color.WHITE);
        circleLineDarkPaint.setStyle(Paint.Style.STROKE);
        circleLineDarkPaint.setAlpha(30);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //title text
        if(viewTitle != null && viewTitle.length() > 0){
            canvas.drawText(viewTitle,getWidth()/2-viewTitle.length()/2,30,paint);
        }
        if(weatherText != null && weatherText.length() > 0){
            paint.setAlpha(80);
            paint.setTextSize(12);
            canvas.drawText(weatherText,getWidth()/2-weatherText.length()/2,50,paint);
        }
        //draw the center circle
        float sweepAngle = getCurrentRotation();

        Log.e("CircleView", "sweepAngle = " + sweepAngle);
        canvas.drawArc(loadCircleRect(), 130, sweepAngle, false, circleLightPaint);
        canvas.drawArc(loadCircleRect2(), 130, sweepAngle, false, circleLineLightPaint);

        canvas.drawArc(loadCircleRect(), 130 + sweepAngle, 280 - sweepAngle, false, circleDarkPaint);
        canvas.drawArc(loadCircleRect2(), 130 + sweepAngle, 280 - sweepAngle, false, circleLineDarkPaint);

        //center text
        if(centerTextValueObjects != null && centerTextValueObjects.length > 0){
            int length = centerTextValueObjects.length;
            int indexCenter = Math.round(length/2);
            for(int i=0; i < length ;i++){
                if(centerTextValueObjects[i] != null && centerTextValueObjects[i].getTextValue() != null
                        && centerTextValueObjects[i].getTextValue().length() > 0){

                    int alpha =   80;
                    int textSize = 12;
                    int textHeight = getHeight() *6 / 11+(i-indexCenter)*20;
                    if(i < indexCenter){
                        textHeight -= 5;
                    }

                    if(centerTextValueObjects[i].isHighlight()){
                        alpha = 140;
                        textSize = 22;
                    }
                    paint.setAlpha(alpha);
                    paint.setTextSize(textSize);

                    canvas.drawText(centerTextValueObjects[i].getTextValue(),
                            getWidth() / 2 - centerTextValueObjects[i].getTextValue().length() / 2,textHeight , paint);
                }
            }
        }

    }

    /**
     * outer circle rect
     * @return
     */
    private RectF loadCircleRect() {
        RectF  circleRect = new RectF();
        circleRect.set(getWidth() / 2 - circleRadius , getHeight() *6 / 11 - circleRadius,
                getWidth() / 2 + circleRadius, getHeight() *6 / 11 + circleRadius);
        return circleRect;
    }

    /**
     * inner circle rect
     * @return
     */
    private RectF loadCircleRect2() {
        RectF  circleRect = new RectF();
        circleRect.set(getWidth() / 2 - circleRadius + circleBorderWidth*2/3, getHeight()*6 / 11 - circleRadius + circleBorderWidth*2/3,
                getWidth() / 2 + circleRadius - circleBorderWidth*2/3, getHeight() *6 / 11 + circleRadius - circleBorderWidth*2/3);
        return circleRect;
    }

    private float getCurrentRotation() {
        return (280*progressValue)/100;
    }
}
