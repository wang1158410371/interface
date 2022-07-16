import com.example.demo01.Interface01Application;

import com.example.utils.HttpClientResult;
import com.example.utils.HttpClientUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.influx.InfluxDbOkHttpClientBuilderProvider;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.example.demo01.service.UsersSer;


import java.io.*;
import java.net.URLEncoder;
import java.util.*;


/**
 * Created by anita on 2022/6/19.
 */
@SpringBootTest(classes = Interface01Application.class)
public class TestService  extends AbstractTestNGSpringContextTests{

    @Autowired
    private UsersSer usersSer;



    @Test
    public void test01() {
        Map<String, String> map = new HashMap<>();
        StringBuffer stringBuffer=new StringBuffer();
        map.put("name", "wangzhihao");
        map.put("sex", "22");




        for (String key:map.keySet())
        {

//            String value=map.get(key);
//          System.out.println(key+value);
            stringBuffer.append(key+"=");
            stringBuffer.append(map.get(key));
            System.out.println("stringBuffer"+stringBuffer);



        }

    }


    @Test
    public void test03() throws Exception {
        String url="http://localhost:8080/hello1/222?sex=22&name=wangzhihao";
        HttpClientResult httpClientResult =HttpClientUtils.doGet(url);
        System.out.println(httpClientResult);

    }

    @Test
    public void printTest(){


        Map<Object,Object> map = new HashMap<>();
        StringBuffer stringBuffer=new StringBuffer();
        map.put("name","wangzhihao");
        map.put("sex","222");
        System.out.println("map"+map);

        int i=0;
        for (Object key:map.keySet())
        {
            i++;
            stringBuffer.append(key+"=");
            stringBuffer.append(map.get(key));
            if (i<map.size()) {

                stringBuffer.append("&");

            }
            System.out.println("stringBuffer"+stringBuffer);

        }
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();



        // 创建Get请求
        String url="http://localhost:8080/hello1/222?"+stringBuffer;
        System.out.println("url"+url);
        HttpGet httpGet = new HttpGet(url);
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(5000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(5000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(5000)
                    // 设置是否允许重定向(默认为true)
                    .setRedirectsEnabled(true).build();

            // 将上面的配置信息 运用到这个Get请求里
            httpGet.setConfig(requestConfig);

            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);

            // 从响应模型中获取响应实体

            HttpEntity responseEntity = response.getEntity();


            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("相应内容为："+EntityUtils.toString(responseEntity));
//                //io流形式获取相应内容
//                InputStream content =responseEntity.getContent();
//                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(content));
//                String line="";
//                //当内容为空时停止打印
//                while ((line=bufferedReader.readLine())!=null) {
//                    System.out.println("响应内容为:" + line);
//                }
//                //关闭读流
//                bufferedReader.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    }

