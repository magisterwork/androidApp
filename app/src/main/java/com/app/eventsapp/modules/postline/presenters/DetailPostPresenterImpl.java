package com.app.eventsapp.modules.postline.presenters;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.app.eventsapp.R;
import com.app.eventsapp.core.managers.PicassoImageManager;
import com.app.eventsapp.modules.postline.views.DetailPostFragmentView;
import com.app.eventsapp.utils.NetworkUtil;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by Grigory Kalyashov on 12.02.2017.
 */
public class DetailPostPresenterImpl implements DetailPostPresenter
{
    private DetailPostFragmentView view;

    @Override
    public void init(DetailPostFragmentView view)
    {
        this.view = view;
    }

    @Override
    public void showFullEventImage(Context context, String fullImageUrl)
    {
        if(NetworkUtil.isInternetAvailable(context) && !StringUtils.isEmpty(fullImageUrl))
        {
            //TODO можно использовать DialogFragment, чтобы окно не пропадало после смены ориентации
            final Dialog fullImageDialog = new Dialog(context,
                    android.R.style.Theme_Black_NoTitleBar);

            fullImageDialog.setContentView(R.layout.full_image_dialog);

            Button btnClose = (Button) fullImageDialog.findViewById(R.id.btnIvClose);
            PhotoView fullImage = (PhotoView) fullImageDialog.findViewById(R.id.detail_full_image);

            PicassoImageManager.getInstance().loadResource(fullImageUrl,
                    fullImage, Picasso.Priority.HIGH);

            btnClose.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View arg0) {

                    fullImageDialog.dismiss();
                }
            });

            fullImageDialog.show();
        }
    }
}
