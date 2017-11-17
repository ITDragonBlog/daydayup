# Nginx 安装部署

> 这里有最简单的安装步骤，很参见的疑难杂症

## 理想流程

```
[root@itdragon ~]# wget http://nginx.org/download/nginx-1.13.6.tar.gz
[root@itdragon ~]# tar -zxvf nginx-1.13.6.tar.gz
[root@itdragon ~]# ll
total 824
drwxr-xr-x 9 1001 1001   4096 Nov 14 14:26 nginx-1.13.6
-rw-r--r-- 1 root root 832104 Nov 14 14:18 nginx-1.13.6.tar.gz
[root@itdragon ~]# cd nginx-1.13.6
[root@itdragon nginx-1.13.6]# ./configure
[root@itdragon nginx-1.13.6]# make
[root@itdragon nginx-1.13.6]# make install
[root@itdragon nginx-1.13.6]# cd /usr/local/nginx/sbin/
[root@itdragon sbin]# ./nginx
[root@itdragon sbin]# ifconfig
```
第一步：下载Nginx压缩包  
第二步：解压  
第三步：配置，编译，安装，启动  
第四步：查看ip地址  
第五步：浏览器访问：ip：port  
若出现如下图片则说明安装成功。  
![Nginx欢迎页面](http://img.blog.csdn.net/20171117101936334?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMTk1NTg3MDU=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
  
但是，Nginx是调皮的，它不会让我们如此顺利

## 常见问题
踩坑？不存在的，我踩过的坑，不允许让你们再踩。它是我滴！

 - ./configure: error: C compiler cc is not found
 - ./configure: error: the HTTP rewrite module requires the PCRE library.
 - ./configure: error: the HTTP gzip module requires the zlib library
 - OpenSSL library is not used
 - nginx: [emerg] bind() to 0.0.0.0:88 failed (98: Address already in use)
 
第一个问题，是因为 nginx 解压编译依赖 gcc 环境造成的。
```
[root@itdragon ~]# yum install gcc-c++
```
第二个问题，是因为 nginx 的 http 模块使用 pcre 来解析正则表达式
```
[root@itdragon ~]# yum install -y pcre pcre-devel
```
第三个问题，是因为 nginx 使用 zlib 对 http 包的内容进行 gzip 操作
```
[root@itdragon ~]# yum install -y zlib zlib-devel
```
第四个问题，建议安装，nginx 它是支持https 协议的
```
[root@itdragon ~]# yum install -y openssl openssl-devel
```
第五个问题，是很常见的端口占用，修改 nginx.config 文件中的端口即可。 /port，快速找到端口配置的地方。[Insert] 开启编辑模式。[Esc] :wq 退出保存
```
[root@itdragon sbin]# ./nginx 
nginx: [emerg] bind() to 0.0.0.0:88 failed (98: Address already in use)
[root@itdragon sbin]# vim ../conf/nginx.conf
server {
        listen       88;
        server_name  localhost;
[root@itdragon sbin]# ./nginx
```
若出现 Loaded plugins: fastestmirror 不是问题的问题。可以通过修改fastestmirror.conf 文件，这是一种不负责任的做法，如果自己玩 Nginx 可以这样做。如果是实际开发，就老老实实的按照提示来做。
```
[root@plugins ~]# vim /etc/yum/pluginconf.d/fastestmirror.conf
enabled=0
[root@plugins ~]# vim /etc/yum.conf
plugins=0
[root@plugins ~]# yum clean dbcache
```

 
