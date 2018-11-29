package tools.netty;

import com.cs.esp.org.json.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.cookie.ServerCookieDecoder;
import io.netty.handler.codec.http.cookie.ServerCookieEncoder;
import io.netty.util.CharsetUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        StringBuilder responseContent = new StringBuilder();
        FullHttpRequest request= (FullHttpRequest) msg;


        String uri = getUrl(request);
        String body = getBody(request);
        System.out.println("URI : " + uri);
        System.out.println("BODY : " + body);

        JSONObject res = execute(uri,body);
        responseContent.append(res.toString('"'));

        FullHttpResponse resp = returnResp(responseContent);
        boolean isKeeplive = setResponseHeader(resp,request);

        // Write the response.
        ctx.writeAndFlush(resp);
        if(!isKeeplive){
            // If keep-alive is off, close the connection once the content is fully written.
            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }

    private String getUrl(FullHttpRequest msg) throws URISyntaxException {
        URI uri = new URI(msg.uri());
        return uri.toString();
    }

    private String getBody(FullHttpRequest request){
        ByteBuf buf = request.content();
        return buf.toString(CharsetUtil.UTF_8);
    }

    private JSONObject execute(String uri,String body){
        JSONObject json = new JSONObject();
        if(body.length() != 0){
            json.put("status","pass");
            json.put("msg",body);
        } else {
            json.put("status","error");
            json.put("msg","the body is null");
        }
        return json;
    }

    private FullHttpResponse returnResp(StringBuilder responseContent){
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,OK,Unpooled.copiedBuffer(responseContent.toString(), CharsetUtil.UTF_8));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json;charset=UTF-8");
        response.headers().set("Access-Control-Allow-Origin","*");
        response.headers().set("Access-Control-Allow-Methods","POST, GET, OPTIONS");
        response.headers().set("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, esp_token");
        return response;
    }

    private boolean setResponseHeader(FullHttpResponse response,FullHttpRequest request){
        boolean keepAlive = HttpUtil.isKeepAlive(request);
        if (keepAlive) {
            // Add 'Content-Length' header only for a keep-alive connection.
            response.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
            // Add keep alive header as per:
            // - http://www.w3.org/Protocols/HTTP/1.1/draft-ietf-http-v11-spec-01.html#Connection
            response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }
        String cookieString = request.headers().get(HttpHeaderNames.COOKIE);
        if (cookieString != null) {
            Set<io.netty.handler.codec.http.cookie.Cookie> cookies = ServerCookieDecoder.STRICT.decode(cookieString);
            if (!cookies.isEmpty()) {
                // Reset the cookies if necessary.
                for (io.netty.handler.codec.http.cookie.Cookie cookie: cookies) {
                    response.headers().add(HttpHeaderNames.SET_COOKIE, io.netty.handler.codec.http.cookie.ServerCookieEncoder.STRICT.encode(cookie));
                }
            }
        } else {
            // Browser sent no cookie.  Add some.
            response.headers().add(HttpHeaderNames.SET_COOKIE, io.netty.handler.codec.http.cookie.ServerCookieEncoder.STRICT.encode("key1", "value1"));
            response.headers().add(HttpHeaderNames.SET_COOKIE, ServerCookieEncoder.STRICT.encode("key2", "value2"));
        }
        return keepAlive;
    }




}
