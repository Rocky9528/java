mysql修改了从库，导致主从不同步报错。真实解决案例

1.show slave status\G 显示

Last_Error: Coordinator stopped because there were error(s) in the worker(s). The most recent failure being: Worker 1 failed exec
uting transaction '94427323-3107-11ed-aedf-000c29f83a98:12' at master log master-bin.000002, end_log_pos 2530. See error log and/or **performance_schema.replication_applier_status_by_worker** table for more details about this failure or others, if any.

 2.上面标注的粗体字是一个表名， 通过select * from **performance_schema.replication_applier_status_by_worker** 可以查询到具体异常日志：
Worker 1 failed executing tra
nsaction '**94427323-3107-11ed-aedf-000c29f83a98:12**' at master log **master-bin.000002**, end_log_pos 2530; Could not execute Update_rows event on table **ms.ms**; Can't find record in 'ms', Error_code: 1032; handler error HA_ERR_END_OF_FILE; the event's master log master-bin.000002, end_log_pos **2530** | 2022-09-12 22:39:48

3.通过上面日志分析，主库中master-bin.000002中2530处发生异常，导致虽然IO线程已经接收bin log数据并写入到relay log中，但是SQL线程读取relay log重放时出现异常.

4.查看master-binlog的2530处

mysqlbinlog --no-defaults -v -v --base64-output=decode-rows /var/lib/mysql/node02-relay-bin.000002 | grep -A 20 "2530" --color

日志：

#220912 22:39:48 server id 1  end_log_pos **2530** CRC32 0xc4e9a106 	Update_rows: table id 114 flags: STMT_END_F

UPDATE `ms`.`ms`

WHERE

@1=7 /* INT meta=0 nullable=1 is_null=0 */

@2='76' /* VARSTRING(50) meta=50 nullable=1 is_null=0 */

SET

@1=7 /* INT meta=0 nullable=1 is_null=0 */

@2='77' /* VARSTRING(50) meta=50 nullable=1 is_null=0 */

at **2530**

#220912 22:39:48 server id 1  end_log_pos 2561 CRC32 0x70e76b27 	Xid = 209
COMMIT/*!*/;
SET @@SESSION.GTID_NEXT= 'AUTOMATIC' /* added by mysqlbinlog */ /*!*/;
DELIMITER ;

End of log file

/*!50003 SET COMPLETION_TYPE=@OLD_COMPLETION_TYPE*/;
/*!50530 SET @@SESSION.PSEUDO_SLAVE_MODE=0*/;

5.relay log 中也可以查看对应日志

mysqlbinlog --no-defaults -v -v --base64-output=decode-rows /var/lib/mysql/node02-relay-bin.000002 | grep -A 20 "2530" --color

日志：

#220912 22:39:48 server id 1  end_log_pos 2530 CRC32 0xc4e9a106 	Update_rows: table id 114 flags: STMT_END_F

UPDATE `ms`.`ms`

WHERE

@1=7 /* INT meta=0 nullable=1 is_null=0 */

@2='76' /* VARSTRING(50) meta=50 nullable=1 is_null=0 */

SET

@1=7 /* INT meta=0 nullable=1 is_null=0 */

@2='77' /* VARSTRING(50) meta=50 nullable=1 is_null=0 */

at 1874

#220912 22:39:48 server id 1  end_log_pos 2561 CRC32 0x70e76b27 	Xid = 209
COMMIT/*!*/;
SET @@SESSION.GTID_NEXT= 'AUTOMATIC' /* added by mysqlbinlog */ /*!*/;
DELIMITER ;

End of log file

/*!50003 SET COMPLETION_TYPE=@OLD_COMPLETION_TYPE*/;
/*!50530 SET @@SESSION.PSEUDO_SLAVE_MODE=0*/;

6：分析原因为更新 @1=7 @2='76'这条数据时失败。主库变为了7，77，但是从库更新不了，经查是因为从库招不到7 76 这条数据，出现原因比如，从库被人改为了7 ，80，导致找不到。7是id，虽然主库sql语句是更新where id=7这条记录，但是我们设置的 binlog-format=ROW，按精确数据更新，所以找不到7，76这条数据，导致无法同步。

7.解决办法。先把从库的7，80这条记录改为7，76.

 在从库中执行
Stop slave;
Set @@SESSION.GTID_NEXT=‘94427323-3107-11ed-aedf-000c29f83a98:2530’
Begin;
Commit;
Set @@SESSION.GTID_NEXT = AUTOMATIC;
Start slave;
再次查看从库状态，恢复正常即解决 

8.问题已解决，主从恢复同步。

类似问题举一反三