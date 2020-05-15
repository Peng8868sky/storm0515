package com.study.storm.internet;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

/**
 * （3）创建bolt
 */
public class WebLogBolt implements IRichBolt {
    private int line_num=0;

    //准备工作
    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {

    }

    //执行
    @Override
    public void execute(Tuple input) {
        //1、获取数据
       /* String StringByField = input.getStringByField("log");*/
        String line = input.getString(0);

        //2、切割数据:www.atguigu.com  BBYH61456FGHHJ7JL89RG5VV9UYU7  017-08-07 10:40:49
        String[] split = line.split("\t");
        String session_id = split[1];

        //3、统计发送行数
        line_num++;

        //打印
        System.err.println(Thread.currentThread().getId()+" session_id :"+session_id+" line_num:"+line_num);
    }

    //清除资源
    @Override
    public void cleanup() {

    }

    //声明
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }

    //获取配置信息
    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
