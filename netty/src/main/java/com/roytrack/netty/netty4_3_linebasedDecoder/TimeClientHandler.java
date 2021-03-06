package com.roytrack.netty.netty4_3_linebasedDecoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.logging.Logger;

/**
 * Created by roytrack on 2016-05-04.
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {
  private static final Logger logger = Logger.getLogger(TimeClientHandler.class.getName());

  private int counter;


  private byte[] seq;

  public TimeClientHandler() {
    seq = ("time" + System.getProperty("line.separator")).getBytes();
  }


  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    logger.warning("Unexpected exception  from downstream :" + cause.getMessage());
    ctx.close();
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) {
    ByteBuf message = null;
    for (int i = 0; i < 100; i++) {
      message = Unpooled.buffer(seq.length);
      message.writeBytes(seq);
      ctx.writeAndFlush(message);
    }


  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    String body = (String) msg;
    System.out.println("Now is : " + body + " ; the counter is :" + ++counter);

  }


}
