# Linux软件安装的几种方式

**目录**



[一.rpm安装](https://blog.csdn.net/weixin_43880061/article/details/125354573#t0)

[二.yum安装](https://blog.csdn.net/weixin_43880061/article/details/125354573#t1)

[三.编译安装](https://blog.csdn.net/weixin_43880061/article/details/125354573#t2)

[四.二进制安装](https://blog.csdn.net/weixin_43880061/article/details/125354573#t3)

[五.比喻说明](https://blog.csdn.net/weixin_43880061/article/details/125354573#t4)

一.rpm安装
1.rpm介绍

什么是rpm呢？

rpm也就是red hat package manager，是红帽公司出品的软件包管理工具，能进行软件包的安装，卸载，升级，查询

rpm 是centos/redhat系统里软件安装管理的命令，比yum要底层，yum底层就是调用rpm去安装软件的。

rpm包管理机制的系统：
    centos，redhat，opensuse，oracle linux ，fedora等  --》红帽系
    zziplib-0.13.68-8.el8.x86_64.rpm
    enterprise linux 8

    x86_64 -->64
    i686 -->32
yelp-libs-3.28.1-3.el8.i686.rpm
yelp-libs-3.28.1-3.el8.x86_64.rpm
deb包管理机制的系统：
    debian，Ubuntu
     zfsutils-linux_0.8.3-1ubuntu12_amd64.deb

2.rpm包

rpm包其实就是redhat系列的Linux系统里的软件包，是别人制作好的可以直接安装使用的软件包，类似于windows里的.exe。rpm包的来源是自己或者公司，第三方

————————————————
版权声明：本文为CSDN博主「万物皆可爱33」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/weixin_43880061/article/details/125354573

![img](F:\Rocky\JAVA\GitHubFileClone\java\database\image\233cc4f260d44f28afe1949ce86cb8b0.png)

3.source rpm

Source RPM  : openssh-8.0p1-5.el8.src.rpm  -->里面有源码的rpm包 --》不能直接安装使用，安装的过程其实是解压src.rpm包得到软件的源代码
包含了源码的rpm包

4.哪里可以下载rpm包？

        1.镜像文件，centos或者redhat公司制作，使用方法如下：
    
                挂载镜像：
                    1.请将镜像文件放入光驱
                    2.使用mount命令挂载
                           mount /dev/cdrom  /mnt
                    3.使用
    
        2.官方网站
————————————————
版权声明：本文为CSDN博主「万物皆可爱33」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/weixin_43880061/article/details/125354573

![62217e42b2c74d3db6b92ab35a644069](F:\Rocky\JAVA\GitHubFileClone\java\database\image\62217e42b2c74d3db6b92ab35a644069.png).

3.第三方平台：Rpmfind mirror

        4.自己制作

5.使用rpm的缺点：

不能自动解决软件包之间的依赖关系，缺乏自动化以及智能化

6.rpm命令详解：

选项：

查询类：

-q        --query        查询

-qa        查询系统里已经安装了的所有的软件

-qi        查询命令的详细信息

-ql        查询已经安装的软件的路径，也就是安装到了哪里

-qc        查询配置文件的路径

-qf        查询已经安装的命令或者是文件是通过哪个软件包安装过来的

-qd        显示文档文件列表

-qpl        查询没有安装的一个软件包，它会安装到哪里

-qpi        查询没有安装的一个软件包，它的信息

安装类：

-i        -- install        安装

-ivh        可以查看到安装的进度
————————————————
版权声明：本文为CSDN博主「万物皆可爱33」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/weixin_43880061/article/details/125354573

[root@sc-lvm Packages]# rpm -ivh tree-1.7.0-15.el8.x86_64.rpm 
Verifying...                          ################################# [100%]
准备中...                          ################################# [100%]
正在升级/安装...
   1:tree-1.7.0-15.el8                ################################# [100%]
[root@sc-lvm Packages]# 

--reinstall     重新安装

[root@huoyrz Packages]# rpm --reinstall tree-1.6.0-10.el7.x86_64.rpm

卸载类：

-e     erase     卸载

[root@sc-lvm Packages]# rpm -e tree
升级类：

-U        如果软件没有安装，升级操作会自动帮助安装，需要你提供高版本的软件包，不会自动去帮助到哪里下载

[root@sc-lvm Packages]# rpm -U tree-1.7.0-15.el8.x86_64.rpm 
————————————————
版权声明：本文为CSDN博主「万物皆可爱33」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/weixin_43880061/article/details/125354573

二.yum安装
1.什么是yum

yum是基于rpm但更胜于rpm的软件管理工具

yum的优点：
    更方便的管理rpm软件包
    自动解决rpm包的依赖关系，可以配置多个资源仓库

yum是python编写的一个软件管理的工具

安装一个软件包，可以得到很多的命令，也有可能得到一个命令
rpm/yum 软件安装的过程，本质上就是解压文件，然后拷贝文件到某些目录下的过程

问题：
    1.yum是如何解决依赖关系的？
    2.yum怎么样知道去哪里下载软件？
————————————————
版权声明：本文为CSDN博主「万物皆可爱33」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/weixin_43880061/article/details/125354573

![e6832516e1cb49b997bc48d24fdb9567](F:\Rocky\JAVA\GitHubFileClone\java\database\image\e6832516e1cb49b997bc48d24fdb9567.png)

2.仓库文件目录

2.1仓库配置文件目录：/etc/yum.repos.d/

Yum Repository yum仓库

2.2仓库文件：都是以.repo结尾的，前面叫什么名字不重要，只是识别

repo文件详解：

[root@sc-lvm /]# cd /etc/yum.repos.d/   是yum会到这个目录下查找repo结尾的文件
[root@sc-lvm yum.repos.d]# ls
CentOS-Linux-AppStream.repo          CentOS-Linux-Debuginfo.repo  CentOS-Linux-FastTrack.repo         CentOS-Linux-Plus.repo
CentOS-Linux-BaseOS.repo             CentOS-Linux-Devel.repo      CentOS-Linux-HighAvailability.repo  CentOS-Linux-PowerTools.repo
CentOS-Linux-ContinuousRelease.repo  CentOS-Linux-Extras.repo     CentOS-Linux-Media.repo             CentOS-Linux-Sources.repo
[root@sc-lvm yum.repos.d]# vim grafana.repo
[grafana]   --》yum源的名字，仓库的名字
name=grafana  sanchaung  --》对这个源的描述
baseurl=https://packages.grafana.com/enterprise/rpm   --》仓库的具体位置  源的位置
repo_gpgcheck=1    --》gpg 是一种加密技术 repo_gpgcheck 对源进行检查，是否是真正的grafana的仓库
enabled=1    表示这个源可以使用   1 启用  0 禁用
gpgcheck=1  对这个源里的下载的所有的软件进行gpg校验--》检验所有从这个源下载的软件，防止是假的 --》防伪
gpgkey=https://packages.grafana.com/gpg.key  --》具体的加密和解密使用的key的位置
sslverify=1
sslcacert=/etc/pki/tls/certs/ca-bundle.crt

#yum源： 软件的仓库

#问渠那得清如许，为有源头活水来

[root@sc-lvm yum.repos.d]# vim CentOS-Linux-BaseOS.repo 

[baseos]
name=CentOS Linux $releasever - BaseOS
mirrorlist=http://mirrorlist.centos.org/?release=$releasever&arch=$basearch&repo=BaseOS&infra=$infra  --》查询获取离你的机器最近的镜像的位置
#baseurl=http://mirror.centos.org/$contentdir/$releasever/BaseOS/$basearch/os/
gpgcheck=1
enabled=1
gpgkey=file:///etc/pki/rpm-gpg/RPM-GPG-KEY-centosofficial   --》gppkey的位置
===
2.3如何获得仓库文件：

        1.自己vim一个        grafana
    
        2.下载rpm包安装
    
        3.下载repo文件        docker

2.4阿里云的仓库文件：centos-7.9.2009-os-x86_64-Packages安装包下载_开源镜像站-阿里云

3.缓存目录：/var/cache/yum/x86_64/7

4.元数据：

元数据就是解决依赖关系的数据：安装一条命令有哪些软件，每个软件依赖哪些软件等

/var/cache/dnf  --》存放的是各个源解决依赖关系和软件信息的元数据

[root@sc-lvm dnf]# yum clean all  -->清除缓存的元数据
5.源：

        1.官方源：包括centos的官方，或者是某个软件的官方（例如nginx)
    
        2.第三方：epel-release 
    
         yum install epel-release，安装好后会存放在 /etc/yum.repos.d/epel.repo
    
        3.可以自己制作源

6.如何禁用某个源：

1.enableed=0

2.删除repo文件

3.修改repo文件的后缀名

4. sudo yum-config-manager --enable docker-ce-nightly
 yum-config-manager --disable docker-ce-nightly

7.命令详解：

查询类：

yum search

yum list        查看哪些软件安装了，哪些软件没安装，相当于prm -qa

[root@localhost base]# yum list|more
已加载插件：fastestmirror
Loading mirror speeds from cached hostfile
 * base: mirrors.aliyun.com
 * extras: mirrors.aliyun.com
 * updates: mirrors.aliyun.com
已安装的软件包
NetworkManager.x86_64                     1:1.18.8-1.el7               @anaconda
NetworkManager-libnm.x86_64               1:1.18.8-1.el7               @anaconda
NetworkManager-team.x86_64                1:1.18.8-1.el7               @anaconda
NetworkManager-tui.x86_64                 1:1.18.8-1.el7               @anaconda
acl.x86_64                                2.2.51-15.el7                @anaconda
aic94xx-firmware.noarch                   30-6.el7                     @anaconda
[root@localhost base]# yum list|grep tree
tree.x86_64                               1.6.0-10.el7                 @base
gomtree.x86_64                            0.5.0-0.2.git16da0f8.el7     extras
maven-dependency-tree.noarch              2.0-7.el7                    base
maven-dependency-tree-javadoc.noarch      2.0-7.el7                    base
ostree.x86_64                             2019.1-2.el7                 extras
ostree-devel.x86_64                       2019.1-2.el7                 extras
ostree-fuse.x86_64                        2019.1-2.el7                 extras
ostree-grub2.x86_64                       2019.1-2.el7                 extras
subscription-manager-plugin-ostree.x86_64 1.24.51-1.el7.centos         updates
texlive-pst-tree.noarch                   2:svn24142.1.12-45.el7       base
texlive-pst-tree-doc.noarch               2:svn24142.1.12-45.el7       base
[root@localhost base]#

有@符号的，表示是已经安装，并且@符号后面接的字符串，表示使用那个源安装的
没有@符号，表示软件没有安装，可以通过那个源安装
yum repolist        查看哪些源可以使用

yum provides         查看某个命令是通过哪个软件包安装过来的（本机没有安装的命令也可以查询得到）

[root@sc-lvm mnt]# yum provides iostat
上次元数据过期检查：0:37:14 前，执行于 2021年09月04日 星期六 11时14分13秒。
sysstat-11.7.3-5.el8.x86_64 : Collection of performance monitoring tools for Linux
仓库        ：@System
匹配来源：
文件名    ：/usr/bin/iostat

sysstat-11.7.3-5.el8.x86_64 : Collection of performance monitoring tools for Linux
仓库        ：appstream
匹配来源：
文件名    ：/usr/bin/iostat

[root@sc-lvm mnt]# which iostat
/usr/bin/iostat
[root@sc-lvm mnt]# rpm -qf /usr/bin/iostat 
sysstat-11.7.3-5.el8.x86_64
[root@sc-lvm mnt]# 
安装类：

yum install

yum reinstall

卸载类：

yum remove

升级类：

yum update        默认会升级所有的软件，安装好系统后马上升级所有的软件

清除缓存类：

yum clean all        只要我们安装软件，就会自动下载相应源的元数据

其他：

yum-utils --》yum-config-manager  --》可以帮忙我们去某个网站下载repo文件，然后存放到/etc/yum.repos.d目录

 sudo yum install -y yum-utils
 sudo yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo

yum repolist        查看所有的仓库

[root@sc-lvm yum.repos.d]# yum repolist
仓库 id                                                 仓库名称
appstream                                               CentOS Linux 8 - AppStream
baseos                                                  CentOS Linux 8 - BaseOS
docker-ce-stable                                        Docker CE Stable - x86_64
epel                                                    Extra Packages for Enterprise Linux 8 - x86_64
epel-modular                                            Extra Packages for Enterprise Linux Modular 8 - x86_64
extras                                                  CentOS Linux 8 - Extras
mysql-connectors-community                              MySQL Connectors Community
mysql-tools-community                                   MySQL Tools Community
mysql80-community                                       MySQL 8.0 Community Server
[root@sc-lvm yum.repos.d]# 
三.编译安装
1.为什么需要编译安装

编译安装可以根据需求来定制软件

        可以指定安装路径
    
        可以指定某些模块的功能开启或者禁用
    
        为了业务的最优配置

2.编译安装的优缺点

优点：可以自定义，非常的安全，一般安装的都是最新版本的软件

缺点：速度稍微慢点，步骤多

3.举例说明----编译安装nginx

步骤如下：

1.下载，然后解压

2.编译前的配置：./configure  --prefix=/usr/local/nginx

收集安装的参数：

         --with-http_ssl_module        启用
    
        --without-pcre2        禁用

编译前的配置，生成Makefile的文件，指导make工作的配置文件

3.make

将c语言编写的代码文件编译成二进制文件

make -j 2        启用2个进程去编译安装

4.make install        将编译好的二进制文件拷贝指定的安装目录下

5.启动（如何知道nginx是否启动和关闭，请查看我nginx详解的那篇博客）https://blog.csdn.net/weixin_43880061/article/details/124148864?spm=1001.2014.3001.5502

4.一件部署安装nginx的脚本

[root@localhost 0610]# cat onekey_install_nginx_2.sh
#!/bin/bash

#install nginx
#version:1.21.6
#!/bin/bash

#install nginx
#version:1.21.6
#author: cali
#mail: 695811769@qq.com
#company: sanchuang
#time:  2022-06-10

#create user chenyulin
useradd -s /sbin/nologin chenyulin

#download nginx
mkdir -p /lianxi/chenyulin
cd /lianxi/chenyulin
curl -O https://nginx.org/download/nginx-1.21.6.tar.gz

#uncompress nginx source
tar xf nginx-1.21.6.tar.gz
cd nginx-1.21.6

#resolution dependency
yum  install pcre2 pcre2-devel zlib zlib-devel openssl  openssl-devel -y

#configure
./configure --prefix=/usr/local/chenyulin  --user=chenyulin --with-threads  --with-http_ssl_module --with-http_v2_module

#make
make -j 2
#install
make install
#path variable
PATH=/usr/local/chenyulin/sbin/:$PATH
echo 'PATH=/usr/local/chenyulin/sbin/:$PATH'  >>/root/.bashrc

#start nginx

if  pidof nginx &>/dev/null ;then
	echo "nginx is running"
	#kill old nginx process
	killall -9 nginx
	#start new nginx process
	nginx
else
	nginx
fi

 

[root@localhost 0610]#
四.二进制安装
到官方网站下载源码包解压后，直接使用

里面的程序都是一件编译好的可执行的二进制文件

非常的快捷方便

例如安装prometheus和MySQL

五.比喻说明
以上几种安装方式的比喻说明

1.rpm和yum：相当于到专卖店买衣服，款式，颜色，面料等都不可以修改了，喜欢，直接带走。速度快，安装路径都是固定了的，不能修改，部分功能也许没有开启

2.源码编译安装：到裁缝店去定制衣服，颜色，面料，款式都可以自己决定，可以选择定制。时间长，好处功能完全可以自己定制

3.二进制安装：解压后就可以使用，速度快

六.小练习
1.卸载tree,nfs-utils,vsftpd
[root@huoyrz Packages]# rpm -e tree   nfs-utils vsftpd
2.卸载vim编辑器
[root@huoyrz Packages]# rpm -e vim
错误：未安装软件包 vim
[root@huoyrz Packages]# rpm -qa|grep vim
vim-filesystem-7.4.629-8.el7_9.x86_64
vim-common-7.4.629-8.el7_9.x86_64
vim-enhanced-7.4.629-8.el7_9.x86_64
vim-minimal-7.4.629-7.el7.x86_64
[root@huoyrz Packages]# which vim
/bin/vim
[root@huoyrz Packages]# rpm -qf /bin/vim
vim-enhanced-7.4.629-8.el7_9.x86_64
[root@huoyrz Packages]#
3.到镜像文件里安装vsftpd，squid，nfs-utils

[root@huoyrz Packages]# rpm -vih vsftpd-3.0.2-28.el7.x86_64.rpm squid-3.5.20-15.el7_8.1.x86_64.rpm nfs-utils-1.3.0-0.68.el7.x86_64.rpm squid-migration-script-3.5.20-15.el7_8.1.x86_64.rpm perl-Digest-MD5-2.52-3.el7.x86_64.rpm libecap-1.0.0-1.el7.x86_64.rpm perl-Digest-1.17-245.el7.noarch.rpm

4.安装libstdc++-devel，python-devel相关的包
	 libstdc++-devel  -->development  developer 开发者
	 python-devel-2.7.5-89.el7.x86_64.rpm  --》为别的程序调用python的接口，提供这些接口的开发包
	 苹果开发者大会： IOS -->APP -->给开发者提供了SDK（软件开发接口程序）
	 软件开发工具包（Software Development Kit）

5.查询gcc软件安装到哪里了？
[root@huoyrz Packages]# rpm -ql gcc
[root@huoyrz Packages]# rpm -qpl gcc-4.8.5-44.el7.x86_64.rpm

6.查询ip命令是通过那个软件安装过来的？
[root@huoyrz Packages]# which ip
/sbin/ip
[root@huoyrz Packages]# rpm  -qf  /sbin/ip
iproute-4.11.0-30.el7.x86_64
[root@huoyrz Packages]#

7.统计系统里安装了多少个软件？
[root@huoyrz Packages]# rpm  -qa|wc -l
485
[root@huoyrz Packages]#
————————————————
版权声明：本文为CSDN博主「万物皆可爱33」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/weixin_43880061/article/details/125354573