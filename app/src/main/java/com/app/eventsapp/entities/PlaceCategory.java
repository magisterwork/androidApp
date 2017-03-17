package com.app.eventsapp.entities;

/**
 * Created by Grigory Kalyashov on 17.03.2017.
 *
 * Тип места
 */
public enum PlaceCategory
{
    CAFE("Кафе"),
    BAR("Бар"),
    MOVIE_THEATER("Кинотеатр"),
    CASINO("Казино"),
    FOOD("Еда"),
    MUSEUM("Музей"),
    NIGHT_CLUB("Ночной клуб"),
    RESTAURANT("Ресторан"),
    ZOO("Зоопарк");

    private String categoryName;

    PlaceCategory(String categoryName)
    {
      this.categoryName = categoryName;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }
}
