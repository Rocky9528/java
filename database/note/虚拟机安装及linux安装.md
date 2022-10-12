# 虚拟机安装及linux安装\配置、注意事项

### 1、虚拟机软件傻瓜式安装

2.安装虚拟机

新建虚拟机->自定义->稍后安装操作系统->Linux 版本 CentOS 6 64位->虚拟机名称及地址->配置CD/DVD 的ISO映像文件地址->完成

启动虚拟机开始安装Linux系统，



![1662267752346](C:\Users\Administrator\Desktop\1662267752346.png)

![1662267786071](F:\Rocky\JAVA\Organ\msb\GitHubFileClone\java\database\image\1662267786071.png)

![1662267828770](F:\Rocky\JAVA\Organ\msb\GitHubFileClone\java\database\image\1662267828770.png)

![1662267862629](F:\Rocky\JAVA\Organ\msb\GitHubFileClone\java\database\image\1662267862629.png)

![1662267887363](F:\Rocky\JAVA\Organ\msb\GitHubFileClone\java\database\image\1662267887363.png)

![1662267921663](F:\Rocky\JAVA\Organ\msb\GitHubFileClone\java\database\image\1662267921663.png)

![1662267956426](F:\Rocky\JAVA\Organ\msb\GitHubFileClone\java\database\image\1662267956426.png)

![1662267988467](F:\Rocky\JAVA\Organ\msb\GitHubFileClone\java\database\image\1662267988467.png)

安装完成重启linux系统

3.配置网络

进入目录   cd /etc/sysconfig/network-scripts/ 

打开文件   vi ifcfg-eth0   修改文件（按a） 开始修改  退出并保存文件（按ESC 再输入:wq 再回车)

service network restart

![1662277753674](F:\Rocky\JAVA\Organ\msb\GitHubFileClone\java\database\image\1662277753674.png)

![1662268190670](F:\Rocky\JAVA\Organ\msb\GitHubFileClone\java\database\image\1662268190670.png)

4.关闭防火墙

service iptables stop

5.设置防火墙开机不启动

chkconfig iptables off

6.备份快照之前的其他操作：

6-1：yum的资源库源站点配置手工修改：

https://mirrors.aliyun.com/repo/Centos-vault-6.10.repo 下载下来，替换 /etc/yum.repos.d/CentOS-Base.repo

6-2：删除文件

cd /etc/udev/rules.d/

rm 70-persistent-net.rules 一个物理地址的文件    最后一行，设备是eth1,  如果不删除该文件，当前虚拟对应的mac地址就会是虚拟机网卡的mac地址  会报错 Device eth0 does not seem to be present,delaying initialization

poweroff 关掉电脑

注意：删掉70-persistent-net.rules文件后直接poweroff，立刻备份快照。因为如果重新启动过该虚拟机，则会自动新生成一个70-persistent-net.rules文件，这时再关机备份快照的话，快照中就有了一个该文件，克隆新虚拟机时，新机器就不能使用eth0了(会自动变为eth1)，mac地址就变了，每次克隆新虚拟机时都要手工删一次该文件

快照管理->拍摄快照 命名为base

管理->克隆->从现有快照->创建连接克隆->命名->完成

新的虚拟机改一下对应的ip地址

cd /etc/sysconfig/network-scripts/

vi ifcfg-eth0 

还要修改虚拟机名称 cd /etc/sysconfig

vi network   中HOSTNAME改为新虚拟机名 nodexx

service network restart

ping ...

7. ## 通过xshell终端工具上传  

   https://blog.csdn.net/weixin_45965089/article/details/122104677

   1. xshell  

      yum -y install lrzsz

   ​      安装成功之后输入命令 rz 就可以上传文件了
   2.使用第三方工具filezilla传输
   3.通过WinSCP工具上传

查看os版本命令： cat /etc/os-release 



安装编译环境()

如果不安装编译环境，相关安装软件会报错，比如安装vmware tools工具时： ./vmware-install.pl 报错

安装编译环境命令：yum -y install perl gcc make kernel-headers kernel-devel

