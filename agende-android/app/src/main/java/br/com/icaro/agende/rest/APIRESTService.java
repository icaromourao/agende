package br.com.icaro.agende.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.icaro.agende.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class APIRESTService {

    static final List<String> noAuthRequired =  new ArrayList<>(Arrays.asList("/api/app/login", "/api/app/token/refresh"));

    private static final String BASE_URL =  BuildConfig.SERVER_URL + "/api/app/";

    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new APIRESTInterceptor())
            .authenticator(new APIRESTAuthenticator())
            .build();

    private static Retrofit retrofit = new Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(JacksonConverterFactory.create())
            .build();


    public static <T> T getRESTService(Class<T> RESTService) {
        return retrofit.create(RESTService);
    }

}
