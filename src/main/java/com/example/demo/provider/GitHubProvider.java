package com.example.demo.provider;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.AccessTokenDTO;
import com.example.demo.dto.GitHubUser;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
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
            log.error("Get github access_token error", e);
        }
        return null;
    }

    public GitHubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Accept", "application/json")
                .addHeader("Authorization", "token " + accessToken)
                .build();
        try {
            log.info("Start get userinfo by api.github.com/user...");
            long start = System.currentTimeMillis();
            Response response = client.newCall(request).execute();
            log.info("Wait api.github.com/user response task [{}] ms", System.currentTimeMillis() - start);
            if (response.isSuccessful()) {
                GitHubUser gitHubUser = JSONObject.parseObject(response.body().string(), GitHubUser.class);
                log.info(gitHubUser.toString());
                return gitHubUser;
            }
        } catch (IOException e) {
            log.error("Get github user info error", e);
        }
        return null;
    }

}
