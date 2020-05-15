package com.study.storm.count;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * （3）创建汇总单词个数的bolt
 */
public class WordCountBolt extends BaseRichBolt {

    //存放单词为key，单词出现的次数为value
    private Map<String, Integer> map = new HashMap<String, Integer>();

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {

    }

    @Override
    public void execute(Tuple input) {
        //1、获取传递过来的数据
        String word = input.getString(0);
        Integer num = input.getInteger(1);

        //2、业务处理
        if (map.containsKey(word)) {
            Integer count = map.get(word);
            map.put(word, count + num);
        } else {
            map.put(word, num);
        }

        //3、控制台打印
        System.err.println(Thread.currentThread().getId() + "  word:" + word + "  num:" + map.get(word));
    }




    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
