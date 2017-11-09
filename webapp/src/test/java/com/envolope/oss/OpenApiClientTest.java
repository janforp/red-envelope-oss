package com.envolope.oss;

import com.iask.open.api.sdk.AutoRetryOpenApiClient;
import com.iask.open.api.sdk.DefaultOpenApiClient;
import com.iask.open.api.sdk.request.RedUrlRequest;
import com.iask.open.api.sdk.response.RedUrlResponse;

public class OpenApiClientTest {

    private static DefaultOpenApiClient client = new AutoRetryOpenApiClient("3", "8JJ3usSF8gkfEFdWL4Q9csgG9Ij9qv12");

    public static void main(String[] args) {
        RedUrlRequest("12311111");
    }

    private static void RedUrlRequest(String userId) {

        RedUrlRequest request = new RedUrlRequest();
        request.setUserId(userId);

        RedUrlResponse response = client.execute(request);

        String url = response.getRedUrl().getUrl();

        System.out.println(url);

    }

}
