package com.app.eventsapp.modules.postline.recyclerview;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.AbsListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Grigory Kalyashov on 01.02.2017.
 */

public abstract class OnLoadMoreListener extends RecyclerView.OnScrollListener
{
    private int visibleThreshold = 5;
    private int currentPage = 0;
    private int previousTotalItemCount = 0;
    private boolean loading = true;
    private int startingPageIndex = 0;

    public OnLoadMoreListener ()
    {
    }

    public OnLoadMoreListener(int visibleThreshold)
    {
        this.visibleThreshold = visibleThreshold;
    }

    public OnLoadMoreListener(int visibleThreshold, int startPage)
    {
        this.visibleThreshold = visibleThreshold;
        this.startingPageIndex = startPage;
        this.currentPage = startPage;
    }

    public abstract void onLoadMore();

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy)
    {
        int totalItemCount = recyclerView.getAdapter().getItemCount();
        int firstVisibleItem = getFirstVisibleItemPosition(recyclerView);
        int visibleItemCount = recyclerView.getChildCount();

        onScroll(firstVisibleItem, visibleItemCount, totalItemCount);
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState)
    {

    }


    private void onScroll(int firstVisibleItem, int visibleItemCount, int totalItemCount)
    {

        if (totalItemCount < previousTotalItemCount)
        {
            this.currentPage = this.startingPageIndex;
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) { this.loading = true; }
        }

        if (loading && (totalItemCount > previousTotalItemCount))
        {
            loading = false;
            previousTotalItemCount = totalItemCount;
            currentPage++;
        }

        if (!loading && (totalItemCount - visibleItemCount)<=(firstVisibleItem + visibleThreshold))
        {
            onLoadMore();
            loading = true;
        }
    }

    /**
     *  Получение порядкового номера первого видимого на экране элемента
     * @param recyclerView
     * @return
     */
    private int getFirstVisibleItemPosition(RecyclerView recyclerView)
    {
        LinearLayoutManager manager = (LinearLayoutManager)recyclerView.getLayoutManager();
        return manager.findFirstVisibleItemPosition();
    }
}
