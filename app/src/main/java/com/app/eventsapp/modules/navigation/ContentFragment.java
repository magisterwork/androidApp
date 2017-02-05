package com.app.eventsapp.modules.navigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.eventsapp.R;
import com.app.eventsapp.core.base.NavigationDrawerFragment;

/**
 * Created by Grigory Kalyashov on 13.11.2016.
 *
 * Содержит навигацию табами и другие фрагменты
 */
public class ContentFragment extends NavigationDrawerFragment
{
    // количество табов в навигации
    private static final int TABS_COUNT = 3;

    private static final String BUNDLE_TAB_PAGE = "TAB_PAGE";

    private ViewPager viewPager;

    public ContentFragment()
    {
        //TODO убрать(?)
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        super.onCreateView(inflater,container,savedInstanceState);
        initTabs(savedInstanceState);
        initToolbar();

        return rootView;
    }

    @NonNull
    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container)
    {
        rootView = inflater.inflate(R.layout.content_fragment, container, false);

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);

        if(viewPager != null)
        {
            outState.putInt(BUNDLE_TAB_PAGE, viewPager.getCurrentItem());
        }
    }

    private void initTabs(Bundle savedInstanceState)
    {
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        viewPager.setAdapter(new TabNavigationAdapter(getChildFragmentManager()));
        viewPager.setOffscreenPageLimit(TABS_COUNT);

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setCurrentItem(savedInstanceState == null ? 0 :
                savedInstanceState.getInt(BUNDLE_TAB_PAGE));
    }

    private void initToolbar()
    {
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        context.setSupportActionBar(toolbar);
    }
}
