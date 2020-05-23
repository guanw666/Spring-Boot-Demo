package com.example.demo.provider;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.AccessTokenDTO;
import com.example.demo.dto.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GitHubProvider {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");


    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MEDIA_TYPE, JSONObject.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .header("Accept", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                JSONObject resp = JSONObject.parseObject(response.body().string());
                return resp.getString("access_token");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GitHubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Accept", "application/json")
                .addHeader("Authorization", "token " + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return JSONObject.parseObject(response.body().string(), GitHubUser.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
