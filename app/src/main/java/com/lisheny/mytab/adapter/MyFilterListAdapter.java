package com.lisheny.mytab.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.lisheny.mytab.R;
import com.lisheny.mytab.javabeens.FilterListBeen;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <pre>
 *     author : lisheny
 *     e-mail : 1020044519@qq.com
 *     time   : 2017/07/20
 *     desc   : 我的滤镜
 *     version: 1.0
 * </pre>
 */
public class MyFilterListAdapter extends RecyclerView.Adapter<MyFilterListAdapter.MyViewHolder> {

    private FilterListBeen.FiltersBean filtersBean;
    private List<FilterListBeen.FiltersBean> mDatas;
    private Context mContext;
    private OnItemClickLitener mOnItemClickLitener;

    public MyFilterListAdapter(Context context, List<FilterListBeen.FiltersBean> datas) {
        this.mDatas = datas;
        this.mContext = context;
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(
                R.layout.item_myfilter_list, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        filtersBean = mDatas.get(position);

        holder.ivMyfilterState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShortToast("点击了小图标 "+ position);
            }
        });

        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.tv_myfilter_name)
        TextView tvMyfilterName;
        @InjectView(R.id.tv_myfilter_state)
        TextView tvMyfilterState;
        @InjectView(R.id.iv_myfilter_state)
        ImageView ivMyfilterState;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }
}