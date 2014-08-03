package com.morihacky.android.icobutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.StateSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class IcoButton
    extends ViewGroup {

    public static final String HOLO_BLUE = "#ff0099cc";
    public static final String WHITE = "#ffffff";
    public static int SPACE_BTW_ICON_TXT = 10;
    public static int ICO_DIRECTION_LEFT = 0;
    public static int ICO_DIRECTION_RIGHT = 1;

    private final Context _context;
    private AttributeSet _attributes;

    private View _btn;
    private TextView _btnText;
    private ImageView _btnIcon;

    private int _icoDirection;

    public IcoButton(Context context) {
        super(context);
        _context = context;
        _initializeView();
    }

    public IcoButton(Context context, AttributeSet attributes) {
        super(context, attributes);
        _context = context;
        _attributes = attributes;
        _initializeView();
    }

    public IcoButton(Context context, AttributeSet attributes, int defStyle) {
        super(context, attributes, defStyle);
        _context = context;
        _attributes = attributes;
        _initializeView();
    }

    private void _initializeView() {
        if (_context == null) {
            return;
        }

        _btn = LayoutInflater.from(_context)
                             .inflate(R.layout.com_morihacky_android_icobutton, this, true);

        _btnText = (TextView) findViewById(R.id.com_morihacky_android_icobutton_text);
        _btnIcon = (ImageView) findViewById(R.id.com_morihacky_android_icobutton_icon);

        _setupView();
    }

    private void _setupView() {
        TypedArray xmlAttrs = _context.obtainStyledAttributes(_attributes, R.styleable.IcoButton);
        if (xmlAttrs == null) {
            return;
        }

        // -----------------------------------------------------------------------------------
        // Button
        _btn.setClickable(true);
        _btn.setBackgroundDrawable(_getStateListDrawableForButtonColor(Color.parseColor(HOLO_BLUE),
                                                                       true));

        int padding = xmlAttrs.getDimensionPixelSize(R.styleable.IcoButton_padding,
                                                     _convertDpToPixels(8));
        _btn.setPadding(padding, padding, padding, padding);

        // -----------------------------------------------------------------------------------
        // Button Text
        _btnText.setText(xmlAttrs.getString(R.styleable.IcoButton_text));
        _btnText.setTextColor(xmlAttrs.getColor(R.styleable.IcoButton_textColor,
                                                Color.parseColor(WHITE)));
        _btnText.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                             xmlAttrs.getDimensionPixelSize(R.styleable.IcoButton_textSize,
                                                            _convertDpToPixels(16)));

        // -----------------------------------------------------------------------------------
        // Button Icon
        _btnIcon.setImageResource(xmlAttrs.getInt(R.styleable.IcoButton_iconResource,
                                                  android.R.drawable.ic_input_get));
        _icoDirection = xmlAttrs.getInt(R.styleable.IcoButton_iconDirection, ICO_DIRECTION_LEFT);

        xmlAttrs.recycle();
    }

    // -----------------------------------------------------------------------
    // Custom ViewGroup implementation

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChild(_btnIcon, widthMeasureSpec, heightMeasureSpec);
        measureChild(_btnText, widthMeasureSpec, heightMeasureSpec);

        final int totalWidth = MeasureSpec.getSize(widthMeasureSpec);
        final int totalHeight = Math.max(_btnIcon.getMeasuredHeight(), _btnText.getMeasuredHeight()) +
                                getPaddingTop() +
                                getPaddingBottom();

        setMeasuredDimension(totalWidth, totalHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int totalHeight = getMeasuredHeight();
        int totalWidth = getMeasuredWidth();

        if (changed) {
            _btnIcon.setAdjustViewBounds(true);
            _btnIcon.setMaxHeight(_btnText.getMeasuredHeight());
        }

        int contentWidth = _btnIcon.getMeasuredWidth() +
                           SPACE_BTW_ICON_TXT +
                           _btnText.getMeasuredWidth();

        int distanceBtwContentEdgeAndBoundary = (totalWidth - contentWidth) / 2;
        int iconDistanceFromTop = (totalHeight - _btnIcon.getMeasuredHeight()) / 2;
        int txtDistanceFromTop = (totalHeight - _btnText.getMeasuredHeight()) / 2;

        if (_icoDirection == ICO_DIRECTION_LEFT) {
            _btnIcon.layout(distanceBtwContentEdgeAndBoundary,
                            iconDistanceFromTop,
                            distanceBtwContentEdgeAndBoundary + _btnIcon.getMeasuredWidth(),
                            iconDistanceFromTop + _btnIcon.getMeasuredHeight());

            _btnText.layout(distanceBtwContentEdgeAndBoundary +
                            _btnIcon.getMeasuredWidth() +
                            SPACE_BTW_ICON_TXT,
                            txtDistanceFromTop,
                            totalWidth - distanceBtwContentEdgeAndBoundary,
                            txtDistanceFromTop + _btnText.getMeasuredHeight());
        } else {
            _btnText.layout(distanceBtwContentEdgeAndBoundary,
                            txtDistanceFromTop,
                            distanceBtwContentEdgeAndBoundary + _btnText.getMeasuredWidth(),
                            txtDistanceFromTop + _btnText.getMeasuredHeight());

            _btnIcon.layout(totalWidth - distanceBtwContentEdgeAndBoundary - _btnIcon.getMeasuredWidth(),
                            iconDistanceFromTop,
                            totalWidth - distanceBtwContentEdgeAndBoundary,
                            iconDistanceFromTop + _btnIcon.getMeasuredHeight());
        }

    }

    /**
     * The default is true and is only required if your container is scrollable
     */
    @Override
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    private StateListDrawable _getStateListDrawableForButtonColor(int buttonColor, boolean darken) {

        // -----------------------------------------------------------------------------------
        // Creating bitmap for color which will be used at normal state
        Rect rectNormal = new Rect(0, 0, 1, 1);
        Bitmap imageNormal = Bitmap.createBitmap(rectNormal.width(),
                                                 rectNormal.height(),
                                                 Bitmap.Config.ARGB_8888);
        Canvas canvasNormal = new Canvas(imageNormal);
        int colorNormal = buttonColor;
        Paint paintNormal = new Paint();
        paintNormal.setColor(colorNormal);
        canvasNormal.drawRect(rectNormal, paintNormal);

        // -----------------------------------------------------------------------------------
        // Creating bitmap for color which will be used at pressed state
        Rect rectPressed = new Rect(0, 0, 1, 1);

        Bitmap imagePressed = Bitmap.createBitmap(rectPressed.width(),
                                                  rectPressed.height(),
                                                  Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(imagePressed);
        int colorPressed = _darkenOrLightenColor(colorNormal, darken);
        Paint paintPressed = new Paint();
        paintPressed.setColor(colorPressed);
        canvas.drawRect(rectPressed, paintPressed);
        RectF bounds = new RectF();
        bounds.round(rectPressed);

        // -----------------------------------------------------------------------------------
        // Now assigning states to StateListDrawable
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{ android.R.attr.state_pressed },
                                   new BitmapDrawable(imagePressed));
        stateListDrawable.addState(StateSet.WILD_CARD, new BitmapDrawable(imageNormal));

        return stateListDrawable;
    }

    private int _darkenOrLightenColor(int color, boolean darken) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);

        // value component
        if (darken) {
            hsv[2] *= Math.max(hsv[2] * 0.99f, 0);
        } else {
            hsv[2] = Math.max(hsv[2] * 1.5f, 1);
        }

        return Color.HSVToColor(hsv);
    }

    private int _convertDpToPixels(int dpSize) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpSize * scale + 0.5f);
    }
}
