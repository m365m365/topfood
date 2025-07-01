package com.yourapp.util;

import javax.servlet.http.HttpServletRequest;

public class UrlUtil {

    // 傳入 request 和 token，自動產生完整驗證連結
    public static String buildVerificationLink(HttpServletRequest request, String token) {
        String scheme = request.getScheme(); // http or https
        String serverName = request.getServerName(); // localhost or domain
        int serverPort = request.getServerPort(); // 8080 or 80
        String contextPath = request.getContextPath(); // /ttttt

        // 如果是 80/443 就省略 port
        String portPart = ((scheme.equals("http") && serverPort == 80) ||
                           (scheme.equals("https") && serverPort == 443))
                           ? "" : ":" + serverPort;

        return scheme + "://" + serverName + portPart + contextPath + "/verify?token=" + token;
    }
}
