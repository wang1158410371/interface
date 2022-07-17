import com.example.demo01.Interface01Application;

import com.example.utils.HttpClientResult;
import com.example.utils.HttpClientUtils;
import lombok.val;
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
import java.util.stream.Stream;


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

        List<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("小昭");
        list.add("殷离");
        list.add("张三");
        list.add("张三丰");
        list.stream()
                .filter(name -> name.startsWith("张"))
                .filter(name -> name.length() == 3)
                .forEach(name -> System.out.println(name));
    }

    @Test
    public void test04(){
        List<Integer> list = Arrays.asList(6, 7, 3, 8, 1, 2, 9);
        Stream<Integer> stream = list.stream();
        stream.filter(x -> x > 3).forEach(System.out::println);
        List<Integer> list1=Arrays.asList(1,2,3,4,5);
        Stream<Integer> stream1= list1.stream();
        stream1.filter(x -> x <5).forEach(System.out::println);




    }

}







