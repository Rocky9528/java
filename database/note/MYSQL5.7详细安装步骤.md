

# MYSQL5.7详细安装步骤：

### 0、更换yum源

1、打开 mirrors.aliyun.com，选择centos的系统，点击帮助

2、执行命令：yum install wget -y

3、先备份文件

```
mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo.backup
```

4、执行更换yum源的命令

```
wget -O /etc/yum.repos.d/CentOS-Base.repo https://mirrors.aliyun.com/repo/Centos-6.repo
```

 **centos6（centos6官方源已下线，建议切换centos-vault源）** 

```
wget -O /etc/yum.repos.d/CentOS-Base.repo https://mirrors.aliyun.com/repo/Centos-vault-6.10.repo
或者
curl -o /etc/yum.repos.d/CentOS-Base.repo https://mirrors.aliyun.com/repo/Centos-vault-6.10.repo
```

如果还是报错  执行vi epel.repo 将项[xxx]（注意：中括号的项有好几个，注意都要改）中的enabled=1改为enabled=0

5、更新本地缓存

yum clean all

yum makecache

### 1、查看系统中是否自带安装mysql

```shell
yum list installed | grep mysql
```

![1570541665646](F:\Rocky\JAVA\Organ\msb\GitHubFileClone\java\database\image\1570541665646.png)

### 2、删除系统自带的mysql及其依赖（防止冲突）

```shell
yum -y remove mysql-libs.x86_64
```

![1570541838485](F:\Rocky\JAVA\Organ\msb\GitHubFileClone\java\database\image\1570541838485.png)

### 3、安装wget命令

```
yum install wget -y 
```

![1570541946471](F:\Rocky\JAVA\Organ\msb\GitHubFileClone\java\database\image\1570541946471.png)

### 4、给CentOS添加rpm源，并且选择较新的源

```
wget dev.mysql.com/get/mysql-community-release-el6-5.noarch.rpm
```

![1570542045332](F:\Rocky\JAVA\Organ\msb\GitHubFileClone\java\database\image\1570542045332.png)

### 5、安装下载好的rpm文件

```
 yum install mysql-community-release-el6-5.noarch.rpm -y
```

![1570542254949](F:\Rocky\JAVA\Organ\msb\GitHubFileClone\java\database\image\1570542254949.png)	

### 6、安装成功之后，会在/etc/yum.repos.d/文件夹下增加两个文件

![1570542341604](F:\Rocky\JAVA\Organ\msb\GitHubFileClone\java\database\image\1570542341604.png)

### 7、修改mysql-community.repo文件

原文件：

![1570542415955](F:\Rocky\JAVA\Organ\msb\GitHubFileClone\java\database\image\1570542415955.png)

修改之后：

![1570542471948](F:\Rocky\JAVA\Organ\msb\GitHubFileClone\java\database\image\1570542471948.png)

### 8、使用yum安装mysql

```
yum install mysql-community-server -y
```

![1570542688796](F:\Rocky\JAVA\Organ\msb\GitHubFileClone\java\database\image\1570542688796.png)

如果没有密钥可能会报错无法安装，

warning: rpmts_HdrFromFdno: Header V4 RSA/SHA256 Signature, key ID 3a79bd29: NOKEY
Retrieving key from file:/etc/pki/rpm-gpg/RPM-GPG-KEY-mysql

查看是否有密钥文件 cat /etc/pki/rpm-gpg/RPM-GPG-KEY-mysql

 没有的话需要去对应源官网下载密钥文件。下载完后上传到Linux/etc/pki/rpm-gpg/中  然后用命令导入公钥文件后可解决报错问题： rpm --import /etc/pki/rpm-gpg/密钥文件名

 也可以查看所有已经导入的gpg格式的公钥，成功的话就可以看到报错所缺少的keyID： 

 rpm -qa gpg-pubkey*   显示linux系统已经导入的所有gpg公钥

rpm支持URL加载:

rpm --import https://repo.mysql.com/RPM-GPG-KEY-mysql-2022   ---这个对应keyid:3a79bd29

也可以先下载密钥文件，再放到 /etc/pki/rpm-gpg/目录中，unix格式。再导入 。报错：key 1 not an armored public key. 因为密钥文件末尾没有换行导致的

报错信息：

从 file:///etc/pki/rpm-gpg/RPM-GPG-KEY-mysql 检索密钥 源 "MySQL 5.7 Community Server" 的 GPG 密钥已安装，但是不适用于此软件包。请检查源的公钥 URL 是否配置正确。 失败的软件包是：mysql-community-server-5.7.37-1.el7.x86_64 GPG 密钥配置为：file:///etc/pki/rpm-gpg/RPM-GPG-KEY-mysql

原因：

GPG验证不通过，即linux系统中没有导入软件包对应的公钥，签名验证失败，不允许安装，找到当前软件包对应的key并导入即可。
也可以 尝试yum源配置不要进行gpg检测,设置gpgcheck=0

如果未指定安装目录，则默认的mysql日志在：/var/log/mysqld.log中

启动初始化失败原因之一： 进入/var/lib/mysql/   删除目录所有文件  rm -rf ./*  再次启动mysqld服务

### 9、启动mysql服务并设置开机启动

```shell
#启动之前需要生成临时密码，需要用到证书，可能证书过期，需要进行更新操作  
yum update -y
#启动mysql服务
service mysqld start
如果启动报初始化错误，可以 cd /var/lib/mysql/     rm -rf ./* 然后再次启动服务
#设置mysql开机启动
chkconfig mysqld on
```

### 10、获取mysql的临时密码

```shell
grep "password" /var/log/mysqld.log
```

![1570604493708](F:\Rocky\JAVA\Organ\msb\GitHubFileClone\java\database\image\1570604493708.png)

### 11、使用临时密码登录

```shell
mysql -uroot -p
#输入密码
```

### 12、修改密码

```sql
set global validate_password_policy=0;#判断修改密码时候新密码是否符合当前的策略，不满足报错，不让修改。
set global validate_password_length=1;#密码长度的最小值(
ALTER USER 'root'@'localhost' IDENTIFIED BY '123456';
```

### 13、修改远程访问权限

```sql
grant all privileges on *.* to 'root'@'%' identified by '123456' with grant option;
flush privileges;
```

### 14、设置字符集为utf-8

```shell
修改文件：/etc/my.cnf
#在[mysqld]部分添加：
character-set-server=utf8
#在文件末尾新增[client]段，并在[client]段添加：
default-character-set=utf8
```

远程连接mysql：mysql -uroot -p123456 -h192.168.116.51

查看mysql安装在哪里：

1. 首先需要知道mysql数据库安装在什么位置 

   执行命令 which mysql  

​       提示：/usr/bin/mysql  

2. 查找配置文件位置 

   根据出现的目录执行命令：/usr/bin/mysql --verbose --help|grep -A 1 'Default options'   

​      提示：Default options are read from the following files in the given order:
​      /etc/my.cnf /etc/mysql/my.cnf /usr/etc/my.cnf ~/.my.cnf 

3. 修改文件 

    服务器首先读取的是/etc/my.cnf文件，如果前一个文件不存在则继续读/etc/mysql/my.cnf文件，如果还不存在依次向后查找。 找到配置文件以后就可以根据需要修改配置文件。 



通过rpm方式安装：

1.  查看是否已经安装 Mysql 

    rpm -qa | grep mysql  

   如果存在就卸载： rpm -ev --nodeps mysql-libs  

   2. 上传解压（解压后可删除安装包，节省空间）

   ```undefined
   tar -zxvf mysql-5.7.35-linux-glibc2.12-x86_64.tar.gz
   ```

   3. 先检查是否有mysql用户组和mysql用户,没有就添加有就忽略：

   groups mysql 

    添加用户组和用户 groupadd mysql && useradd -r -g mysql mysql 

   4.创建数据目录并赋予权限

   ​    mkdir -p /data/mysql
   　　chown mysql:mysql -R /data/mysql

   5.修改配置文件  vim /etc/my.cnf （没有就新建）

   [mysqld]
   bind-address=0.0.0.0
   port=3306
   user=mysql
   basedir=/usr/local/mysql
   datadir=/data/mysql
   socket=/tmp/mysql.sock
   log-error=/data/mysql/mysql.err
   pid-file=/data/mysql/mysql.pid
   #character config
   character_set_server=utf8mb4
   symbolic-links=0
   explicit_defaults_for_timestamp=true

   6. 初始化

   解压后的mysql-5.7.35-linux-glibc2.12-x86_64文件移动到/usr/local/mysql （文件夹名称修改为mysql不带版本号信息）

   cd /usr/local/mysql/bin/
   ./mysqld --defaults-file=/etc/my.cnf --basedir=/usr/local/mysql/ --datadir=/data/mysql/ --user=mysql --initialize

   初始化报错：./mysqld: error while loading shared libraries: libnuma.so.1，需要安装libaio-devel及numactl。

   7. 查看初始密码

   cat /data/mysql/mysql.err

   8. 启动mysql

   　　cp /usr/local/mysql/support-files/mysql.server /etc/init.d/mysql
   　　service mysql start

   必须进去mysql安装目录的bin目录下才能执行mysql 命令的解决办法：把该目录下的mysql文件复制一份到/usr/local/bin/目录中

   修改密码

   1. 开启免密码登陆 修改my.cnf文件   默认在/etc/my.cnf。

   　vim /etc/my.cnf         在【mysqld】模块下面添加：skip-grant-tables 保存退出。

    2. 重启服务，使配置生效 。   

    service mysql restart

   3. 登陆     /usr/local/mysql/bin/mysql -u root -p   //不输入密码直接敲回车键
   4. 刷新规则允许外部访问
   　　use mysql 　　　　 #选择访问mysql库
   　　update user set host = '%' where user = 'root'; 　　　　 #使root能再任何host访问
   　　FLUSH PRIVILEGES; 　　 　　 #刷新 

   5.修改密码

   ALTER USER "root"@"%" IDENTIFIED  BY "1234";

   FLUSH PRIVILEGES; 　　 　　 #刷新 

    6. 退出   quit     

      把/etc/my.cnf免密删掉。

       重启服务    service mysql restart

   7. 登陆   /usr/local/mysql/bin/mysql -u root -p   //输入刚修改的密码1234敲回车键

   用户权限之创建新用户并给授权指定的数据库权限
   创建mysql新用户
   CREATE USER 'test'@'%' IDENTIFIED BY '123';
   备注上面@后的命令解释

   '%' - 所有情况都能访问
   ‘localhost’ - 本机才能访问
   ’111.222.33.44‘ - 指定 ip 才能访问
   3.给用户授予权限
   grant all on 数据库名.数据库表 to 用户名@'%'  identified by "密码";
   备注

   all 可以替换为 select,delete,update,create,drop
   数据库名 所有的 用*
   数据库表 所有的 用*


