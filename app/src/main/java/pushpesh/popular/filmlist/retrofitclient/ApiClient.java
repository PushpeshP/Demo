package pushpesh.popular.filmlist.retrofitclient;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import pushpesh.popular.filmlist.api.ApiServices;
import pushpesh.popular.filmlist.common.Constant;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static ApiServices initRetrofit() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        Interceptor interceptor = chain -> {
            Request request = chain.request();
            Request newRequest = request.newBuilder()
                    .build();
            return chain.proceed(newRequest);
        };
        OkHttpClient.Builder builder =
                new OkHttpClient.Builder();
        builder.networkInterceptors().add(interceptor);
        OkHttpClient client = builder.addInterceptor(loggingInterceptor)
                .connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(chain -> {
                    Request request=chain.request();
                    Response response=chain.proceed(request);
                    return response;
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        return retrofit.create(ApiServices.class);
    }
}
