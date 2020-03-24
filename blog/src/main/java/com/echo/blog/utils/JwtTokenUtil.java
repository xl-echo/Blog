package com.echo.blog.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * @author echo
 * @date 2020-01-30 15:57:39
 */
public class JwtTokenUtil {

    /**
     * 选择了记住我之后的过期时间为7天
     */
    private static final long EXPIRATION_REMEMBER = 604800L;
    private static final String SECRET = "SDFEEdfdeFDRE";

    /**
     * 生成token
     * @param username 用户名
     * @return String
     */
    public static String generateToken(String username) {
        //签发时间
        Date istDate = new Date();

        Map<String, Object> map = new HashMap<>(16);
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        String token = null;
        try {
            token = JWT.create()
                    .withHeader(map)
                    .withClaim("username", username)
                    .withExpiresAt(new Date(EXPIRATION_REMEMBER * 1000))
                    .withIssuedAt(istDate)
                    .sign(Algorithm.HMAC256(SECRET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return token;
    }

    /**
     * 验证token是否过期
     * @param token token
     * @return map
     * @throws Exception
     */
    public static Map<String, Claim> verifyToken(String token) throws Exception {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        } catch (Exception e) {
            throw new RuntimeException("凭证过期！");
        }

        return jwt.getClaims();
    }

}
