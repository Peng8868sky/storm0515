package com.study.storm.pv;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Values;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * 实时计算网站PV案例
 *
 * （1）创建数据输入源PVSpout
 */
public class PVSpout implements IRichSpout {
    private SpoutOutputCollector collector ;
    private BufferedReader reader;

    @SuppressWarnings("resource")
    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector collector) {
        //读取文件
        this.collector=collector;
        //读取文件
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("e:/website.log")));
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
        //发送读取文件每一行
        try {
            //发送读取文件的每一行
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
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        //声明发送字段

    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
