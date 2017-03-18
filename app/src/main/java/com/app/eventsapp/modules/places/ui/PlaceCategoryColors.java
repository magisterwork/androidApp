package com.app.eventsapp.modules.places.ui;

import com.app.eventsapp.entities.PlaceCategory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Grigory Kalyashov on 18.03.2017.
 *
 * Цвета категорий
 */
public class PlaceCategoryColors
{
    private static Map<PlaceCategory, Integer> placeCategoryColors;

    static
    {
        placeCategoryColors = new HashMap<>();

        placeCategoryColors.put(PlaceCategory.BAR, 0xff336699);
        placeCategoryColors.put(PlaceCategory.CAFE, 0xffCC9933);
        placeCategoryColors.put(PlaceCategory.CASINO, 0xffCC9999);
        placeCategoryColors.put(PlaceCategory.FOOD, 0xff336699);
        placeCategoryColors.put(PlaceCategory.MOVIE_THEATER, 0xff336699);
        placeCategoryColors.put(PlaceCategory.MUSEUM, 0xffCC9933);
        placeCategoryColors.put(PlaceCategory.NIGHT_CLUB, 0xffCC9999);
        placeCategoryColors.put(PlaceCategory.RESTAURANT, 0xffCC9999);
        placeCategoryColors.put(PlaceCategory.ZOO, 0xffCC9933);
    }

    /**
     * Получить цвет категории
     *
     * @param category категория заведения
     * @return цвет
     */
    public static int getColor(PlaceCategory category)
    {
        return placeCategoryColors.get(category);
    }
}
