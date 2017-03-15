package com.app.eventsapp.modules.navigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.app.eventsapp.modules.places.views.PlacesListFragment;
import com.app.eventsapp.modules.postline.views.PostLineFragment;

/**
 * Created by Grigory Kalyashov on 13.11.2016.
 *
 * Адаптер для ViewPager
 */
public class TabNavigationAdapter extends FragmentPagerAdapter
{
    //TODO доставать из строковых ресурсов
    private final String[] tabsTitles = {"СОБЫТИЯ", "ЗАВЕДЕНИЯ"};

    public TabNavigationAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return tabsTitles[position];
    }

    @Override
    public Fragment getItem(int position)
    {
        Fragment fragment;

        switch (position)
        {
            case 0:
                fragment = new PostLineFragment();
                break;
            default:
                fragment = new PlacesListFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount()
    {
        return tabsTitles.length;
    }
}
