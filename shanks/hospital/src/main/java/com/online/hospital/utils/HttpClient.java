package com.online.hospital.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.online.hospital.utils.constants.CommRespVO;
import com.online.hospital.utils.constants.FutureMap;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import okhttp3.*;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by miaodongbiao
 * Date:2018/4/21-20:17
 * Description:
 */
public class HttpClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClient.class);

    private OkHttpClient client = null;

    private static class ClassHolder {
        private final static HttpClient INSTANCE = new HttpClient();
    }

    public static HttpClient of() {
        return ClassHolder.INSTANCE;
    }

    private HttpClient() {
        client = new OkHttpClient();
    }

    public String get(String url, FutureMap<String, Object> params) {
        url = Strings.isNullOrEmpty(url) ?
                url :
                String.format("%s?%s",
                        url,
                        params.streamEntry()
                                .map(entry -> String.format("%s=%s", entry.getKey(), entry.getValue()))
                                .collect(Collectors.joining("&")));

        LOGGER.debug("HttpClient->get url={}", url);

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        return handleResponse(request);
    }

    //
    public String get2(String url, Map<String, Object> params) {
        if (params != null) {
            url += "?";
            for (String key : params.keySet()) {
                url += key + "=" + params.get(key) + "&";
            }

            url = url.substring(0, url.length() - 1);
        }

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        return handleResponse(request);
    }


    public String post(String url, FutureMap<String, Object> params) {

        LOGGER.debug("HttpClient->post url={} , params={}", url, params);

        FormBody.Builder builder = new FormBody.Builder();

        params.streamK().forEach((key) -> builder.add(key, params.sv(key)));

        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();

        return handleResponse(request);
    }

    public String post4String(String url, FutureMap<String, String> params) {

        LOGGER.debug("HttpClient->post url={} , params={}", url, params);

        FormBody.Builder builder = new FormBody.Builder();

        params.streamK().forEach((key) -> builder.add(key, params.sv(key)));
        Request request = new Request.Builder()

                .url(url)
                .post(builder.build())
                .build();

        return handleResponse(request);
    }

    /*public String post4Map(String url, FutureMap<String, String> params) {

        LOGGER.debug("HttpClient->post url={} , params={}", url, params);

        FormBody.Builder builder = new FormBody.Builder();
        RequestBody body = FormBody.create(MediaType.parse("application/json"), params);

        params.streamK().forEach((key) -> builder.add(key, params.sv(key)));
        Request request = new Request.Builder()

                .url(url)
                .post(builder.build())
                .build();

        return handleResponse(request);
    }*/

    private String handleResponse(Request request) {
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            LOGGER.warn("error = {}", e.getMessage());
        } finally {
            if (response != null) response.body().close();
        }

        return null;
    }

    /**
     * post json请求
     *
     * @param url
     * @param params
     * @return
     */
    public String post2json(String url, FutureMap<String, Object> params) {
        return post2header(url, "application/json; charset=utf-8", params.toJson());
    }

    public String post2header(String url, String header, FutureMap<String, String> params) {
        return post2header(url, header, params.toJson());
    }

    public String post2header(String url, String header, String params) {
        LOGGER.info("HttpClient->post url={} , header={} , params={}", url, header, params);
        MediaType jsonMediaType = MediaType.parse(header);
        RequestBody body = RequestBody.create(jsonMediaType, params);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        return handleResponse(request);
    }


    public String post2json(String url, String params) {
        MediaType jsonMediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(jsonMediaType, params);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        return handleResponse(request);
    }

    public String post2jsonMap(String url, FutureMap<String, String> params) {
        LOGGER.info("HttpClient->post url={} , params={}", url, params.toJson());

        return post4String(url, params);
    }


    public JSONObject get4Json(String url) {
        return JSON.parseObject(get2(url, null));
    }

    /**
     * 返回值是json
     *
     * @param url
     * @param params
     * @return
     */
    public JSONObject post4Json(String url, FutureMap<String, Object> params) {
        return JSON.parseObject(post(url, params));
    }


    /**
     * 通用的post请求,适用于信贷系统
     *
     * @param url
     * @param params
     * @return
     */
    public CommRespVO commPost(String url, FutureMap<String, Object> params) {
        return JSON.parseObject(post(url, params), CommRespVO.class);
    }

    /**
     * 通用的post请求,适用于信贷系统
     *
     * @param url
     * @param params
     * @return
     */
    public CommRespVO commPost(StringBuilder url, FutureMap<String, Object> params) {
        return JSON.parseObject(post(url.toString(), params), CommRespVO.class);
    }

    /**
     * 通用的get请求,适用于信贷系统
     *
     * @param url
     * @param params
     * @return
     */
    public CommRespVO commGet(String url, FutureMap<String, Object> params) {
        return JSON.parseObject(get(url, params), CommRespVO.class);
    }

}
