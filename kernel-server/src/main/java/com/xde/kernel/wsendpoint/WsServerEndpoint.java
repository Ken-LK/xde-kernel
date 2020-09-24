package com.xde.kernel.wsendpoint;

import com.xde.kernel.tools.RedisTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="mailto:liukailk.ken@gmail.com"> Ken </a>
 * @date 2020/9/2 4:04 下午
 **/
//@ServerEndpoint("/myWs")
//@Component
@Slf4j
public class WsServerEndpoint {

    private RedisTool redisTool;
    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        WsServerEndpoint.applicationContext = applicationContext;
    }

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WsServerEndpoint> webSocketSet = new CopyOnWriteArraySet<WsServerEndpoint>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static AtomicInteger ONLINE_COUNT = new AtomicInteger(0);


    /**
     * 连接成功
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        log.info("一个用户来了:{}", session.getId());

        this.session = session;
        //加入set中
        webSocketSet.add(this);
        //在线数加1
        ONLINE_COUNT.incrementAndGet();
        log.info("有新连接加入！当前在线人数为-->{}", ONLINE_COUNT);

    }

    /**
     * 连接关闭
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        //从set中删除
        webSocketSet.remove(this);
        //在线数减1
        ONLINE_COUNT.decrementAndGet();
        log.info("有一连接关闭！当前在线人数为-->{}", ONLINE_COUNT);
    }

    /**
     * 接收到消息
     *
     * @param text
     */
    @OnMessage
    public String onMsg(String text) {
        log.info("接收内容:{}", text);

        //群发消息
        for (WsServerEndpoint item : webSocketSet) {
            item.sendMessage("");
        }
        return "servet 发送：" + text;
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误-->{}", error.getMessage());
    }

    public void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendObject(message);
        } catch (Exception e) {
            log.error("发送消息异常-->", e);
        }
    }


}
