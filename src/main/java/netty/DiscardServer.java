package netty;

/**
 * User guide for 4.x
 * https://netty.io/wiki/user-guide-for-4.x.html
 */

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class DiscardServer {
    private int port;

    public DiscardServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        /*
         *  We are implementing a server-side application in this example,
         *  and therefore two NioEventLoopGroup will be used.
         *
         *  The first one, often called 'boss', accepts an incoming connection
         *  The second one, often called 'worker',
         * handles the traffic of the accepted connection once the boss accepts the connection
         * and registers the accepted connection to the worker.
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            /*
             *   ServerBootstrap is a helper class that sets up a server.
             */
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    /*
                     * NioServerSocketChannel is used to instantiate a new Channel to accept incoming connections.
                     */
                    .channel(NioServerSocketChannel.class)
                    /*
                     * The handler specified here will always be evaluated by a newly accepted Channel.
                     * The ChannelInitializer is a special handler that is purposed to help a user configure a new Channel
                     */
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new TimeServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8080;
        }
        new DiscardServer(port).run();
    }
}
