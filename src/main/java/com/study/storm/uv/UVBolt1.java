package com.study.storm.uv;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * （3）创建UVBolt1
 */
public class UVBolt1  extends BaseRichBolt {
    private OutputCollector collector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector collector) {

        this.collector=collector;

    }

    @Override
    public void execute(Tuple input) {
        //业务逻辑
        //1、获取数据
        String line = input.getString(0);

        //2、截取：www.atguigu.com	ABYH6Y4V4SCVXTG6DPB4VH9U123	 2017-08-07 12:40:49	192.168.1.105
        String[] splits = line.split("\t");
        String ip = splits[3];

        //3、发送
        collector.emit(new Values(ip, 1));


    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("ip", "num"));

    }
}
