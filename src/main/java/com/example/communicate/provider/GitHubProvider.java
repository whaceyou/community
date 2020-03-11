package com.example.communicate.provider;

import com.alibaba.fastjson.JSON;
import com.example.communicate.dto.AccessTokenDTO;
import com.example.communicate.dto.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * @ClassName GitHubProvider
 * @Description: 调用github服务
 * @Author whaceyou
 * @Date 2020/3/11
 * @Version V1.0
 **/
@Component
public class GitHubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder().url("https://github.com/login/oauth/access_token").post(body).build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            System.out.println(token);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GitHubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try{
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            System.out.println(string);
            GitHubUser gitHubUser = JSON.parseObject(string, GitHubUser.class);
            return gitHubUser;
        } catch (IOException e) {
        }
        return null;
    }










}
