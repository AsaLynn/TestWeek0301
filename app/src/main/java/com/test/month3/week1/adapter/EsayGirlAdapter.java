package com.test.month3.week1.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.demonstrate.DemonstrateUtil;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.test.month3.week1.R;
import com.test.month3.week1.listener.MySimpleTarget;

import java.io.File;
import java.util.List;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/1/20
 */
public class EsayGirlAdapter extends RecyclerArrayAdapter<File> {


    private OnClickHolderItemListener onClickHolderItemListener;
    private String TAG = this.getClass().getSimpleName();
    private int height;
    private final MySimpleTarget mySimpleTarget;

    public EsayGirlAdapter(Context context, List<File> objects) {
        super(context, objects);
        mySimpleTarget = new MySimpleTarget();
    }

    public void setListData(List<File> objects) {
        this.mObjects = objects;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {

        GirlViewHolder girlViewHolder = new GirlViewHolder(parent, R.layout.list_girls_item);

        return girlViewHolder;
    }

    @Override
    public void OnBindViewHolder(final BaseViewHolder holder, final int position) {
        super.OnBindViewHolder(holder, position);
        /* map.put("height", (120 + 5 * i) + "");*/
   /*     if (height == 0) {
            holder.itemView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    //使用完立刻撤销监听，否则比较耗性能
                    holder.itemView
                            .getViewTreeObserver()
                            .removeGlobalOnLayoutListener(this);
                    height = holder.itemView.getMeasuredHeight();
                    DemonstrateUtil.showLogResult("height:".concat(String.valueOf(height)));
                }
            });
        }*/

//        double random = Math.random();
//        if (random < 0.2) {
//            random = 0.2;
//        }
//        if (random > 0.8) {
//            random = 0.8;
//        }
//
//        int newHeight = (int) (height + random * height);
//        DemonstrateUtil.showLogResult(String.valueOf("newHeight:" + newHeight));
//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.itemView.getLayoutParams();
//        params.height = newHeight;
//        holder.itemView.setLayoutParams(params);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickHolderItemListener != null) {
                    onClickHolderItemListener.onItemClick(holder, position);
                }
            }
        });
    }

    public interface OnClickHolderItemListener {
        public void onItemClick(BaseViewHolder holder, int position);
    }


    public void setOnClickHolderItemListener(OnClickHolderItemListener onClickHolderItemListener) {
        this.onClickHolderItemListener = onClickHolderItemListener;
    }

    public class GirlViewHolder extends BaseViewHolder<File> {

        private ImageView iv;
        SimpleTarget<Bitmap> target = new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                int height = resource.getHeight();
                DemonstrateUtil.showLogResult("height:".concat(String.valueOf(height)));
//                int mHeight = (height / 4) * 3;
                int mHeight = getRandomHeight(height);
                DemonstrateUtil.showLogResult("mHeight:".concat(String.valueOf(mHeight)));
                Glide.with(getContext()).load(mFile)
                        .apply(
                                new RequestOptions()
                                        .override(100, 60)
                        )
                        .into(iv);

               /* iv.setImageBitmap(resource);*/
            }
        };
        private File mFile;

        public GirlViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            iv = $(R.id.ivImage);
        }

        @Override
        public void setData(File data) {
            super.setData(data);
            mFile = data;
            /*Log.i(TAG, "---->setData: ");*/
            // .crossFade()
           /* Glide
                    .with(getContext())
                    .load(data)
                    .apply(
                            new RequestOptions()
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    )
                    .into(mySimpleTarget);*/

            //强制Glide返回一个Bitmap

            Glide.with(getContext()).asBitmap()
                    .load(data)
                    .into(target);
        }
    }


    public int getRandomHeight(int h) {
        double random = Math.random();
        /*if (random < 0.2) {
            random = 0.2;
        }
        if (random > 0.8) {
            random = 0.8;
        }*/
        int newHeight = (int) (h + random * h);
        return newHeight;
    }

}
