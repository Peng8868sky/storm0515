package com.study.storm.uv;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * （2）创建接收数据UVSpout
 */
public class UVSpout implements IRichSpout {
    private SpoutOutputCollector collector ;
    private BufferedReader reader;

    @SuppressWarnings("resource")
    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector collector) {
        this.collector=collector;

        //读取文件
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("e:/website.log"),"UTF-8"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void activate() {

    }

    @Override
    public void deactivate() {

    }

    private String str=null;
    @Override
    public void nextTuple() {
        //业务逻辑
        try {
            while((str = reader.readLine()) != null){

                collector.emit(new Values(str));

                Thread.sleep(500);
            }
        } catch (Exception e) {

        }
    }


    @Override
    public void ack(Object o) {

    }

    @Override
    public void fail(Object o) {

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("log"));

    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
