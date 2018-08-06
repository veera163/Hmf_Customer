package com.anyasoft.es.Hmf_Customer.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anyasoft.es.Hmf_Customer.Api.InfoApi;
import com.anyasoft.es.Hmf_Customer.AppController;
import com.anyasoft.es.Hmf_Customer.ESurvey;
import com.anyasoft.es.Hmf_Customer.Fragments.DashBoard;
import com.anyasoft.es.Hmf_Customer.Fragments.InvestmentDocmentation;
import com.anyasoft.es.Hmf_Customer.Fragments.LogisticsTracker;
import com.anyasoft.es.Hmf_Customer.Fragments.Redemption;
import com.anyasoft.es.Hmf_Customer.R;
import com.anyasoft.es.Hmf_Customer.domains.Information;
import com.anyasoft.es.Hmf_Customer.utility.CustomAlertDialog;
import com.anyasoft.es.Hmf_Customer.views.CircleTransform;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog;
    Bundle bundle;
    String url;
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private ProgressDialog progressDialog;
    Information information;
    ImageView img_profile;
    TextView name;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        setSupportActionBar(toolbar);
        info(ESurvey.getLoginId());
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        img_profile=(ImageView)navigationView.getHeaderView(0).findViewById(R.id.img_profile);
        name = (TextView)navigationView.getHeaderView(0).findViewById(R.id.name);
        //navigationView.inflateMenu(R.menu.activity_main_drawer); //inflate new items.
        navigationView.setNavigationItemSelectedListener(this);

        //add this line to display menu1 when the activity is loaded
        displaySelectedScreen(R.id.dashboard);
    }

    private void info(String loginId) {

        url= AppController.BaseUrl+"profile/phone/"+loginId;

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("authorization", ESurvey.getAccessToken())
                        .header("content-type", "application/json")
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppController.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        progressDialog = ProgressDialog.show(MainActivity.this, "Please wait.",
                "Loading Data..!", true);
        InfoApi availabilityApi = retrofit.create(InfoApi.class);
        Call<Information> listCall= availabilityApi.getData(url);
        listCall.enqueue(new Callback<Information>() {
            @Override
            public void onResponse(Call<Information> call, retrofit2.Response<Information> response) {

                progressDialog.dismiss();

                if(response.isSuccessful()){

                    if(response.body()==null){

                        Toast.makeText(MainActivity.this, "Something Went Wrong, Please Try Again", Toast.LENGTH_SHORT).show();

                    } else  if(response.body().equals("")){

                        Toast.makeText(MainActivity.this, "Something Went Wrong, Please Try Again", Toast.LENGTH_SHORT).show();

                    }else {

                        information=response.body();

                        if(information.getPhotoUrl()==null){

                            ESurvey.saveImage("http://www.hiremyfarmer.com/assets/images/seller1.png");
                        }
                        else if(information.getPhotoUrl().equals("")){

                            ESurvey.saveImage("http://www.hiremyfarmer.com/assets/images/seller1.png");

                        }
                        else {
                            ESurvey.saveImage(information.getPhotoUrl());

                        }
                        if(information.getConsumerType()==null){

                        }
                        else if(information.getConsumerType().equals("")){


                        }
                        else {
                            ESurvey.saveType(information.getConsumerType());

                        }


                        name.setText(String.valueOf(information.getName()));

                        Glide.with(MainActivity.this)
                                .load(ESurvey.getIMAGEURL())
                                .crossFade()
                                .thumbnail(0.5f)
                                .bitmapTransform(new CircleTransform(MainActivity.this))
                                .error(R.drawable.ic_account_circle_black_24dp)      // optional
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(img_profile);
                        if(ESurvey.getTYPE().equals("B2C")){

                            navigationView.inflateMenu(R.menu.activity_main_drawer_redepm); //inflate new items.
                        }
                        else {

                            navigationView.inflateMenu(R.menu.activity_main_drawer); //inflate new items.

                        }

                    }

                }
                else {

                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Information> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.exit:
                bundle = new Bundle();
                bundle.putString(CustomAlertDialog.MESSAGE, "would you Like to close this App");
                alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Exit Application?");
                alertDialogBuilder
                        .setMessage("would you Like to close this App!")
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                                        homeIntent.addCategory( Intent.CATEGORY_HOME );
                                        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(homeIntent);


                                    }
                                })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }
                        });

                alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                break;
            case R.id.signout:

                bundle = new Bundle();
                bundle.putString(CustomAlertDialog.MESSAGE, "would you Like to Signout this App");
                alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Signout Application?");
                alertDialogBuilder
                        .setMessage("would you Like to Signout this App!")
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        ESurvey.clearCache();
                                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    }
                                })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }
                        });

                alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.dashboard:
                fragment = new DashBoard();
                break;
            case R.id.inveast:
                fragment = new InvestmentDocmentation();
                break;
            case R.id.logistics:
                fragment = new LogisticsTracker();
                break;
            case R.id.redemption:
                fragment = new Redemption();
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        //calling the method displayselectedscreen and passing the id of selected menu
        displaySelectedScreen(item.getItemId());
        //make this method blank
        return true;
    }


}
