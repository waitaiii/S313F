package hk.edu.hkmu.mobileapp;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import android.util.Log;

public class ApiClient {
    private static final String TAG = "ApiClient";
    private static final OkHttpClient client = new OkHttpClient();

    public static String getBusRoutes(String searchTerm, boolean isSearchByStop) throws IOException {

        String baseUrl = isSearchByStop
                ? "https://data.etabus.gov.hk/v1/transport/kmb/stop/"
                : "https://data.etabus.gov.hk/v1/transport/kmb/route/";

        String url = baseUrl + searchTerm;
        Log.d(TAG, "Request URL: " + url);


        Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/json")
                .build();


        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Request Failed: " + response.code() + " " + response.message());
            }

            String jsonResponse = response.body().string();
            Log.d(TAG, "Response Data: " + jsonResponse.substring(0, Math.min(100, jsonResponse.length())));
            return jsonResponse;
        } catch (Exception e) {
            Log.e(TAG, "Request Error: " + e.getMessage(), e);
            throw e;
        }
    }
}