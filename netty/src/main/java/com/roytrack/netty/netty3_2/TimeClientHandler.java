package com.roytrack.netty.netty3_2;

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

  private final ByteBuf firstMessage;

  public TimeClientHandler() {
    byte[] seq = "time".getBytes();
    firstMessage = Unpooled.buffer(seq.length);
    firstMessage.writeBytes(seq);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    logger.warning("Unexpected exception  from downstream :" + cause.getMessage());
    ctx.close();
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) {
    ctx.writeAndFlush(firstMessage);
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    ByteBuf buf = (ByteBuf) msg;
    byte[] req = new byte[buf.readableBytes()];
    buf.readBytes(req);
    String body = new String(req, "UTF-8");
    System.out.println("Now is : " + body);

  }


}
