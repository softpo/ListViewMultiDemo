package com.softpo.listviewmultidemo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.softpo.listviewmultidemo.R;

import java.util.List;

/**
 * Created by softpo on 2016/5/9.
 */
public class MultiAdapter extends BaseAdapter {

    private Context mContext;

    private List<String> mList;

    public MultiAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (mList != null) {
            ret = mList.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = null;
//        获取类型
        int type = getItemViewType(position);
        if (convertView != null) {
            ret = convertView;
        } else {
            switch (type) {
                case 1://左边展示数据
                    ret = LayoutInflater.from(mContext).inflate(R.layout.left, null);
                    break;
                case 2://右边展示数据
                    ret = LayoutInflater.from(mContext).inflate(R.layout.right, null);
                    break;
            }
        }

//         获取Holder
        Object holder = ret.getTag();
        if (holder == null) {
            switch (type) {
                case 1:
                    holder = new ViewHolderLeft();
                    ((ViewHolderLeft) holder).left = ((TextView) ret.findViewById(R.id.left));
                    ret.setTag(holder);
                    break;
                case 2:
                    holder = new ViewHolderRight();
                    ((ViewHolderRight) holder).right = ((TextView) ret.findViewById(R.id.right));
                    ret.setTag(holder);
                    break;
            }
        }

        //进行赋值
        switch (type) {
            case 1:
//                聊天的左侧显示文本
                ((ViewHolderLeft) holder).left.setText(mList.get(position));

                break;

            case 2:
                //聊天的右侧显示文本
                ((ViewHolderRight) holder).right.setText(mList.get(position));
                break;
        }
        return ret;
    }


    //    告诉ListView有几种布局
    //多布局展示，展示几种类型,如果小于3个就必须写上三个
//    测试返回值是2个程序崩溃
    @Override
    public int getViewTypeCount() {
        return 3;
    }

    //    获取布局类型
    @Override
    public int getItemViewType(int position) {
        int type = 0;
//        根据position奇数偶数来确定显示数据布局
        int index = position % 2;

        switch (index) {
            case 0:
                type = 1;
                return type;
            case 1:
                type = 2;
                return type;
        }
        return type;
    }

    //    两套ViewHolder
    private class ViewHolderLeft {
        private TextView left;
    }

    private class ViewHolderRight {
        private TextView right;
    }
}
