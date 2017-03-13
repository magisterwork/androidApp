package com.app.eventsapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * Created by Grigory Kalyashov on 05.02.2017.
 */
public class NetworkUtil
{
    /**
     * Проверка - есть ли подключение к интернету
     *
     * @param context
     * @return true - есть, false - нет
     */
    public static boolean isInternetAvailable(Context context)
    {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    /**
     * Получить тип подключения к интернету
     *
     * @param context
     * @return тип подключения
     */
    public static ConnectionType getConnectivityStatus(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return ConnectionType.WIFI;

            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return ConnectionType.MOBILE;
        }
        return ConnectionType.NOT_CONNECTED;
    }

    /**
     * Тип подключения к интернету
     */
    public enum ConnectionType
    {
        WIFI,
        MOBILE,
        NOT_CONNECTED
    }
}
