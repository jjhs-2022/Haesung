package com.jhspt.recycleproj.activity.main;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import com.jhspt.recycleproj.R;
import com.jhspt.recycleproj.tool.BapTool;
import com.jhspt.recycleproj.tool.Preference;

/**
 * Created by whdghks913 on 2015-11-30.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    //    private int mBackground;
    private ArrayList<MainInfo> mValues = new ArrayList<>();

    public MainAdapter(Context mContext) {
//        TypedValue mTypedValue = new TypedValue();
//        mContext.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
//        mBackground = mTypedValue.resourceId;
    }
//
    public void addItem(int imageId, String mTitle, boolean isSimple) {
        MainInfo addInfo = new MainInfo();

        addInfo.imageId = imageId;
        addInfo.mTitle = mTitle;
        addInfo.isSimple = true;
        addInfo.isSimpleButDetailedLayout = isSimple;

        mValues.add(addInfo);
    }
    //
    public void addItem(int imageId, String mTitle, String mText, boolean isSimple) {
        MainInfo addInfo = new MainInfo();

        addInfo.imageId = imageId;
        addInfo.mTitle = mTitle;
        addInfo.mText = mText;
        addInfo.isSimple = true;
        addInfo.isSimpleButDetailedLayout = isSimple;

        mValues.add(addInfo);
    }

    public void ddayItem(int imageId, String mTitle, String mText, boolean isSimple) {
        MainInfo addInfo = new MainInfo();

        addInfo.imageId = imageId;
        addInfo.mTitle = mTitle;
        addInfo.mText = mText;
        addInfo.isSimple = true;
        addInfo.isSimpleButDetailedLayout = isSimple;
        addInfo.OnOff = true;
        mValues.add(addInfo);
    }


    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_main_fragment, parent, false);
//        mView.setBackgroundResource(mBackground);

        return new MainViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final MainViewHolder holder, int position) {
        MainInfo mInfo = getItemData(position);


        holder.mTitle.setText(mInfo.mTitle);
        holder.mText.setText(mInfo.mText);

        if (mInfo.isSimple && !(mInfo.isSimpleButDetailedLayout)) {
            holder.mSimpleLayout.setVisibility(View.VISIBLE);
            holder.mSimpleTitle.setText(mInfo.mSimpleTitle);
            holder.mSimpleText.setText(mInfo.mSimpleText);
        } else {
            holder.mSimpleLayout.setVisibility(View.GONE);
        }

        Glide.with(holder.mImageView.getContext())
                .load(mInfo.imageId)
                .fitCenter()
                .into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public MainInfo getItemData(int position) {
        return mValues.get(position);
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
//        public final View mView;
        // public final CircleImageView mCircleImageView;
        public final LinearLayout mSimpleLayout;
        public final TextView mTitle, mText, mSimpleTitle, mSimpleText;
        public final CardView mCardView;
        public final ImageView mImageView;

        public MainViewHolder(View mView) {
            super(mView);
//            this.mView = mView;
            mImageView = (ImageView) mView.findViewById(R.id.mImageView);
            mTitle = (TextView) mView.findViewById(R.id.mTitle);
            mText = (TextView) mView.findViewById(R.id.mText);
            mSimpleLayout = (LinearLayout) mView.findViewById(R.id.mSimpleLayout);
            mSimpleTitle = (TextView) mView.findViewById(R.id.mSimpleTitle);
            mSimpleText = (TextView) mView.findViewById(R.id.mSimpleText);
            mCardView = (CardView) mView.findViewById(R.id.mCardView);
        }
    }

    public class MainInfo {
        public boolean isSimple;
        public boolean isSimpleButDetailedLayout = false;
        public int imageId;
        public String mTitle;
        public String mText;
        public String mSimpleTitle;
        public String mSimpleText;
        public boolean OnOff;
    }
}