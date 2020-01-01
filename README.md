# ShiroDemo
这是一个Shiro的小例子,使用了后台MVC进行渲染页面

#### 数据库名:shirotest
-----------------------------------
#### 表结构:

##### 权限表:
CREATE TABLE `permission` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '',
  `url` varchar(255) DEFAULT '',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8

##### 用户表:
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8

##### 角色表:
CREATE TABLE `role` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rname` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8

##### 权限-角色表:
CREATE TABLE `permission_role` (
  `rid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  KEY `idx_rid` (`rid`),
  KEY `idx_pid` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

##### 用户-角色表:
CREATE TABLE `user_role` (
  `uid` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  KEY `idx_uid` (`uid`),
  KEY `idx_rid` (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

-----------------------------------
表数据例子:
##### 权限表:
  pid  | name  | url
---- | ----- | ------ 
1  | add | 
2  | delete |  
3  | edit |  
4  | query | 

##### 用户表:
  uid  | username  | password
---- | ----- | ------ 
1  | admin | 123
2  | demo | 123

##### 角色表:
  rid  | rname  
---- | -----
1  | admin 
2  | customer

##### 权限-角色表:
  rid  | pid 
---- | -----
1  | 1 
1  | 2
1  | 3 
1  | 4
2  | 1 
2  | 4

##### 用户-角色表:
  uid  | rid 
---- | -----
1  | 1 
2  | 2
