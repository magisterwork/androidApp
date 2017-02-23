package com.app.eventsapp.modules.postline.presenters;

import com.app.eventsapp.modules.postline.models.Post;
import com.app.eventsapp.modules.postline.views.PostLineFragmentView;
import com.app.eventsapp.rest.postapi.EventsService;
import com.app.eventsapp.rest.request.RequestListener;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Grigory Kalyashov on 31.10.2016.
 */
public class PostLinePresenterImpl implements PostLinePresenter
{
    private PostLineFragmentView view;

    private int offset = 0;
    private final int count = 20;
    private int totalItemsCount = 0;

    private EventsService eventsService;

    @Inject
    public PostLinePresenterImpl(EventsService eventsService)
    {
        this.eventsService = eventsService;
    }

    @Override
    public void onResume(int eventsCount)
    {
        totalItemsCount = eventsCount;
        //TODO
        offset = calculateOffset();
        sendPostRequest(offset, count, true);
    }

    @Override
    public void onPause()
    {

    }

    @Override
    public void refresh()
    {
        //TODO проверять есть ли новые посты, если есть, то добавлять в начало ленты
        view.clearAdapter();
        offset = 0;
        totalItemsCount = 0;
        sendPostRequest(offset, count, false);
    }

    @Override
    public void onLoadMore()
    {
        sendPostRequest(offset, count, true);
    }

    @Override
    public void onItemClick(int position)
    {
        view.openPostDetails(position);
    }

    @Override
    public void addPostsToAdapter(List<Post> postList)
    {
        view.addPostsToAdapter(postList);
    }

    @Override
    public void init(PostLineFragmentView view)
    {
        this.view = view;
    }

    //TODO стоит вынести всю логику с пагинацией, запросами в отдельный класс
    // offset нужно увеличивать на кол-во полученных событий ?
    // иногда посылаем запрос дважды с одинаковыми параметрами, почему? (срабатывает onLoadMore слушатель)
    private void sendPostRequest(int postsOffset, final int count, boolean isNeedShowProgressBar)
    {
        if(postsOffset <= totalItemsCount)
        {
            if (isNeedShowProgressBar)
            {
                view.showProgressBar();
            }

            eventsService.getPosts(new RequestListener<List<Post>>() {
                @Override
                public void onSuccess(Call<List<Post>> call, Response<List<Post>> response) {
                    view.hideProgressBar();
                    view.setRecyclerViewAdapter(response.body());
                    int loadedEventsCount = response.body().size();
                    totalItemsCount += loadedEventsCount;
                }

                @Override
                public void onErrorResponse(Call<List<Post>> call, Response<List<Post>> response) {
                    view.hideProgressBar();
                    view.onErrorLoading();
                }

                @Override
                public void onFailure(Call<List<Post>> call, Throwable t) {
                    view.hideProgressBar();
                    view.onFailureLoading();
                }
            }, postsOffset, count);

            //TODO offset стоит увеличивать только в случае удачной загрузки
            offset+=count;
        }
    }

    private int calculateOffset()
    {
        return (totalItemsCount == 0 ) ? 0 : (totalItemsCount / count + 1) * count;
    }
}
