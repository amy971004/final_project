package org.example.kakao.service;

import java.util.HashMap;

public interface KakaoService {
    String getAccessToken(String code) throws Exception;

    HashMap<String, Object> getUserInfo(String token) throws Throwable;
}
