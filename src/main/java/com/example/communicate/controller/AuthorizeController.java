package com.example.communicate.controller;

import com.example.communicate.dto.AccessTokenDTO;
import com.example.communicate.dto.GitHubUser;
import com.example.communicate.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("e930eca2f38d57122558");
        accessTokenDTO.setClient_secret("bce3844d96f194102b5489ffb790dab1fea3f727");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setState(state);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GitHubUser user = gitHubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
