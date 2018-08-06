package com.anyasoft.es.Hmf_Customer.Fragments;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.anyasoft.es.Hmf_Customer.Adpter.OrdersAdpter;
import com.anyasoft.es.Hmf_Customer.Api.InfoApi;
import com.anyasoft.es.Hmf_Customer.AppController;
import com.anyasoft.es.Hmf_Customer.ESurvey;
import com.anyasoft.es.Hmf_Customer.R;
import com.anyasoft.es.Hmf_Customer.activity.MainActivity;
import com.anyasoft.es.Hmf_Customer.domains.B2BInvestmentDetails;
import com.anyasoft.es.Hmf_Customer.domains.B2CInvestmentDetails;
import com.anyasoft.es.Hmf_Customer.domains.Information;
import com.anyasoft.es.Hmf_Customer.views.CircleTransform;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Belal on 18/09/16.
 */


public class DashBoard extends Fragment {

    CardView card_org,card_personal,card_invest,card_crop,card_trans,personal_details,org_details,invest_b2c_details,
            invest_b2b_details,crop_b2b_details,crop_b2c_details;
    String url;
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    Information information;
    OrdersAdpter ordersAdpter;
    RecyclerView recyclerView;
    B2BInvestmentDetails b2BInvestmentDetails;
    B2CInvestmentDetails b2CInvestmentDetails;
    TextView orgname,gst,pointofcontact,contactNumber,pan,regPhone,regEmail,turnOver,
            pemail,phone,profesion,employeeof,aadhaar,personal_pan,bank,ifsc,total_b2b_investment,typeofb2bconsumer,
            invest_b2b_plan,invest_b2b_info,investment_b2c_plan,investment_b2c_amount,invest_b2c_peroid,typeof_b2c_consumer,
            grid_b2c_type,interested_b2c_crop,currently_b2c_processed,
            grid_b2b_type,currently_b2b_processed,expected_b2b_delivery, quantityof_b2b_units,interested_b2b_crop;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Dash Board");


        card_org=(CardView)view.findViewById(R.id.card_org);
        card_personal=(CardView)view.findViewById(R.id.card_personal);
        card_invest=(CardView)view.findViewById(R.id.card_invest);
        card_crop=(CardView)view.findViewById(R.id.card_crop);
        card_trans=(CardView)view.findViewById(R.id.card_trans);
        org_details=(CardView)view.findViewById(R.id.org_details);
        personal_details=(CardView)view.findViewById(R.id.personal_details);
        invest_b2b_details=(CardView)view.findViewById(R.id.invest_b2b_details);
        invest_b2c_details=(CardView)view.findViewById(R.id.invest_b2c_details);

        crop_b2b_details=(CardView)view.findViewById(R.id.crop_b2b_details);
        crop_b2c_details=(CardView)view.findViewById(R.id.crop_b2c_details);

        recyclerView = (RecyclerView)view.findViewById(R.id.transview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(ordersAdpter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        orgname=(TextView)view.findViewById(R.id.orgname);
        gst=(TextView)view.findViewById(R.id.gst);
        pointofcontact=(TextView)view.findViewById(R.id.pointofcontact);
        contactNumber=(TextView)view.findViewById(R.id.contactNumber);
        pan=(TextView)view.findViewById(R.id.pan);
        regPhone=(TextView)view.findViewById(R.id.regPhone);
        regEmail=(TextView)view.findViewById(R.id.regEmail);
        turnOver=(TextView)view.findViewById(R.id.turnOver);
        pemail=(TextView)view.findViewById(R.id.pemail);
        phone=(TextView)view.findViewById(R.id.phone);
        profesion=(TextView)view.findViewById(R.id.profesion);
        employeeof=(TextView)view.findViewById(R.id.employeeof);
        aadhaar=(TextView)view.findViewById(R.id.aadhaar);
        personal_pan=(TextView)view.findViewById(R.id.personal_pan);
        bank=(TextView)view.findViewById(R.id.bank);
        ifsc=(TextView)view.findViewById(R.id.ifsc);
        total_b2b_investment=(TextView)view.findViewById(R.id.total_b2b_investment);
        typeofb2bconsumer=(TextView)view.findViewById(R.id.typeofb2bconsumer);
        invest_b2b_plan=(TextView)view.findViewById(R.id.invest_b2b_plan);
        invest_b2b_info=(TextView)view.findViewById(R.id.invest_b2b_info);


        investment_b2c_plan=(TextView)view.findViewById(R.id.investment_b2c_plan);
        investment_b2c_amount=(TextView)view.findViewById(R.id.investment_b2c_amount);
        invest_b2c_peroid=(TextView)view.findViewById(R.id.invest_b2c_peroid);
        typeof_b2c_consumer=(TextView)view.findViewById(R.id.typeof_b2c_consumer);


        grid_b2c_type=(TextView)view.findViewById(R.id.grid_b2c_type);
        interested_b2c_crop=(TextView)view.findViewById(R.id.interested_b2c_crop);
        currently_b2c_processed=(TextView)view.findViewById(R.id.currently_b2c_processed);

        grid_b2b_type=(TextView)view.findViewById(R.id.grid_b2b_type);
        currently_b2b_processed=(TextView)view.findViewById(R.id.currently_b2b_processed);
        expected_b2b_delivery=(TextView)view.findViewById(R.id.expected_b2b_delivery);
        quantityof_b2b_units=(TextView)view.findViewById(R.id.quantityof_b2b_units);
        interested_b2b_crop=(TextView)view.findViewById(R.id.interested_b2b_crop);




        org_details.setVisibility(View.GONE);
        personal_details.setVisibility(View.GONE);
        invest_b2b_details.setVisibility(View.GONE);
        invest_b2c_details.setVisibility(View.GONE);
        crop_b2b_details.setVisibility(View.GONE);
        crop_b2c_details.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);

            card_org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(org_details.getVisibility()==View.GONE)
                {
                    org_details.setVisibility(View.VISIBLE);

                    personal_details.setVisibility(View.GONE);
                    invest_b2b_details.setVisibility(View.GONE);
                    invest_b2c_details.setVisibility(View.GONE);
                    crop_b2b_details.setVisibility(View.GONE);
                    crop_b2c_details.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);

                }
                else if(org_details.getVisibility()==View.VISIBLE) {
                    org_details.setVisibility(View.GONE);
                    personal_details.setVisibility(View.GONE);
                    invest_b2b_details.setVisibility(View.GONE);
                    invest_b2c_details.setVisibility(View.GONE);
                    crop_b2b_details.setVisibility(View.GONE);
                    crop_b2c_details.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                }

            }
        });

        card_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(personal_details.getVisibility()==View.GONE)
                {
                    personal_details.setVisibility(View.VISIBLE);
                    org_details.setVisibility(View.GONE);
                    invest_b2b_details.setVisibility(View.GONE);
                    invest_b2c_details.setVisibility(View.GONE);
                    crop_b2b_details.setVisibility(View.GONE);
                    crop_b2c_details.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);

                }
                else if(personal_details.getVisibility()==View.VISIBLE) {
                    personal_details.setVisibility(View.GONE);
                    org_details.setVisibility(View.GONE);
                    invest_b2b_details.setVisibility(View.GONE);
                    invest_b2c_details.setVisibility(View.GONE);
                    crop_b2b_details.setVisibility(View.GONE);
                    crop_b2c_details.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                }

            }
        });


        card_invest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                   if(ESurvey.getTYPE().equals("B2C")){

                       if(invest_b2c_details.getVisibility()==View.GONE)
                       {
                           invest_b2c_details.setVisibility(View.VISIBLE);
                           org_details.setVisibility(View.GONE);
                           personal_details.setVisibility(View.GONE);
                           invest_b2b_details.setVisibility(View.GONE);
                           crop_b2b_details.setVisibility(View.GONE);
                           crop_b2c_details.setVisibility(View.GONE);
                           recyclerView.setVisibility(View.GONE);

                       }
                       else if(invest_b2c_details.getVisibility()==View.VISIBLE) {
                           invest_b2c_details.setVisibility(View.GONE);
                           org_details.setVisibility(View.GONE);
                           personal_details.setVisibility(View.GONE);
                           invest_b2b_details.setVisibility(View.GONE);
                           crop_b2b_details.setVisibility(View.GONE);
                           crop_b2c_details.setVisibility(View.GONE);
                           recyclerView.setVisibility(View.GONE);
                       }


                }
                else {

                       if(invest_b2b_details.getVisibility()==View.GONE)
                       {
                           invest_b2b_details.setVisibility(View.VISIBLE);
                           org_details.setVisibility(View.GONE);
                           personal_details.setVisibility(View.GONE);
                           invest_b2c_details.setVisibility(View.GONE);
                           crop_b2b_details.setVisibility(View.GONE);
                           crop_b2c_details.setVisibility(View.GONE);
                           recyclerView.setVisibility(View.GONE);

                       }
                       else if(invest_b2b_details.getVisibility()==View.VISIBLE) {
                           invest_b2b_details.setVisibility(View.GONE);
                           org_details.setVisibility(View.GONE);
                           personal_details.setVisibility(View.GONE);
                           invest_b2c_details.setVisibility(View.GONE);
                           crop_b2b_details.setVisibility(View.GONE);
                           crop_b2c_details.setVisibility(View.GONE);
                           recyclerView.setVisibility(View.GONE);
                       }

                }

            }
        });

        card_crop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(ESurvey.getTYPE().equals("B2C")){

                    if(crop_b2c_details.getVisibility()==View.GONE)
                    {
                        crop_b2c_details.setVisibility(View.VISIBLE);
                        org_details.setVisibility(View.GONE);
                        personal_details.setVisibility(View.GONE);
                        invest_b2b_details.setVisibility(View.GONE);
                        invest_b2c_details.setVisibility(View.GONE);
                        crop_b2b_details.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.GONE);

                    }
                    else if(crop_b2c_details.getVisibility()==View.VISIBLE) {
                        crop_b2c_details.setVisibility(View.GONE);
                        org_details.setVisibility(View.GONE);
                        personal_details.setVisibility(View.GONE);
                        invest_b2b_details.setVisibility(View.GONE);
                        invest_b2c_details.setVisibility(View.GONE);
                        crop_b2b_details.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.GONE);
                    }


                }
                else {

                    if(crop_b2b_details.getVisibility()==View.GONE)
                    {
                        crop_b2b_details.setVisibility(View.VISIBLE);
                        org_details.setVisibility(View.GONE);
                        personal_details.setVisibility(View.GONE);
                        invest_b2b_details.setVisibility(View.GONE);
                        invest_b2c_details.setVisibility(View.GONE);
                        crop_b2c_details.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.GONE);

                    }
                    else if(crop_b2b_details.getVisibility()==View.VISIBLE) {
                        crop_b2b_details.setVisibility(View.GONE);
                        org_details.setVisibility(View.GONE);
                        personal_details.setVisibility(View.GONE);
                        invest_b2b_details.setVisibility(View.GONE);
                        invest_b2c_details.setVisibility(View.GONE);
                        crop_b2c_details.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.GONE);
                    }

                }

            }
        });

        card_trans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(recyclerView.getVisibility()==View.GONE)
                {
                    recyclerView.setVisibility(View.VISIBLE);
                    org_details.setVisibility(View.GONE);
                    personal_details.setVisibility(View.GONE);
                    invest_b2b_details.setVisibility(View.GONE);
                    invest_b2c_details.setVisibility(View.GONE);
                    crop_b2b_details.setVisibility(View.GONE);
                    crop_b2c_details.setVisibility(View.GONE);

                }
                else if(recyclerView.getVisibility()==View.VISIBLE) {
                    recyclerView.setVisibility(View.GONE);
                    org_details.setVisibility(View.GONE);
                    personal_details.setVisibility(View.GONE);
                    invest_b2b_details.setVisibility(View.GONE);
                    invest_b2c_details.setVisibility(View.GONE);
                    crop_b2b_details.setVisibility(View.GONE);
                    crop_b2c_details.setVisibility(View.GONE);
                }

            }
        });


        info(ESurvey.getLoginId());





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

        InfoApi availabilityApi = retrofit.create(InfoApi.class);
        Call<Information> listCall= availabilityApi.getData(url);
        listCall.enqueue(new Callback<Information>() {
            @Override
            public void onResponse(Call<Information> call, retrofit2.Response<Information> response) {



                if(response.isSuccessful()){

                    if(response.body()==null){

                        Toast.makeText(getContext(), "Something Went Wrong, Please Try Again", Toast.LENGTH_SHORT).show();

                    } else  if(response.body().equals("")){

                        Toast.makeText(getContext(), "Something Went Wrong, Please Try Again", Toast.LENGTH_SHORT).show();

                    }else {

                        information=response.body();
                        if(information.getOrders()==null || information.getOrders().equals("")){

                        }
                        else {

                            int s=information.getOrders().size();
                            ordersAdpter = new OrdersAdpter(getContext(),information.getOrders());
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setItemViewCacheSize(s);
                            recyclerView.setDrawingCacheEnabled(true);
                            recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                            recyclerView.setAdapter(ordersAdpter);
                        }


                        if(information.getConsumerType()==null){

                        }
                        else if(information.getConsumerType().equals("")){


                        }
                        else {
                            ESurvey.saveType(information.getConsumerType());

                        }

                        if(ESurvey.getTYPE().equals("B2C")){

                            card_org.setVisibility(View.GONE);
                          //  org_details.setVisibility(View.GONE);
                           card_personal.setVisibility(View.VISIBLE);
                            personal_details.setVisibility(View.VISIBLE);

                            b2CInvestmentDetails=information.getB2CInvestmentDetails();

                            if(b2CInvestmentDetails==null){

                                Toast.makeText(getContext(),"Something Went Wrong, Please Try Again", Toast.LENGTH_SHORT).show();

                            }else {



                                pemail.setText(b2CInvestmentDetails.getEmail());
                                phone.setText(b2CInvestmentDetails.getPhone());
                                profesion.setText(b2CInvestmentDetails.getProfession());
                                employeeof.setText(b2CInvestmentDetails.getEmployeeOf());
                                aadhaar.setText(b2CInvestmentDetails.getAadharNumber());
                                personal_pan.setText(b2CInvestmentDetails.getPan());
                                bank.setText(b2CInvestmentDetails.getBankAccount());
                                ifsc.setText(b2CInvestmentDetails.getIfsc());
                                investment_b2c_plan.setText(b2CInvestmentDetails.getInvestmentPlan());
                                investment_b2c_amount.setText(b2CInvestmentDetails.getInvestmentAmount());
                                invest_b2c_peroid.setText(b2CInvestmentDetails.getInvestmentPeriod());
                                typeof_b2c_consumer.setText(information.getConsumerType());
                                grid_b2c_type.setText(b2CInvestmentDetails.getGridType());
                                interested_b2c_crop.setText(b2CInvestmentDetails.getInterestedCrop());
                                currently_b2c_processed.setText(b2CInvestmentDetails.getCurrentProcessed());
                            }

                        }
                        else {

                            b2BInvestmentDetails= information.getB2BInvestmentDetails();
                            card_org.setVisibility(View.VISIBLE);
                            org_details.setVisibility(View.VISIBLE);
                            card_personal.setVisibility(View.GONE);
                          //  personal_details.setVisibility(View.VISIBLE);


                            if(b2BInvestmentDetails==null){

                                Toast.makeText(getContext(),"Something Went Wrong, Please Try Again", Toast.LENGTH_SHORT).show();

                            }else {

                                orgname.setText(b2BInvestmentDetails.getOrganizationName());
                                gst.setText(b2BInvestmentDetails.getGstNumber());
                                pointofcontact.setText(b2BInvestmentDetails.getPointOfContact());
                                contactNumber.setText(b2BInvestmentDetails.getContactNumber());
                                pan.setText(b2BInvestmentDetails.getCompanyPAN());
                                regPhone.setText(b2BInvestmentDetails.getCompanyRegisteredPhone());
                                regEmail.setText(b2BInvestmentDetails.getEmail());
                                turnOver.setText(b2BInvestmentDetails.getTurnover());
                                total_b2b_investment.setText(b2BInvestmentDetails.getTotalInvestment());
                                typeofb2bconsumer.setText(b2BInvestmentDetails.getTypeOfConsumer());
                                invest_b2b_plan.setText(b2BInvestmentDetails.getTypeOfInvestmentPlan());
                                invest_b2b_info.setText(b2BInvestmentDetails.getInvestmentInfo());
                                grid_b2b_type.setText(b2BInvestmentDetails.getGridType());
                                currently_b2b_processed.setText(b2BInvestmentDetails.getCurrentlyProcessed());
                                expected_b2b_delivery.setText(b2BInvestmentDetails.getExpectedDelivery());
                                quantityof_b2b_units.setText(b2BInvestmentDetails.getQuantity()+"  "+b2BInvestmentDetails.getQuatityUnits());
                                interested_b2b_crop.setText(b2BInvestmentDetails.getInterestedCrop());
                            }

                        }

                    }

                }
                else {

                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Information> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
