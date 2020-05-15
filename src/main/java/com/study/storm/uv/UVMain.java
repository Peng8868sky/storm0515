package com.study.storm.uv;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

/**
 * 实时计算网站UV去重案例
 *
 * （5）创建UVMain驱动
 */
public class UVMain {
    public static void main(String[] args) {
        //1、创建拓扑对象
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("UVSpout", new UVSpout(),1);
        builder.setBolt("UVBolt1", new UVBolt1(),4).shuffleGrouping("UVSpout");
        builder.setBolt("UVSumBolt", new UVSumBolt(), 1).shuffleGrouping("UVBolt1");

        //2、设置配置参数
        Config conf = new Config();

        //3、提交
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("uvtopology", conf, builder.createTopology());


    }
}
