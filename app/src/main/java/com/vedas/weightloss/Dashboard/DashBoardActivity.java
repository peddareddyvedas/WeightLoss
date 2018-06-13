package com.vedas.weightloss.Dashboard;



import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vedas.weightloss.R;

/**
 * Created by Rise on 17/05/2018.
 */

public class DashBoardActivity extends AppCompatActivity implements SideMenuViewController.FragmentDrawerListener  {
    Toolbar toolbar;
    //this is Fragment which handles the Navigationdrawer items.
    SideMenuViewController drawerFragment;
    DrawerLayout drawer;
    ImageView back, add;
    FrameLayout frameLayout;
    TextView tool_text;
    private TabLayout tabLayout;
    private ViewPager viewPager;
   // ViewPagerAdapter adapter;
    RelativeLayout rootLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        init();
        //viewPager = (ViewPager) findViewById(R.id.viewpager);
        //setupViewPager(viewPager);
        initializeDrawer();
       // setupTabIcons();
        setupNavigationView();



    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("onResumeHome", "call");

    }
    public void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        back = (ImageView) findViewById(R.id.toolbar_icon);//Spectrum
        back.setBackgroundResource(R.drawable.ic_lock);


        tool_text = (TextView) toolbar.findViewById(R.id.toolbar_text);
        //frameLayout = (FrameLayout) findViewById(R.id.frame);
        add = (ImageView) toolbar.findViewById(R.id.img_share);
        // add.setImageResource(R.drawable.ic_add);
        add.setVisibility(View.VISIBLE);

        tool_text.setText("Dashboard");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }


    private void setupNavigationView() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        if (bottomNavigationView != null) {

            // Select first menu item by default and show Fragment accordingly.
            Menu menu = bottomNavigationView.getMenu();
            selectFragment(menu.getItem(0));

            // Set action to perform when any menu-item is selected.
            bottomNavigationView.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            selectFragment(item);
                            return false;
                        }
                    });
        }
    }

    /**
     * Perform action when any item is selected.
     *
     * @param item Item that is selected.
     */
    protected void selectFragment(MenuItem item) {

        item.setChecked(true);

        switch (item.getItemId()) {
            case R.id.action_dashbore:
                // Action to perform when Home Menu item is selected.
                pushFragment(new DashBordFragment());
                break;

            case R.id.action_news:
                // Action to perform when Account Menu item is selected.
                pushFragment(new NewsFragment());
                break;
            case R.id.action_more:
                // Action to perform when Account Menu item is selected.
                pushFragment(new MoreFragment());
                break;

            case R.id.action_graph:
                // Action to perform when Bag Menu item is selected.
                pushFragment(new GraphFragment());
                break;
        }
    }




    /**
     * Method to push any fragment into given id.
     *
     * @param fragment An instance of Fragment to show into the given id.
     */
    protected void pushFragment(Fragment fragment) {
        if (fragment == null)
            return;

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (ft != null) {
                ft.replace(R.id.rootLayout, fragment);
                ft.commit();
            }
        }
    }

    //initializing the navigation drawer
    public void initializeDrawer() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerFragment = (SideMenuViewController) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, drawer, toolbar);

        drawerFragment.setDrawerListener( this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("openDrawer", "call");
                drawer.openDrawer(GravityCompat.START);// for open the side menu
            }
        });
    }


    @Override
    public void onDrawerItemSelected(View view, int position) {
        Log.e("onDrawerItemSelected", "call" + position);
        drawer.closeDrawer(GravityCompat.END);

    }


    @Override
    public void onBackPressed() {    //when click on phone backbutton
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


   /* private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("DashBoard");
      //tabOne.setTextColor(Color.parseColor("#ffffff"));
        tabOne.setTextSize(16);
        tabOne.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_lock, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);





        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("Dairy");
       // tabTwo.setTextColor(Color.parseColor("#ffffff"));
        tabTwo.setTextSize(16);
        tabTwo.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_lock, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);



        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText("More");
        tabThree.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
      // tabThree.setTextColor(Color.parseColor("#ffffff"));
        tabThree.setTextSize(16);
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_lock, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);

    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new DashboardFragment(), "");
        adapter.addFrag(new DairyFragment(), "");
        adapter.addFrag(new MoreFragment(), "");
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }


    ///////////


    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();


        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                Log.e("fragment", "position 0");
            }
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
*/



}
