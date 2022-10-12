what：

　　mysql中binglog使用statement模式会出现，数据同步时：主从数据不一致问题。

 

why：

　　主要原因有两个：

　　1、记录的sql上下文不全，例如：下面例子的RC场景；

　　2、mysql版本迭代，新功能加入，导致不同版本之间的能力不同，从而导致数据不一致。例如：sleep函数在老版本中没有。

 

具体例子（sql上下文不全）：

　　说下MySQL5.0之前为啥事务的隔离机制是RC（'read-committed）并且存储格式是Statement时会出现主从复制数据不一致问题。

创建表：

![](D:\Rocky\JAVA\培训资料\马士兵\GitHubFileClone\java\database\image\2489423-20220106115103513-63242498.png)

 把自动提交关闭 执行两个会话： 

![](D:\Rocky\JAVA\培训资料\马士兵\GitHubFileClone\java\database\image\2489423-20220106115159760-1425463140.png)

此时查看会话1的提交结果：

dba> select * from t1;

+------+------+

| b1 | b2 |

+------+------+

| 1 | 4 |

| 2 | 8 |

| 3 | 4 |

| 4 | 8 |

| 5 | 4 |

+------+------+

5 rows in set (0.00 sec)

这个结果不会有任何问题。



**STATEMENT模式下的主从情况：**

假设在RC隔离级别下支持STATEMENT格式的binlog，并且binlog是打开的。binlog的记录顺序是按照事务commit顺序为序的。那么显而易见，binlog中的顺序为：

会话2：

dba> set tx_isolation='read-committed';

dba> BEGIN;

dba> update t1 set b2=4 where b2=2;

dba> commit;

会话1：

dba> set tx_isolation='read-committed';

dba> BEGIN;（开启事务）

dba> update t1 set b2=8 where b2=4;

\#会话1进行提交

dba> commit;

那么此时在主从复制的从库上看到的结果应为：

dba> select * from t1;

+------+------+

| b1 | b2 |

+------+------+

| 1 | 8 |

| 2 | 8 |

| 3 | 8 |

| 4 | 8 |

| 5 | 8 |

+------+------+

5 rows in set (0.00 sec)

可见，在RC隔离级别下，如果支持STATEMENT格式的binlog，是有可能导致主从数据不一致的！



Row和mixed的模式情况：

那么你可能会问，在RC隔离级别下，如果binlog格式为ROW或者MIXED，难道就不会有主从数据不一致的风险吗？答案是肯定的，如果binlog的格式是ROW或者MIXED，在RC隔离级别下，不会导致主从数据不一致。为什么呢？

因为ROW或者MIXED格式的binlog，是基于数据的变动。在进行update或者delete操作，记录到binlog，同时会把数据的原始记录写入到binlog。所以日志文件会比Statement大些，上述演示过程，binlog的记录顺序仍然是按照事务的commit顺序为序的，binlog的顺序仍然为：

会话2：

dba> set tx_isolation='read-committed';

dba> BEGIN;

dba> update t1 set b2=4 where b2=2;

dba> commit;

 

会话1：

dba> set tx_isolation='read-committed';

dba> BEGIN;（开启事务）

dba> update t1 set b2=8 where b2=4;

\#会话1进行提交

dba> commit;

 

在从库仍然是按照这个binlog的执行时序，进行更新操作。但不同之处在于。会话2的update操作：

dba> update t1 set b2=4 where b2=2;

写入到binlog时，会把原始的记录也记录下来。它是这样记录的：

update dba.t1

where

b1=1

b2=2

set

b1=1

b2=4

 

update dba.t1

where

b1=3

b2=2

set

b1=3

b2=4

update dba.t1

where

b1=5

b2=2

set

b1=5

b2=4

从库上会话2的更新操作完成之后，接着执行会话1的更新操作：

dba> update t1 set b2=8 where b2=4;

binlog中的记录为：

update dba.t1

where

b1=2

b2=4

set

b1=2

b2=8

update dba.t1

where

b1=4

b2=4

set

b1=4

b2=8

这样从库看到的结果就是：

dba> select * from t1;

+------+------+

| b1 | b2 |

+------+------+

| 1 | 4 |

| 2 | 8 |

| 3 | 4 |

| 4 | 8 |

| 5 | 4 |

+------+------+

5 rows in set (0.00 sec)

这样，主从数据就是一致的。