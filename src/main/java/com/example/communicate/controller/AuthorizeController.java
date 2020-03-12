package com.example.communicate.controller;

import com.example.communicate.dto.AccessTokenDTO;
import com.example.communicate.dto.GitHubUser;
import com.example.communicate.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName AuthorizeController
 * @Description: TODO
 * @Author whaceyou
 * @Date 2020/3/11
 * @Version V1.0
 **/
@Controller
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @Value("${gitHub.client.id}")
    private String clientID;
    @Value("${gitHub.client.secret}")
    private String clientSecret;
    @Value("${gitHub.client.redirect-uri}")
    private String clientRedirectUri;


    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientID);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(clientRedirectUri);
        accessTokenDTO.setState(state);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GitHubUser user = gitHubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
