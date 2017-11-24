# Redis安装.md

> CentOS6.5 环境安装 redis-4.0.2 服务,启动服务，关闭服务，启动客户端，解决redis服务连接被拒的问题

## 理想流程

```
[root@itdragon ~]# wget http://download.redis.io/releases/redis-4.0.2.tar.gz
[root@itdragon ~]# tar -zxvf redis-4.0.2.tar.gz
[root@itdragon ~]# cd redis-4.0.2
[root@itdragon redis-4.0.2]# make
[root@itdragon redis-4.0.2]# make install PREFIX=/usr/local/redis-4
[root@itdragon redis-4.0.2]# cd /usr/local/redis-4/bin/
[root@itdragon bin]# ./redis-server
```
看到这个界面就说明安装成功了  
![redis安装成功界面](https://images2018.cnblogs.com/blog/806956/201711/806956-20171124115546984-1987305277.png)

## 安装前的准备
redis 编译依赖 gcc 环境
```
[root@itdragon ~]# yum install gcc-c++
```
## 启动 redis
前端启动，不推荐
```
[root@itdragon bin]# ./redis-server
```
后台启动，推荐
```
[root@itdragon bin]# cp /root/redis-4.0.2/redis.conf ./
[root@itdragon bin]# vim redis.conf
[root@itdragon bin]# ./redis-server redis.conf 
[root@itdragon bin]# ps -aux | grep redis
```
第一次启动步骤：
第一步：为了方便管理，将解压后的redis.conf 拷贝一份到 /usr/local/redis-4/bin/ 目录下  
第二步：修改 redis.conf 文件，将 daemonize no 改成 daemonize yes 。表示开启守护进程  
第三步：启动 redis 服务。前两步设置好后，就不用再设置了    
第四步：检查是否启动成功。若打印类似如下信息，则表示启动成功。127.0.0.1 是默认的IP地址，也可以在 redis.conf 文件中修改  
```
root      5347  0.0  0.7 143920  7604 ?        Ssl  10:39   0:00 ./redis-server 127.0.0.1:6379
```

## 关闭 redis 
第一种：针对前端启动，可以用 Ctrl + c  
第二种：针对后台启动，可以通过杀进程 kill -s 9 xxx  
第三种：前两种都不推荐，在 /usr/local/redis-4/bin/ 目录下执行 ./redis-cli shutdown 是最好的选择。这是考虑到在关闭 redis 时，可能还未将内存中的数据持久化到本地。
```
[root@itdragon bin]# ./redis-cli shutdown
```

## 客户端启动
启动redis 自带的客户端：
在 /usr/local/redis-4/bin/ 目录下执行 ./redis-cli -h 127.0.0.1 -p 6379 命令。  
若 host 是 127.0.0.1 ， port 是 6379 。则可以直接输入 ./redis-cli
```
[root@itdragon bin]# ./redis-cli -h 127.0.0.1 -p 6379
127.0.0.1:6379> quit
```
通过 RedisDesktopManager 启动  
下载地址 ：https://redisdesktop.com/download   
使用方法 ：打开应用后，点击左下角的 Connect to Redis Server 按钮，输入 host 和 port 点击连接  
连接被拒 ：确保 host 和 port 正确的情况下，修改 CentOS 防火墙配置，使其支持 6379 端口   
```
[root@itdragon bin]# ./redis-server redis.conf 
[root@itdragon bin]# vim /etc/sysconfig/iptables
-A INPUT -p tcp -m tcp --dport 6379 -j ACCEPT
[root@itdragon bin]# service iptables restart
```
重启后，就可以正常连接了。


