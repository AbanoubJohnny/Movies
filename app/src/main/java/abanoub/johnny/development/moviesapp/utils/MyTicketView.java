package abanoub.johnny.development.moviesapp.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import abanoub.johnny.library.ticketview.DividerDrawer;
import abanoub.johnny.library.ticketview.Utils;

public class MyTicketView extends LinearLayout {

    private Drawable mSeparator;
    private Rect mSeparatorBounds = new Rect();
    private int mSeparatorHeight;
    private int mSeparatorWidth;

    private boolean mShowBorder;
    private int mBorderWidth;
    private int mBorderColor;
    private Paint mBorderPaint = new Paint();
    private RectF mScallopCornerArc = new RectF();
    private Path mPath = new Path();
    private int mBackgroundColor;


    private int mScallopRadius = 20;
    private int mShadowBlurRadius = 10;
    private boolean mDirty = true;
    private Paint mBackgroundPaint = new Paint();
    private Paint mDividerPaint = new Paint();
    private int mOrientation;
    private float mDividerStartX, mDividerStartY, mDividerStopX, mDividerStopY;
    private RectF mRect = new RectF();
    private RectF mRoundedCornerArc = new RectF();
    private int mDividerDashLength;
    private int mDividerDashGap;
    private int mDividerType;
    private int mDividerWidth;
    private int mDividerColor;
    private int mCornerType;
    private int mCornerRadius;
    private int mDividerPadding;
    private int mScallopHeight;

    public MyTicketView(Context context) {
        this(context, null);
    }

    public MyTicketView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTicketView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, abanoub.johnny.library.ticketview.R.styleable.SeparatorLinearLayout, 0, 0);


        try {

            mScallopRadius = typedArray.getDimensionPixelSize(abanoub.johnny.library.ticketview.R.styleable.SeparatorLinearLayout_scallopRadius, Utils.dpToPx(20f, getContext()));
            mBackgroundColor = typedArray.getColor(abanoub.johnny.library.ticketview.R.styleable.SeparatorLinearLayout_backgroundColor, getResources().getColor(android.R.color.white));
            mOrientation = typedArray.getInt(abanoub.johnny.library.ticketview.R.styleable.SeparatorLinearLayout_orientation, DividerDrawer.Orientation.HORIZONTAL);
            mDividerType = typedArray.getInt(abanoub.johnny.library.ticketview.R.styleable.SeparatorLinearLayout_dividerType, DividerDrawer.DividerType.NORMAL);
            mShowBorder = typedArray.getBoolean(abanoub.johnny.library.ticketview.R.styleable.SeparatorLinearLayout_showBorder, false);
            mBorderWidth = typedArray.getDimensionPixelSize(abanoub.johnny.library.ticketview.R.styleable.SeparatorLinearLayout_borderWidth, Utils.dpToPx(2f, getContext()));
            mBorderColor = typedArray.getColor(abanoub.johnny.library.ticketview.R.styleable.SeparatorLinearLayout_borderColor, getResources().getColor(android.R.color.black));
            mDividerWidth = typedArray.getDimensionPixelSize(abanoub.johnny.library.ticketview.R.styleable.SeparatorLinearLayout_dividerWidth, Utils.dpToPx(2f, getContext()));
            mDividerColor = typedArray.getColor(abanoub.johnny.library.ticketview.R.styleable.SeparatorLinearLayout_dividerColor, getResources().getColor(android.R.color.darker_gray));
            mDividerDashLength = typedArray.getDimensionPixelSize(abanoub.johnny.library.ticketview.R.styleable.SeparatorLinearLayout_dividerDashLength, Utils.dpToPx(8f, getContext()));
            mDividerDashGap = typedArray.getDimensionPixelSize(abanoub.johnny.library.ticketview.R.styleable.SeparatorLinearLayout_dividerDashGap, Utils.dpToPx(4f, getContext()));
            mCornerType = typedArray.getInt(abanoub.johnny.library.ticketview.R.styleable.SeparatorLinearLayout_cornerType, DividerDrawer.CornerType.NORMAL);
            mCornerRadius = typedArray.getDimensionPixelSize(abanoub.johnny.library.ticketview.R.styleable.SeparatorLinearLayout_cornerRadius, Utils.dpToPx(4f, getContext()));
            mDividerPadding = typedArray.getDimensionPixelSize(abanoub.johnny.library.ticketview.R.styleable.SeparatorLinearLayout_dividerPadding, Utils.dpToPx(10f, getContext()));

        } finally {
            typedArray.recycle();
        }



        mScallopHeight = mScallopRadius * 2;
        setBackgroundPaint();
        setBorderPaint();
        setDividerPaint();

        mDirty = true;
    }

    public void setSeparator(Drawable drawable) {
        mSeparator = drawable;
        if (mSeparator != null) {
            mSeparatorHeight = mSeparator.getIntrinsicHeight();
            mSeparatorWidth = mSeparator.getIntrinsicWidth();
        }
    }

    public void setSeparatorHeight(int separatorHeight) {
        mSeparatorHeight = separatorHeight;
    }

    public void setSeparatorWidth(int separatorWidth) {
        mSeparatorWidth = separatorWidth;
    }

    private void drawVerticalSeparators(Canvas canvas) {
        mSeparatorBounds.left = 0;
        mSeparatorBounds.right = canvas.getWidth();
        if (getChildCount()>1)
            doLayout( getChildAt(0).getBottom() - 1);

        for (int i = 0; i <= getChildCount() - 1; i++) {
            View child = getChildAt(i);

            mSeparatorBounds.top = child.getBottom() - 1;
            mSeparatorBounds.bottom = mSeparatorBounds.top + mSeparatorHeight;
            drawDrawableToCanvas(canvas);
        }
    }

    private void drawHorizontalSeparators(Canvas canvas) {
        mSeparatorBounds.top = 0;
        mSeparatorBounds.bottom = canvas.getHeight();
        for (int i = 1; i <= getChildCount() - 1; i++) {
            View child = getChildAt(i);
            doLayout(child.getClipBounds().bottom);
            mSeparatorBounds.left = child.getRight() - 1;
            mSeparatorBounds.right = mSeparatorBounds.left + mSeparatorWidth;
            drawDrawableToCanvas(canvas);
        }
    }

    private void drawDrawableToCanvas(Canvas canvas) {
        mSeparator.setBounds(mSeparatorBounds);
        mSeparator.draw(canvas);
    }

    private void drawSeparators(Canvas canvas) {
        switch (getOrientation()) {
            case VERTICAL:
                if (mSeparator != null) {
                    drawVerticalSeparators(canvas);
                }
                break;
            case HORIZONTAL:
                if (mSeparator != null) {
                    drawHorizontalSeparators(canvas);
                }
                break;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        DividerDrawer dividerDrawer = new DividerDrawer(mBackgroundPaint,mDividerPaint,mOrientation,mDividerStartX,mDividerStartY,mDividerStopX,mDividerStopY,mRoundedCornerArc,
                mDividerDashLength,mDividerDashGap,mDividerType,mDividerWidth,mDividerColor,mCornerType,mCornerRadius,mDividerPadding);
        setSeparator(dividerDrawer.drawBreaker(this,getContext()));
        drawSeparators(canvas);
        canvas.drawPath(mPath, mBackgroundPaint);
        if (mShowBorder) {
            canvas.drawPath(mPath, mBorderPaint);
        }
    }

    private void doLayout(float mScallopPosition) {
        float offset;
        float left = getPaddingLeft() + mShadowBlurRadius;
        float right = getWidth() - getPaddingRight() - mShadowBlurRadius;
        float top = getPaddingTop() + (mShadowBlurRadius / 2);
        float bottom = getHeight() - getPaddingBottom() - mShadowBlurRadius - (mShadowBlurRadius / 2);


        if (mOrientation == DividerDrawer.Orientation.HORIZONTAL) {
            offset = mScallopPosition  ;

            if (mCornerType == DividerDrawer.CornerType.ROUNDED) {
                mPath.arcTo(getTopLeftCornerRoundedArc(top, left), 180.0f, 90.0f, false);
                mPath.lineTo(left + mCornerRadius, top);

                mPath.lineTo(right - mCornerRadius, top);
                mPath.arcTo(getTopRightCornerRoundedArc(top, right), -90.0f, 90.0f, false);

            } else if (mCornerType == DividerDrawer.CornerType.SCALLOP) {
                mPath.arcTo(getTopLeftCornerScallopArc(top, left), 90.0f, -90.0f, false);
                mPath.lineTo(left + mCornerRadius, top);

                mPath.lineTo(right - mCornerRadius, top);
                mPath.arcTo(getTopRightCornerScallopArc(top, right), 180.0f, -90.0f, false);

            } else {
                mPath.moveTo(left, top);
                mPath.lineTo(right, top);
            }

            mRect.set(right - mScallopRadius, top + offset, right + mScallopRadius, mScallopHeight + offset + top);
            mPath.arcTo(mRect, 270, -180.0f, false);

            if (mCornerType == DividerDrawer.CornerType.ROUNDED) {

                mPath.arcTo(getBottomRightCornerRoundedArc(bottom, right), 0.0f, 90.0f, false);
                mPath.lineTo(right - mCornerRadius, bottom);

                mPath.lineTo(left + mCornerRadius, bottom);
                mPath.arcTo(getBottomLeftCornerRoundedArc(left, bottom), 90.0f, 90.0f, false);

            } else if (mCornerType == DividerDrawer.CornerType.SCALLOP) {

                mPath.arcTo(getBottomRightCornerScallopArc(bottom, right), 270.0f, -90.0f, false);
                mPath.lineTo(right - mCornerRadius, bottom);

                mPath.lineTo(left + mCornerRadius, bottom);
                mPath.arcTo(getBottomLeftCornerScallopArc(left, bottom), 0.0f, -90.0f, false);

            } else {
                mPath.lineTo(right, bottom);
                mPath.lineTo(left, bottom);
            }

            mRect.set(left - mScallopRadius, top + offset, left + mScallopRadius, mScallopHeight + offset + top);
            mPath.arcTo(mRect, 90.0f, -180.0f, false);
            mPath.close();

        } else {
            offset = mScallopPosition ;

            if (mCornerType == DividerDrawer.CornerType.ROUNDED) {
                mPath.arcTo(getTopLeftCornerRoundedArc(top, left), 180.0f, 90.0f, false);
                mPath.lineTo(left + mCornerRadius, top);

            } else if (mCornerType == DividerDrawer.CornerType.SCALLOP) {

                mPath.arcTo(getTopLeftCornerScallopArc(top, left), 90.0f, -90.0f, false);
                mPath.lineTo(left + mCornerRadius, top);

            } else {
                mPath.moveTo(left, top);
            }

            mRect.set(left + offset, top - mScallopRadius, mScallopHeight + offset + left, top + mScallopRadius);
            mPath.arcTo(mRect, 180.0f, -180.0f, false);

            if (mCornerType == DividerDrawer.CornerType.ROUNDED) {

                mPath.lineTo(right - mCornerRadius, top);
                mPath.arcTo(getTopRightCornerRoundedArc(top, right), -90.0f, 90.0f, false);

                mPath.arcTo(getBottomRightCornerRoundedArc(bottom, right), 0.0f, 90.0f, false);
                mPath.lineTo(right - mCornerRadius, bottom);

            } else if (mCornerType == DividerDrawer.CornerType.SCALLOP) {

                mPath.lineTo(right - mCornerRadius, top);
                mPath.arcTo(getTopRightCornerScallopArc(top, right), 180.0f, -90.0f, false);

                mPath.arcTo(getBottomRightCornerScallopArc(bottom, right), 270.0f, -90.0f, false);
                mPath.lineTo(right - mCornerRadius, bottom);

            } else {
                mPath.lineTo(right, top);
                mPath.lineTo(right, bottom);
            }

            mRect.set(left + offset, bottom - mScallopRadius, mScallopHeight + offset + left, bottom + mScallopRadius);
            mPath.arcTo(mRect, 0.0f, -180.0f, false);

            if (mCornerType == DividerDrawer.CornerType.ROUNDED) {

                mPath.arcTo(getBottomLeftCornerRoundedArc(left, bottom), 90.0f, 90.0f, false);
                mPath.lineTo(left, bottom - mCornerRadius);

            } else if (mCornerType == DividerDrawer.CornerType.SCALLOP) {

                mPath.arcTo(getBottomLeftCornerScallopArc(left, bottom), 0.0f, -90.0f, false);
                mPath.lineTo(left, bottom - mCornerRadius);

            } else {
                mPath.lineTo(left, bottom);
            }
            mPath.close();
        }
        // divider
        if (mOrientation == DividerDrawer.Orientation.HORIZONTAL) {
            mDividerStartX = left + mScallopRadius + mDividerPadding;
            mDividerStartY = mScallopRadius + top + offset;
            mDividerStopX = right - mScallopRadius - mDividerPadding;
            mDividerStopY = mScallopRadius + top + offset;
        } else {
            mDividerStartX = mScallopRadius + left + offset;
            mDividerStartY = top + mScallopRadius + mDividerPadding;
            mDividerStopX = mScallopRadius + left + offset;
            mDividerStopY = bottom - mScallopRadius - mDividerPadding;
        }

        mDirty = false;
    }
        private void setBackgroundPaint() {
        mBackgroundPaint.setAlpha(0);
        mBackgroundPaint.setAntiAlias(true);
        mBackgroundPaint.setColor(mBackgroundColor);
        mBackgroundPaint.setStyle(Paint.Style.FILL);
    }
    private void setBorderPaint() {
        mBorderPaint.setAlpha(0);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setColor(mBorderColor);
        mBorderPaint.setStrokeWidth(mBorderWidth);
        mBorderPaint.setStyle(Paint.Style.STROKE);
    }
    private void setDividerPaint() {
        mDividerPaint.setAlpha(0);
        mDividerPaint.setAntiAlias(true);
        mDividerPaint.setColor(mDividerColor);
        mDividerPaint.setStrokeWidth(mDividerWidth);

        if (mDividerType == DividerDrawer.DividerType.DASH)
            mDividerPaint.setPathEffect(new DashPathEffect(new float[]{(float) mDividerDashLength, (float) mDividerDashGap}, 0.0f));
        else
            mDividerPaint.setPathEffect(new PathEffect());
    }
    private RectF getTopLeftCornerRoundedArc(float top, float left) {
        mRoundedCornerArc.set(left, top, left + mCornerRadius * 2, top + mCornerRadius * 2);
        return mRoundedCornerArc;
    }

    private RectF getTopRightCornerRoundedArc(float top, float right) {
        mRoundedCornerArc.set(right - mCornerRadius * 2, top, right, top + mCornerRadius * 2);
        return mRoundedCornerArc;
    }

    private RectF getBottomLeftCornerRoundedArc(float left, float bottom) {
        mRoundedCornerArc.set(left, bottom - mCornerRadius * 2, left + mCornerRadius * 2, bottom);
        return mRoundedCornerArc;
    }

    private RectF getBottomRightCornerRoundedArc(float bottom, float right) {
        mRoundedCornerArc.set(right - mCornerRadius * 2, bottom - mCornerRadius * 2, right, bottom);
        return mRoundedCornerArc;
    }

    private RectF getTopLeftCornerScallopArc(float top, float left) {
        mScallopCornerArc.set(left - mCornerRadius, top - mCornerRadius, left + mCornerRadius, top + mCornerRadius);
        return mScallopCornerArc;
    }

    private RectF getTopRightCornerScallopArc(float top, float right) {
        mScallopCornerArc.set(right - mCornerRadius, top - mCornerRadius, right + mCornerRadius, top + mCornerRadius);
        return mScallopCornerArc;
    }

    private RectF getBottomLeftCornerScallopArc(float left, float bottom) {
        mScallopCornerArc.set(left - mCornerRadius, bottom - mCornerRadius, left + mCornerRadius, bottom + mCornerRadius);
        return mScallopCornerArc;
    }

    private RectF getBottomRightCornerScallopArc(float bottom, float right) {
        mScallopCornerArc.set(right - mCornerRadius, bottom - mCornerRadius, right + mCornerRadius, bottom + mCornerRadius);
        return mScallopCornerArc;
    }

}
