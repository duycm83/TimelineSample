package jp.co.spidersoft.timelinesample;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by lamphucduy on 16/06/26.
 */
public class TimelineAdapter extends RecyclerView.Adapter<TimeLineViewHolder>  {
    private static final String TAG = TimelineAdapter.class.getSimpleName();
    private int mOrientation;
    private List<PostDetail> mDataSet;
    private ItemOnClickListener mListener;

    public TimelineAdapter(List<PostDetail> dataset, int orientation) {
        mDataSet = dataset;
        mOrientation = orientation;
    }
    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v;
        if (RecyclerView.VERTICAL == mOrientation) {
            v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.vertical_item, viewGroup, false);
        } else {
            v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.horizontal_item, viewGroup, false);
        }

        return new TimeLineViewHolder(v);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(TimeLineViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.getTextView().setText(mDataSet.get(position).getTitle());
        viewHolder.setOnClickListener(mListener);

    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void setOnItemClickListener(ItemOnClickListener onClickListener) {
        mListener = onClickListener;
    }

    private OnLoadMoreListener mOnLoadMoreListener;
    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }
}
