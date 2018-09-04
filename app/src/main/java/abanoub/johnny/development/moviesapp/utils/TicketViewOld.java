package abanoub.johnny.development.moviesapp.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import abanoub.johnny.development.moviesapp.R;

import static android.graphics.Bitmap.Config.ALPHA_8;
import static android.graphics.Color.BLACK;
import static android.graphics.Color.TRANSPARENT;
import static android.graphics.Paint.ANTI_ALIAS_FLAG;
import static android.graphics.PorterDuff.Mode.SRC_IN;


/**
 * Created by Vipul Asri on 31/10/17.
 */

public class TicketViewOld extends LinearLayout {

    public static final String TAG = TicketViewOld.class.getSimpleName();

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({Orientation.HORIZONTAL, Orientation.VERTICAL})
    public @interface Orientation {
        int HORIZONTAL = 0;
        int VERTICAL = 1;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TicketViewOld.DividerType.NORMAL, TicketViewOld.DividerType.DASH})
    public @interface DividerType {
        int NORMAL = 0;
        int DASH = 1;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TicketViewOld.CornerType.NORMAL, TicketViewOld.CornerType.ROUNDED})
    public @interface CornerType {
        int NORMAL = 0;
        int ROUNDED = 1;
        int SCALLOP = 2;
    }

    private Paint mBackgroundPaint = new Paint();
    private Paint mBorderPaint = new Paint();
    private Paint mDividerPaint = new Paint();
    private int mOrientation;
    private Path mPath = new Path();
    private boolean mDirty = true;
    private float mDividerStartX, mDividerStartY, mDividerStopX, mDividerStopY;
    private RectF mRect = new RectF();
    private RectF mRoundedCornerArc = new RectF();
    private RectF mScallopCornerArc = new RectF();
    private int mScallopHeight;
    private int mBackgroundColor;
    private boolean mShowBorder;
    private int mBorderWidth;
    private int mBorderColor;
    private boolean mShowDivider;
    private int mScallopRadius;
    private int mDividerDashLength;
    private int mDividerDashGap;
    private int mDividerType;
    private int mDividerWidth;
    private int mDividerColor;
    private int mCornerType;
    private int mCornerRadius;
    private int mDividerPadding;
    private Bitmap mShadow;
    private final Paint mShadowPaint = new Paint(ANTI_ALIAS_FLAG);
    private float mShadowBlurRadius = 0f;

    public TicketViewOld(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TicketViewOld(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        init(attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDirty&&getChildCount()>1) {
            doLayout(getChildAt(0).getBottom()-1);
        }
        if (mShadowBlurRadius > 0f && !isInEditMode()) {
            canvas.drawBitmap(mShadow, 0f, mShadowBlurRadius / 2f, null);
        }
        canvas.drawPath(mPath, mBackgroundPaint);
        if (mShowBorder) {
            canvas.drawPath(mPath, mBorderPaint);
        }
        if (mShowDivider) {
            canvas.drawLine(mDividerStartX, mDividerStartY, mDividerStopX, mDividerStopY, mDividerPaint);
        }
    }

    private void doLayout(float mScallopPosition) {
        float offset;
        float left = getPaddingLeft() + mShadowBlurRadius;
        float right = getWidth() - getPaddingRight() - mShadowBlurRadius;
        float top = getPaddingTop() + (mShadowBlurRadius / 2);
        float bottom = getHeight() - getPaddingBottom() - mShadowBlurRadius - (mShadowBlurRadius / 2);
        mPath.reset();

        if (mOrientation == TicketViewOld.Orientation.HORIZONTAL) {
            offset =  mScallopPosition;

            if (mCornerType == TicketViewOld.CornerType.ROUNDED) {
                mPath.arcTo(getTopLeftCornerRoundedArc(top, left), 180.0f, 90.0f, false);
                mPath.lineTo(left + mCornerRadius, top);

                mPath.lineTo(right - mCornerRadius, top);
                mPath.arcTo(getTopRightCornerRoundedArc(top, right), -90.0f, 90.0f, false);

            } else if (mCornerType == TicketViewOld.CornerType.SCALLOP) {
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

            if (mCornerType == TicketViewOld.CornerType.ROUNDED) {

                mPath.arcTo(getBottomRightCornerRoundedArc(bottom, right), 0.0f, 90.0f, false);
                mPath.lineTo(right - mCornerRadius, bottom);

                mPath.lineTo(left + mCornerRadius, bottom);
                mPath.arcTo(getBottomLeftCornerRoundedArc(left, bottom), 90.0f, 90.0f, false);

            } else if (mCornerType == TicketViewOld.CornerType.SCALLOP) {

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
            offset =  mScallopPosition;


            if (mCornerType == TicketViewOld.CornerType.ROUNDED) {
                mPath.arcTo(getTopLeftCornerRoundedArc(top, left), 180.0f, 90.0f, false);
                mPath.lineTo(left + mCornerRadius, top);

            } else if (mCornerType == TicketViewOld.CornerType.SCALLOP) {

                mPath.arcTo(getTopLeftCornerScallopArc(top, left), 90.0f, -90.0f, false);
                mPath.lineTo(left + mCornerRadius, top);

            } else {
                mPath.moveTo(left, top);
            }

            mRect.set(left + offset, top - mScallopRadius, mScallopHeight + offset + left, top + mScallopRadius);
            mPath.arcTo(mRect, 180.0f, -180.0f, false);

            if (mCornerType == TicketViewOld.CornerType.ROUNDED) {

                mPath.lineTo(right - mCornerRadius, top);
                mPath.arcTo(getTopRightCornerRoundedArc(top, right), -90.0f, 90.0f, false);

                mPath.arcTo(getBottomRightCornerRoundedArc(bottom, right), 0.0f, 90.0f, false);
                mPath.lineTo(right - mCornerRadius, bottom);

            } else if (mCornerType == TicketViewOld.CornerType.SCALLOP) {

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

            if (mCornerType == TicketViewOld.CornerType.ROUNDED) {

                mPath.arcTo(getBottomLeftCornerRoundedArc(left, bottom), 90.0f, 90.0f, false);
                mPath.lineTo(left, bottom - mCornerRadius);

            } else if (mCornerType == TicketViewOld.CornerType.SCALLOP) {

                mPath.arcTo(getBottomLeftCornerScallopArc(left, bottom), 0.0f, -90.0f, false);
                mPath.lineTo(left, bottom - mCornerRadius);

            } else {
                mPath.lineTo(left, bottom);
            }
            mPath.close();
        }

        // divider
        if (mOrientation == TicketViewOld.Orientation.HORIZONTAL) {
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

        generateShadow();
        mDirty = false;
    }

    private void generateShadow() {
        if (!isInEditMode()) {
            if (mShadowBlurRadius == 0f) return;

            if (mShadow == null) {
                mShadow = Bitmap.createBitmap(getWidth(), getHeight(), ALPHA_8);
            } else {
                mShadow.eraseColor(TRANSPARENT);
            }
            Canvas c = new Canvas(mShadow);
            c.drawPath(mPath, mShadowPaint);
            if (mShowBorder) {
                c.drawPath(mPath, mShadowPaint);
            }
            RenderScript rs = RenderScript.create(getContext());
            ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(rs, Element.U8(rs));
            Allocation input = Allocation.createFromBitmap(rs, mShadow);
            Allocation output = Allocation.createTyped(rs, input.getType());
            blur.setRadius(mShadowBlurRadius);
            blur.setInput(input);
            blur.forEach(output);
            output.copyTo(mShadow);
            input.destroy();
            output.destroy();
            blur.destroy();
        }
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SeparatorLinearLayout);
            mOrientation = typedArray.getInt(R.styleable.SeparatorLinearLayout_orientation, TicketViewOld.Orientation.HORIZONTAL);
            mBackgroundColor = typedArray.getColor(R.styleable.SeparatorLinearLayout_backgroundColor, getResources().getColor(android.R.color.white));
            mScallopRadius = typedArray.getDimensionPixelSize(R.styleable.SeparatorLinearLayout_scallopRadius, Utils.dpToPx(20f, getContext()));
            mShowBorder = typedArray.getBoolean(R.styleable.SeparatorLinearLayout_showBorder, false);
            mBorderWidth = typedArray.getDimensionPixelSize(R.styleable.SeparatorLinearLayout_borderWidth, Utils.dpToPx(2f, getContext()));
            mBorderColor = typedArray.getColor(R.styleable.SeparatorLinearLayout_borderColor, getResources().getColor(android.R.color.black));
            mShowDivider = typedArray.getBoolean(R.styleable.SeparatorLinearLayout_showDivider, false);
            mDividerType = typedArray.getInt(R.styleable.SeparatorLinearLayout_dividerType, TicketViewOld.DividerType.NORMAL);
            mDividerWidth = typedArray.getDimensionPixelSize(R.styleable.SeparatorLinearLayout_dividerWidth, Utils.dpToPx(2f, getContext()));
            mDividerColor = typedArray.getColor(R.styleable.SeparatorLinearLayout_dividerColor, getResources().getColor(android.R.color.darker_gray));
            mDividerDashLength = typedArray.getDimensionPixelSize(R.styleable.SeparatorLinearLayout_dividerDashLength, Utils.dpToPx(8f, getContext()));
            mDividerDashGap = typedArray.getDimensionPixelSize(R.styleable.SeparatorLinearLayout_dividerDashGap, Utils.dpToPx(4f, getContext()));
            mCornerType = typedArray.getInt(R.styleable.SeparatorLinearLayout_cornerType, TicketViewOld.CornerType.NORMAL);
            mCornerRadius = typedArray.getDimensionPixelSize(R.styleable.SeparatorLinearLayout_cornerRadius, Utils.dpToPx(4f, getContext()));
            mDividerPadding = typedArray.getDimensionPixelSize(R.styleable.SeparatorLinearLayout_dividerPadding, Utils.dpToPx(10f, getContext()));
            float elevation = 0f;
            if (typedArray.hasValue(R.styleable.SeparatorLinearLayout_ticketElevation)) {
                elevation = typedArray.getDimension(R.styleable.SeparatorLinearLayout_ticketElevation, elevation);
            } else if (typedArray.hasValue(R.styleable.SeparatorLinearLayout_android_elevation)) {
                elevation = typedArray.getDimension(R.styleable.SeparatorLinearLayout_android_elevation, elevation);
            }
            if (elevation > 0f) {
                setShadowBlurRadius(elevation);
            }

            typedArray.recycle();
        }

        mShadowPaint.setColorFilter(new PorterDuffColorFilter(BLACK, SRC_IN));
        mShadowPaint.setAlpha(51); // 20%

        initElements();

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    private void initElements() {

        if (mDividerWidth > mScallopRadius) {
            mDividerWidth = mScallopRadius;
            Log.w(TAG, "You cannot apply divider width greater than scallop radius. Applying divider width to scallop radius.");
        }
        mScallopHeight = mScallopRadius * 2;

        setBackgroundPaint();
        setBorderPaint();
        setDividerPaint();

        mDirty = true;
        invalidate();
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

        if (mDividerType == TicketViewOld.DividerType.DASH)
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

    public void setBackgroundColor(int backgroundColor) {
        this.mBackgroundColor = backgroundColor;
        initElements();
    }

    public boolean isShowBorder() {
        return mShowBorder;
    }

    public void setShowBorder(boolean showBorder) {
        this.mShowBorder = showBorder;
        initElements();
    }

    public int getBorderWidth() {
        return mBorderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.mBorderWidth = borderWidth;
        initElements();
    }


    public int getDividerPadding() {
        return mDividerPadding;
    }

    public void setDividerPadding(int mDividerPadding) {
        this.mDividerPadding = mDividerPadding;
        initElements();
    }

    private void setShadowBlurRadius(float elevation) {

        float maxElevation = Utils.dpToPx(24f, getContext());
        mShadowBlurRadius = Math.min(25f * (elevation / maxElevation), 25f);
    }
}