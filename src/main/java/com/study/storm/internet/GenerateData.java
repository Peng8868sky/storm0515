package com.study.storm.internet;

import java.io.File;
import java.io.FileOutputStream;

import java.util.Random;

/**
 * （1）创建网站访问日志
 */
public class GenerateData  {
    @SuppressWarnings("resource")
    public static void main(String[] args) {


        //1、创建文件路径
        File logFile = new File("e:/website.log");

        //2、准备数据
        //2.1  网站名称
        String[] hosts = { "www.atguigu.com" };
        //2.2  会话id
        String[] session_id = { "ABYH6Y4V4SCVXTG6DPB4VH9U123", "XXYH6YCGFJYERTT834R52FDXV9U34",
                "BBYH61456FGHHJ7JL89RG5VV9UYU7", "CYYH6Y2345GHI899OFG4V9U567", "VVVYH6Y4V4SFXZ56JIPDPB4V678" };
        // 2.3 访问网站时间
        String[] time = { "2017-08-07 08:40:50", "2017-08-07 08:40:51", "2017-08-07 08:40:52", "2017-08-07 08:40:53",
                "2017-08-07 09:40:49", "2017-08-07 10:40:49", "2017-08-07 11:40:49", "2017-08-07 12:40:49" };

        //3、拼接数据
        Random random = new Random();
        StringBuffer sbBuffer = new StringBuffer();
        for (int i = 0; i < 30; i++) {
            sbBuffer.append(hosts[0] + "\t" + session_id[random.nextInt(5)] + "\t" + time[random.nextInt(8)] + "\n");
        }
        //5、写数据到文件
        FileOutputStream outputStream=null;
        try {
            outputStream = new FileOutputStream(logFile);
            outputStream.write(sbBuffer.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //5、关闭资源
            try {
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}