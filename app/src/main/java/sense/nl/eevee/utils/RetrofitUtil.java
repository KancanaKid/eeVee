package sense.nl.eevee.utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by budi-ahmad-syidiq on 12/10/16.
 */
public class RetrofitUtil {
    public static Retrofit doIntercepted(String url) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder reBuilder = originalRequest.newBuilder()
                        .addHeader("Content-Type","application/json")
                        .method(originalRequest.method(), originalRequest.body());
                Request request = reBuilder.build();
                return chain.proceed(request);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }
}
