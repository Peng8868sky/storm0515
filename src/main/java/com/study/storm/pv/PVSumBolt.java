package com.study.storm.pv;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * （3）创建PVSumBolt
 */
public class PVSumBolt implements IRichBolt {

    private Map<Long, Long> counts = new HashMap<>();

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {

    }

    @Override
    public void execute(Tuple input) {
        //累加求和业务

        //1、获取对象
        Long threadID = input.getLong(0);
        Long pv = input.getLong(1);

        //2、替换原来线程的pvnum
        counts.put(threadID, pv);

        //3、累加求和
        long word_sum = 0;
        Iterator<Long> iterator = counts.values().iterator();
        while (iterator.hasNext()) {
            word_sum += iterator.next();
        }
        System.err.println("word_sum:" + word_sum);

      //4、打印到控制台
        System.err.println("word_sum:" + word_sum);


    }

    @Override
    public void cleanup() {

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
