package com.lisheny.mytab.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lisheny.mytab.R;
import com.lisheny.mytab.javabeens.FaceListBeen;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <pre>
 *     author : lisheny
 *     e-mail : 1020044519@qq.com
 *     time   : 2017/07/27
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class FaceListAdapter extends RecyclerView.Adapter<FaceListAdapter.MyViewHolder> {

    private FaceListBeen.ExpsBean expsBean;
    private List<FaceListBeen.ExpsBean> mDatas;
    private Context mContext;
    private OnItemClickLitener mOnItemClickLitener;

    public FaceListAdapter(Context context, List<FaceListBeen.ExpsBean> datas) {
        this.mDatas = datas;
        this.mContext = context;
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(
                R.layout.item_sound_list, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        expsBean = mDatas.get(position);

        holder.tvSound.setBackgroundResource(R.drawable.home_shape_bg_face);
        holder.tvSound.setText(expsBean.getExpname());

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
        @InjectView(R.id.tv_sound)
        TextView tvSound;
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
