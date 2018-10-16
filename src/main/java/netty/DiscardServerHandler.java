package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
    public void channelRead(ChannelHandlerContext ctx,Object msg){
        ByteBuf in = (ByteBuf) msg;
//        try{
////            while(in.isReadable()){
////                System.out.print((char) in.readByte());
////                System.out.flush();
////            }
//            System.out.print(in.toString(io.netty.util.CharsetUtil.US_ASCII));
//        } finally {
//            ReferenceCountUtil.release(msg);
//        }


        ctx.write(msg);
        ctx.flush();
        //System.out.print(in.toString(io.netty.util.CharsetUtil.US_ASCII));
    }

    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
}
