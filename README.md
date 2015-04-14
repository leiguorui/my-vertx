#### 在IntelliJ IDEA中调试vert.x程序

> 1. Open up the Run configuration
> 2. I select the Application configuration
> 3. I specify the main class as : org.vertx.java.platform.impl.cli.Starter
> 4. Program arguments : run src/main/java/com/renrennet/vertx/eventbusbridge/BridgeServer.java
> 5. use classpath of module .. the only module selectable

#### 如何测试

1.  启动 BridgeServer.java,访问localhost:8087
2.  用maven打包，使用vertx runzip target/my-vertx-1.0-SNAPSHOT-mod.zip 启动

#### 生成vertx 的maven项目骨架

    mvn archetype:generate -Dfilter=io.vertx: