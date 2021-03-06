package com.roytrack.netty.delimiter5_2_1;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by roytrack on 2016/5/24.
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {

  static final String ECHO_REQ = "Hi,welcome to Netty.$_";
  private int count;

  @Override
  public void channelActive(ChannelHandlerContext ctx) {
    for (int i = 0; i < 10; i++) {
      String s = "";
      if (i % 2 == 0) {
        s = "12345678901234567890";
      } else {
        s = "aaaaaaaaaa";
      }
      ctx.writeAndFlush(Unpooled.copiedBuffer(s.getBytes()));
      System.out.println("client write to server " + i);
    }
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    System.out.println("This is " + ++count + " times  receive server:[" + msg + "]");
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) {
    ctx.flush();
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    cause.printStackTrace();
    ctx.close();
  }
}
