package com.jhspt.recycleproj.activity.suggestion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jhspt.recycleproj.R;

import java.util.ArrayList;

/**
 * Created by whdghks913 on 2015-12-10.
 */
class SuggestionViewHolder {
    public TextView mTitle;
    public TextView mMessage;
    public TextView mDate;
}

class SuggestionShowData {
    public String title;
    public String message;
    public String date;
}

public class SuggestionAdapter extends BaseAdapter {
    private Context mContext = null;
    private ArrayList<SuggestionShowData> mListData = new ArrayList<SuggestionShowData>();

    public SuggestionAdapter(Context mContext) {
        super();

        this.mContext = mContext;
    }

    public void addItem(String title, String message, String date) {
        SuggestionShowData addItemInfo = new SuggestionShowData();
        addItemInfo.title = title;
        addItemInfo.message = message;
        addItemInfo.date = date;

        mListData.add(0, addItemInfo);
    }

    public void clearData() {
        mListData.clear();
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public SuggestionShowData getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        SuggestionViewHolder mHolder;

        if (convertView == null) {
            mHolder = new SuggestionViewHolder();

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_suggestion_item, null);

            mHolder.mTitle = (TextView) convertView.findViewById(R.id.mTitle);
            mHolder.mMessage = (TextView) convertView.findViewById(R.id.mMessage);
            mHolder.mDate = (TextView) convertView.findViewById(R.id.mDate);

            convertView.setTag(mHolder);
        } else {
            mHolder = (SuggestionViewHolder) convertView.getTag();
        }

        SuggestionShowData mData = mListData.get(position);
        String title = mData.title;
        String message = mData.message;
        String date = mData.date;

        mHolder.mTitle.setText(title);
        mHolder.mMessage.setText(message);
        mHolder.mDate.setText(date);

        return convertView;
    }
}
