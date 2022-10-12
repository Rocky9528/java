mysql主从不同步，重做主从，完全同步

 1.先进入主库，进行锁表，防止数据写入 

开个会话使用命令：mysql> flush tables with read lock;

 注意：该处是锁定为只读状态，语句不区分大小写 

 2.进行数据备份  

\#把数据备份到dbname.bak.sql文件

[root@server01 mysql]#mysqldump -uroot -p dbname>/tmp/dbname.bak.sql;

还原阶段可能报错 ERROR 1840 (HY000) at line 24: @@GLOBAL.GTID_PURGED can only be set when @@GLOBAL.GTID_EXECUTED is empty

原因是当前GTID_EXECUTED参数已经有值

show global variables like '%GTID%';解决办法：

在dump导出时，添加--set-gtid-purged=off参数，避免将gtid信息导出：

 mysqldump dbname -uroot -p --set-gtid-purged=off > /tmp/dbname.bak.sql

这里注意一点：数据库备份一定要定期进行，可以用shell脚本，都比较方便，确保数据万无一失

 3.查看master 状态 

 mysql> show master status; 

 4.把mysql备份文件传到从库机器，进行数据恢复 

\#使用scp命令

[root@node01 ~]# scp /tmp/dbname.bak.sql root@192.168.116.52:/tmp/

5.停止从库的状态

mysql> stop slave;

6.然后到从库执行mysql命令，导入数据备份

mysql> source /tmp/dbname.bak.sql

7.设置从库同步，注意该处的同步点，就是主库show master status信息里的| File| Position两项

change master to master_host='192.168.116.51',master_user='root',master_password='123456',master_port=3306,master_log_file='master-bin.000002',master_log_pos=977;

8.重新开启从同步

mysql> start slave;

9.查看同步状态

mysql> show slave status\G  查看：

Slave_IO_Running: Yes

Slave_SQL_Running: Yes

记得解锁主库： unlock tables 

好了，同步完成啦。