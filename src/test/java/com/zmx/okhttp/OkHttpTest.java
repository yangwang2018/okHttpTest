package com.zmx.okhttp;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.junit.Test;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author : 钟鸣星
 * @date : 2019年12月12日
 */
public class OkHttpTest {

    /**
     * post表单提交
     * @throws IOException
     */
    @Test
    public void testPostFormBody() throws IOException {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("name", "小明");
        builder.add("userId", "18");

        Request request = new Request.Builder().url("http://118.25.112.207:8080/getUserInfo").post(builder.build()).build();

        OkHttpClient okHttpClient = new OkHttpClient();
        Response response = okHttpClient.newCall(request).execute();
        int code = response.code();
        if(code == 200){
            ResponseBody body = response.body();
            String string = body.string();
            System.out.println(string);
        }
    }

    /**
     * post发送json的写法，前端发送json,后台要用@RequestBody去接收
     * @throws IOException
     */
    @Test
    public void testPostJsonParams() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        HashMap<String, String> jsonMap = new HashMap<>();
        jsonMap.put("id", "151325");
        jsonMap.put("name", "手机");
        String json = JSON.toJSONString(jsonMap);
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(json, mediaType);
        Request request = new Request.Builder().url("http://118.25.112.207:8080/getOrderInfo").post(requestBody).build();
        Response response = okHttpClient.newCall(request).execute();
        int code = response.code();
        if(code == 200){
            String s = response.body().string();
            System.out.println(s);
        }

    }

    /**
     * post发送webService请求
     * @throws IOException
     */
    @Test
    public void testWebService() throws IOException {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">" +
                "  <soap12:Body>" +
                "    <getWeatherbyCityName xmlns=\"http://WebXml.com.cn/\">" +
                "      <theCityName>宁德</theCityName>" +
                "    </getWeatherbyCityName>" +
                "  </soap12:Body>" +
                "</soap12:Envelope>";

        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/soap+xml; charset=utf-8");
        RequestBody requestBody = RequestBody.create(xml, mediaType);
        Request request = new Request.Builder().url("http://www.webxml.com.cn/webservices/weatherwebservice.asmx?wsdl").post(requestBody).build();
        Response response = okHttpClient.newCall(request).execute();
        int code = response.code();
        if(code == 200){
            String string = response.body().string();
            System.out.println(string);
        }
    }

    /**
     * get请求写法
     * @throws IOException
     */
    @Test
    public void testGet() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        //Get请求的参数直接拼接在url后面
        Request request = builder.url("http://118.25.112.207:8080/getUserInfo?userId=15&name=小明").get().build();
        Response response = okHttpClient.newCall(request).execute();
        int code = response.code();
        if(code == 200){
            ResponseBody body = response.body();
            String string = body.string();
            System.out.println(string);
        }
    }

}
