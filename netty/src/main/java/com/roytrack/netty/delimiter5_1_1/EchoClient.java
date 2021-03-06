package com.roytrack.netty.delimiter5_1_1;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created by roytrack on 2016/5/24.
 */
public class EchoClient {
  public static void main(String[] args) throws InterruptedException {
    int port = 9009;
    new EchoClient().connect(port, "127.0.0.1");
  }

  public void connect(int port, String host) throws InterruptedException {
    EventLoopGroup group = new NioEventLoopGroup();
    try {
      Bootstrap b = new Bootstrap();
      b.group(group).channel(NioSocketChannel.class)
              .option(ChannelOption.TCP_NODELAY, true)
              .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) {
                  ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
                  socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
                  socketChannel.pipeline().addLast(new StringDecoder());
                  socketChannel.pipeline().addLast(new EchoClientHandler());
                }
              });
      ChannelFuture future = b.connect(host, port).sync();
      System.out.println("client start");
      future.channel().closeFuture().sync();
    } finally {
      group.shutdownGracefully();
    }
  }
}
