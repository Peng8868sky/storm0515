package com.study.storm.count;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * 单词实时统计案例完成
 *
 * （1）创建spout
 */

//发送一条语句
public class WordCountSpout extends BaseRichSpout {
    private SpoutOutputCollector collector;

    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector collector) {
        this.collector=collector;

    }


    @Override
    public void nextTuple() {
       //发送数据
        collector.emit(new Values("i am ximen love jinlian"));
        //延迟0.5s
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("love"));

    }
}
