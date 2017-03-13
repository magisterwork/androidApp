package com.app.eventsapp.utils;

import android.view.View;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Grigory Kalyashov on 12.02.2017.
 *
 * Вспомогательные функции для UI
 */
public class ViewUtils
{
    /**
     * Скрыть view
     * @param v
     */
    public static void hideView(View v)
    {
        if(v != null)
        {
            v.setVisibility(View.GONE);
        }
    }

    /**
     * Если нет текста, который нужно отобразить, то
     * TextView скрывается
     * @param text - текст
     * @param v - TextView
     */
    public static void hideTextViewIfNoText(String text, TextView v)
    {
        if(StringUtils.isEmpty(text))
        {
            hideView(v);
        }
        else
        {
            v.setText(text);
        }
    }
}
