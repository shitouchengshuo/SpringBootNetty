package com.qiqi.service;

import com.qiqi.config.thread.NettyThreadPoolConfig;
import com.qiqi.service.decoder.SimpleByteToMessageDecoder;
import com.qiqi.service.input.SimpleChannelInputBoundHandler;
import com.qiqi.service.output.SimpleChannelOutputBoundHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.ResourceLeakDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Netty启动器
 * netty启动一个服务所经过的流程
 * 1.设置启动类参数，最重要的就是设置channel
 * 2.创建server对应的channel，创建各大组件，包括ChannelConfig,ChannelId,ChannelPipeline,ChannelHandler,Unsafe等
 * 3.初始化server对应的channel，设置一些attr，option，以及设置子channel的attr，option，给server的channel添加新channel接入器，
 *   并出发addHandler,register等事件
 * 4.调用到jdk底层做端口绑定，并触发active事件，active触发的时候，真正做服务端口绑定
 *
 */
@Component
public class NettyBootstrap {

    private NettyThreadPoolConfig nettyThreadPoolConfig;

    @Autowired
    public NettyBootstrap(NettyThreadPoolConfig nettyThreadPoolConfig) {
        this.nettyThreadPoolConfig = nettyThreadPoolConfig;
        Assert.notNull(this.nettyThreadPoolConfig, "netty pool setter init fail.");
    }

    /**
     * 启动器
     *
     * @param port 端口号
     */
    public void start(int port) {
        //NioEventLoopGroup可以理解为一个线程池（就是一个死循环，不停地检测IO事件，处理IO事件，执行任务）
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup(nettyThreadPoolConfig.getBoss());
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup(nettyThreadPoolConfig.getWorker());
        //启动辅助类，引导我们进行服务端的启动工作
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //bossLoopGroup 监听端口，是创建新连接的线程组；该线程组接收新连接，并创建新连接(老板接活)，
        //workerLoopGroup是处理每一条连接的数据读写的线程组；该线程组用于读取数据以及业务逻辑处理(工人干活)
        serverBootstrap.group(bossLoopGroup, workerLoopGroup);
        //指定我们服务端的IO模型为NIO，表示服务端启动的是nio相关的channel（连接）
        serverBootstrap.channel(NioServerSocketChannel.class);
        //设置内存泄露检测级别， ADVANCED：抽样检测，记录对象最近几次的调用记录，有泄漏时可能会延迟报告
        ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.ADVANCED);
        //childHandler:用于指定处理新连接数据的读写处理逻辑
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) {
                ch.pipeline().addFirst(new SimpleChannelOutputBoundHandler());
                ch.pipeline().addLast(new SimpleByteToMessageDecoder());

                //ch.pipeline().addLast(new StringDecoder());
                ch.pipeline().addLast(new SimpleChannelInputBoundHandler());
            }
        });
        try {
            // 真正的启动过程，绑定端口，等待服务器启动完毕，才会进入下行代码(同步等待)
            ChannelFuture future = serverBootstrap.bind(port).sync();
            //等待服务端关闭socket(同步)
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //关闭 acceptor 和 worker 线程
            bossLoopGroup.shutdownGracefully();
            workerLoopGroup.shutdownGracefully();
        }
    }
}
