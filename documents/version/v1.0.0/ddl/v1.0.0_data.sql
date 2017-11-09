INSERT INTO oss_role (role_id, role_name, role_desc, update_time_ms, create_time_ms) VALUES
(1000,'管理员','管理员',UNIX_TIMESTAMP()*1000,UNIX_TIMESTAMP()*1000);


INSERT INTO oss_admin (admin_id, role_id, real_name, login_name, login_pwd, cellphone, email, portrait, status, update_time_ms, create_time_ms) VALUES
(10000,1000,'超级管理员','system','96df681a32aec2a3b4ffeacdb8aaaf4c', /*密码：123456*/'','','', 1, UNIX_TIMESTAMP()*1000,UNIX_TIMESTAMP()*1000);


INSERT INTO oss_menu_module (module_id, module_name, icon, order_by) VALUES
('1001', '帐号管理', 'user', 5),
('1002', '角色管理', 'regular-group', 5),
('1003', '用户管理', 'user', 5),
('1004', '入口管理', 'list-alt', 5),
('1005', '红包管理', 'list-alt', 5),
('1006', '提现管理', 'list-alt', 5),
('1007', '金币商城', 'list-alt', 5),
('1008', '任务管理', 'list-alt', 5),
('1009', '游戏管理', 'list-alt', 5),
('1010', '数据管理', 'list-alt', 5);


INSERT INTO oss_menu_function (function_id, module_id, function_name, function_desc, request_uri, related_request_uri, focus_function_id, show_in_menu, order_by) VALUES
(100101, 1001, '帐号列表', '帐号列表', '/c/page/console/auth/account/list', NULL, NULL, 1, 5),
(100102, 1001, '新建帐号', '新建帐号', '/c/page/console/auth/account/save', NULL, 100101, 0, 5),
(100103, 1001, '修改帐号', '修改帐号', '/c/page/console/auth/account/edit', '/c/page/console/auth/account/update', 100101, 0, 5),
(100104, 1001, '删除帐号', '删除帐号', '/c/page/console/auth/account/remove', NULL, 100101, 0, 5),

(100201, 1002, '角色列表', '角色列表', '/c/page/console/auth/role/list', NULL, NULL, 1, 5),
(100202, 1002, '新建角色', '新建角色', '/c/page/console/auth/role/add', '/c/page/console/auth/role/save', 100201, 0, 5),
(100203, 1002, '修改角色', '修改角色', '/c/page/console/auth/role/edit', '/c/page/console/auth/role/update', 100201, 0, 5),
(100204, 1002, '删除角色', '删除角色', '/c/page/console/auth/role/remove', NULL, 100201, 0, 5),

(100301, 1003, '用户数据', '用户数据', '/c/page/console/auth/user/userData', NULL, NULL, 1, 5),
(100302, 1003, '用户列表', '用户列表', '/c/page/console/auth/user/userList', NULL, NULL, 1, 5),
(100303, 1003, '渠道包名', '渠道包名', '/c/page/console/auth/packageChannel/list', NULL, NULL, 1, 5),

(100401, 1004, '启动广告', '启动广告', '/c/page/console/auth/startAd/startAdList', NULL, NULL, 1, 5),
(100402, 1004, '首页banner', '首页banner', '/c/page/console/auth/banner/bannerList', NULL, NULL, 1, 5),
(100403, 1004, '首页导航', '首页导航', '/c/page/console/auth/navigation/navigationList', NULL, NULL, 1, 5),
(100404, 1004, '发现Banner', '发现Banner', '/c/page/console/auth/discoverBanner/bannerList', NULL, NULL, 1, 5),
(100405, 1004, '小游戏', '小游戏', '/c/page/console/auth/discover/discoverList', NULL, NULL, 1, 5),
(100406, 1004, '积分墙', '积分墙', '/c/page/console/auth/integral/integralList', NULL, NULL, 1, 5),

(100501, 1005, '定时红包', '定时红包', '/c/page/console/auth/red/fixedRedList', NULL, NULL, 1, 5),

(100601, 1006, '提现方式', '提现方式', '/c/page/console/auth/withdraw/withdrawSortList', NULL, NULL, 1, 5),
(100602, 1006, '现金提现', '现金提现', '/c/page/console/auth/withdraw/withdrawList', NULL, NULL, 1, 5),
(100603, 1006, '佣金提现', '佣金提现', '/c/page/console/auth/commission/commissionWithdrawList', NULL, NULL, 1, 5),

(100701, 1007, '金币兑换', '金币兑换', '/c/page/console/auth/coinMall/exchangeListPage', NULL, NULL, 1, 5),

(100801, 1008, '优惠生活', '优惠生活', '/c/page/console/auth/mission/missionList', NULL, NULL, 1, 5),
(100802, 1008, '关注任务', '关注任务', '/c/page/console/auth/exchange/exchangeList', NULL, NULL, 1, 5),
(100803, 1008, '微信红包', '微信红包', '/c/page/console/auth/share/shareList', NULL, NULL, 1, 5),
(100804, 1008, '高额任务', '高额任务', '/c/page/console/auth/recommend/recommendList', NULL, NULL, 1, 5),
(100805, 1008, '试玩任务', '试玩任务', '/c/page/console/auth/demo/demoPage', NULL, NULL, 1, 5),
(100806, 1008, '分享任务', '分享任务', '/c/page/console/auth/appShare/appSharePage', NULL, NULL, 1, 5),
(100807, 1008, '口令红包', '口令红包', '/c/page/console/auth/password/passwordPage', NULL, NULL, 1, 5),
(100808, 1008, '注册任务', '注册任务', '/c/page/console/auth/registerMission/registerMissionPage', NULL, NULL, 1, 5),
(100809, 1008, '分享任务(新)', '分享任务(新)', '/c/page/console/auth/article/articlePage', NULL, NULL, 1, 5),
(100810, 1008, '积分墙', '积分墙', '/c/page/console/auth/selfIntegral/selfIntegralPage', NULL, NULL, 1, 5),
(100811, 1008, '福利', '福利', '/c/page/console/auth/welfare/welfarePage', NULL, NULL, 1, 5),

(100901, 1009, '新版幸运转盘', '新版幸运转盘', '/c/page/console/auth/game/rotate2', NULL, NULL, 1, 5),

(101001, 1010, '模块选择', '模块选择', '/c/page/console/auth/data/modelList', NULL, NULL, 1, 5);



INSERT INTO oss_rel_role_menu_function (role_id, function_id) VALUES
(1000, 100101),
(1000, 100102),
(1000, 100103),
(1000, 100104),

(1000, 100201),
(1000, 100202),
(1000, 100203),
(1000, 100204),

(1000, 100301),
(1000, 100302),
(1000, 100303),

(1000, 100401),
(1000, 100402),
(1000, 100403),
(1000, 100404),
(1000, 100405),
(1000, 100406),

(1000, 100501),

(1000, 100601),
(1000, 100602),
(1000, 100603),

(1000, 100701),

(1000, 100801),
(1000, 100802),
(1000, 100803),
(1000, 100804),
(1000, 100805),
(1000, 100806),
(1000, 100807),
(1000, 100808),
(1000, 100809),
(1000, 100810),
(1000, 100811),

(1000, 100901),

(1000, 101001);
















