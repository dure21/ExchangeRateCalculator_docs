package io.yagus.exchangeratecalculator;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

import io.yagus.exchangeratecalculator.API.API_Exchange;
import io.yagus.exchangeratecalculator.Adapter.Adapter_Exchange;
import io.yagus.exchangeratecalculator.Model.Model_Exchange;
import io.yagus.exchangeratecalculator.Model.Model_Exchange_trans;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    RecyclerView recyclerView;
    Retrofit retrofit;
    API_Exchange apiService;
    Adapter_Exchange adapter;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<Model_Exchange_trans> modelExchangeTrans = null;
    TextInputEditText editText_SearchExchange;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_SearchExchange = (TextInputEditText) findViewById(R.id.editText_SearchExchange);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter_Exchange();
        recyclerView.setAdapter(adapter);

        retrofit = new Retrofit.Builder()
                .baseUrl(API_Exchange.BASEURL)
                .build();
        apiService = retrofit.create(API_Exchange.class);
        getExchange();

    }

//    public interface OnEndApiListener{
//        void onEndApi();
//    }

    private void getExchange() {
        modelExchangeTrans = new ArrayList<>();
        Call<ResponseBody> call = apiService.getExchange();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
               try {
                   Gson gson = new Gson();
                   String content = response.body().string();
                   int idx1 = content.indexOf("exView = ")+9;
                   String c1 = content.substring(idx1);
                   int idx2 = c1.lastIndexOf(",");
                   String c2 = c1.substring(0, idx2);
                   String c3 = c2+" ] }";
                   SharedPreferences pref = getSharedPreferences("ExchangeRate", Activity.MODE_PRIVATE);
                   SharedPreferences.Editor editor = pref.edit();
                   Model_Exchange result = gson.fromJson(c3, Model_Exchange.class);
                   for(int i = 0; i< result.get리스트().size(); i++ ) {
                       modelExchangeTrans.add(result.get리스트().get(i).toMET());
                   }

                   //진흥님이 원하시는 모델로 변환을 반드시 하세요.
                    Log.e("Log","테스트");
                   adapter.setModelExchanges(modelExchangeTrans);
               } catch (Exception e) {

               }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
//        getExchange(new OnEndApiListener() {
//            @Override
//            public void onEndApi() {
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
