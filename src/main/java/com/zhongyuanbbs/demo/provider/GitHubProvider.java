package com.zhongyuanbbs.demo.provider;

import com.alibaba.fastjson.JSON;
import com.zhongyuanbbs.demo.dto.AccessTokenDto;
import com.zhongyuanbbs.demo.dto.GitHubUserDto;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class GitHubProvider {

    private final String url_ac = "https://github.com/login/oauth/access_token";
    private final String url_user= "https://api.github.com/user?access_token=";

    public String getAccessToken(AccessTokenDto accessTokenDto){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10000,TimeUnit.SECONDS).readTimeout(10000,TimeUnit.SECONDS).build();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));
        Request request = new Request.Builder()
                    .url(url_ac)
                    .post(body)
                    .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String accessToken = string.split("&")[0].split("=")[1];
            System.out.println(accessToken);
            return accessToken;
        } catch (IOException e) {
                e.printStackTrace();
        }
        return null;
    }


    public GitHubUserDto getGitHubUser(String accessToken){
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10000,TimeUnit.SECONDS).readTimeout(10000,TimeUnit.SECONDS).build();
        Request request = new Request.Builder()
                .url(url_user+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GitHubUserDto gitHubUserDto = JSON.parseObject(string, GitHubUserDto.class);
            return gitHubUserDto;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
