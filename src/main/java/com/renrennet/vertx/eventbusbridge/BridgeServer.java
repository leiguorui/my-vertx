package com.renrennet.vertx.eventbusbridge;

import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;
import org.vertx.java.core.logging.Logger;
import org.vertx.java.core.sockjs.SockJSServer;
import org.vertx.java.platform.Verticle;
/**
 * Created by leiguorui on 10/29/14.
 */
public class BridgeServer extends Verticle {
    Logger logger;

    public void start() {
        logger = container.logger();

        HttpServer server = vertx.createHttpServer();

        // Also serve the static resources. In real life this would probably be done by a CDN
        server.requestHandler(new Handler<HttpServerRequest>() {
            public void handle(HttpServerRequest req) {
                if (req.path().equals("/")) req.response().sendFile("webapp/eventbus.html"); // Serve the index.html
                if (req.path().endsWith("vertxbus.js")) req.response().sendFile("webapp/js/vertxbus.js"); // Serve the js
            }
        });

        JsonArray permitted = new JsonArray();
        permitted.add(new JsonObject()); // Let everything through

        ServerHook hook = new ServerHook(logger);

        SockJSServer sockJSServer = vertx.createSockJSServer(server);
        sockJSServer.setHook(hook);
        sockJSServer.bridge(new JsonObject().putString("prefix", "/eventbus"), permitted, permitted);

        server.listen(8070);

        container.logger().info("server started");
    }
}