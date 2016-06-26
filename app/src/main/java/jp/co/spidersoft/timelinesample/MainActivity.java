package jp.co.spidersoft.timelinesample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView vertialRecyclerView;
    private boolean isLoading;
    private ProgressBar loadMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        loadMore = (ProgressBar) findViewById(R.id.loadmore);
        setVerticalRV();
        setVerticalHV();

    }
    void setVerticalRV() {
        vertialRecyclerView = (RecyclerView) findViewById(R.id.vertical_rv);
        List<PostDetail> list = Dummy.getDataSet();
        final TimelineAdapter timelineAdapter = new TimelineAdapter(list, RecyclerView.VERTICAL);
        timelineAdapter.setOnItemClickListener(new ItemOnClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        vertialRecyclerView.setLayoutManager(linearLayoutManager);
        vertialRecyclerView.setAdapter(timelineAdapter);
        final OnLoadMoreListener mOnLoadMoreListener = new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                Log.v(TAG, "Vertical loadmore");
                Dummy.loadMore();
                timelineAdapter.notifyDataSetChanged();
                loadMore.setVisibility(View.INVISIBLE);
                isLoading = false;
            }
        };
        vertialRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int totalItemCount = linearLayoutManager.getItemCount();
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                if (!isLoading && totalItemCount <= (lastVisibleItem + 5)) {
                    loadMore.setVisibility(View.VISIBLE);
                    if (mOnLoadMoreListener != null) {
                        mOnLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    void setVerticalHV() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.horizontal_rv);
        List<PostDetail> list = Dummy.getDataSet();
        final TimelineAdapter timelineAdapter = new TimelineAdapter(list, RecyclerView.HORIZONTAL);
        timelineAdapter.setOnItemClickListener(new ItemOnClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getApplicationContext(), "pos:"+position, Toast.LENGTH_SHORT).show();
                ((LinearLayoutManager)vertialRecyclerView.getLayoutManager()).scrollToPositionWithOffset(position, 0);
            }
        });
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, true);
        final OnLoadMoreListener mOnLoadMoreListener = new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                Log.v(TAG, "Horizontal loadmore");
                Dummy.loadMore();
                timelineAdapter.notifyDataSetChanged();
                loadMore.setVisibility(View.INVISIBLE);
                isLoading = false;
            }
        };
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(timelineAdapter);
        vertialRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int totalItemCount = linearLayoutManager.getItemCount();
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

                if (!isLoading && totalItemCount <= (lastVisibleItem + 5)) {
                    loadMore.setVisibility(View.VISIBLE);
                    if (mOnLoadMoreListener != null) {
                        mOnLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
