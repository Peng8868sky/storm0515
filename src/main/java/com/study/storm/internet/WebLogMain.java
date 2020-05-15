package com.study.storm.internet;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;

/**
 * （4）创建main
 */
public class WebLogMain {
    public static void main(String[] args) throws InvalidTopologyException, AuthorizationException, AlreadyAliveException {
        //1、创建拓扑对象
        TopologyBuilder builder = new TopologyBuilder();
        //1.1  设置Spout和bolt
        builder.setSpout("weblogspout", new WebLogSpout(), 1);
        builder.setBolt("weblogBolt", new WebLogBolt(), 1).shuffleGrouping("weblogspout");

        //2、创建配置信息对象：配置Worker开启个数
        Config conf = new Config();
        conf.setNumWorkers(2);
        if (args.length > 0) {   //集群提交
            StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
        }else{

            //3、本地提交
            LocalCluster localCluster = new LocalCluster();
            localCluster.submitTopology("weblogtopology", conf, builder.createTopology());
        }
    }
}

