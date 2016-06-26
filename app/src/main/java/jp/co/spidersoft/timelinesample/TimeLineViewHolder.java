package jp.co.spidersoft.timelinesample;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by lamphucduy on 16/06/26.
 */
public class TimeLineViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = TimeLineViewHolder.class.getSimpleName();
    private final TextView textView;
    private ItemOnClickListener mListener;
    public TimeLineViewHolder(View itemView) {
        super(itemView);
        // Define click listener for the ViewHolder's View.
        textView = (TextView) itemView.findViewById(R.id.textView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(getAdapterPosition());
            }
        });
    }

    public TextView getTextView() {
        return textView;
    }

    public void setOnClickListener(ItemOnClickListener onClickListener) {
        mListener = onClickListener;
    }
}
