import com.alibaba.fastjson.JSONObject;
import com.example.demo01.Interface01Application;
import com.example.utils.HttpClientResult;
import com.example.utils.HttpClientUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.bson.json.JsonObject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.io.File;
import java.util.*;

/**
 * Created by anita on 2022/7/14.
 */
@SpringBootTest(classes = Interface01Application.class)
public class TestPerformance extends AbstractTestNGSpringContextTests {


    //批量提交自评，
    // @param传入performance_id，
    // @param上级评价人uid；

//    获取performance_id
//    SELECT id from app_bmbo_test.performance_assessments WHERE department_name ='财务部' and examine_id='2' and deleted_at IS NULL ;


//    根据performance_id，查询uid；
//    SELECT a.id,b.passport from app_bmbo_test.users a right join app_bmbo_test.performance_assessment_steps b  on a.passport =b.passport
//    where b.performance_id in (764,765,766)AND  b.step ='employee_comment';

   /* 员工自评：employee_comment
    上级评价：superior_comment
    绩效校准：performance_align
    提交面谈：performance_take
    绩效确认：performance_confirm*/




    @Test
    public void test01(){
        //SELECT id from app_bmbo_test.performance_assessments WHERE department_name ='财务部';

//          需要操作的performance_id、UID传入数组
            int[] performance_id = {801,
                    802,
                    803,
                    804,
                    805,
                    806,
                    807,
                    808,
                    809,
                    810,
                    811,
                    812,
                    813,
                    814,
                    815,
                    816,
                    817,
                    818,
                    819,
                    820};
            Integer[] UID={309,
                    640,0
                    ,
                    820,0
                    ,
                    653,
                    452,
                    884,
                    2077,
                    1209,
                    1210,
                    455,
                    453,
                    716,
                    25,0
                    ,
                    461,0
                    ,
                    566,
                    423};
//          根据uid数量控制循环次数
        for (int i=0;i<performance_id.length;i++) {
            String url = "https://bmbo-test-performance.daqun.team/api/exam/v1/performance/step/employee_comment";
//           每次循环取当前performance_id传入json
            String json = "{\n" +
                    "\t\"bad\": \"<p>不太行的12443</p>\",\n" +
                    "\t\"good\": \"<p>做的好的123</p>\",\n" +
                    "\t\"performance_id\":"+performance_id[i]+",\n" +
                    "\t\"result\": 2,\n" +
                    "\t\"score\": 78,\n" +
                    "\t\"work_summary_list\": [{\n" +
                    "\t\t\"content\": \"<p>工作总结123</p>\",\n" +
                    "\t\t\"object_hash_id_list\": [],\n" +
                    "\t\t\"performance_id\": 785\n" +
                    "\t}],\n" +
                    "\t\"file_list\": []\n" +
                    "}";
            Map<String, String> header = new HashMap();
//          循环遍历uid传入

            header.put("uid", UID[i].toString());
            HttpClientResult httpClientResult = HttpClientUtils.doPost(url, header, json, false);
            System.out.println(httpClientResult);

        }

    }
    //批量上级评价，
    // @param传入performance_id，
    // @param上级评价人uid；

    @Test

    public void test02() {



        int[] performance_id = {801,
                802,
                803,
                804,
                805,
                806,
                807,
                808,
                809,
                810,
                811,
                812,
                813,
                814,
                815,
                816,
                817,
                818,
                819,
                820};
        String uid="10";
        for (int i=0;i<performance_id.length;i++) {
                String url = "https://bmbo-test-performance.daqun.team/api/exam/v1/performance/step/superior_comment";

                String json = "{\n" +
                    "\t\"performance_id\": "+performance_id[i]+",\n" +
                    "\t\"good\": \"上级评价做的好的\",\n" +
                    "\t\"improve\": \"上级评价做的差的\",\n" +
                    "\t\"result\": 2,\n" +
                    "\t\"score\": 87\n" +
                    "}";
                Map<String, String> header = new HashMap();

                header.put("uid",uid);



                HttpClientResult httpClientResult = HttpClientUtils.doPost(url, header, json, false);
                System.out.println(httpClientResult);

        }




    }
    //批量校准绩效，
    // @param传入performance_id，
    // @param上级评价人uid；

    @Test
    public void test03(){


        String performance ="801,\n" +
                "802,\n" +
                "803,\n" +
                "804,\n" +
                "805,\n" +
                "806,\n" +
                "807,\n" +
                "808,\n" +
                "809,\n" +
                "810,\n" +
                "811,\n" +
                "812,\n" +
                "813,\n" +
                "814,\n" +
                "815,\n" +
                "816,\n" +
                "817,\n" +
                "818,\n" +
                "819,\n" +
                "820";
        String performance_ids="["+performance+"]";

        Integer[] UID={10};
        for (int i=0;i<UID.length;i++) {
            String url = "https://bmbo-test-performance.daqun.team/api/exam/v1/performance/step/batch_align";

            String json = "{\n" +
                    "\t\"performance_ids\": "+performance_ids+",\n" +
                    "\t\"suggestion\": \"批量校准意见同意\"\n" +
                    "}";

            System.out.println(json);
            Map<String, String> header = new HashMap();

            header.put("uid", UID[i].toString());



            HttpClientResult httpClientResult = HttpClientUtils.doPost(url, header, json, false);
            System.out.println(httpClientResult);

        }







    }
    //批量提交面谈，
    // @param传入performance_id，
    // @param上级评价人uid；

    @Test
    public void test04() {



        int[] performance_id = {801,
                802,
                803,
                804,
                805,
                806,
                807,
                808,
                809,
                810,
                811,
                812,
                813,
                814,
                815,
                816,
                817,
                818,
                819,
                820};
        Integer[] UID = {309,
                640,0
                ,
                820,0
                ,
                653,
                452,
                884,
                2077,
                1209,
                1210,
                455,
                453,
                716,
                25,0
                ,
                461,0
                ,
                566,
                423,};
        for (int i = 0; i < performance_id.length; i++) {
            String url = "https://bmbo-test-performance.daqun.team/api/exam/v1/performance/step/take";

            String json = "{\n" +
                    "\t\"comment\": \"<p>批量面谈123</p>\",\n" +
                    "\t\"plan\": \"<p>批量下阶段提升计划</p>\",\n" +
                    "\t\"performance_id\": "+performance_id[i]+",\n" +
                    "\t\"result\": 2\n" +
                    "}";
            Map<String, String> header = new HashMap();

            header.put("uid", UID[i].toString());


            HttpClientResult httpClientResult = HttpClientUtils.doPost(url, header, json, false);
            System.out.println(httpClientResult);

        }


    }

    //批量处理绩效确认，
    // @param传入performance_id，
    // @param上级评价人uid；
    @Test
    public void test05() {

        //批量处理绩效确认，传入performance_id，上级评价人uid；
        String performance ="801,\n" +
                "802,\n" +
                "803,\n" +
                "804,\n" +
                "805,\n" +
                "806,\n" +
                "807,\n" +
                "808,\n" +
                "809,\n" +
                "810,\n" +
                "811,\n" +
                "812,\n" +
                "813,\n" +
                "814,\n" +
                "815,\n" +
                "816,\n" +
                "817,\n" +
                "818,\n" +
                "819,\n" +
                "820";
        String performance_ids="["+performance+"]";
        Integer[] UID = {25};
        for (int i = 0; i < UID.length; i++) {
            String url = "https://bmbo-test-performance.daqun.team/api/exam/v1/performance/step/confirm/batch_pass";

            String json = "{\n" +
                    "\t\"performance_ids\": "+performance_ids+",\n" +
                    "\t\"remark\": \"批量确认\"\n" +
                    "}";
            JSONObject jsonObject=JSONObject.parseObject(json);
            Map<String, String> header = new HashMap();

            header.put("uid", UID[i].toString());


            HttpClientResult httpClientResult = HttpClientUtils.doPost(url, header, json, false);

            System.out.println(httpClientResult);
//            JSONObject jsonObject=JSONObject.parseObject(json);

        }
    }



    @Test
    public  void test06(){



    }






}
