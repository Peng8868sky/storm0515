package com.study.storm.internet;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.io.*;
import java.util.Map;

/**
 * （2）创建spout
 */
public class WebLogSpout implements IRichSpout {
    private BufferedReader reader;
    private SpoutOutputCollector collector;
    private String str;



    @SuppressWarnings("resource")
    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector collector) {
        this.collector=collector;
        //打开文件
        try{
           reader=new BufferedReader(new InputStreamReader(new FileInputStream("e:/website.log")));

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
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

    @Override
    public void nextTuple() {

        try{
            //发送数据
            while((str=reader.readLine())!=null){
                //发送数据
                collector.emit(new Values(str));
                Thread.sleep(500);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
