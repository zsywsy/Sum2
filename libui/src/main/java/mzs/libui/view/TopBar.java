package mzs.libui.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import mzs.libtools.utils.StringUtils;
import mzs.libtools.utils.ViewUtils;
import mzs.libtools.utils.depend.DebugUtils;
import mzs.libui.R;


public class TopBar extends RelativeLayout {

    private final int DEFAULT_BG = 0x00000000;
    private final int DEFAULT_COLOR = 0XFFFFFFFF;
    private final int DEFAULT_PADDING_SM = 20;
    private final int DEFAULT_PADDING_MD = 40;
    private final int DEFAULT_PADDING_LG = 60;


    private final float DEFAULT_TITLE_SIZE = 20f;
    private final float DEFAULT_LEFT_SIZE = 16f;
    private final float DEFAULT_RIGHT_SIZE = 16f;

    private int cl = DEFAULT_COLOR;

    private String leftText = "";
    private ColorStateList leftColor;
    private float leftSize = DEFAULT_LEFT_SIZE;
    private Drawable leftBackground;
    private Drawable leftDrawableleft;
    private int leftDrawablePadding = 0;

    private String rightText = "";
    private ColorStateList rightColor;
    private float rightSize = DEFAULT_RIGHT_SIZE;
    private Drawable rightBackground;
    private Drawable rightDrawableright;
    private int rightDrawablePadding = 0;

    private String titleText = "";
    private int titleColor;
    private float titleSize = DEFAULT_TITLE_SIZE;
    private int titleBackground = DEFAULT_BG;

    private TextView leftTv;
    private TextView rightTv;
    private TextView titleTv;

    private LayoutParams leftParams, rightParams, titleParams;
    private TopbarOnClickListener topbarOnClickListener = null;

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        int indexCount = ta.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int attr = ta.getIndex(i);
            if (attr == R.styleable.TopBar_cl) {
                cl = ta.getColor(attr, cl);

            } else if (attr == R.styleable.TopBar_leftText) {
                leftText = ta.getString(attr);

            } else if (attr == R.styleable.TopBar_leftColor) {
                leftColor = ta.getColorStateList(attr);

            } else if (attr == R.styleable.TopBar_leftSize) {
                leftSize = ta.getDimension(attr, DEFAULT_LEFT_SIZE);

            } else if (attr == R.styleable.TopBar_leftBackground) {
                leftBackground = ta.getDrawable(attr);

            } else if (attr == R.styleable.TopBar_leftDrawableLeft) {
                leftDrawableleft = ta.getDrawable(attr);

            } else if (attr == R.styleable.TopBar_leftDrawablePadding) {
                leftDrawablePadding = ta.getDimensionPixelSize(attr, leftDrawablePadding);

            } else if (attr == R.styleable.TopBar_rightText) {
                rightText = ta.getString(attr);

            } else if (attr == R.styleable.TopBar_rightColor) {
                rightColor = ta.getColorStateList(attr);

            } else if (attr == R.styleable.TopBar_rightSize) {
                rightSize = ta.getDimension(attr, DEFAULT_RIGHT_SIZE);

            } else if (attr == R.styleable.TopBar_rightBackground) {
                rightBackground = ta.getDrawable(attr);

            } else if (attr == R.styleable.TopBar_rightDrawableRight) {
                rightDrawableright = ta.getDrawable(attr);

            } else if (attr == R.styleable.TopBar_rightDrawablePadding) {
                rightDrawablePadding = ta.getDimensionPixelSize(attr, rightDrawablePadding);

            } else if (attr == R.styleable.TopBar_titleText) {
                titleText = ta.getString(attr);

            } else if (attr == R.styleable.TopBar_titleColor) {
                titleColor = ta.getColor(attr, cl);

            } else if (attr == R.styleable.TopBar_titleSize) {
                titleSize = ta.getDimension(attr, DEFAULT_TITLE_SIZE);

            } else if (attr == R.styleable.TopBar_titleBackground) {
                titleBackground = ta.getColor(attr, DEFAULT_BG);

            }
        }
        ta.recycle();

        leftTv = new TextView(context);
        rightTv = new TextView(context);
        titleTv = new TextView(context);

        leftTv.setAllCaps(false);
        leftTv.setGravity(Gravity.CENTER);
        leftTv.setText(leftText);
        leftTv.setTextColor(leftColor != null ? leftColor : ColorStateList.valueOf(cl));
        leftTv.setTextSize(leftSize);
        leftTv.setCompoundDrawablesWithIntrinsicBounds(leftDrawableleft, null, null, null);
        leftTv.setCompoundDrawablePadding(leftDrawablePadding);
        ViewUtils.setBg(leftTv, leftBackground, DEFAULT_BG);

        if (!TextUtils.isEmpty(leftText)) {
            leftTv.setPadding(DEFAULT_PADDING_SM, 0, DEFAULT_PADDING_SM, 0);
        } else if (leftDrawableleft != null) {
            leftTv.setPadding(DEFAULT_PADDING_MD, 0, DEFAULT_PADDING_MD, 0);
        }


//        leftTv.setPadding(DEFAULT_PADDING_SM, 0, DEFAULT_PADDING_LG, 0);
//        if (leftDrawableleft != null && StringUtils.isEmpty(leftText)) {
//            leftTv.setPadding(DEFAULT_PADDING_SM, 0, DEFAULT_PADDING_LG, 0);
//        }else {
//            leftTv.setPadding(DEFAULT_PADDING_SM, 0, 0, 0);
//        }
        leftTv.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (topbarOnClickListener != null)
                    topbarOnClickListener.leftTvOnClick(leftTv);
            }
        });

        rightTv.setAllCaps(false);
        rightTv.setGravity(Gravity.CENTER);
        rightTv.setText(rightText);
        rightTv.setTextColor(rightColor != null ? rightColor : ColorStateList.valueOf(cl));
        rightTv.setTextSize(rightSize);
        rightTv.setCompoundDrawablesWithIntrinsicBounds(rightDrawableright, null, null, null);
        rightTv.setCompoundDrawablePadding(rightDrawablePadding);
        ViewUtils.setBg(rightTv, rightBackground, DEFAULT_BG);
        if (rightDrawableright != null || !TextUtils.isEmpty(rightText)) {
            rightTv.setPadding(DEFAULT_PADDING_LG, 0, DEFAULT_PADDING_SM, 0);
            DebugUtils.log("setPadding");
        }
//        if (rightDrawableright != null && StringUtils.isEmpty(rightText)) {
//            rightTv.setPadding(DEFAULT_PADDING_LG, 0, DEFAULT_PADDING_SM, 0);
//        }
        rightTv.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (topbarOnClickListener != null)
                    topbarOnClickListener.rightTvOnClick(rightTv);
            }
        });

        titleTv.setAllCaps(false);
        titleTv.setText(titleText);
        titleTv.setTextColor(titleColor == 0 ? cl : titleColor);
        titleTv.setTextSize(titleSize);
        titleTv.setBackgroundColor((titleBackground));
        titleTv.setGravity(Gravity.CENTER);


        leftParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        addView(leftTv, leftParams);

        rightParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        addView(rightTv, rightParams);

        titleParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(titleTv, titleParams);
    }


//    public void setLeftVisibility(int visibility) {
//        leftTv.setVisibility(visibility);
//    }
//
//    public void setTitleVisibility(int visibility) {
//        titleTv.setVisibility(visibility);
//    }
//
//    public void setRightVisibility(int visibility) {
//        rightTv.setVisibility(visibility);
//    }
//
//    public void setLeftText(CharSequence text) {
//        leftTv.setText(text);
//    }
//
//    public void setRightText(CharSequence text) {
//        rightTv.setText(text);
//    }
//
//    public void setTitleText(CharSequence text) {
//        titleTv.setText(text);
//    }

    public void setTopbarOnClickListener(TopbarOnClickListener topbarOnClickListener) {
        this.topbarOnClickListener = topbarOnClickListener;
    }

    public interface TopbarOnClickListener {
        void leftTvOnClick(View v);

        void rightTvOnClick(View v);
    }

    public abstract class LeftOnClickListener implements TopbarOnClickListener {

        @Override
        public abstract void leftTvOnClick(View v);

        @Override
        public void rightTvOnClick(View v) {

        }
    }

    public abstract class RightOnClickListener implements TopbarOnClickListener {

        @Override
        public void leftTvOnClick(View v) {

        }

        @Override
        public abstract void rightTvOnClick(View v);
    }
}
