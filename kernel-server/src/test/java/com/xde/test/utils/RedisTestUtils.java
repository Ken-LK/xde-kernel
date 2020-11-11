package com.xde.test.utils;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.xde.kernel.XdeKernelApplication;
import com.xde.kernel.tools.PasswordUtil;
import com.xde.kernel.tools.RedisPipelineService;
import com.xde.kernel.tools.RedisTool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:liukailk.ken@gmail.com"> Ken </a>
 * @date 2020/10/21 1:47 下午
 **/
@SpringBootTest(classes = XdeKernelApplication.class)
@Slf4j
public class RedisTestUtils {

    @Autowired
    private RedisTool redisTool;

    @Autowired
    private RedisPipelineService redisPipelineService;

    @Autowired
    StringRedisTemplate redisTemplate;


    @Test
    public void testRedis() {

    }

    @Test
    public void setWeakPassword() {

        System.out.println("开始时间:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

//        List<String> list = readTxtFileIntoStringArrList("/Users/ken/work/xiaode/homecare_server/com.xde.platform.eoms/src/test/java/com/xde/platform/eoms/utils/weak_password.txt");
        List<String> list = readTxt("/Users/ken/work/xiaode/homecare_server/com.xde.platform.eoms/src/test/java/com/xde/platform/eoms/utils/weak_password.txt");

        System.out.println("共：" + list.size() + "条数据");

//        redisPipelineService.batchInsert("weakPassword", list);

        System.out.println("结束时间:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));


    }

    public static void main(String[] args) {
        List<String> list = readTxt("/Users/ken/work/xiaode/homecare_server/com.xde.platform.eoms/src/test/java/com/xde/platform/eoms/utils/weak_password.txt");
        Set<Object> objectList = list.stream().map(o -> {
            if(PasswordUtil.checkRules(o)){

                return o;
            }
            return null;
        })
                .filter(RedisTestUtils :: notHasNull)
                .collect(Collectors.toSet());

        System.out.println("objectList:" + JSON.toJSONString(objectList));
        System.out.println("size:" + objectList.size());
    }

    public static boolean notHasNull(Object obj) {
        return obj != null;
    }




    /**
     * 使用common io 读取文件内容
     */

    private static List<String> readTxt(String path) {

        List<String> list = null;

        try {
            list = FileUtils.readLines(new File(path), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("文件读取失败，文件路径为:{}", path);
        }
        return list;
    }

    /**
     * 功能：Java读取txt文件的内容 步骤：
     * 1：先获得文件句柄
     * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * 3：读取到输入流后，需要读取生成字节流 4：一行一行的输出。readline()。
     *
     * @param filePath 文件路径
     * @return 将这个文件按照每一行切割成数组存放到list中。
     */
    public static List<String> readTxtFileIntoStringArrList(String filePath) {
        List<String> list = new ArrayList<>();
        try {
            String encoding = "UTF-8";
            File file = new File(filePath);
            // 判断文件是否存在
            if (file.isFile() && file.exists()) {
                // 考虑到编码格式
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    list.add(lineTxt);
                }
                bufferedReader.close();
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

        return list;
    }



}
