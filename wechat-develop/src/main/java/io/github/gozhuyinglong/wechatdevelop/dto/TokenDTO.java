package io.github.gozhuyinglong.wechatdevelop.dto;


import java.io.Serializable;

/**
 * @author ZhuYinglong
 * @date 2021/11/10 0010
 */
public class TokenDTO implements Serializable {

    /**
     * 生成的Token
     */
    private String token;

    /**
     * 请将 Token 放入此请求头中
     */
    private String tokenHeader;

    /**
     * 请为 Token 附加上此前缀
     */
    private String tokenPrefix;

    /**
     * Token有效期，Unix毫秒数
     */
    private Long ttl;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenHeader() {
        return tokenHeader;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public Long getTtl() {
        return ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }
}
