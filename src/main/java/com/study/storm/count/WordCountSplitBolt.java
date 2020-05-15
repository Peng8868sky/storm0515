package com.study.storm.count;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * （2）创建切割单词的bolt
 */
public class WordCountSplitBolt extends BaseRichBolt {
    private OutputCollector collector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector collector) {
        this.collector=collector;

    }

    @Override
    public void execute(Tuple input) {
        //接受数据
        //1、获取数据
        String line = input.getString(0);
        //2、截取数据：i am ximen love jinlian
        String[] arrWords = line.split(" ");
        //3、发送出去
        for (String word : arrWords) {
            collector.emit(new Values(word, 1));
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        //声明字段
        declarer.declare(new Fields("word", "num"));

    }
}
