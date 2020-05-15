package com.study.storm.pv;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

/**
 * （4）驱动
 */
public class PVMain {
    public static void main(String[] args) {
        //1、创建拓扑对象
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("PVSpout", new PVSpout(), 1);
        builder.setBolt("PVBolt1", new PVBolt1(), 4).shuffleGrouping("PVSpout");
        builder.setBolt("PVSumBolt", new PVSumBolt(), 1).shuffleGrouping("PVBolt1");

         //2、创建配置信息
        Config conf = new Config();

        //3、提交
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("pvtopology", conf, builder.createTopology());

    }
}
