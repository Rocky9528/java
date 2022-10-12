bin目录：binary 二进制，该目录中存储的是一些二进制文件，文件都可以被运行
dev目录：该目录中主要存放的是外接设备，例如盘、其他的光盘等。在其中的外接设备是不能直接被使用的，需要挂载（类似windows下的分配盘符）
etc目录：该目录主要存储一些配置文件
home目录：表示除了root用户以外，其他用户的家目录（类似windows下的user用户目录）
proc目录：process进程，该目录中存储的是linux运行时候的进程（一般不要打开，容易卡屏）
root目录：该目录是root用户自己的家目录
sbin目录：super binary，该目录也是存储一些可以被执行的二进制文件，但是必须得有super权限的用户才能执行
tmp目录：temporary临时的，当系统运行时产生的临时文件会在这个目录下
usr目录：存放的是用户自己安装的软件（类似windows下的program files）
var目录：存放的程序/系统的日志文件的目录
mnt目录：当外接设备需要挂载的时候，就需要挂载到mnt目录下

一、登录与注销
1)	sudo useradd lilei  //添加用户 (不能被立即使用，需设置密码 sudo passwd lilei)  
2)	sudo adduser lilei  //添加用户
3)	login  //登录或切换用户
4)	logout //注销用户（命令行）  exit(shell-退出控制台)
5)	shutdown -h 10  //10分钟后自动关机	shutdown -c  //取消
6)	halt(root用户)  //关闭所有进程后自动关机
7)	poweroff //同上
8)	shutdown -r 10 //十分钟后自动重启
9)	init 6  //重启 （0-停机，1-单用户，2-多用户，3-完全多用户，4-图形化，5-安全模式，6-重启）
10)	reboot  //重启
二、目录与文件
1)	pwd   //显示当前工作目录
2)	mkdir mydir  //创建工作目录
3)	cd mydir  //更改工作目录
4)	rmdir mydir //删除工作目录
5)	touch myfile  //创建文件
6)	mv myfile mydir  //移动目录或文件
7)	cp myfile myfir  //复制目录或文件
8)	rm -rf mydir  //删除目录或文件
9)	ls -l myfile  //查看文件最后被编辑时间
10)	ls -lu myfile //查看文件最后被访问时间
11)	touch -at 01011212 myfile  //修改文件最后被访问时间
12)	ls //列出所有文件和目录
13)	ls -a //查看所有文件
14)	ls -i //显示文件索引节点号
15)	ls -l //详细显示
16)	ls -m //以逗号分隔
17)	sudo apt-get install tree 
18)	tree -l//以树状图列出目录内容
19)	tree -a //所有
20)	tree -i //不以阶梯状
21)	tree -s  //列出文件或目录大小
22)	tree -t  //按更改时间
23)	file -b myfile  //显示目录或文件的详细信息
24)	stat myfile  //同上
三、文件内容显示
1)	cat > myfile  //创建文件并编辑内容（ctrl+D结束编辑）
2)	cat -n myfile  //查看文件
3)	chmod [u/g/o/a][+/-/=][r/w/x] myfile  //更改文件权限
u-user,g-group,o-others,a-all  .   +-添加,--删除,=-重置   .
r-read读（4），w-write写（2），x-execute执行（1）
4)	more myfile  //分页往后显示文件（Space空格）
5)	less myfile  //分页自由显示文件（Page Down / Page Up）
6)	head (-10) myfile  //指定显示文件前若干行（默认前10）
7)	tail (-10) myfile  //指定显示文件后若干行（默认后10）
四、文件内容处理
1)	sort myfile  //对文件内容进行排序
2)	sort -r myfile  //逆序
3)	uniq myfile  //检查文件中的重复内容
4)	grep （-c）‘a’ myfile  //在文件中查找指定内容 (显示行号)
5)	diff myfile01 myfile02  //对不同文件进行比较
6)	diff3 myfile01 myfile02 myfile03  //三个文件
7)	sdiff myfile01 myfile02  //合并
8)	cmp myfile01 myfile02  //通过字节对不同文件进行比较
9)	comm myfile01 myfile02  //对有序文件进行比较
10)	cut -b(-c)(-d) 2(3) myfile  //对文件内容进行剪切
11)	paste myfile02 myfile01 //对文件内容进行粘贴 02-）01
12)	wc （-参数） myfile  //对文件内容进行统计 （c-字符数,w-单词数,l-行数）
五、压缩
1)	zip myfile.zip myfile  //压缩
2)	zip -d myfile.zip myfile  //添加
3)	zip -m myfile.zip myfile  //删除
4)	unzip -o myfile.zip  //解压（覆盖）
5)	unzip -n myfile.zip  //解压（不覆盖）
6)	zipinfo myfile.zip  //列出压缩文件信息
六、获取帮助
1)	man ls  //获取帮助
2)	man -k ls  //不清楚完整名字
3)	whatis ls  //获取帮助
4)	help cd  / cd –help  //获取帮助 -d(简短描述) -s(用法简介)
5)	info who  //获取帮助
七、其他命令
1)	clear  //清楚屏幕信息
2)	echo xx  //显示文本  x=0  echo $x . echo -e \$x . echo $(pwd)
3)	date  //显示日期和时间（+%y 年  +%m 月  +%d日）
4)	cal  //显示当前日期  cal -y
5)	ps  //查看当前进程  -A(所有)  U  lilei (用户lilei)
6)	kill -9 2315  //终止某一进程  
7)	ps -ef | grep Jincheng
8)	pkill Jincheng
9)	killall Jincheng
10)	last  //显示最近登录系统的用户信息-6列
11)	history （10） //显示历史指令-默认1000行
12)	sudo adduser lilei sudo  //给普通用户赋予root权限
13)	sudo usermod -G sudo lilei  //同上
14)	alias l=’ls’  //定义命令别名
15)	unalias l  //删除别名
16)	alias  //列出别名

小技巧：

1.比如修改文件中，但是想查看一个目录中的信息，但是不退出当前文件修改状态

: ! ls /root



解决时间不对问题

yum install -y ntpdate

rm -rf /etc/localtime

cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

ntpdate -u ntp.api.bz



# centos系统启动报告Checking filesystems 失败解决方法

 系统启动报告Checking filesystems 失败,系统不能启动的真正原因是 /etc/fstab给写错了；

此时,根据系统提示,输入root密码进入repair filesystem模式,尝试修改 /etc/fstab 发现系统是read-only模式,运行以下命令：

mount -o remount,rw /	　（以可读写方式重新挂载文件系统）

如果在修改/etc/fstab文件后，运行mount -a命令验证一下配置是否正确，则可以避免此类问题。

问题的修复方法不仅适用于以上所描述的问题，同样可以处理由于错误修改配置导致系统无法启动的其他问题（比如修改时间改为了很早以前，启动系统时检查时间比最后一次登录的时间还早，就会把文件设定为只读，启动失败报错）。

如：/etc/passwd文件出错，/etc/shadow文件出错等...

问题的解决过程中，重新mount /是比较关键的一步（mount -o remount,rw /）。如果没有此步操作，则文件系统处于只读状态，导致不能修改配置文件并保存。



### linux怎么查看安装了哪些软件

**1、rpm包安装的，可以用rpm -qa看到**

如果要查找某软件包是否安装，用 rpm -qa | grep “软件或者包的名字”。

```
[root@hexuweb102 ~] rpm -qa | grep ruby
```

**2、以deb包安装的，可以用dpkg -l能看到**

如果是查找指定软件包，用dpkg -l | grep “软件或者包的名字”；

```
[root@hexuweb102~]dpkg-l|grepruby
```

**3、yum方法安装的，可以用yum list installed查找**

如果是查找指定包，命令后加 | grep “软件名或者包名”；

```
[root@hexuweb102 ~] yum list installed | grep ruby
```

如果是以源码包自己编译安装的，例如.tar.gz或者tar.bz2形式的，这个只能看可执行文件是否存在了，

其中rpm yum 是Redhat系linux的软件包管理命令，dpkg是debian系列的软件包管理命令







# linux 卸载软件三种方式

1.我们来卸载用[yum](https://so.csdn.net/so/search?q=yum&spm=1001.2101.3001.7020)安装的软件：yum remove 软件名字；

2.如果是用rpm包安装的软件呢，则使用如图命令进行卸载；
rpm -e 软件名；

3.如果是用tar包安装的软件呢，则使用make uninstall 软件名称来卸载，直接删除也可以的；



查看某个端口的进程信息

netstat -unlpt |grep java

或netstat -unlpt |grep 端口

kill -9 2315  //终止某一进程 

