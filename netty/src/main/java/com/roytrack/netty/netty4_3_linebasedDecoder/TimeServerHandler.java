package com.roytrack.netty.netty4_3_linebasedDecoder;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * Created by roytrack on 2016/3/28.
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

  private int counter;

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    String body = (String) msg;
    System.out.println("received command : " + body + "; the counter is :" + ++counter);
    String currentTime = "time".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD COMMAND";
    currentTime = currentTime + System.getProperty("line.separator");
    ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
    ctx.write(resp);
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) {
    ctx.flush();
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    ctx.close();
  }
}
