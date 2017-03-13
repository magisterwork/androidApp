package com.app.eventsapp.utils;

import android.support.annotation.NonNull;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Grigory Kalyashov on 07.11.2016.
 *
 * Вспомогательные функции для работы с датой/временем
 */
public class DateTimeHelper
{
    public static final String SIMPLE_DATE_PATTERN = "yyyy-MM-dd";
    public static final String POST_CALENDAR_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String EVENT_DATE_PATTERN = "dd MMMM HH:mm";
    public static final String EVENT_DATE_PATTERN_WITH_YEAR = "dd MMMM yyyy HH:mm";
    public static final String HOURS_AND_MINUTES = "HH:mm";

    /**
     * Отформатировать дату к формату, с которым работает сервер
     * @param date - дата для формата, если передать null возьмет текущую дату
     * @return - дата
     */
    @NonNull
    public static String dateToAPIFormat(Calendar date)
    {
        if (date == null)
            date = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat(POST_CALENDAR_PATTERN);
        formater.setDateFormatSymbols(dateFormatSymbols);
        return formater.format(date.getTime());
    }

    /**
     * Отформатировать дату события для отображения пользователю.
     * Если текущий год совпадает с годом события,
     * то не показываем его
     * @param date
     * @return дата
     */
    public static String formatEventDate(Calendar date)
    {
        SimpleDateFormat formater;

        if(date == null)
        {
            return "";
        }

        if(date.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR))
        {
            formater = new SimpleDateFormat(EVENT_DATE_PATTERN);
        }
        else
        {
            formater = new SimpleDateFormat(EVENT_DATE_PATTERN_WITH_YEAR);
        }

        formater.setDateFormatSymbols(dateFormatSymbols);

        return  formater.format(date.getTime());
    }

    /**
     * Получить период времени из двух дат
     * @param startDate - дата начала
     * @param endDate - дата конца
     * @return период времени
     */
    public static String formatDateWithPeriod(Calendar startDate, Calendar endDate)
    {
        SimpleDateFormat formater = new SimpleDateFormat(HOURS_AND_MINUTES);

        String startDateTime = formatEventDate(startDate);

        if(endDate == null)
        {
            return startDateTime;
        }

        String endDateTime = formater.format(endDate.getTime());

        return startDateTime + " - " + endDateTime;
    }

    private static DateFormatSymbols dateFormatSymbols = new DateFormatSymbols()
    {
        @Override
        public String[] getMonths()
        {
            return new String[] {"января", "февраля", "марта", "апреля", "мая", "июня",
                    "июля", "августа", "сентября", "октября", "ноября", "декабря"};
        }
    };
}
