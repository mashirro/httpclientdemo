package com.mashirro.httpclientdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashirro.httpclientdemo.pojo.Book;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class HttpclientdemoApplicationTests {

    @Test
    void postTest() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httppost = new HttpPost("http://localhost:9001/book/add");
            //form  post
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("name", "math"));
            params.add(new BasicNameValuePair("id", "1"));
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                System.out.println(EntityUtils.toString(response.getEntity()));
            } finally {
                //closing the response
                response.close();
            }
        } finally {
            //HttpClient资源释放
            httpclient.close();
        }
    }

    @Test
    void postTest2() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httppost = new HttpPost("http://localhost:9001/book/increase");
            //json  post
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonStr = objectMapper.writeValueAsString(new Book("music", 2));
            StringEntity stringEntity = new StringEntity(jsonStr, "UTF-8");
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httppost.setEntity(stringEntity);

            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                System.out.println(EntityUtils.toString(response.getEntity()));
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

}
