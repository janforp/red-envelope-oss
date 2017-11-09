/**
DROP DATABASE IF EXISTS red_envelope;
CREATE DATABASE red_envelope DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
**/


drop database if EXISTS red_envelope;
create database red_envelope CHARACTER SET utf8 COLLATE utf8_general_ci;

USE red_envelope;


DROP TABLE IF EXISTS oss_role;
CREATE TABLE oss_role(
  role_id							  INT UNSIGNED AUTO_INCREMENT COMMENT '角色ID，主键，自增长ID',
  role_name					    VARCHAR(64) NOT NULL COMMENT '角色名',
  role_desc 				    VARCHAR(128) COMMENT '角色描述',
  update_time_ms        BIGINT NOT NULL COMMENT '更新时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()',
  create_time_ms        BIGINT NOT NULL COMMENT '创建时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()',
  PRIMARY KEY (role_id)
) ENGINE=InnoDB AUTO_INCREMENT=1001 COMMENT='管理员角色表';


DROP TABLE IF EXISTS oss_admin;
CREATE TABLE oss_admin(
  admin_id							INT UNSIGNED AUTO_INCREMENT COMMENT '管理员ID，主键，自增长ID',
  role_id							  INT NOT NULL COMMENT '角色ID',
  real_name					    VARCHAR(64) NOT NULL COMMENT '姓名',
  login_name					  VARCHAR(64) UNIQUE NOT NULL COMMENT '登录名；唯一／非空',
  login_pwd						  VARCHAR(64) NOT NULL COMMENT '登录密码',
  cellphone			        VARCHAR(16) UNIQUE NOT NULL COMMENT '登录手机；唯一／可空',
  email									VARCHAR(64) UNIQUE COMMENT '登录邮箱；唯一／可空',
  portrait							VARCHAR(128) NOT NULL COMMENT '头像文件名',
  status								TINYINT(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '0：禁用；1：正常',
  update_time_ms        BIGINT NOT NULL COMMENT '更新时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()',
  create_time_ms        BIGINT NOT NULL COMMENT '创建时间，UNIX_TIMESTAMP()*1000或System.currentTimeMillis()',
  PRIMARY KEY (admin_id)
) ENGINE=InnoDB AUTO_INCREMENT=10001 COMMENT='管理员表';


DROP TABLE IF EXISTS oss_menu_module;
CREATE TABLE oss_menu_module(
  module_id             INT UNSIGNED NOT NULL COMMENT '模块ID',
  module_name           VARCHAR(64) NOT NULL COMMENT '模块名称',
  icon                  VARCHAR(64) NOT NULL COMMENT 'glyphicon icon，如：glyphicon-cog，则icon=cog',
  order_by							TINYINT(3) UNSIGNED DEFAULT 5 COMMENT '排序，值越小，越靠前',
  PRIMARY KEY (module_id)
) ENGINE=InnoDB COMMENT='所有的功能模块表';


DROP TABLE IF EXISTS oss_menu_function;
CREATE TABLE oss_menu_function(
  function_id           INT UNSIGNED NOT NULL COMMENT '功能ID(主键)',
  module_id             INT NOT NULL COMMENT '所属的模块ID',
  request_uri           VARCHAR(128) UNIQUE NOT NULL COMMENT '访问路径（不含参数，唯一）',
  related_request_uri		VARCHAR(128) COMMENT '关联的访问路径（不含参数，可空），如添加账号页面add和添加账号保存save，就只需要在add记录中的related_request_uri设置为save',
  focus_function_id     INT COMMENT '当访问此功能，时要让菜单定位到的功能ID；如：当前访问“添加角色：/role/add”页面，则希望菜单自动定位到“角色列表：/role/list”这个菜单上',
  function_name         VARCHAR(64) NOT NULL COMMENT '功能名称',
  function_desc         VARCHAR(128) COMMENT '功能描述',
  show_in_menu					TINYINT(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否出现在菜单中。1：是；0：否',
  order_by							TINYINT(3) UNSIGNED DEFAULT 5 COMMENT '排序，值越小，越靠前',
  PRIMARY KEY (function_id)
) ENGINE=InnoDB COMMENT='所有的功能表';


DROP TABLE IF EXISTS oss_rel_role_menu_function;
CREATE TABLE oss_rel_role_menu_function (
  role_id				      INT NOT NULL COMMENT '角色ID',
  function_id         INT NOT NULL COMMENT '功能ID',
  PRIMARY KEY (role_id,function_id)
) ENGINE=InnoDB COMMENT='角色所具备的权限关系表';


