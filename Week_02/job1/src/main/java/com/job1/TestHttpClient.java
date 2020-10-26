package com.job1;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @program: javaproject
 * @description:
 * @author: zhengh
 * @create: 2020-10-26 20:53
 **/
public class TestHttpClient {

    public static void main(String[] args) {
        String type = "get";
        String url = "http://127.0.0.1:8801";
        String parem = null;

        send(url,parem,type);


    }

    /**
     *
     * @param url
     * @param parem
     */
    public static void send(String url,String parem,String type){

        //获得一个http客户端
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //创建get请求
        HttpGet httpGet = new HttpGet(url);

        //响应模型
        CloseableHttpResponse response = null;

        try {
            //客户端-----(发送Get请求)----->目标url
            response = httpClient.execute(httpGet);

            //从相应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            System.out.println("响应状态为："+response.getStatusLine());

            if(responseEntity != null){

                System.out.println("响应内容长度为："+responseEntity.getContentLength());
                System.out.println("响应内容为："+ EntityUtils.toString(responseEntity));

            }

        } catch (IOException e) {
            System.out.println("发送请求失败，失败原因："+e.getMessage());
        }finally {
            try {
                //释放资源
                if (httpClient != null){
                    httpClient.close();
                }
                if(response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
