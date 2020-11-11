package com.xde.test.thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 多线程下载图片测试
 *
 * @author <a href="mailto:liukailk.ken@gmail.com"> Ken </a>
 * @date 2020/10/22 8:54 上午
 **/
public class DownloaderTest implements Runnable{

    // 图片地址
    private String url;
    // 存放路径
    private String path;


    public DownloaderTest(String url, String path) {
        this.url = url;
        this.path = path;
    }

    @Override
    public void run() {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(path));
            System.out.println("下载完成了："+path);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("图片下载失败了");
        }
    }

    public static void main(String[] args) {

        DownloaderTest dt1 = new DownloaderTest("https://iconfont.alicdn.com/t/368415f1-c55a-40ba-a94f-fbed1882683f.png","/Users/ken/Downloads/temp/1.jpg");
        DownloaderTest dt2 = new DownloaderTest("https://iconfont.alicdn.com/t/fc878fcf-4e07-4793-93c7-87210a9571f1.png","/Users/ken/Downloads/temp/2.jpg");
        DownloaderTest dt3 = new DownloaderTest("https://iconfont.alicdn.com/t/7fb05a64-ea94-41e9-b422-e1282563f94c.png","/Users/ken/Downloads/temp/3.jpg");

        new Thread(dt1).start();
        new Thread(dt1).start();
//        dt1.start();
//        dt1.start(); // IllegalThreadStateException
//        dt2.start();
//        dt3.start();



    }
}
