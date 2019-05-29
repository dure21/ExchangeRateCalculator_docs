package io.yagus.exchangeratecalculator.API;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API_Exchange {

    //베이스 URL
    String BASEURL = "http://fx.kebhana.com";

    // 샘플 : http://fx.kebhana.com/FER1101M.web

    @GET("/FER1101M.web")
    Call<ResponseBody> getExchange();
}
