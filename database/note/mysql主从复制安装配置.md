# mysql主从复制安装配置

### 1、基础设置准备

```shell
#操作系统：
centos6.5
#mysql版本：
5.7
#两台虚拟机：
node1:192.168.85.111（主）
node2:192.168.85.112（从）
```

### 2、安装mysql数据库

```shell
#详细安装和卸载的步骤参考对应的文档
```

### 3、在两台数据库中分别创建数据库

```sql
status  查看mysql状态
--注意两台必须全部执行
create database msb;
```

### 4、在主（node1）服务器进行如下配置：

```shell
#修改配置文件，执行以下命令打开mysql配置文件
vi /etc/my.cnf
#在mysqld模块中添加如下配置信息
log-bin=master-bin #二进制文件名称
binlog-format=ROW  #二进制日志格式，有row、statement、mixed三种格式，row指的是把改变的内容复制过去，而不是把命令在从服务器上执行一遍，statement指的是在主服务器上执行的SQL语句，在从服务器上执行同样的语句。MySQL默认采用基于语句的复制，效率比较高。mixed指的是默认采用基于语句的复制，一旦发现基于语句的无法精确的复制时，就会采用基于行的复制。
server-id=1		   #要求各个服务器的id必须不一样
binlog-do-db=msb   #同步的数据库名称
binlog-do-db=msb2   #同步的数据库名称
```

### 5、在主服务器中：配置从服务器登录主服务器的账号授权

```sql
--授权操作
set global validate_password_policy=0;
set global validate_password_length=1;
grant replication slave on *.* to 'root'@'%' identified by '123456';
--刷新权限
flush privileges;
```

### 6、从服务器的配置

```shell
#修改配置文件，执行以下命令打开mysql配置文件
vi /etc/my.cnf
#在mysqld模块中添加如下配置信息
log-bin=master-bin	#二进制文件的名称
binlog-format=ROW	#二进制文件的格式
server-id=2			#服务器的id
```

### 7、重启主服务器的mysqld服务

```shell
#重启mysql服务
service mysqld restart
#登录mysql数据库
mysql -uroot -p
#查看master的状态
show master status；
```

![1570703264912](E:\lian\oracle\typora-user-images\1570703264912.png)

### 8、重启从服务器并进行相关配置

```shell
#重启mysql服务
service mysqld restart
#登录mysql
mysql -uroot -p
#连接主服务器
change master to master_host='192.168.85.11',master_user='root',master_password='123456',master_port=3306,master_log_file='master-bin.000001',master_log_pos=154;
#启动slave
start slave;
#查看slave的状态
show slave status\G(注意没有分号)
#注意：master_log_pos的值不是固定不变的，配置了主从复制重启后，在/var/lib/mysql/目录中会生成对应的binlog文件，如master-bin.000001，这个文件会进行初始化，会占用一些字节数，所以主服务器中该文件的position是多少（通过show master status;查看），那么从服务器该文件的Read_Master_Log_Pos也要是同样的位置（通过show slave status\G查看）
```

### 9、此时可以在主服务器进行相关的数据添加删除工作，在从服务器看相关的状态



互为主从时，即把B库也设置为主库（注意：一般不这么整，而是一主多重,仅主库负责写，其他从库负责读，从库限制不能写，不然从库单独写可能会导致数据与本地中继日志回放数据冲突）。

只需要B中 vi /etc/my.cnf 也加上  binlog-do-db=msb   #同步的数据库名称

A中配置从库登录A的账号授权

--授权操作
set global validate_password_policy=0;
set global validate_password_length=1;
grant replication slave on *.* to 'root'@'%' identified by '123456';
--刷新权限
flush privileges;

A、B重启,
#连接A
change master to master_host='192.168.85.11',master_user='root',master_password='123456',master_port=3306,master_log_file='master-bin.000001',master_log_pos=154;
#启动slave
start slave
#查看slave的状态
show slave status\G(注意没有分号)
#注意：master_log_pos的值不是固定不变的，配置了主从复制重启后，在/var/lib/mysql/目录中会生成对应的binlog文件，如master-bin.000001，这个文件会进行初始化，会占用一些字节数，所以主服务器中该文件的position是多少（通过show master status;查看），那么从服务器该文件的Read_Master_Log_Pos也要是同样的位置（通过show slave status\G查看）



#  删除从库

mysql> stop slave;

Query OK, 0 rows affected (0.19 sec)


mysql> reset slave;

以上如果未清除，用下面
从库删除：
mysql> reset slave all;
Query OK, 0 rows affected (0.04 sec)
mysql> show slave status\G;



