# vsftpd 安装
> 这里有最简洁的安装步骤
## 理想流程
```
[root@itdragon ~]# useradd ftpuser
[root@itdragon ~]# passwd ftpuser
Changing password for user ftpuser.
New password: 
BAD PASSWORD: it is too short
BAD PASSWORD: is too simple
Retype new password: 
passwd: all authentication tokens updated successfully.
[root@itdragon ~]# yum -y install vsftpd
[root@itdragon ~]# ifconfig
```
第一步：添加ftp用户  
第二步：设置ftp用户密码  
第三步：安装vsftpd  
第四步：查看ip地址  
第五步：本地使用免费的FileZilla 链接虚拟机  

## 遇到的问题
### 连接失败
```
状态:	正在等待重试...
状态:	正在连接 192.168.0.11:21...
错误:	20 秒后无活动，连接超时
错误:	无法连接到服务器
```
不用灰心，安装本来就不是顺风顺水的，每个人会根据自己的环境出现不同的问题。下面是我的解决方法  
首先，要确定两边能 pind 通，虚拟机选择的是桥接模式，能正常 ping 通。  
然后，**百度**   
网上有很多五花八门的解决方法，主要是针对三个方面的   
这里我会贴出几个网址，因为我按照网站上面的做法没有连接成功，最后是关闭了防火墙才解决的。我对这块比较薄弱，就不误人子弟了。贴出来是为了以后方便以后修改。  
第一个操作是关闭匿名，开启被动模式  
第二个操作是开启防火墙的21端口  
第三个操作是修改selinux，开启外网可访问
```
[root@itdragon ~]# vim /etc/vsftpd/vsftpd.conf
anonymous_enable=NO
pasv_min_port=30000
pasv_max_port=31000
[root@itdragon ~]# service vsftpd restart
[root@itdragon ~]# vim /etc/sysconfig/iptables
-A INPUT -p tcp -m multiport --dport 20,21 -m state --state NEW -j ACCEPT
-A INPUT -p tcp -m state --state NEW -m tcp --dport 21 -j ACCEPT
-A INPUT -p tcp --dport 30000:31000 -j ACCEPT
[root@itdragon ~]# service iptables restart
[root@itdragon ~]# getsebool -a | grep ftp
allow_ftpd_anon_write --> off
allow_ftpd_full_access --> off
allow_ftpd_use_cifs --> off
allow_ftpd_use_nfs --> off
ftp_home_dir --> off
ftpd_connect_db --> off
ftpd_use_fusefs --> off
ftpd_use_passive_mode --> off
httpd_enable_ftp_server --> off
tftp_anon_write --> off
tftp_use_cifs --> off
tftp_use_nfs --> off
[root@itdragon ~]# setsebool -P allow_ftpd_full_access on
[root@itdragon ~]# setsebool -P ftp_home_dir on
```
参考博客：  
http://blog.csdn.net/jiftlixu/article/details/54344050  
https://jingyan.baidu.com/article/ea24bc3984a3b2da63b33154.html  
遗憾的是，我按照上面的方法，没有连接成功，最后一刀切，直接关闭了防火墙。
临时关闭防火墙不过瘾，直接永久关闭防火墙
```
[root@itdragon modprobe.d]# service iptables stop
iptables: Setting chains to policy ACCEPT: filter          [  OK  ]
iptables: Flushing firewall rules:                         [  OK  ]
iptables: Unloading modules:                               [  OK  ]
[root@itdragon modprobe.d]# chkconfig iptables off
```
### 上传失败
在nginx 安装目录下创建了一个images文件夹，通过FileZilla上传图片提示错误
```
响应:553 Could not create file.
错误:	严重文件传输错误
```
解决方法如下
```
[root@itdragon html]# mkdir images
[root@itdragon html]# chmod -R 777 images
[root@itdragon html]# ll
total 12
-rw-r--r--. 1 root root  537 Nov 18 10:53 50x.html
drwxrwxrwx. 2 root root 4096 Nov 18 10:55 images
-rw-r--r--. 1 root root  612 Nov 18 10:53 index.html
[root@itdragon html]# vim /etc/vsftpd/vsftpd.conf 
local_root=/var/ftp
[root@itdragon html]# service vsftpd restart
```
参考博客：http://blog.chinaunix.net/uid-20680669-id-3142726.html

## 注意点
- 网络要能ping通
- 防火墙的问题要处理好
- 不能创建文件问题

最后，感谢大家阅读。有什么建议请赐教！！！
