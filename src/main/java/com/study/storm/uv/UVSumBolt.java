package com.study.storm.uv;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

/**
 * （4）创建UVSumBolt
 */
public class UVSumBolt extends BaseRichBolt {
    private Map<String, Integer> map = new HashMap<String, Integer>();
    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {

    }

    @Override
    public void execute(Tuple input) {
        //业务
        //1、获取数据
        String ip = input.getString(0);
        Integer num = input.getInteger(1);

        //2、去重业务
        if (map.containsKey(ip)) {
            Integer count = map.get(ip);
            map.put(ip, count + num);
        } else {
            map.put(ip, num);
        }

        //3、打印到控制台
        System.err.println(Thread.currentThread().getId() + "  ip:" + ip + "  num:" + map.get(ip));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
