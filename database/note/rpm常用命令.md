RMP (Red Hat Package Manager) 是一款 Red Hat 系统的开源包管理工具，支持安装、更新、卸载、查询、验证和管理系统软件包。RPM以前称为 .rpm 文件，文件内包含编译好的软件和包所需要的库。

这篇文章主要介绍了 20 个常用的 RPM 命令。

***1\***|***0\*****关于 RPM 的一些常识**

- RPM 是免费的，并且遵循 GPL 开源协议
- RPM 将所有已安装软件包的信息保存在 `/var/lib/rpm` 数据库中。
- RPM 是在 Linux 系统下安装软件包的唯一方法，如果您使用源代码安装了软件包，则 rpm 将无法对其进行管理。
- RPM 处理 .rpm 文件，其中包含有关软件包的实际信息，例如：它是什么，它来自哪里，软件依赖信息，版本信息等。

***2\***|***0\*****RPM命令的五个基本模式**

- **Install** : 使用于安装任意的 RPM 包。
- **Remove** ：用于擦除，删除或卸载任何 RPM 软件包。
- **Upgrade** : 用于更新已经存在的 RPM 软件包。
- **Verify** ：用来验证 RPM 软件包。
- **Query**：用来查询 RPM 软件包。

***3\***|***0\*****查找和下载 RPM 包**

以下是rpm网站的列表，您可以在其中找到和下载所有RPM软件包。

- [http://rpmfind.net](https://rpmfind.net/)
- [http://www.redhat.com](https://www.redhat.com/)
- http://freshrpms.net/
- http://rpm.pbone.net/

***4\***|***0\*****检查 RPM 包的签名**

在将软件包安装在Linux系统上之前，先检查软件包的 PGP 签名，并确保其完整性和来源是正确的。使用 **–-checksig** (**check signature**) 命令检查 RPM 包的签名。



```
[root@tecmint]# rpm --checksig pidgin-2.7.9-5.el6.2.i686.rpm

pidgin-2.7.9-5.el6.2.i686.rpm: rsa sha1 (md5) pgp md5 OK
```

***5\***|***0\*****安装 RPM 包**

使用 **-i** 选项安装 RPM 包



```
[root@localhost ~]# rpm -ivh tree-1.6.0-10.el7.x86_64.rpm
准备中...                          ################################# [100%]
正在升级/安装...
   1:tree-1.6.0-10.el7                ################################# [100%]
```

RPM 命令和选项

- **-i** : 安装包
- **-v :** 详细显示
- **-h** 在打包归档文件解压缩时打印哈希标记。

***6\***|***0\*****安装 RPM 包之前检查包依赖**



```
[root@localhost ~]# rpm -qpR tree-1.6.0-10.el7.x86_64.rpm
libc.so.6()(64bit)
libc.so.6(GLIBC_2.14)(64bit)
libc.so.6(GLIBC_2.2.5)(64bit)
libc.so.6(GLIBC_2.3)(64bit)
libc.so.6(GLIBC_2.3.4)(64bit)
libc.so.6(GLIBC_2.4)(64bit)
rpmlib(CompressedFileNames) <= 3.0.4-1
rpmlib(FileDigests) <= 4.6.0-1
rpmlib(PayloadFilesHavePrefix) <= 4.0-1
rtld(GNU_HASH)
rpmlib(PayloadIsXz) <= 5.2-1
```

命令和选项说明：

- **-q :** 查询一个包
- **-p:** 列出此软件包提供的功能。
- **-R:** 列出此程序包所依赖的功能。

***7\***|***0\*****忽略依赖安装 RPM 包**

如果已经知道所有必需的软件包都已安装，那么可以在安装软件包之前使用 **-–nodeps(no dependencies check)** 选项来忽略那些依赖项。



```
[root@localhost ~]# rpm -ivh --nodeps tree-1.6.0-10.el7.x86_64.rpm
准备中...                          ################################# [100%]
        软件包 tree-1.6.0-10.el7.x86_64 已经安装
```

上面的命令通过忽略依赖项错误来强制安装rpm软件包，但是如果缺少那些依赖项文件会导致程序将无法运行。

***8\***|***0\*****查找一个已经安装的 RPM 包**

在软件包名称中使用 **-q** 选项，将显示是否已安装 rpm 包。



```
[root@localhost ~]# rpm -q tree
tree-1.6.0-10.el7.x86_64
```

***9\***|***0\*****列出已安装的RPM软件包的所有文件**

要查看已安装的rpm软件包的所有文件，请使用 **-ql（query list）** rpm 命令。



```
[root@localhost ~]# rpm -ql tree
/usr/bin/tree
/usr/share/doc/tree-1.6.0
/usr/share/doc/tree-1.6.0/LICENSE
/usr/share/doc/tree-1.6.0/README
/usr/share/man/man1/tree.1.gz
```

***10\***|***0\*****列出最近安装的 RPM 包**

使用 **-qa(query all)** 命令，会列出最近安装的所有 RPM 包



```
[root@localhost ~]# rpm -qa --last
tree-1.6.0-10.el7.x86_64                      2020年06月01日 星期一 19时04分28秒
perl-Git-1.8.3.1-22.el7_8.noarch              2020年06月01日 星期一 14时18分37秒
git-1.8.3.1-22.el7_8.x86_64                   2020年06月01日 星期一 14时18分36秒
perl-TermReadKey-2.30-20.el7.x86_64           2020年06月01日 星期一 14时18分34秒
rsync-3.1.2-10.el7.x86_64                     2020年06月01日 星期一 14时18分33秒
perl-Error-0.17020-2.el7.noarch               2020年06月01日 星期一 14时18分33秒
nux-dextop-release-0-5.el7.nux.noarch         2020年05月22日 星期五 19时40分35秒
gpg-pubkey-85c6cd8a-4e060c35                  2020年05月22日 星期五 19时40分19秒
epel-release-7-11.noarch                      2020年05月22日 星期五 19时39分27秒
libtirpc-0.2.4-0.16.el7.x86_64                2020年05月22日 星期五 18时58分40秒
vim-enhanced-7.4.629-6.el7.x86_64             2020年05月22日 星期五 17时48分48秒
vim-common-7.4.629-6.el7.x86_64               2020年05月22日 星期五 17时48分48秒
vim-filesystem-7.4.629-6.el7.x86_64           2020年05月22日 星期五 17时48分44秒
...
```

***11\***|***0\*****列出所有已安装的 RPM 包**

键入以下命令以打印Linux系统上已安装软件包的所有名称。该命令和 grep 一起使用，即可搜索到我们是否安装过某个包，例如 `rpm -qa | grep git`，查看我们是否安装过 git 。



```
[root@localhost ~]# rpm -qa
kexec-tools-2.0.15-43.el7.x86_64
grub2-common-2.02-0.81.el7.centos.noarch
openssh-clients-7.4p1-21.el7.x86_64
setup-2.8.71-11.el7.noarch
authconfig-6.2.8-30.el7.x86_64
basesystem-10.0-7.el7.centos.noarch
postfix-2.10.1-9.el7.x86_64
ncurses-base-5.9-14.20130511.el7_4.noarch
kbd-1.15.5-15.el7.x86_64
kbd-misc-1.15.5-15.el7.noarch
qemu-guest-agent-2.12.0-3.el7.x86_64
...
```

***12\***|***0\*****更新 RPM 包**

使用 **-U(upgrade)** 选项来升级 RPM 包。该命令不仅会将某个 rpm 包升级到最新版本，而且还会维护旧软件包的备份，以便在新的升级软件包不能使用的时候还能使用旧的 RPM 包。



```
[root@localhost ~]# rpm -Uvh tree-1.6.0-10.el7.x86_64.rpm
准备中...                          ################################# [100%]
        软件包 tree-1.6.0-10.el7.x86_64 已经安装
```

***13\***|***0\*****删除 RPM 包**

使用 **-e (erase)** 命令来移除已安装的 rpm 包。如果要移除的 RPM 包不存在，就会有错误提示。



```
[root@localhost ~]# rpm -evv tree
D: loading keyring from pubkeys in /var/lib/rpm/pubkeys/*.key
D: couldn't find any keys in /var/lib/rpm/pubkeys/*.key
D: loading keyring from rpmdb
D: opening  db environment /var/lib/rpm cdb:0x401
D: opening  db index       /var/lib/rpm/Packages 0x400 mode=0x0
D: locked   db index       /var/lib/rpm/Packages
D: opening  db index       /var/lib/rpm/Name 0x400 mode=0x0
D:  read h#     302 头 SHA1 摘要： OK (489efff35e604042709daf46fb78611fe90a75aa)
D: added key gpg-pubkey-f4a80eb5-53a7ff4b to keyring
D:  read h#     371 头 SHA1 摘要： OK (052c9c3b53cea0014763d9f82c173a87dc743eea)
D: added key gpg-pubkey-85c6cd8a-4e060c35 to keyring
D: Using legacy gpg-pubkey(s) from rpmdb
D:  read h#     380 头V3 RSA/SHA256 Signature, 密钥 ID f4a80eb5: OK
D: opening  db index       /var/lib/rpm/Conflictname 0x400 mode=0x0
D: ========== --- tree-1.6.0-10.el7 x86_64/linux 0x2
D: opening  db index       /var/lib/rpm/Requirename 0x400 mode=0x0
D: ========== recording tsort relations
D: ========== tsorting packages (order, #predecessors, #succesors, depth)
D:     0    0    0    1   -tree-1.6.0-10.el7.x86_64
D: erasing packages
D: closed   db index       /var/lib/rpm/Conflictname
D: closed   db index       /var/lib/rpm/Requirename
D: closed   db index       /var/lib/rpm/Name
D: closed   db index       /var/lib/rpm/Packages
D: closed   db environment /var/lib/rpm
D: opening  db environment /var/lib/rpm cdb:0x401
D: opening  db index       /var/lib/rpm/Packages (none) mode=0x42
D: sanity checking 1 elements
D: running pre-transaction scripts
D: computing 5 file fingerprints
D: opening  db index       /var/lib/rpm/Name (none) mode=0x42
D: opening  db index       /var/lib/rpm/Basenames (none) mode=0x42
D: opening  db index       /var/lib/rpm/Group (none) mode=0x42
D: opening  db index       /var/lib/rpm/Requirename (none) mode=0x42
D: opening  db index       /var/lib/rpm/Providename (none) mode=0x42
D: opening  db index       /var/lib/rpm/Conflictname (none) mode=0x42
D: opening  db index       /var/lib/rpm/Obsoletename (none) mode=0x42
D: opening  db index       /var/lib/rpm/Triggername (none) mode=0x42
D: opening  db index       /var/lib/rpm/Dirnames (none) mode=0x42
D: opening  db index       /var/lib/rpm/Installtid (none) mode=0x42
D: opening  db index       /var/lib/rpm/Sigmd5 (none) mode=0x42
D: opening  db index       /var/lib/rpm/Sha1header (none) mode=0x42
软件包准备中...
D: computing file dispositions
D: 0x0000fd00     4096      9228841     19356493 /
D: ========== +++ tree-1.6.0-10.el7 x86_64-linux 0x2
D:  read h#     380 头V3 RSA/SHA256 Signature, 密钥 ID f4a80eb5: OK
D:     erase: tree-1.6.0-10.el7 has 5 files
tree-1.6.0-10.el7.x86_64
D: erase      100644  1 (   0,   0)  4100 /usr/share/man/man1/tree.1.gz
D: erase      100644  1 (   0,   0)  4628 /usr/share/doc/tree-1.6.0/README
D: erase      100644  1 (   0,   0) 18009 /usr/share/doc/tree-1.6.0/LICENSE
D: erase      040755  2 (   0,   0)     6 /usr/share/doc/tree-1.6.0
D: erase      100755  1 (   0,   0) 62768 /usr/bin/tree
D:   --- h#     380 tree-1.6.0-10.el7.x86_64
D: removing "tree" from Name index.
D: removing 5 entries from Basenames index.
D: removing "Applications/File" from Group index.
D: removing 11 entries from Requirename index.
D: removing 2 entries from Providename index.
D: removing 4 entries from Dirnames index.
D: removing 1 entries from Installtid index.
D: removing 1 entries from Sigmd5 index.
D: removing "a09f99f73ee3fe352489e734c63c32fa41b1be56" from Sha1header index.
D: running post-transaction scripts
D: closed   db index       /var/lib/rpm/Sha1header
D: closed   db index       /var/lib/rpm/Sigmd5
D: closed   db index       /var/lib/rpm/Installtid
D: closed   db index       /var/lib/rpm/Dirnames
D: closed   db index       /var/lib/rpm/Triggername
D: closed   db index       /var/lib/rpm/Obsoletename
D: closed   db index       /var/lib/rpm/Conflictname
D: closed   db index       /var/lib/rpm/Providename
D: closed   db index       /var/lib/rpm/Requirename
D: closed   db index       /var/lib/rpm/Group
D: closed   db index       /var/lib/rpm/Basenames
D: closed   db index       /var/lib/rpm/Name
D: closed   db index       /var/lib/rpm/Packages
D: closed   db environment /var/lib/rpm
[root@localhost ~]# echo $?
0
[root@localhost ~]# rpm -e tree
错误：未安装软件包 tree
```

***14\***|***0\*****忽略依赖地删除RPM 包**

使用 **--nodeps (Do not check dependencies)** 命令项强制从系统中删除 RPM 包。需要注意的是，删除特定的软件包可能会破坏其他正在运行的应用程序。



```
[root@localhost ~]# rpm -ev --nodeps tree
```

***15\***|***0\*****查看一个文件属于哪个 RPM 包**

假设有一个文件列表，并且想找出这些文件属于哪个 RPM 包的，那么可以使用 **-qf (query file)** 命令。



```
[root@localhost ~]# rpm -qf /usr/bin/tree
tree-1.6.0-10.el7.x86_64
```

***16\***|***0\*****查看已安装的 RPM 包的信息**

使用 **-qi (query info)** 命令查询想要知道的 rpm 包的信息。



```
[root@localhost ~]# rpm -qi tree
Name        : tree
Version     : 1.6.0
Release     : 10.el7
Architecture: x86_64
Install Date: 2020年06月02日 星期二 19时05分24秒
Group       : Applications/File
Size        : 89505
License     : GPLv2+
Signature   : RSA/SHA256, 2014年07月04日 星期五 13时36分46秒, Key ID 24c6a8a7f4a80eb5
Source RPM  : tree-1.6.0-10.el7.src.rpm
Build Date  : 2014年06月10日 星期二 03时28分53秒
Build Host  : worker1.bsys.centos.org
Relocations : (not relocatable)
Packager    : CentOS BuildSystem <http://bugs.centos.org>
Vendor      : CentOS
URL         : http://mama.indstate.edu/users/ice/tree/
Summary     : File system tree viewer
Description :
The tree utility recursively displays the contents of directories in a
tree-like format.  Tree is basically a UNIX port of the DOS tree
utility.
```

***17\***|***0\*****在安装之前获取 RPM 包的信息**

假设你从网上下载了一个 rpm 包，并且想要在安装之前知道这个 rpm 包的信息，那么可以使用 **-qip (query info package)** 这个命令来打印软件包的信息。



```
[root@localhost ~]# rpm -qip python3-3.6.8-13.el7.x86_64.rpm
Name        : python3
Version     : 3.6.8
Release     : 13.el7
Architecture: x86_64
Install Date: (not installed)
Group       : Unspecified
Size        : 39904
License     : Python
Signature   : RSA/SHA256, 2020年04月04日 星期六 05时06分11秒, Key ID 24c6a8a7f4a80eb5
Source RPM  : python3-3.6.8-13.el7.src.rpm
Build Date  : 2020年04月02日 星期四 22时17分47秒
Build Host  : x86-01.bsys.centos.org
Relocations : (not relocatable)
Packager    : CentOS BuildSystem <http://bugs.centos.org>
Vendor      : CentOS
URL         : https://www.python.org/
Summary     : Interpreter of the Python programming language
Description :
Python is an accessible, high-level, dynamically typed, interpreted programming
language, designed with an emphasis on code readability.
It includes an extensive standard library, and has a vast ecosystem of
third-party libraries.

The python3 package provides the "python3" executable: the reference
interpreter for the Python language, version 3.
The majority of its standard library is provided in the python3-libs package,
which should be installed automatically along with python3.
The remaining parts of the Python standard library are broken out into the
python3-tkinter and python3-test packages, which may need to be installed
separately.

Documentation for Python is provided in the python3-docs package.

Packages containing additional libraries for Python are generally named with
the "python3-" prefix.
```

***18\***|***0\*****查看 RPM 包安装了哪些目录**

要获取已安装软件包的文件列表，使用选项 **-qdf（query document file)** 的命令。



```
[root@localhost ~]# rpm -qdf /usr/bin/tree
/usr/share/doc/tree-1.6.0/LICENSE
/usr/share/doc/tree-1.6.0/README
/usr/share/man/man1/tree.1.gz
```

***19\***|***0\*****验证一个 RPM 包**

验证软件包会将软件包已安装文件的信息与rpm数据库进行比较。使用 **-Vp (verify package)** 命令来验证一个软件包。



```
[root@localhost ~]# rpm -Vp python3-3.6.8-13.el7.x86_64.rpm
未满足的依赖关系 python3-3.6.8-13.el7.x86_64：
        libpython3.6m.so.1.0()(64bit) 被 python3-3.6.8-13.el7.x86_64 需要
        python3-libs(x86-64) = 3.6.8-13.el7 被 python3-3.6.8-13.el7.x86_64 需要
        python3-pip 被 python3-3.6.8-13.el7.x86_64 需要
        python3-setuptools 被 python3-3.6.8-13.el7.x86_64 需要
遗漏     /usr/bin/pydoc3
遗漏     /usr/bin/pydoc3.6
遗漏     /usr/bin/python3
遗漏     /usr/bin/python3.6
遗漏     /usr/bin/python3.6m
遗漏     /usr/bin/pyvenv
遗漏     /usr/bin/pyvenv-3.6
遗漏     /usr/share/doc/python3-3.6.8
遗漏   d /usr/share/doc/python3-3.6.8/README.rst
遗漏     /usr/share/licenses/python3-3.6.8
遗漏   l /usr/share/licenses/python3-3.6.8/LICENSE
遗漏   d /usr/share/man/man1/python3.1.gz
遗漏   d /usr/share/man/man1/python3.6.1.gz
```

***20\***|***0\*****验证所有的 RPM 包**



```
[root@tecmint]# rpm -Va

S.5....T.  c /etc/rc.d/rc.local
.......T.  c /etc/dnsmasq.conf
.......T.    /etc/ld.so.conf.d/kernel-2.6.32-279.5.2.el6.i686.conf
S.5....T.  c /etc/yum.conf
S.5....T.  c /etc/yum.repos.d/epel.repo
```

***21\***|***0\*****导入 GPG key**

要验证 RHEL / CentOS / Fedora 软件包，必须导入 GPG 密钥。为此，执行以下命令，它将导入CentOS 6 GPG密钥。



```
[root@localhost ~]# rpm --import /etc/pki/rpm-gpg/RPM-GPG-KEY-CentOS-7
```

***22\***|***0\*****列出所有导入的 RPM GPG key**



```
[root@localhost ~]# rpm -qa gpg-pubkey*
gpg-pubkey-85c6cd8a-4e060c35
gpg-pubkey-f4a80eb5-53a7ff4b
```

***23\***|***0\*****重建损坏的RPM数据库**

有时rpm数据库损坏并停止rpm和系统上其他应用程序的所有功能。因此，当时我们需要重建rpm数据库并在以下命令的帮助下将其还原。



```
[root@tecmint]# cd /var/lib
[root@tecmint]# rm __db*
[root@tecmint]# rpm --rebuilddb
[root@tecmint]# rpmdb_verify Packages
```