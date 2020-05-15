package com.study.storm.pv;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * （2）创建数据处理pvbolt1
 */
public class PVBolt1 implements IRichBolt {
    private OutputCollector collector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector collector) {
        //获取collector
        this.collector=collector;


    }
    private int pvnum=0;

    @Override
    public void execute(Tuple input) {
        //业务处理

        //1、获取数据
        String line = input.getString(0);

        //2、截取数据:www.atguigu.com	XXYH6YCGFJYERTT834R52FDXV9U34	2017-08-07 11:40:49
        String[] split = line.split("\t");
        String session_id = split[1];

        // 根据会话id不同统计pv次数
        if (session_id != null) {
            //3、局部累加
            pvnum++;

            //4、输出
            collector.emit(new Values(Thread.currentThread().getId(), pvnum));
        }
        System.err.println("threadid:" + Thread.currentThread().getId() + "  pv_num:" + pvnum);
    }

    @Override
    public void cleanup() {

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        //声明输出字段
        declarer.declare(new Fields("thireadiD", "pvnum"));

    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
