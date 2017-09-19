package com.gondia.dailyneeds.MainPage;

import android.database.DataSetObserver;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
//import com.gondia.dailyneeds.MainPage.ListAdapter;


import com.gondia.dailyneeds.MainPage.APIModel.Item;

import com.gondia.dailyneeds.NavigationHandler.NavigationHandler;
import com.gondia.dailyneeds.R;
import com.gondia.dailyneeds.root.App;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    /*@BindView(R.id.recycler_view_mainpage)
    RecyclerView recyclerView;

    @BindView(R.id.list_view_root)
    ViewGroup rootView;

    @Inject
    MainActivityMVP.Presenter presenter;

    private ListAdapter listAdapter;
    private List<ViewModel> resultList=new ArrayList<>();*/

    public View headerView;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter adapter;
    private List<Item> items;
    private ApiInterface apiInterface;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       //((App)getApplication()).getComponent().inject(this);

       // ButterKnife.bind(this);

       // listAdapter=new ListAdapter(resultList);
        //recyclerView.setAdapter(listAdapter);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));








        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        headerView =  navigationView.inflateHeaderView(R.layout.nav_header_main);
        processNavigationDrawer();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_mainpage);
        final ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
        layoutManager = new GridLayoutManager(MainActivity.this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

       /* Call<List<Item>> call=apiInterface.getItems();
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                items = response.body();
                adapter = new RecyclerAdapter(items,ImageLoader.getInstance());
                recyclerView.setAdapter(adapter);
                System.out.println("Inside Resoponse");
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                t.printStackTrace();
                System.out.println("On Failure");
            }
        });*/

    }

    @Override
    protected void onResume() {
        super.onResume();
       /* presenter.setView(this);
        presenter.loadData();*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.



        return true;

    }
    private void processNavigationDrawer() {
        NavigationHandler.processNavigationDrawer(headerView, getApplicationContext());

    }
    public  boolean  navigation(View d) {

        NavigationHandler.navigation(this,d,headerView);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*@Override
    public void updateData(ViewModel viewModel) {
        resultList.add(viewModel);
        if(resultList.isEmpty()){
            listAdapter.notifyItemInserted(0);
        }else{
            listAdapter.notifyItemInserted(resultList.size()-1);
        }
        Log.d(Tag,"updateData: "+resultList.size());
    }

    @Override
    public void showSnackbar(String s) {
        Snackbar.make(rootView,s,Snackbar.LENGTH_SHORT).show();
    }*/
}
