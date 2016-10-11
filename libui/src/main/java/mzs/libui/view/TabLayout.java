package mzs.libui.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import mzs.libtools.utils.depend.DebugUtils;
import mzs.libui.R;

/**
 * Created by 24275 on 2016/9/21.
 */

public class TabLayout extends LinearLayout {

    private final String SuperSateKey = "SuperSateKey";
    private final String PrePositionKey = "PrePositionKey";

    private final LayoutParams DefaultLp = new LayoutParams(0, FrameLayout.LayoutParams.MATCH_PARENT, 1.0f);

    private Context mContext;
    private Entity[] datas;

    private int defaultColor = 0xffff0000;
    private int selectedColor;

    private int prePosition = -1;

    private onSelectedChangedLsn onSelectedChangedLsn;


    public TabLayout(Context context) {
        this(context, null);
    }

    public TabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public void setData(Entity[] datas) {
        this.datas = datas;
        init();
    }

    public void setOnSelectedChangedLsn(TabLayout.onSelectedChangedLsn onSelectedChangedLsn) {
        this.onSelectedChangedLsn = onSelectedChangedLsn;
    }

    private void init() {
        setOrientation(HORIZONTAL);
        for (int i = 0; i < datas.length; i++) {
            addTab(getTab(i));
        }
    }

    private View getTab(final int position) {
        View tabView = View.inflate(mContext, R.layout.tab_bottom, null);
        final ViewHolder holder = new ViewHolder();
        holder.tabIconIv = (ImageView) tabView.findViewById(R.id.iv_tab_icon);
        holder.tabTitleTv = (TextView) tabView.findViewById(R.id.tv_tab_title);
        holder.tabIconIv.setImageResource(datas[position].getDefaultIcon());
        holder.tabTitleTv.setText(datas[position].getTitle());
        holder.tabTitleTv.setTextSize(10);
        holder.tabTitleTv.setTextColor(ColorStateList.valueOf(defaultColor));
        tabView.setTag(holder);
        tabView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == prePosition) {
                    return;
                }
                if (prePosition >= 0) {
                    setUnSelected(prePosition);
                }
                setSelected(position);
            }
        });
        return tabView;
    }

    public void setSelected(int position) {
        ViewHolder viewHolder = getViewHolder(position);
        viewHolder.tabIconIv.setImageResource(datas[position].getSelectedIcon());
        viewHolder.tabTitleTv.setTextColor(ColorStateList.valueOf(selectedColor));
        prePosition = position;
        if (onSelectedChangedLsn != null) {
            onSelectedChangedLsn.onSelected(position);
        }
    }

    private void setUnSelected(int position) {
        ViewHolder viewHolder = getViewHolder(position);
        viewHolder.tabIconIv.setImageResource(datas[position].getDefaultIcon());
        viewHolder.tabTitleTv.setTextColor(ColorStateList.valueOf(defaultColor));
        if (onSelectedChangedLsn != null) {
            onSelectedChangedLsn.onUnSelected(position);
        }
    }

    private ViewHolder getViewHolder(int position) {
        return (ViewHolder) getChildAt(position).getTag();
    }

    private void addTab(View view) {
        addView(view, DefaultLp);
    }

    public void setTextColor(int defaultColor, int selectedColor) {
        DebugUtils.log("setTextColor");
        this.defaultColor = defaultColor;
        this.selectedColor = selectedColor;
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        DebugUtils.log(Integer.toHexString(defaultColor) + ";" + Integer.toHexString(selectedColor) + ";" + prePosition);
        Parcelable superState = super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putParcelable(SuperSateKey, superState);
        bundle.putInt(PrePositionKey, prePosition);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        DebugUtils.log(Integer.toHexString(defaultColor) + ";" + Integer.toHexString(selectedColor) + ";" + prePosition);
        if (!(state instanceof Bundle)) {
            super.onRestoreInstanceState(state);
            return;
        }
        Bundle bundle = (Bundle) state;
        state = bundle.getParcelable(SuperSateKey);
        int position = bundle.getInt(PrePositionKey);
        if (position >= 0) {
            getChildAt(position).callOnClick();
        }
        super.onRestoreInstanceState(state);
    }

    public interface onSelectedChangedLsn {
        void onSelected(int position);

        void onUnSelected(int position);
    }

    private class ViewHolder {
        ImageView tabIconIv;
        TextView tabTitleTv;
    }

    public class Entity {
        private String title;
        private int defaultIcon;
        private int selectedIcon;

        public Entity() {
        }

        public Entity(String title, int defaultIcon, int selectedIcon) {
            this.title = title;
            this.defaultIcon = defaultIcon;
            this.selectedIcon = selectedIcon;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getDefaultIcon() {
            return defaultIcon;
        }

        public void setDefaultIcon(int defaultIcon) {
            this.defaultIcon = defaultIcon;
        }

        public int getSelectedIcon() {
            return selectedIcon;
        }

        public void setSelectedIcon(int selectedIcon) {
            this.selectedIcon = selectedIcon;
        }
    }

}
