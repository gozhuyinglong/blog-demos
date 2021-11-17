package io.github.gozhuyinglong.wechatdevelop.controller;

import com.alibaba.fastjson.JSONObject;
import io.github.gozhuyinglong.wechatdevelop.dto.TokenDTO;
import io.github.gozhuyinglong.wechatdevelop.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/wechat")
public class WechatController {

    private static final String APP_ID = "wxce34f24b4736434a";
    private static final String APP_SECRET = "3ab7b64fc2d8c84713e7414d8a628306";
    private static final String REDIRECT_URI = "http://127.0.0.1:8080/wechat/accessToken";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/authorize")
    public void authorize(HttpServletResponse response) throws IOException {

        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid=" + APP_ID +
                "&redirect_uri=" + URLEncoder.encode(REDIRECT_URI, "UTF-8") +
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=STATE" +
                "#wechat_redirec";
        response.sendRedirect(url);
    }

    @GetMapping("/accessToken")
    public TokenDTO accessToken(String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=" + APP_ID +
                "&secret=" + APP_SECRET +
                "&code=" + code +
                "&grant_type=authorization_code";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String body = responseEntity.getBody();
        JSONObject json = JSONObject.parseObject(body);
        if (json == null) {
            return null;
        }
        System.out.println("-------------------获取openid-------------------");
        System.out.println(json.toJSONString());
        String accessToken = json.getString("access_token");
        int expiresIn = json.getIntValue("expires_in");
        String openid = json.getString("openid");
        String refreshToken = json.getString("refresh_token");
        String scope = json.getString("scope");

        // 获取微信用户信息
        userinfo(accessToken, openid);

        // 封装 TokenDTO 类
        TokenDTO dto = new TokenDTO();
        dto.setToken(JwtUtil.generateToken(openid));
        dto.setTokenHeader(JwtUtil.TOKEN_HEADER);
        dto.setTokenPrefix(JwtUtil.TOKEN_PREFIX);
        dto.setTtl(JwtUtil.TTL);
        return dto;
    }

    private void userinfo(String accessToken, String openid) {
        String url = "https://api.weixin.qq.com/sns/userinfo?" +
                "access_token=" + accessToken +
                "&openid=" + openid +
                "&lang=zh_CN";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String body = responseEntity.getBody();
        JSONObject json = JSONObject.parseObject(body);
        if (json == null) {
            return;
        }
        System.out.println("-------------------获取用户信息-------------------");
        System.out.println(json.toJSONString());
    }
}
