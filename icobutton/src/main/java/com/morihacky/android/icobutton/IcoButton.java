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
import android.util.Log;
import android.util.StateSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.morihacky.android.icobutton.core.IcoButtonViewHelper;

import static com.morihacky.android.icobutton.core.IcoButtonViewHelper.ICON_ALIGN_LEFT_OF_TEXT;

public class IcoButton
    extends ViewGroup {

    public static final String HOLO_BLUE = "#ff0099cc";
    public static final String WHITE = "#ffffff";

    private final Context _context;
    private AttributeSet _attributes;

    private View _btn;
    private TextView _btnText;
    private ImageView _btnIcon;
    private IcoButtonViewHelper _icoViewHelper;

    private int _icoDirection;
    private int _padding;

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

    public void setText(String text) {
        _btnText.setText(text);
    }

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
        if (changed) {
            _btnIcon.setAdjustViewBounds(true);
            _btnIcon.setMaxHeight(_btnText.getMeasuredHeight());

            _icoViewHelper.setTotalWidth(getMeasuredWidth());
            _icoViewHelper.setTotalHeight(getMeasuredHeight());
            _icoViewHelper.setPadding(_padding);

            _icoViewHelper.setIcoWidth(_btnIcon.getMeasuredWidth());
            _icoViewHelper.setTextHeight(_btnText.getMeasuredHeight());
            _icoViewHelper.setIcoAlign(_icoDirection);
        }
        _icoViewHelper.setTextWidth(_btnText.getMeasuredWidth());

        _btnIcon.layout(_icoViewHelper.getLeftForIcon(),
                        _icoViewHelper.getTopForIcon(),
                        _icoViewHelper.getRightForIcon(),
                        _icoViewHelper.getBottomForIcon());
        _btnText.layout(_icoViewHelper.getLeftForText(),
                        _icoViewHelper.getTopForText(),
                        _icoViewHelper.getRightForText(),
                        _icoViewHelper.getBottomForText());
    }

    /**
     * The default is true and is only required if your container is scrollable
     */
    @Override
    public boolean shouldDelayChildPressedState() {
        return false;
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

        _icoViewHelper = new IcoButtonViewHelper();

        _setupButton(xmlAttrs);
        _setupButtonText(xmlAttrs);
        _setupButtonIcon(xmlAttrs);

        xmlAttrs.recycle();
    }

    private void _setupButtonIcon(TypedArray xmlAttrs) {
        _btnIcon.setImageDrawable(xmlAttrs.getDrawable(R.styleable.IcoButton_drawable));
        _icoDirection = xmlAttrs.getInt(R.styleable.IcoButton_iconAlign, ICON_ALIGN_LEFT_OF_TEXT);
    }

    private void _setupButton(TypedArray xmlAttrs) {
        _btn.setClickable(true);

        int color = xmlAttrs.getColor(R.styleable.IcoButton_color, Color.parseColor(HOLO_BLUE));
        _btn.setBackgroundDrawable(_getStateListDrawableForButtonColor(color, true));

        _padding = xmlAttrs.getDimensionPixelSize(R.styleable.IcoButton_padding, _convertDpToPixels(10));
        _btn.setPadding(_padding, _padding, _padding, _padding);
    }

    private void _setupButtonText(TypedArray xmlAttrs) {
        boolean allCaps = xmlAttrs.getBoolean(R.styleable.IcoButton_txtAllCaps, false);
        String btnText = xmlAttrs.getString(R.styleable.IcoButton_txt);
        if (allCaps) {
            btnText = btnText.toUpperCase();
        }

        _btnText.setText(btnText);
        _btnText.setTextColor(xmlAttrs.getColor(R.styleable.IcoButton_txtColor,
                                                Color.parseColor(WHITE)));
        _btnText.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                             xmlAttrs.getDimensionPixelSize(R.styleable.IcoButton_txtSize,
                                                            _convertDpToPixels(16)));
    }

    private StateListDrawable _getStateListDrawableForButtonColor(int buttonColor, boolean darken) {

        // -----------------------------------------------------------------------------------
        // Creating bitmap for color which will be used at normal state
        Rect rectNormal = new Rect(0, 0, 1, 1);
        Bitmap imageNormal = Bitmap.createBitmap(rectNormal.width(),
                                                 rectNormal.height(),
                                                 Bitmap.Config.ARGB_8888);
        Canvas canvasNormal = new Canvas(imageNormal);
        Paint paintNormal = new Paint();
        paintNormal.setColor(buttonColor);
        canvasNormal.drawRect(rectNormal, paintNormal);

        // -----------------------------------------------------------------------------------
        // Creating bitmap for color which will be used at pressed state
        Rect rectPressed = new Rect(0, 0, 1, 1);

        Bitmap imagePressed = Bitmap.createBitmap(rectPressed.width(),
                                                  rectPressed.height(),
                                                  Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(imagePressed);
        int colorPressed = _darkenOrLightenColor(buttonColor, darken);
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
            hsv[2] *= Math.max(hsv[2] * 0.8f, 0);
        } else {
            hsv[2] = Math.max(hsv[2] * 1.5f, 1);
        }

        return Color.HSVToColor(hsv);
    }

    private int _convertDpToPixels(int dps) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                               dps,
                                               getResources().getDisplayMetrics());
    }
}
