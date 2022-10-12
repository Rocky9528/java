yum常用命令：

1检查可用更新的软件清单：yum check-update

2更新所有软件包：yum update

3 仅安装指定的软件命令：yum install <package_name>

4 仅更新指定的软件命令：yum update <package_name>

5列出所有可安裝的软件清单命令：yum list

6删除软件包命令：yum remove <package_name>

7查找软件包命令：yum search <keyword>

8清除缓存命令：

yum clean packages: 清除缓存目录下的软件

yum clean headers: 清除缓存目录下的 headers

yum clean oldheaders: 清除缓存目录下旧的 headers

yum clean, yum clean all (= yum clean packages; yum clean oldheaders) :清除缓存目录下的软件包及旧的 headers



```
1.使用 yum 查找软件包 
命令：yum search 
2.列出所有可安装的软件包 
命令：yum list 
3.列出所有可更新的软件包 
命令：yum list updates 
4.列出所有已安装的软件包 
命令：yum list installed
```

 

## 国内 yum 源

阿里yum源、网易（163）yum源

### 安装步骤

首先备份/etc/yum.repos.d/CentOS-Base.repo

```
mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo.backup
```

执行更换yum源的命令

```
wget -O /etc/yum.repos.d/CentOS-Base.repo https://mirrors.aliyun.com/repo/Centos-6.repo
```

 **centos6（centos6官方源已下线，建议切换centos-vault源）** 

wget -O /etc/yum.repos.d/CentOS-Base.repo https://mirrors.aliyun.com/repo/Centos-vault-6.10.repo
或者
curl -o /etc/yum.repos.d/CentOS-Base.repo https://mirrors.aliyun.com/repo/Centos-vault-6.10.repo

可以下载下来后，手工替换该文件



运行以下命令生成缓存

```
yum clean all
yum makecache
```