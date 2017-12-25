package com.itdragon.config;

import java.util.concurrent.TimeUnit;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.IdleConnectionEvictor;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

/**
 * @Configuration 	作用于类上，相当于一个xml配置文件
 * @Bean 			作用于方法上，相当于xml配置中的<bean>
 * @PropertySource	指定读取的配置文件,ignoreResourceNotFound=true 文件不存在是忽略
 * @Value			获取配置文件的值
 */
@Configuration
@PropertySource(value = "classpath:httpclient.properties", ignoreResourceNotFound=true)
public class HttpclientSpringConfig {

    @Value("${http.maxTotal}")
    private Integer httpMaxTotal;

    @Value("${http.defaultMaxPerRoute}")
    private Integer httpDefaultMaxPerRoute;

    @Value("${http.connectTimeout}")
    private Integer httpConnectTimeout;

    @Value("${http.connectionRequestTimeout}")
    private Integer httpConnectionRequestTimeout;

    @Value("${http.socketTimeout}")
    private Integer httpSocketTimeout;

    @Autowired
    private PoolingHttpClientConnectionManager manager;

    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        // 最大连接数
        poolingHttpClientConnectionManager.setMaxTotal(httpMaxTotal);
        // 每个主机的最大并发数
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(httpDefaultMaxPerRoute);
        return poolingHttpClientConnectionManager;
    }

    @Bean	// 定期清理无效连接
    public IdleConnectionEvictor idleConnectionEvictor() {
        return new IdleConnectionEvictor(manager, 1L, TimeUnit.HOURS);
    }

    @Bean	// 定义HttpClient对象 注意该对象需要设置scope="prototype":多例对象
    @Scope("prototype")
    public CloseableHttpClient closeableHttpClient() {
        return HttpClients.custom().setConnectionManager(this.manager).build();
    }

    @Bean	// 请求配置
    public RequestConfig requestConfig() {
        return RequestConfig.custom().setConnectTimeout(httpConnectTimeout) // 创建连接的最长时间
                .setConnectionRequestTimeout(httpConnectionRequestTimeout) // 从连接池中获取到连接的最长时间
                .setSocketTimeout(httpSocketTimeout) // 数据传输的最长时间
                .build();
    }

}
