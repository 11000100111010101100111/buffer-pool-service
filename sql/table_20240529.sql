-- ----------------------------
-- 1、部门表
-- ----------------------------
drop table if exists sys_dept;
create table sys_dept
(
    dept_id     bigint(20) not null auto_increment comment '部门id',
    parent_id   bigint(20)  default 0 comment '父部门id',
    ancestors   varchar(50) default '' comment '祖级列表',
    dept_name   varchar(30) default '' comment '部门名称',
    order_num   int(4)      default 0 comment '显示顺序',
    leader      varchar(20) default null comment '负责人',
    phone       varchar(11) default null comment '联系电话',
    email       varchar(50) default null comment '邮箱',
    status      char(1)     default '0' comment '部门状态（0正常 1停用）',
    del_flag    char(1)     default '0' comment '删除标志（0代表存在 2代表删除）',
    create_by   varchar(64) default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64) default '' comment '更新者',
    update_time datetime comment '更新时间',
    primary key (dept_id)
) engine = innodb
  auto_increment = 200 comment = '部门表'
  character set 'utf8mb4';

-- ----------------------------
-- 初始化-部门表数据
-- ----------------------------
insert into sys_dept
values (100, 0, '0', '若依科技', 0, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept
values (101, 100, '0,100', '深圳总公司', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept
values (102, 100, '0,100', '长沙分公司', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept
values (103, 101, '0,100,101', '研发部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept
values (104, 101, '0,100,101', '市场部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept
values (105, 101, '0,100,101', '测试部门', 3, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept
values (106, 101, '0,100,101', '财务部门', 4, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept
values (107, 101, '0,100,101', '运维部门', 5, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept
values (108, 102, '0,100,102', '市场部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept
values (109, 102, '0,100,102', '财务部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', sysdate(), '', null);


-- ----------------------------
-- 2、用户信息表
-- ----------------------------
drop table if exists sys_user;
create table sys_user
(
    user_id     bigint(20)  not null auto_increment comment '用户ID',
    dept_id     bigint(20)   default null comment '部门ID',
    user_name   varchar(30) not null comment '用户账号',
    nick_name   varchar(30) not null comment '用户昵称',
    user_type   varchar(2)   default '00' comment '用户类型（00系统用户）',
    email       varchar(50)  default '' comment '用户邮箱',
    phonenumber varchar(11)  default '' comment '手机号码',
    sex         char(1)      default '0' comment '用户性别（0男 1女 2未知）',
    avatar      varchar(100) default '' comment '头像地址',
    password    varchar(100) default '' comment '密码',
    status      char(1)      default '0' comment '帐号状态（0正常 1停用）',
    del_flag    char(1)      default '0' comment '删除标志（0代表存在 2代表删除）',
    login_ip    varchar(128) default '' comment '最后登录IP',
    login_date  datetime comment '最后登录时间',
    create_by   varchar(64)  default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64)  default '' comment '更新者',
    update_time datetime comment '更新时间',
    remark      varchar(500) default null comment '备注',
    primary key (user_id)
) engine = innodb
  auto_increment = 100 comment = '用户信息表'
  character set 'utf8mb4';

-- ----------------------------
-- 初始化-用户信息表数据
-- ----------------------------
insert into sys_user
values (1, 103, 'admin', '若依', '00', 'ry@163.com', '15888888888', '1', '',
        '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', sysdate(), 'admin',
        sysdate(), '', null, '管理员');
insert into sys_user
values (2, 105, 'ry', '若依', '00', 'ry@qq.com', '15666666666', '1', '',
        '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', sysdate(), 'admin',
        sysdate(), '', null, '测试员');


-- ----------------------------
-- 3、岗位信息表
-- ----------------------------
drop table if exists sys_post;
create table sys_post
(
    post_id     bigint(20)  not null auto_increment comment '岗位ID',
    post_code   varchar(64) not null comment '岗位编码',
    post_name   varchar(50) not null comment '岗位名称',
    post_sort   int(4)      not null comment '显示顺序',
    status      char(1)     not null comment '状态（0正常 1停用）',
    create_by   varchar(64)  default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64)  default '' comment '更新者',
    update_time datetime comment '更新时间',
    remark      varchar(500) default null comment '备注',
    primary key (post_id)
) engine = innodb comment = '岗位信息表'
  character set 'utf8mb4';

-- ----------------------------
-- 初始化-岗位信息表数据
-- ----------------------------
insert into sys_post
values (1, 'ceo', '董事长', 1, '0', 'admin', sysdate(), '', null, '');
insert into sys_post
values (2, 'se', '项目经理', 2, '0', 'admin', sysdate(), '', null, '');
insert into sys_post
values (3, 'hr', '人力资源', 3, '0', 'admin', sysdate(), '', null, '');
insert into sys_post
values (4, 'user', '普通员工', 4, '0', 'admin', sysdate(), '', null, '');


-- ----------------------------
-- 4、角色信息表
-- ----------------------------
drop table if exists sys_role;
create table sys_role
(
    role_id             bigint(20)   not null auto_increment comment '角色ID',
    role_name           varchar(30)  not null comment '角色名称',
    role_key            varchar(100) not null comment '角色权限字符串',
    role_sort           int(4)       not null comment '显示顺序',
    data_scope          char(1)      default '1' comment '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
    menu_check_strictly tinyint(1)   default 1 comment '菜单树选择项是否关联显示',
    dept_check_strictly tinyint(1)   default 1 comment '部门树选择项是否关联显示',
    status              char(1)      not null comment '角色状态（0正常 1停用）',
    del_flag            char(1)      default '0' comment '删除标志（0代表存在 2代表删除）',
    create_by           varchar(64)  default '' comment '创建者',
    create_time         datetime comment '创建时间',
    update_by           varchar(64)  default '' comment '更新者',
    update_time         datetime comment '更新时间',
    remark              varchar(500) default null comment '备注',
    primary key (role_id)
) engine = innodb
  auto_increment = 100 comment = '角色信息表'
  character set 'utf8mb4';

-- ----------------------------
-- 初始化-角色信息表数据
-- ----------------------------
insert into sys_role
values ('1', '超级管理员', 'admin', 1, 1, 1, 1, '0', '0', 'admin', sysdate(), '', null, '超级管理员');
insert into sys_role
values ('2', '普通角色', 'common', 2, 2, 1, 1, '0', '0', 'admin', sysdate(), '', null, '普通角色');
insert into sys_role
values ('3', '游客', 'guest', 9, 9, 9, 9, '0', '0', 'admin', sysdate(), '', null, '游客');


-- ----------------------------
-- 5、菜单权限表
-- ----------------------------
drop table if exists sys_menu;
create table sys_menu
(
    menu_id     bigint(20)  not null auto_increment comment '菜单ID',
    menu_name   varchar(50) not null comment '菜单名称',
    parent_id   bigint(20)   default 0 comment '父菜单ID',
    order_num   int(4)       default 0 comment '显示顺序',
    path        varchar(200) default '' comment '路由地址',
    component   varchar(255) default null comment '组件路径',
    query       varchar(255) default null comment '路由参数',
    is_frame    int(1)       default 1 comment '是否为外链（0是 1否）',
    is_cache    int(1)       default 0 comment '是否缓存（0缓存 1不缓存）',
    menu_type   char(1)      default '' comment '菜单类型（M目录 C菜单 F按钮）',
    visible     char(1)      default 0 comment '菜单状态（0显示 1隐藏）',
    status      char(1)      default 0 comment '菜单状态（0正常 1停用）',
    perms       varchar(100) default null comment '权限标识',
    icon        varchar(100) default '#' comment '菜单图标',
    create_by   varchar(64)  default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64)  default '' comment '更新者',
    update_time datetime comment '更新时间',
    remark      varchar(500) default '' comment '备注',
    primary key (menu_id)
) engine = innodb
  auto_increment = 2000 comment = '菜单权限表'
  character set 'utf8mb4';

-- ----------------------------
-- 初始化-菜单信息表数据
-- ----------------------------
-- 一级菜单
insert into sys_menu
values ('1', '系统管理', '0', '1', 'system', null, '', 1, 0, 'M', '0', '0', '', 'system', 'admin', sysdate(), '', null,
        '系统管理目录');
insert into sys_menu
values ('2', '系统监控', '0', '2', 'monitor', null, '', 1, 0, 'M', '0', '0', '', 'monitor', 'admin', sysdate(), '', null,
        '系统监控目录');
insert into sys_menu
values ('3', '系统工具', '0', '3', 'tool', null, '', 1, 0, 'M', '0', '0', '', 'tool', 'admin', sysdate(), '', null,
        '系统工具目录');
insert into sys_menu
values ('4', '若依官网', '0', '4', 'http://ruoyi.vip', null, '', 0, 0, 'M', '0', '0', '', 'guide', 'admin', sysdate(), '',
        null, '若依官网地址');
-- 二级菜单
insert into sys_menu
values ('100', '用户管理', '1', '1', 'user', 'system/user/index', '', 1, 0, 'C', '0', '0', 'system:user:list', 'user',
        'admin', sysdate(), '', null, '用户管理菜单');
insert into sys_menu
values ('101', '角色管理', '1', '2', 'role', 'system/role/index', '', 1, 0, 'C', '0', '0', 'system:role:list', 'peoples',
        'admin', sysdate(), '', null, '角色管理菜单');
insert into sys_menu
values ('102', '菜单管理', '1', '3', 'menu', 'system/menu/index', '', 1, 0, 'C', '0', '0', 'system:menu:list', 'tree-table',
        'admin', sysdate(), '', null, '菜单管理菜单');
insert into sys_menu
values ('103', '部门管理', '1', '4', 'dept', 'system/dept/index', '', 1, 0, 'C', '0', '0', 'system:dept:list', 'tree',
        'admin', sysdate(), '', null, '部门管理菜单');
insert into sys_menu
values ('104', '岗位管理', '1', '5', 'post', 'system/post/index', '', 1, 0, 'C', '0', '0', 'system:post:list', 'post',
        'admin', sysdate(), '', null, '岗位管理菜单');
insert into sys_menu
values ('105', '字典管理', '1', '6', 'dict', 'system/dict/index', '', 1, 0, 'C', '0', '0', 'system:dict:list', 'dict',
        'admin', sysdate(), '', null, '字典管理菜单');
insert into sys_menu
values ('106', '参数设置', '1', '7', 'config', 'system/config/index', '', 1, 0, 'C', '0', '0', 'system:config:list', 'edit',
        'admin', sysdate(), '', null, '参数设置菜单');
insert into sys_menu
values ('107', '通知公告', '1', '8', 'notice', 'system/notice/index', '', 1, 0, 'C', '0', '0', 'system:notice:list',
        'message', 'admin', sysdate(), '', null, '通知公告菜单');
insert into sys_menu
values ('108', '日志管理', '1', '9', 'log', '', '', 1, 0, 'M', '0', '0', '', 'log', 'admin', sysdate(), '', null, '日志管理菜单');
insert into sys_menu
values ('109', '在线用户', '2', '1', 'online', 'monitor/online/index', '', 1, 0, 'C', '0', '0', 'monitor:online:list',
        'online', 'admin', sysdate(), '', null, '在线用户菜单');
insert into sys_menu
values ('110', '定时任务', '2', '2', 'job', 'monitor/job/index', '', 1, 0, 'C', '0', '0', 'monitor:job:list', 'job',
        'admin', sysdate(), '', null, '定时任务菜单');
insert into sys_menu
values ('111', '数据监控', '2', '3', 'druid', 'monitor/druid/index', '', 1, 0, 'C', '0', '0', 'monitor:druid:list', 'druid',
        'admin', sysdate(), '', null, '数据监控菜单');
insert into sys_menu
values ('112', '服务监控', '2', '4', 'server', 'monitor/server/index', '', 1, 0, 'C', '0', '0', 'monitor:server:list',
        'server', 'admin', sysdate(), '', null, '服务监控菜单');
insert into sys_menu
values ('113', '缓存监控', '2', '5', 'cache', 'monitor/cache/index', '', 1, 0, 'C', '0', '0', 'monitor:cache:list', 'redis',
        'admin', sysdate(), '', null, '缓存监控菜单');
insert into sys_menu
values ('114', '缓存列表', '2', '6', 'cacheList', 'monitor/cache/list', '', 1, 0, 'C', '0', '0', 'monitor:cache:list',
        'redis-list', 'admin', sysdate(), '', null, '缓存列表菜单');
insert into sys_menu
values ('115', '表单构建', '3', '1', 'build', 'tool/build/index', '', 1, 0, 'C', '0', '0', 'tool:build:list', 'build',
        'admin', sysdate(), '', null, '表单构建菜单');
insert into sys_menu
values ('116', '代码生成', '3', '2', 'gen', 'tool/gen/index', '', 1, 0, 'C', '0', '0', 'tool:gen:list', 'code', 'admin',
        sysdate(), '', null, '代码生成菜单');
insert into sys_menu
values ('117', '系统接口', '3', '3', 'swagger', 'tool/swagger/index', '', 1, 0, 'C', '0', '0', 'tool:swagger:list',
        'swagger', 'admin', sysdate(), '', null, '系统接口菜单');
-- 三级菜单
insert into sys_menu
values ('500', '操作日志', '108', '1', 'operlog', 'monitor/operlog/index', '', 1, 0, 'C', '0', '0', 'monitor:operlog:list',
        'form', 'admin', sysdate(), '', null, '操作日志菜单');
insert into sys_menu
values ('501', '登录日志', '108', '2', 'logininfor', 'monitor/logininfor/index', '', 1, 0, 'C', '0', '0',
        'monitor:logininfor:list', 'logininfor', 'admin', sysdate(), '', null, '登录日志菜单');
-- 用户管理按钮
insert into sys_menu
values ('1000', '用户查询', '100', '1', '', '', '', 1, 0, 'F', '0', '0', 'system:user:query', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1001', '用户新增', '100', '2', '', '', '', 1, 0, 'F', '0', '0', 'system:user:add', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1002', '用户修改', '100', '3', '', '', '', 1, 0, 'F', '0', '0', 'system:user:edit', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1003', '用户删除', '100', '4', '', '', '', 1, 0, 'F', '0', '0', 'system:user:remove', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1004', '用户导出', '100', '5', '', '', '', 1, 0, 'F', '0', '0', 'system:user:export', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1005', '用户导入', '100', '6', '', '', '', 1, 0, 'F', '0', '0', 'system:user:import', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1006', '重置密码', '100', '7', '', '', '', 1, 0, 'F', '0', '0', 'system:user:resetPwd', '#', 'admin', sysdate(),
        '', null, '');
-- 角色管理按钮
insert into sys_menu
values ('1007', '角色查询', '101', '1', '', '', '', 1, 0, 'F', '0', '0', 'system:role:query', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1008', '角色新增', '101', '2', '', '', '', 1, 0, 'F', '0', '0', 'system:role:add', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1009', '角色修改', '101', '3', '', '', '', 1, 0, 'F', '0', '0', 'system:role:edit', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1010', '角色删除', '101', '4', '', '', '', 1, 0, 'F', '0', '0', 'system:role:remove', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1011', '角色导出', '101', '5', '', '', '', 1, 0, 'F', '0', '0', 'system:role:export', '#', 'admin', sysdate(), '',
        null, '');
-- 菜单管理按钮
insert into sys_menu
values ('1012', '菜单查询', '102', '1', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:query', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1013', '菜单新增', '102', '2', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:add', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1014', '菜单修改', '102', '3', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:edit', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1015', '菜单删除', '102', '4', '', '', '', 1, 0, 'F', '0', '0', 'system:menu:remove', '#', 'admin', sysdate(), '',
        null, '');
-- 部门管理按钮
insert into sys_menu
values ('1016', '部门查询', '103', '1', '', '', '', 1, 0, 'F', '0', '0', 'system:dept:query', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1017', '部门新增', '103', '2', '', '', '', 1, 0, 'F', '0', '0', 'system:dept:add', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1018', '部门修改', '103', '3', '', '', '', 1, 0, 'F', '0', '0', 'system:dept:edit', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1019', '部门删除', '103', '4', '', '', '', 1, 0, 'F', '0', '0', 'system:dept:remove', '#', 'admin', sysdate(), '',
        null, '');
-- 岗位管理按钮
insert into sys_menu
values ('1020', '岗位查询', '104', '1', '', '', '', 1, 0, 'F', '0', '0', 'system:post:query', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1021', '岗位新增', '104', '2', '', '', '', 1, 0, 'F', '0', '0', 'system:post:add', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1022', '岗位修改', '104', '3', '', '', '', 1, 0, 'F', '0', '0', 'system:post:edit', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1023', '岗位删除', '104', '4', '', '', '', 1, 0, 'F', '0', '0', 'system:post:remove', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1024', '岗位导出', '104', '5', '', '', '', 1, 0, 'F', '0', '0', 'system:post:export', '#', 'admin', sysdate(), '',
        null, '');
-- 字典管理按钮
insert into sys_menu
values ('1025', '字典查询', '105', '1', '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:query', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1026', '字典新增', '105', '2', '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:add', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1027', '字典修改', '105', '3', '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:edit', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1028', '字典删除', '105', '4', '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:remove', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1029', '字典导出', '105', '5', '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:export', '#', 'admin', sysdate(), '',
        null, '');
-- 参数设置按钮
insert into sys_menu
values ('1030', '参数查询', '106', '1', '#', '', '', 1, 0, 'F', '0', '0', 'system:config:query', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1031', '参数新增', '106', '2', '#', '', '', 1, 0, 'F', '0', '0', 'system:config:add', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1032', '参数修改', '106', '3', '#', '', '', 1, 0, 'F', '0', '0', 'system:config:edit', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1033', '参数删除', '106', '4', '#', '', '', 1, 0, 'F', '0', '0', 'system:config:remove', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1034', '参数导出', '106', '5', '#', '', '', 1, 0, 'F', '0', '0', 'system:config:export', '#', 'admin', sysdate(),
        '', null, '');
-- 通知公告按钮
insert into sys_menu
values ('1035', '公告查询', '107', '1', '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:query', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1036', '公告新增', '107', '2', '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:add', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1037', '公告修改', '107', '3', '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:edit', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1038', '公告删除', '107', '4', '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:remove', '#', 'admin', sysdate(),
        '', null, '');
-- 操作日志按钮
insert into sys_menu
values ('1039', '操作查询', '500', '1', '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:query', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1040', '操作删除', '500', '2', '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:remove', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1041', '日志导出', '500', '3', '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:export', '#', 'admin', sysdate(),
        '', null, '');
-- 登录日志按钮
insert into sys_menu
values ('1042', '登录查询', '501', '1', '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:query', '#', 'admin',
        sysdate(), '', null, '');
insert into sys_menu
values ('1043', '登录删除', '501', '2', '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:remove', '#', 'admin',
        sysdate(), '', null, '');
insert into sys_menu
values ('1044', '日志导出', '501', '3', '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:export', '#', 'admin',
        sysdate(), '', null, '');
insert into sys_menu
values ('1045', '账户解锁', '501', '4', '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:unlock', '#', 'admin',
        sysdate(), '', null, '');
-- 在线用户按钮
insert into sys_menu
values ('1046', '在线查询', '109', '1', '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:query', '#', 'admin', sysdate(),
        '', null, '');
insert into sys_menu
values ('1047', '批量强退', '109', '2', '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:batchLogout', '#', 'admin',
        sysdate(), '', null, '');
insert into sys_menu
values ('1048', '单条强退', '109', '3', '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:forceLogout', '#', 'admin',
        sysdate(), '', null, '');
-- 定时任务按钮
insert into sys_menu
values ('1049', '任务查询', '110', '1', '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:query', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1050', '任务新增', '110', '2', '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:add', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1051', '任务修改', '110', '3', '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:edit', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1052', '任务删除', '110', '4', '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:remove', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1053', '状态修改', '110', '5', '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:changeStatus', '#', 'admin',
        sysdate(), '', null, '');
insert into sys_menu
values ('1054', '任务导出', '110', '6', '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:export', '#', 'admin', sysdate(), '',
        null, '');
-- 代码生成按钮
insert into sys_menu
values ('1055', '生成查询', '116', '1', '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:query', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1056', '生成修改', '116', '2', '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:edit', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1057', '生成删除', '116', '3', '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:remove', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1058', '导入代码', '116', '4', '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:import', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1059', '预览代码', '116', '5', '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:preview', '#', 'admin', sysdate(), '',
        null, '');
insert into sys_menu
values ('1060', '生成代码', '116', '6', '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:code', '#', 'admin', sysdate(), '',
        null, '');


-- ----------------------------
-- 6、用户和角色关联表  用户N-1角色
-- ----------------------------
drop table if exists sys_user_role;
create table sys_user_role
(
    user_id bigint(20) not null comment '用户ID',
    role_id bigint(20) not null comment '角色ID',
    primary key (user_id, role_id)
) engine = innodb comment = '用户和角色关联表'
  character set 'utf8mb4';

-- ----------------------------
-- 初始化-用户和角色关联表数据
-- ----------------------------
insert into sys_user_role
values ('1', '1');
insert into sys_user_role
values ('2', '2');


-- ----------------------------
-- 7、角色和菜单关联表  角色1-N菜单
-- ----------------------------
drop table if exists sys_role_menu;
create table sys_role_menu
(
    role_id bigint(20) not null comment '角色ID',
    menu_id bigint(20) not null comment '菜单ID',
    primary key (role_id, menu_id)
) engine = innodb comment = '角色和菜单关联表'
  character set 'utf8mb4';

-- ----------------------------
-- 初始化-角色和菜单关联表数据
-- ----------------------------
insert into sys_role_menu
values ('2', '1');
insert into sys_role_menu
values ('2', '2');
insert into sys_role_menu
values ('2', '3');
insert into sys_role_menu
values ('2', '4');
insert into sys_role_menu
values ('2', '100');
insert into sys_role_menu
values ('2', '101');
insert into sys_role_menu
values ('2', '102');
insert into sys_role_menu
values ('2', '103');
insert into sys_role_menu
values ('2', '104');
insert into sys_role_menu
values ('2', '105');
insert into sys_role_menu
values ('2', '106');
insert into sys_role_menu
values ('2', '107');
insert into sys_role_menu
values ('2', '108');
insert into sys_role_menu
values ('2', '109');
insert into sys_role_menu
values ('2', '110');
insert into sys_role_menu
values ('2', '111');
insert into sys_role_menu
values ('2', '112');
insert into sys_role_menu
values ('2', '113');
insert into sys_role_menu
values ('2', '114');
insert into sys_role_menu
values ('2', '115');
insert into sys_role_menu
values ('2', '116');
insert into sys_role_menu
values ('2', '117');
insert into sys_role_menu
values ('2', '500');
insert into sys_role_menu
values ('2', '501');
insert into sys_role_menu
values ('2', '1000');
insert into sys_role_menu
values ('2', '1001');
insert into sys_role_menu
values ('2', '1002');
insert into sys_role_menu
values ('2', '1003');
insert into sys_role_menu
values ('2', '1004');
insert into sys_role_menu
values ('2', '1005');
insert into sys_role_menu
values ('2', '1006');
insert into sys_role_menu
values ('2', '1007');
insert into sys_role_menu
values ('2', '1008');
insert into sys_role_menu
values ('2', '1009');
insert into sys_role_menu
values ('2', '1010');
insert into sys_role_menu
values ('2', '1011');
insert into sys_role_menu
values ('2', '1012');
insert into sys_role_menu
values ('2', '1013');
insert into sys_role_menu
values ('2', '1014');
insert into sys_role_menu
values ('2', '1015');
insert into sys_role_menu
values ('2', '1016');
insert into sys_role_menu
values ('2', '1017');
insert into sys_role_menu
values ('2', '1018');
insert into sys_role_menu
values ('2', '1019');
insert into sys_role_menu
values ('2', '1020');
insert into sys_role_menu
values ('2', '1021');
insert into sys_role_menu
values ('2', '1022');
insert into sys_role_menu
values ('2', '1023');
insert into sys_role_menu
values ('2', '1024');
insert into sys_role_menu
values ('2', '1025');
insert into sys_role_menu
values ('2', '1026');
insert into sys_role_menu
values ('2', '1027');
insert into sys_role_menu
values ('2', '1028');
insert into sys_role_menu
values ('2', '1029');
insert into sys_role_menu
values ('2', '1030');
insert into sys_role_menu
values ('2', '1031');
insert into sys_role_menu
values ('2', '1032');
insert into sys_role_menu
values ('2', '1033');
insert into sys_role_menu
values ('2', '1034');
insert into sys_role_menu
values ('2', '1035');
insert into sys_role_menu
values ('2', '1036');
insert into sys_role_menu
values ('2', '1037');
insert into sys_role_menu
values ('2', '1038');
insert into sys_role_menu
values ('2', '1039');
insert into sys_role_menu
values ('2', '1040');
insert into sys_role_menu
values ('2', '1041');
insert into sys_role_menu
values ('2', '1042');
insert into sys_role_menu
values ('2', '1043');
insert into sys_role_menu
values ('2', '1044');
insert into sys_role_menu
values ('2', '1045');
insert into sys_role_menu
values ('2', '1046');
insert into sys_role_menu
values ('2', '1047');
insert into sys_role_menu
values ('2', '1048');
insert into sys_role_menu
values ('2', '1049');
insert into sys_role_menu
values ('2', '1050');
insert into sys_role_menu
values ('2', '1051');
insert into sys_role_menu
values ('2', '1052');
insert into sys_role_menu
values ('2', '1053');
insert into sys_role_menu
values ('2', '1054');
insert into sys_role_menu
values ('2', '1055');
insert into sys_role_menu
values ('2', '1056');
insert into sys_role_menu
values ('2', '1057');
insert into sys_role_menu
values ('2', '1058');
insert into sys_role_menu
values ('2', '1059');
insert into sys_role_menu
values ('2', '1060');

-- ----------------------------
-- 8、角色和部门关联表  角色1-N部门
-- ----------------------------
drop table if exists sys_role_dept;
create table sys_role_dept
(
    role_id bigint(20) not null comment '角色ID',
    dept_id bigint(20) not null comment '部门ID',
    primary key (role_id, dept_id)
) engine = innodb comment = '角色和部门关联表'
  character set 'utf8mb4';

-- ----------------------------
-- 初始化-角色和部门关联表数据
-- ----------------------------
insert into sys_role_dept
values ('2', '100');
insert into sys_role_dept
values ('2', '101');
insert into sys_role_dept
values ('2', '105');


-- ----------------------------
-- 9、用户与岗位关联表  用户1-N岗位
-- ----------------------------
drop table if exists sys_user_post;
create table sys_user_post
(
    user_id bigint(20) not null comment '用户ID',
    post_id bigint(20) not null comment '岗位ID',
    primary key (user_id, post_id)
) engine = innodb comment = '用户与岗位关联表'
  character set 'utf8mb4';;

-- ----------------------------
-- 初始化-用户与岗位关联表数据
-- ----------------------------
insert into sys_user_post
values ('1', '1');
insert into sys_user_post
values ('2', '2');


-- ----------------------------
-- 10、操作日志记录
-- ----------------------------
drop table if exists sys_oper_log;
create table sys_oper_log
(
    oper_id        bigint(20) not null auto_increment comment '日志主键',
    title          varchar(50)   default '' comment '模块标题',
    business_type  int(2)        default 0 comment '业务类型（0其它 1新增 2修改 3删除）',
    method         varchar(200)  default '' comment '方法名称',
    request_method varchar(10)   default '' comment '请求方式',
    operator_type  int(1)        default 0 comment '操作类别（0其它 1后台用户 2手机端用户）',
    oper_name      varchar(50)   default '' comment '操作人员',
    dept_name      varchar(50)   default '' comment '部门名称',
    oper_url       varchar(255)  default '' comment '请求URL',
    oper_ip        varchar(128)  default '' comment '主机地址',
    oper_location  varchar(255)  default '' comment '操作地点',
    oper_param     varchar(2000) default '' comment '请求参数',
    json_result    varchar(2000) default '' comment '返回参数',
    status         int(1)        default 0 comment '操作状态（0正常 1异常）',
    error_msg      varchar(2000) default '' comment '错误消息',
    oper_time      datetime comment '操作时间',
    cost_time      bigint(20)    default 0 comment '消耗时间',
    primary key (oper_id),
    key idx_sys_oper_log_bt (business_type),
    key idx_sys_oper_log_s (status),
    key idx_sys_oper_log_ot (oper_time)
) engine = innodb
  auto_increment = 100 comment = '操作日志记录'
  character set 'utf8mb4';


-- ----------------------------
-- 11、字典类型表
-- ----------------------------
drop table if exists sys_dict_type;
create table sys_dict_type
(
    dict_id     bigint(20) not null auto_increment comment '字典主键',
    dict_name   varchar(100) default '' comment '字典名称',
    dict_type   varchar(100) default '' comment '字典类型',
    status      char(1)      default '0' comment '状态（0正常 1停用）',
    create_by   varchar(64)  default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64)  default '' comment '更新者',
    update_time datetime comment '更新时间',
    remark      varchar(500) default null comment '备注',
    primary key (dict_id),
    unique (dict_type)
) engine = innodb
  auto_increment = 100 comment = '字典类型表'
  character set 'utf8mb4';

insert into sys_dict_type
values (1, '用户性别', 'sys_user_sex', '0', 'admin', sysdate(), '', null, '用户性别列表');
insert into sys_dict_type
values (2, '菜单状态', 'sys_show_hide', '0', 'admin', sysdate(), '', null, '菜单状态列表');
insert into sys_dict_type
values (3, '系统开关', 'sys_normal_disable', '0', 'admin', sysdate(), '', null, '系统开关列表');
insert into sys_dict_type
values (4, '任务状态', 'sys_job_status', '0', 'admin', sysdate(), '', null, '任务状态列表');
insert into sys_dict_type
values (5, '任务分组', 'sys_job_group', '0', 'admin', sysdate(), '', null, '任务分组列表');
insert into sys_dict_type
values (6, '系统是否', 'sys_yes_no', '0', 'admin', sysdate(), '', null, '系统是否列表');
insert into sys_dict_type
values (7, '通知类型', 'sys_notice_type', '0', 'admin', sysdate(), '', null, '通知类型列表');
insert into sys_dict_type
values (8, '通知状态', 'sys_notice_status', '0', 'admin', sysdate(), '', null, '通知状态列表');
insert into sys_dict_type
values (9, '操作类型', 'sys_oper_type', '0', 'admin', sysdate(), '', null, '操作类型列表');
insert into sys_dict_type
values (10, '系统状态', 'sys_common_status', '0', 'admin', sysdate(), '', null, '登录状态列表');


-- ----------------------------
-- 12、字典数据表
-- ----------------------------
drop table if exists sys_dict_data;
create table sys_dict_data
(
    dict_code   bigint(20) not null auto_increment comment '字典编码',
    dict_sort   int(4)       default 0 comment '字典排序',
    dict_label  varchar(100) default '' comment '字典标签',
    dict_value  varchar(100) default '' comment '字典键值',
    dict_type   varchar(100) default '' comment '字典类型',
    css_class   varchar(100) default null comment '样式属性（其他样式扩展）',
    list_class  varchar(100) default null comment '表格回显样式',
    is_default  char(1)      default 'N' comment '是否默认（Y是 N否）',
    status      char(1)      default '0' comment '状态（0正常 1停用）',
    create_by   varchar(64)  default '' comment '创建者',
    create_time datetime comment '创建时间',
    update_by   varchar(64)  default '' comment '更新者',
    update_time datetime comment '更新时间',
    remark      varchar(500) default null comment '备注',
    primary key (dict_code)
) engine = innodb
  auto_increment = 100 comment = '字典数据表'
  character set 'utf8mb4';

insert into sys_dict_data
values (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', sysdate(), '', null, '性别男');
insert into sys_dict_data
values (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', sysdate(), '', null, '性别女');
insert into sys_dict_data
values (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', sysdate(), '', null, '性别未知');
insert into sys_dict_data
values (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', sysdate(), '', null, '显示菜单');
insert into sys_dict_data
values (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', sysdate(), '', null, '隐藏菜单');
insert into sys_dict_data
values (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', sysdate(), '', null, '正常状态');
insert into sys_dict_data
values (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', sysdate(), '', null, '停用状态');
insert into sys_dict_data
values (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', sysdate(), '', null, '正常状态');
insert into sys_dict_data
values (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', sysdate(), '', null, '停用状态');
insert into sys_dict_data
values (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', sysdate(), '', null, '默认分组');
insert into sys_dict_data
values (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', sysdate(), '', null, '系统分组');
insert into sys_dict_data
values (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', sysdate(), '', null, '系统默认是');
insert into sys_dict_data
values (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', sysdate(), '', null, '系统默认否');
insert into sys_dict_data
values (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', sysdate(), '', null, '通知');
insert into sys_dict_data
values (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', sysdate(), '', null, '公告');
insert into sys_dict_data
values (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', sysdate(), '', null, '正常状态');
insert into sys_dict_data
values (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', sysdate(), '', null, '关闭状态');
insert into sys_dict_data
values (18, 99, '其他', '0', 'sys_oper_type', '', 'info', 'N', '0', 'admin', sysdate(), '', null, '其他操作');
insert into sys_dict_data
values (19, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', sysdate(), '', null, '新增操作');
insert into sys_dict_data
values (20, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', sysdate(), '', null, '修改操作');
insert into sys_dict_data
values (21, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', sysdate(), '', null, '删除操作');
insert into sys_dict_data
values (22, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', sysdate(), '', null, '授权操作');
insert into sys_dict_data
values (23, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', sysdate(), '', null, '导出操作');
insert into sys_dict_data
values (24, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', sysdate(), '', null, '导入操作');
insert into sys_dict_data
values (25, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', sysdate(), '', null, '强退操作');
insert into sys_dict_data
values (26, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', sysdate(), '', null, '生成操作');
insert into sys_dict_data
values (27, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', sysdate(), '', null, '清空操作');
insert into sys_dict_data
values (28, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', sysdate(), '', null, '正常状态');
insert into sys_dict_data
values (29, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', sysdate(), '', null, '停用状态');


-- ----------------------------
-- 13、参数配置表
-- ----------------------------
drop table if exists sys_config;
create table sys_config
(
    config_id    int(5) not null auto_increment comment '参数主键',
    config_name  varchar(100) default '' comment '参数名称',
    config_key   varchar(100) default '' comment '参数键名',
    config_value varchar(500) default '' comment '参数键值',
    config_type  char(1)      default 'N' comment '系统内置（Y是 N否）',
    create_by    varchar(64)  default '' comment '创建者',
    create_time  datetime comment '创建时间',
    update_by    varchar(64)  default '' comment '更新者',
    update_time  datetime comment '更新时间',
    remark       varchar(500) default null comment '备注',
    primary key (config_id)
) engine = innodb
  auto_increment = 100 comment = '参数配置表'
  character set 'utf8mb4';

insert into sys_config
values (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', sysdate(), '', null,
        '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
insert into sys_config
values (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', sysdate(), '', null, '初始化密码 123456');
insert into sys_config
values (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', sysdate(), '', null,
        '深色主题theme-dark，浅色主题theme-light');
insert into sys_config
values (4, '账号自助-验证码开关', 'sys.account.captchaEnabled', 'true', 'Y', 'admin', sysdate(), '', null,
        '是否开启验证码功能（true开启，false关闭）');
insert into sys_config
values (5, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', 'admin', sysdate(), '', null,
        '是否开启注册用户功能（true开启，false关闭）');
insert into sys_config
values (6, '用户登录-黑名单列表', 'sys.login.blackIPList', '', 'Y', 'admin', sysdate(), '', null,
        '设置登录IP黑名单限制，多个匹配项以;分隔，支持匹配（*通配、网段）');


-- ----------------------------
-- 14、系统访问记录
-- ----------------------------
drop table if exists sys_logininfor;
create table sys_logininfor
(
    info_id        bigint(20) not null auto_increment comment '访问ID',
    user_name      varchar(50)  default '' comment '用户账号',
    ipaddr         varchar(128) default '' comment '登录IP地址',
    login_location varchar(255) default '' comment '登录地点',
    browser        varchar(50)  default '' comment '浏览器类型',
    os             varchar(50)  default '' comment '操作系统',
    status         char(1)      default '0' comment '登录状态（0成功 1失败）',
    msg            varchar(255) default '' comment '提示消息',
    login_time     datetime comment '访问时间',
    primary key (info_id),
    key idx_sys_logininfor_s (status),
    key idx_sys_logininfor_lt (login_time)
) engine = innodb
  auto_increment = 100 comment = '系统访问记录'
  character set 'utf8mb4';


-- ----------------------------
-- 15、定时任务调度表
-- ----------------------------
drop table if exists sys_job;
create table sys_job
(
    job_id          bigint(20)   not null auto_increment comment '任务ID',
    job_name        varchar(64)  default '' comment '任务名称',
    job_group       varchar(64)  default 'DEFAULT' comment '任务组名',
    invoke_target   varchar(500) not null comment '调用目标字符串',
    cron_expression varchar(255) default '' comment 'cron执行表达式',
    misfire_policy  varchar(20)  default '3' comment '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
    concurrent      char(1)      default '1' comment '是否并发执行（0允许 1禁止）',
    status          char(1)      default '0' comment '状态（0正常 1暂停）',
    create_by       varchar(64)  default '' comment '创建者',
    create_time     datetime comment '创建时间',
    update_by       varchar(64)  default '' comment '更新者',
    update_time     datetime comment '更新时间',
    remark          varchar(500) default '' comment '备注信息',
    primary key (job_id, job_name, job_group)
) engine = innodb
  auto_increment = 100 comment = '定时任务调度表'
  character set 'utf8mb4';

insert into sys_job
values (1, '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '0/10 * * * * ?', '3', '1', '1', 'admin', sysdate(), '', null,
        '');
insert into sys_job
values (2, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '0/15 * * * * ?', '3', '1', '1', 'admin', sysdate(), '',
        null, '');
insert into sys_job
values (3, '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3',
        '1', '1', 'admin', sysdate(), '', null, '');


-- ----------------------------
-- 16、定时任务调度日志表
-- ----------------------------
drop table if exists sys_job_log;
create table sys_job_log
(
    job_log_id     bigint(20)   not null auto_increment comment '任务日志ID',
    job_name       varchar(64)  not null comment '任务名称',
    job_group      varchar(64)  not null comment '任务组名',
    invoke_target  varchar(500) not null comment '调用目标字符串',
    job_message    varchar(500) comment '日志信息',
    status         char(1)       default '0' comment '执行状态（0正常 1失败）',
    exception_info varchar(2000) default '' comment '异常信息',
    create_time    datetime comment '创建时间',
    primary key (job_log_id)
) engine = innodb comment = '定时任务调度日志表'
  character set 'utf8mb4';


-- ----------------------------
-- 17、通知公告表
-- ----------------------------
drop table if exists sys_notice;
create table sys_notice
(
    notice_id      int(4)      not null auto_increment comment '公告ID',
    notice_title   varchar(50) not null comment '公告标题',
    notice_type    char(1)     not null comment '公告类型（1通知 2公告）',
    notice_content longblob     default null comment '公告内容',
    status         char(1)      default '0' comment '公告状态（0正常 1关闭）',
    create_by      varchar(64)  default '' comment '创建者',
    create_time    datetime comment '创建时间',
    update_by      varchar(64)  default '' comment '更新者',
    update_time    datetime comment '更新时间',
    remark         varchar(255) default null comment '备注',
    primary key (notice_id)
) engine = innodb
  auto_increment = 10 comment = '通知公告表'
  character set 'utf8mb4';

-- ----------------------------
-- 初始化-公告信息表数据
-- ----------------------------
insert into sys_notice
values ('1', '温馨提醒：2018-07-01 若依新版本发布啦', '2', '新版本内容', '0', 'admin', sysdate(), '', null, '管理员');
insert into sys_notice
values ('2', '维护通知：2018-07-01 若依系统凌晨维护', '1', '维护内容', '0', 'admin', sysdate(), '', null, '管理员');


-- ----------------------------
-- 18、代码生成业务表
-- ----------------------------
drop table if exists gen_table;
create table gen_table
(
    table_id          bigint(20) not null auto_increment comment '编号',
    table_name        varchar(200) default '' comment '表名称',
    table_comment     varchar(500) default '' comment '表描述',
    sub_table_name    varchar(64)  default null comment '关联子表的表名',
    sub_table_fk_name varchar(64)  default null comment '子表关联的外键名',
    class_name        varchar(100) default '' comment '实体类名称',
    tpl_category      varchar(200) default 'crud' comment '使用的模板（crud单表操作 tree树表操作）',
    tpl_web_type      varchar(30)  default '' comment '前端模板类型（element-ui模版 element-plus模版）',
    package_name      varchar(100) comment '生成包路径',
    module_name       varchar(30) comment '生成模块名',
    business_name     varchar(30) comment '生成业务名',
    function_name     varchar(50) comment '生成功能名',
    function_author   varchar(50) comment '生成功能作者',
    gen_type          char(1)      default '0' comment '生成代码方式（0zip压缩包 1自定义路径）',
    gen_path          varchar(200) default '/' comment '生成路径（不填默认项目路径）',
    options           varchar(1000) comment '其它生成选项',
    create_by         varchar(64)  default '' comment '创建者',
    create_time       datetime comment '创建时间',
    update_by         varchar(64)  default '' comment '更新者',
    update_time       datetime comment '更新时间',
    remark            varchar(500) default null comment '备注',
    primary key (table_id)
) engine = innodb
  auto_increment = 1 comment = '代码生成业务表'
  character set 'utf8mb4';


-- ----------------------------
-- 19、代码生成业务表字段
-- ----------------------------
drop table if exists gen_table_column;
create table gen_table_column
(
    column_id      bigint(20) not null auto_increment comment '编号',
    table_id       bigint(20) comment '归属表编号',
    column_name    varchar(200) comment '列名称',
    column_comment varchar(500) comment '列描述',
    column_type    varchar(100) comment '列类型',
    java_type      varchar(500) comment 'JAVA类型',
    java_field     varchar(200) comment 'JAVA字段名',
    is_pk          char(1) comment '是否主键（1是）',
    is_increment   char(1) comment '是否自增（1是）',
    is_required    char(1) comment '是否必填（1是）',
    is_insert      char(1) comment '是否为插入字段（1是）',
    is_edit        char(1) comment '是否编辑字段（1是）',
    is_list        char(1) comment '是否列表字段（1是）',
    is_query       char(1) comment '是否查询字段（1是）',
    query_type     varchar(200) default 'EQ' comment '查询方式（等于、不等于、大于、小于、范围）',
    html_type      varchar(200) comment '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
    dict_type      varchar(200) default '' comment '字典类型',
    sort           int comment '排序',
    create_by      varchar(64)  default '' comment '创建者',
    create_time    datetime comment '创建时间',
    update_by      varchar(64)  default '' comment '更新者',
    update_time    datetime comment '更新时间',
    primary key (column_id)
) engine = innodb
  auto_increment = 1 comment = '代码生成业务表字段'
  character set 'utf8mb4';

drop table if exists library_table_info;
create table library_table_info
(
    `id`         bigint PRIMARY KEY AUTO_INCREMENT,
    `tableName`  varchar(50) not null,
    `originName` varchar(50) not null default '',
    `createBy`   varchar(64) COMMENT '创建人ID',
    `createTime` datetime             DEFAULT now() COMMENT '创建时间',
    `delete`     char(1)              DEFAULT '0' COMMENT '是否删除标记, 1:已删除，0：未删除',
    `updateTime` datetime             DEFAULT now() COMMENT '删除时间',
    `updateBy`   varchar(64) COMMENT '删除人ID',
    `remark`     varchar(200)         DEFAULT '' COMMENT '备注信息',
    `status`     char(1)              DEFAULT '1' COMMENT '状态，1:启用，0：停用'
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '题库'
  ROW_FORMAT = Dynamic;


drop table if exists weather_district_info;
create table weather_district_info
(
    id                int auto_increment
        primary key,
    no                varchar(10)   default ''                not null,
    province          varchar(100)  default ''                not null,
    city              varchar(100)  default ''                not null,
    city_geo_code     varchar(6)    default '000000'          null comment '创建人ID',
    district          varchar(100)  default ''                null comment '创建时间',
    district_geo_code char(6)       default '000000'          null comment '是否删除标记, 1:已删除，0：未删除',
    lon               decimal(9, 6) default 0.000000          null,
    lat               decimal(9, 6) default 0.000000          null,
    type              char          default '0'               null comment '省0（直辖市1），省会2，市3，县4（区5）',
    createBy          varchar(64)                             null comment '创建人ID',
    createTime        datetime      default CURRENT_TIMESTAMP null comment '创建时间',
    `delete`          char          default '0'               null comment '是否删除标记, 1:已删除，0：未删除',
    updateTime        datetime      default CURRENT_TIMESTAMP null comment '删除时间',
    updateBy          varchar(64)                             null comment '删除人ID',
    remark            varchar(200)  default ''                null comment '备注信息',
    status            char          default '1'               null comment '状态，1:启用，0：停用'
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '城市-经纬信息表'
  ROW_FORMAT = Dynamic;



create table weather_type_pic_mapping
(
    `id`         int PRIMARY KEY AUTO_INCREMENT,
    `code`       varchar(5)   not null DEFAULT '',
    `name`       varchar(50)  not null DEFAULT '',
    `day_name`   varchar(50)  not null DEFAULT '',
    `night_name` varchar(50)  not null DEFAULT '',
    `path`       varchar(100) not null default '',
    `createBy`   varchar(64) COMMENT '创建人ID',
    `createTime` datetime              DEFAULT now() COMMENT '创建时间',
    `delete`     char(1)               DEFAULT '0' COMMENT '是否删除标记, 1:已删除，0：未删除',
    `updateTime` datetime              DEFAULT now() COMMENT '删除时间',
    `updateBy`   varchar(64) COMMENT '删除人ID',
    `remark`     varchar(200)          DEFAULT '' COMMENT '备注信息',
    `status`     char(1)               DEFAULT '1' COMMENT '状态，1:启用，0：停用'
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '天气-图片对应关系表'
  ROW_FORMAT = Dynamic;

INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (46, '00', '晴', 'Sunny', 'Clear', '/icon/weather/weather_Sunny.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (47, '01', '多云', 'Cloudy', 'Cloudy', '/icon/weather/weather_Cloudy.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (48, '02', '阴', 'Overcast', 'Overcast', '/icon/weather/weather_Overcast.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (49, '03', '阵雨', 'Shower', 'Shower', '/icon/weather/weather_Shower.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (50, '04', '雷阵雨', 'Thundershower', 'Thundershower', '/icon/weather/weather_Thundershower.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (51, '05', '雷阵雨伴有冰雹', 'Thundershower with hail', 'Thundershower with hail',
        '/icon/weather/weather_Thundershower with hail.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (52, '06', '雨夹雪', 'Sleet', 'Sleet', '/icon/weather/weather_Sleet.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (53, '07', '小雨', 'Light rain', 'Light rain', '/icon/weather/weather_Light rain.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (54, '08', '中雨', 'Moderate rain', 'Moderate rain', '/icon/weather/weather_Moderate rain.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (55, '09', '大雨', 'Heavy rain', 'Heavy rain', '/icon/weather/weather_Heavy rain.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (56, '10', '暴雨', 'Storm', 'Storm', '/icon/weather/weather_Storm.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (57, '11', '大暴雨', 'Heavy storm', 'Heavy storm', '/icon/weather/weather_Heavy storm.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (58, '12', '特大暴雨', 'Severe storm', 'Severe storm', '/icon/weather/weather_Severe storm.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (59, '13', '阵雪', 'Snow flurry', 'Snow flurry', '/icon/weather/weather_Snow flurry.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (60, '14', '小雪', 'Light snow', 'Light snow', '/icon/weather/weather_Light snow.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (61, '15', '中雪', 'Moderate snow', 'Moderate snow', '/icon/weather/weather_Moderate snow.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (62, '16', '大雪', 'Heavy snow', 'Heavy snow', '/icon/weather/weather_Heavy snow.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (63, '17', '暴雪', 'Snowstorm', 'Snowstorm', '/icon/weather/weather_Snowstorm.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (64, '18', '雾', 'Fog', 'Fog', '/icon/weather/weather_Fog.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (65, '19', '冻雨', 'Ice rain', 'Ice rain', '/icon/weather/weather_Ice rain.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (66, '20', '沙尘暴', 'Duststorm', 'Duststorm', '/icon/weather/weather_Duststorm.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (67, '21', '小到中雨', 'Light to moderate rain', 'Light to moderate rain',
        '/icon/weather/weather_Light to moderate rain.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (68, '22', '中到大雨', 'Moderate to heavy rain', 'Moderate to heavy rain',
        '/icon/weather/weather_Moderate to heavy rain.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (69, '23', '大到暴雨', 'Heavy rain to storm', 'Heavy rain to storm', '/icon/weather/weather_Heavy rain to storm.png',
        null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (70, '24', '暴雨到大暴雨', 'Storm to heavy storm', 'Storm to heavy storm',
        '/icon/weather/weather_Storm to heavy storm.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null,
        '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (71, '25', '大暴雨到特大暴雨', 'Heavy to severe storm', 'Heavy to severe storm',
        '/icon/weather/weather_Heavy to severe storm.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (72, '26', '小到中雪', 'Light to moderate snow', 'Light to moderate snow',
        '/icon/weather/weather_Light to moderate snow.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (73, '27', '中到大雪', 'Moderate to heavy snow', 'Moderate to heavy snow',
        '/icon/weather/weather_Moderate to heavy snow.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (74, '28', '大到暴雪', 'Heavy snow to snowstorm', 'Heavy snow to snowstorm',
        '/icon/weather/weather_Heavy snow to snowstorm.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (75, '29', '浮尘', 'Dust', 'Dust', '/icon/weather/weather_Dust.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (76, '30', '扬沙', 'Sand', 'Sand', '/icon/weather/weather_Sand.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (77, '31', '强沙尘暴', 'Sandstorm', 'Sandstorm', '/icon/weather/weather_Sandstorm.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (78, '32', '浓雾', 'Dense fog', 'Dense fog', '/icon/weather/weather_Dense fog.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (79, '33', '龙卷风', 'Tornado', 'Tornado', '/icon/weather/weather_Tornado.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (80, '34', '弱高吹雪', 'Weak high blow snow', 'Weak high blow snow', '/icon/weather/weather_Weak high blow snow.png',
        null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (81, '35', '轻雾', 'Mist', 'Mist', '/icon/weather/weather_Mist.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (82, '49', '强浓雾', 'Heavy dense fog', 'Heavy dense fog', '/icon/weather/weather_Heavy dense fog.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (83, '53', '霾', 'Haze', 'Haze', '/icon/weather/weather_Haze.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (84, '54', '中度霾', 'Moderate haze', 'Moderate haze', '/icon/weather/weather_Moderate haze.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (85, '55', '重度霾', 'Severe haze', 'Severe haze', '/icon/weather/weather_Severe haze.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (86, '56', '严重霾', 'Hazardous haze', 'Hazardous haze', '/icon/weather/weather_Hazardous haze.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (87, '57', '大雾', 'Heavy fog', 'Heavy fog', '/icon/weather/weather_Heavy fog.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (88, '58', '特强浓雾', 'Extra-heavy dense fog', 'Extra-heavy dense fog',
        '/icon/weather/weather_Extra_heavy dense fog.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (89, '301', '雨', 'Rain', 'Rain', '/icon/weather/weather_Rain.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (90, '302', '雪', 'Snow', 'Snow', '/icon/weather/weather_Snow.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (91, '-1', '未知', 'unKnow', 'unKnow', '/icon/weather/weather_un_know.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');



INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (46, '00', '晴', 'Sunny', 'Clear', '/icon/weather/weather_Sunny.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (47, '01', '多云', 'Cloudy', 'Cloudy', '/icon/weather/weather_Cloudy.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (48, '02', '阴', 'Overcast', 'Overcast', '/icon/weather/weather_Overcast.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (49, '03', '阵雨', 'Shower', 'Shower', '/icon/weather/weather_Shower.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (50, '04', '雷阵雨', 'Thundershower', 'Thundershower', '/icon/weather/weather_Thundershower.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (51, '05', '雷阵雨伴有冰雹', 'Thundershower with hail', 'Thundershower with hail',
        '/icon/weather/weather_Thundershower with hail.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (52, '06', '雨夹雪', 'Sleet', 'Sleet', '/icon/weather/weather_Sleet.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (53, '07', '小雨', 'Light rain', 'Light rain', '/icon/weather/weather_Light rain.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (54, '08', '中雨', 'Moderate rain', 'Moderate rain', '/icon/weather/weather_Moderate rain.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (55, '09', '大雨', 'Heavy rain', 'Heavy rain', '/icon/weather/weather_Heavy rain.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (56, '10', '暴雨', 'Storm', 'Storm', '/icon/weather/weather_Storm.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (57, '11', '大暴雨', 'Heavy storm', 'Heavy storm', '/icon/weather/weather_Heavy storm.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (58, '12', '特大暴雨', 'Severe storm', 'Severe storm', '/icon/weather/weather_Severe storm.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (59, '13', '阵雪', 'Snow flurry', 'Snow flurry', '/icon/weather/weather_Snow flurry.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (60, '14', '小雪', 'Light snow', 'Light snow', '/icon/weather/weather_Light snow.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (61, '15', '中雪', 'Moderate snow', 'Moderate snow', '/icon/weather/weather_Moderate snow.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (62, '16', '大雪', 'Heavy snow', 'Heavy snow', '/icon/weather/weather_Heavy snow.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (63, '17', '暴雪', 'Snowstorm', 'Snowstorm', '/icon/weather/weather_Snowstorm.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (64, '18', '雾', 'Fog', 'Fog', '/icon/weather/weather_Fog.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (65, '19', '冻雨', 'Ice rain', 'Ice rain', '/icon/weather/weather_Ice rain.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (66, '20', '沙尘暴', 'Duststorm', 'Duststorm', '/icon/weather/weather_Duststorm.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (67, '21', '小到中雨', 'Light to moderate rain', 'Light to moderate rain',
        '/icon/weather/weather_Light to moderate rain.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (68, '22', '中到大雨', 'Moderate to heavy rain', 'Moderate to heavy rain',
        '/icon/weather/weather_Moderate to heavy rain.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (69, '23', '大到暴雨', 'Heavy rain to storm', 'Heavy rain to storm', '/icon/weather/weather_Heavy rain to storm.png',
        null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (70, '24', '暴雨到大暴雨', 'Storm to heavy storm', 'Storm to heavy storm',
        '/icon/weather/weather_Storm to heavy storm.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null,
        '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (71, '25', '大暴雨到特大暴雨', 'Heavy to severe storm', 'Heavy to severe storm',
        '/icon/weather/weather_Heavy to severe storm.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (72, '26', '小到中雪', 'Light to moderate snow', 'Light to moderate snow',
        '/icon/weather/weather_Light to moderate snow.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (73, '27', '中到大雪', 'Moderate to heavy snow', 'Moderate to heavy snow',
        '/icon/weather/weather_Moderate to heavy snow.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (74, '28', '大到暴雪', 'Heavy snow to snowstorm', 'Heavy snow to snowstorm',
        '/icon/weather/weather_Heavy snow to snowstorm.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (75, '29', '浮尘', 'Dust', 'Dust', '/icon/weather/weather_Dust.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (76, '30', '扬沙', 'Sand', 'Sand', '/icon/weather/weather_Sand.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (77, '31', '强沙尘暴', 'Sandstorm', 'Sandstorm', '/icon/weather/weather_Sandstorm.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (78, '32', '浓雾', 'Dense fog', 'Dense fog', '/icon/weather/weather_Dense fog.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (79, '33', '龙卷风', 'Tornado', 'Tornado', '/icon/weather/weather_Tornado.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (80, '34', '弱高吹雪', 'Weak high blow snow', 'Weak high blow snow', '/icon/weather/weather_Weak high blow snow.png',
        null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (81, '35', '轻雾', 'Mist', 'Mist', '/icon/weather/weather_Mist.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (82, '49', '强浓雾', 'Heavy dense fog', 'Heavy dense fog', '/icon/weather/weather_Heavy dense fog.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (83, '53', '霾', 'Haze', 'Haze', '/icon/weather/weather_Haze.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (84, '54', '中度霾', 'Moderate haze', 'Moderate haze', '/icon/weather/weather_Moderate haze.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (85, '55', '重度霾', 'Severe haze', 'Severe haze', '/icon/weather/weather_Severe haze.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (86, '56', '严重霾', 'Hazardous haze', 'Hazardous haze', '/icon/weather/weather_Hazardous haze.png', null,
        '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (87, '57', '大雾', 'Heavy fog', 'Heavy fog', '/icon/weather/weather_Heavy fog.png', null, '2024-09-02 07:56:39',
        '0', '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (88, '58', '特强浓雾', 'Extra-heavy dense fog', 'Extra-heavy dense fog',
        '/icon/weather/weather_Extra_heavy dense fog.png', null, '2024-09-02 07:56:39', '0', '2024-09-02 07:56:39',
        null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (89, '301', '雨', 'Rain', 'Rain', '/icon/weather/weather_Rain.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (90, '302', '雪', 'Snow', 'Snow', '/icon/weather/weather_Snow.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');
INSERT INTO test.weather_type_pic_mapping (id, code, name, day_name, night_name, path, createBy, createTime, `delete`,
                                           updateTime, updateBy, remark, status)
VALUES (91, '-1', '未知', 'unKnow', 'unKnow', '/icon/weather/weather_un_know.png', null, '2024-09-02 07:56:39', '0',
        '2024-09-02 07:56:39', null, '', '1');



create table translate_info_cache
(
    `id`               int PRIMARY KEY AUTO_INCREMENT,
    `hash`             varchar(50)   not null DEFAULT '',
    `from`             varchar(5)    not null DEFAULT '',
    `to`               varchar(5)    not null DEFAULT '',

    `trans_result_src` varchar(1000) not null DEFAULT '',
    `trans_result_dst` varchar(5000) not null default '',

    `createBy`         varchar(64) COMMENT '创建人ID',
    `createTime`       datetime               DEFAULT now() COMMENT '创建时间',
    `delete`           char(1)                DEFAULT '0' COMMENT '是否删除标记, 1:已删除，0：未删除',
    `updateTime`       datetime               DEFAULT now() COMMENT '删除时间',
    `updateBy`         varchar(64) COMMENT '删除人ID',
    `remark`           varchar(200)           DEFAULT '' COMMENT '备注信息',
    `status`           char(1)                DEFAULT '1' COMMENT '状态，1:启用，0：停用'
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '翻译结果表'
  ROW_FORMAT = Dynamic;
CREATE UNIQUE INDEX translate_info_cache_hash_index ON translate_info_cache (hash);

create table translate_lan
(
    `id`         int PRIMARY KEY AUTO_INCREMENT,
    `name`       varchar(100) not null DEFAULT '',
    `code`       varchar(10)  not null DEFAULT '',
    `tag`        varchar(10)  not null DEFAULT '',
    `sort`       varchar(2)   not null DEFAULT '',
    `from_hot`   int          not null default 0 COMMENT '热度',
    `to_hot`     int          not null default 0 COMMENT '热度',
    `createBy`   varchar(64) COMMENT '创建人ID',
    `createTime` datetime              DEFAULT now() COMMENT '创建时间',
    `delete`     char(1)               DEFAULT '0' COMMENT '是否删除标记, 1:已删除，0：未删除',
    `updateTime` datetime              DEFAULT now() COMMENT '删除时间',
    `updateBy`   varchar(64) COMMENT '删除人ID',
    `remark`     varchar(200)          DEFAULT '' COMMENT '备注信息',
    `status`     char(1)               DEFAULT '1' COMMENT '状态，1:启用，0：停用'
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '翻译支持的语言类型表'
  ROW_FORMAT = Dynamic;

INSERT INTO translate_lan (`name`, `code`, `tag`, `sort`)
VALUES ('阿拉伯语', 'ara', '是', 'A'),
       ('爱尔兰语', 'gle', '是', 'A'),
       ('奥克语', 'oci', '是', 'A'),
       ('阿尔巴尼亚语', 'alb', '是', 'A'),
       ('阿尔及利亚阿拉伯语', 'arq', '否', 'A'),
       ('阿肯语', 'aka', '否', 'A'),
       ('阿拉贡语', 'arg', '否', 'A'),
       ('阿姆哈拉语', 'amh', '是', 'A'),
       ('阿萨姆语', 'asm', '是', 'A'),
       ('艾马拉语', 'aym', '否', 'A'),
       ('阿塞拜疆语', 'aze', '是', 'A'),
       ('阿斯图里亚斯语', 'ast', '是', 'A'),
       ('奥塞梯语', 'oss', '否', 'A'),
       ('爱沙尼亚语', 'est', '是', 'A'),
       ('奥杰布瓦语', 'oji', '否', 'A'),
       ('奥里亚语', 'ori', '是', 'A'),
       ('奥罗莫语', 'orm', '否', 'A'),
       ('波兰语', 'pl', '是', 'B'),
       ('波斯语', 'per', '是', 'B'),
       ('布列塔尼语', 'bre', '是', 'B'),
       ('巴什基尔语', 'bak', '否', 'B'),
       ('巴斯克语', 'baq', '是', 'B'),
       ('巴西葡萄牙语', 'pot', '否', 'B'),
       ('白俄罗斯语', 'bel', '是', 'B'),
       ('柏柏尔语', 'ber', '是', 'B'),
       ('邦板牙语', 'pam', '否', 'B'),
       ('保加利亚语', 'bul', '是', 'B'),
       ('北方萨米语', 'sme', '否', 'B'),
       ('北索托语', 'ped', '否', 'B'),
       ('本巴语', 'bem', '否', 'B'),
       ('比林语', 'bli', '否', 'B'),
       ('比斯拉马语', 'bis', '否', 'B'),
       ('俾路支语', 'bal', '否', 'B'),
       ('冰岛语', 'ice', '是', 'B'),
       ('波斯尼亚语', 'bos', '是', 'B'),
       ('博杰普尔语', 'bho', '否', 'B'),
       ('楚瓦什语', 'chv', '否', 'C'),
       ('聪加语', 'tso', '否', 'C'),
       ('丹麦语', 'dan', '是', 'D'),
       ('德语', 'de', '是', 'D'),
       ('鞑靼语', 'tat', '是', 'D'),
       ('掸语', 'sha', '否', 'D'),
       ('德顿语', 'tet', '否', 'D'),
       ('迪维希语', 'div', '否', 'D'),
       ('低地德语', 'log', '是', 'D'),
       ('俄语', 'ru', '是', 'E'),
       ('法语', 'fra', '是', 'F'),
       ('菲律宾语', 'fil', '是', 'F'),
       ('芬兰语', 'fin', '是', 'F'),
       ('梵语', 'san', '否', 'F'),
       ('弗留利语', 'fri', '否', 'F'),
       ('富拉尼语', 'ful', '否', 'F'),
       ('法罗语', 'fao', '否', 'F'),
       ('盖尔语', 'gla', '否', 'G'),
       ('刚果语', 'kon', '否', 'G'),
       ('高地索布语', 'ups', '否', 'G'),
       ('高棉语', 'hkm', '是', 'G'),
       ('格陵兰语', 'kal', '否', 'G'),
       ('格鲁吉亚语', 'geo', '是', 'G'),
       ('古吉拉特语', 'guj', '是', 'G'),
       ('古希腊语', 'gra', '否', 'G'),
       ('古英语', 'eno', '否', 'G'),
       ('瓜拉尼语', 'grn', '否', 'G'),
       ('韩语', 'kor', '是', 'H'),
       ('荷兰语', 'nl', '是', 'H'),
       ('胡帕语', 'hup', '否', 'H'),
       ('哈卡钦语', 'hak', '否', 'H'),
       ('海地语', 'ht', '否', 'H'),
       ('黑山语', 'mot', '否', 'H'),
       ('豪萨语', 'hau', '否', 'H'),
       ('吉尔吉斯语', 'kir', '否', 'J'),
       ('加利西亚语', 'glg', '是', 'J'),
       ('加拿大法语', 'frn', '否', 'J'),
       ('加泰罗尼亚语', 'cat', '是', 'J'),
       ('捷克语', 'cs', '是', 'J'),
       ('卡拜尔语', 'kab', '是', 'K'),
       ('卡纳达语', 'kan', '是', 'K'),
       ('卡努里语', 'kau', '否', 'K'),
       ('卡舒比语', 'kah', '否', 'K'),
       ('康瓦尔语', 'cor', '否', 'K'),
       ('科萨语', 'xho', '是', 'K'),
       ('科西嘉语', 'cos', '否', 'K'),
       ('克里克语', 'cre', '否', 'K'),
       ('克里米亚鞑靼语', 'cri', '否', 'K'),
       ('克林贡语', 'kli', '否', 'K'),
       ('克罗地亚语', 'hrv', '是', 'K'),
       ('克丘亚语', 'que', '否', 'K'),
       ('克什米尔语', 'kas', '否', 'K'),
       ('孔卡尼语', 'kok', '否', 'K'),
       ('库尔德语', 'kur', '是', 'K'),
       ('拉丁语', 'lat', '是', 'L'),
       ('老挝语', 'lao', '否', 'L'),
       ('罗马尼亚语', 'rom', '是', 'L'),
       ('拉特加莱语', 'lag', '否', 'L'),
       ('拉脱维亚语', 'lav', '是', 'L'),
       ('林堡语', 'lim', '否', 'L'),
       ('林加拉语', 'lin', '否', 'L'),
       ('卢干达语', 'lug', '否', 'L'),
       ('卢森堡语', 'ltz', '否', 'L'),
       ('卢森尼亚语', 'ruy', '否', 'L'),
       ('卢旺达语', 'kin', '是', 'L'),
       ('立陶宛语', 'lit', '是', 'L'),
       ('罗曼什语', 'roh', '否', 'L'),
       ('罗姆语', 'ro', '否', 'L'),
       ('逻辑语', 'loj', '否', 'L'),
       ('马来语', 'may', '是', 'M'),
       ('缅甸语', 'bur', '是', 'M'),
       ('马拉地语', 'mar', '否', 'M'),
       ('马拉加斯语', 'mg', '是', 'M'),
       ('马拉雅拉姆语', 'mal', '是', 'M'),
       ('马其顿语', 'mac', '是', 'M'),
       ('马绍尔语', 'mah', '否', 'M'),
       ('迈蒂利语', 'mai', '是', 'M'),
       ('曼克斯语', 'glv', '否', 'M'),
       ('毛里求斯克里奥尔语', 'mau', '否', 'M'),
       ('毛利语', 'mao', '否', 'M'),
       ('孟加拉语', 'ben', '是', 'M'),
       ('马耳他语', 'mlt', '是', 'M'),
       ('苗语', 'hmn', '否', 'M'),
       ('挪威语', 'nor', '是', 'N'),
       ('那不勒斯语', 'nea', '否', 'N'),
       ('南恩德贝莱语', 'nbl', '否', 'N'),
       ('南非荷兰语', 'afr', '是', 'N'),
       ('南索托语', 'sot', '否', 'N'),
       ('尼泊尔语', 'nep', '是', 'N'),
       ('葡萄牙语', 'pt', '是', 'P'),
       ('旁遮普语', 'pan', '是', 'P'),
       ('帕皮阿门托语', 'pap', '否', 'P'),
       ('普什图语', 'pus', '否', 'P'),
       ('齐切瓦语', 'nya', '否', 'Q'),
       ('契维语', 'twi', '否', 'Q'),
       ('切罗基语', 'chr', '否', 'Q'),
       ('日语', 'jp', '是', 'R'),
       ('瑞典语', 'swe', '是', 'R'),
       ('萨丁尼亚语', 'srd', '否', 'S'),
       ('萨摩亚语', 'sm', '否', 'S'),
       ('塞尔维亚-克罗地亚语', 'sec', '否', 'S'),
       ('塞尔维亚语', 'srp', '是', 'S'),
       ('桑海语', 'sol', '否', 'S'),
       ('僧伽罗语', 'sin', '是', 'S'),
       ('世界语', 'epo', '是', 'S'),
       ('书面挪威语', 'nob', '是', 'S'),
       ('斯洛伐克语', 'sk', '是', 'S'),
       ('斯洛文尼亚语', 'slo', '是', 'S'),
       ('斯瓦希里语', 'swa', '是', 'S'),
       ('塞尔维亚语（西里尔）', 'src', '否', 'S'),
       ('索马里语', 'som', '是', 'S'),
       ('泰语', 'th', '是', 'T'),
       ('土耳其语', 'tr', '是', 'T'),
       ('塔吉克语', 'tgk', '是', 'T'),
       ('泰米尔语', 'tam', '是', 'T'),
       ('他加禄语', 'tgl', '是', 'T'),
       ('提格利尼亚语', 'tir', '否', 'T'),
       ('泰卢固语', 'tel', '是', 'T'),
       ('突尼斯阿拉伯语', 'tua', '否', 'T'),
       ('土库曼语', 'tuk', '否', 'T'),
       ('乌克兰语', 'ukr', '是', 'W'),
       ('瓦隆语', 'wln', '是', 'W'),
       ('威尔士语', 'wel', '是', 'W'),
       ('文达语', 'ven', '否', 'W'),
       ('沃洛夫语', 'wol', '否', 'W'),
       ('乌尔都语', 'urd', '是', 'W'),
       ('西班牙语', 'spa', '是', 'X'),
       ('希伯来语', 'heb', '是', 'X'),
       ('希腊语', 'el', '是', 'X'),
       ('匈牙利语', 'hu', '是', 'X'),
       ('西弗里斯语', 'fry', '是', 'X'),
       ('西里西亚语', 'sil', '否', 'X'),
       ('希利盖农语', 'hil', '否', 'X'),
       ('下索布语', 'los', '否', 'X'),
       ('夏威夷语', 'haw', '否', 'X'),
       ('新挪威语', 'nno', '是', 'X'),
       ('西非书面语', 'nqo', '否', 'X'),
       ('信德语', 'snd', '否', 'X'),
       ('修纳语', 'sna', '否', 'X'),
       ('宿务语', 'ceb', '否', 'X'),
       ('叙利亚语', 'syr', '否', 'X'),
       ('巽他语', 'sun', '否', 'X'),
       ('英语', 'en', '是', 'Y'),
       ('印地语', 'hi', '是', 'Y'),
       ('印尼语', 'id', '是', 'Y'),
       ('意大利语', 'it', '是', 'Y'),
       ('越南语', 'vie', '是', 'Y'),
       ('意第绪语', 'yid', '否', 'Y'),
       ('因特语', 'ina', '否', 'Y'),
       ('亚齐语', 'ach', '否', 'Y'),
       ('印古什语', 'ing', '否', 'Y'),
       ('伊博语', 'ibo', '否', 'Y'),
       ('伊多语', 'ido', '否', 'Y'),
       ('约鲁巴语', 'yor', '否', 'Y'),
       ('亚美尼亚语', 'arm', '是', 'Y'),
       ('伊努克提图特语', 'iku', '否', 'Y'),
       ('伊朗语', 'ir', '否', 'Y'),
       ('中文(简体)', 'zh', '是', '0Z'),
       ('中文(繁体)', 'cht', '是', '0Z'),
       ('中文(文言文)', 'wyw', '是', 'Z'),
       ('中文(粤语)', 'yue', '是', 'Z'),
       ('扎扎其语', 'zaz', '否', 'Z'),
       ('中古法语', 'frm', '否', 'Z'),
       ('祖鲁语', 'zul', '否', 'Z'),
       ('爪哇语', 'jav', '否', 'Z');

create table ai_img_wait_queue_info
(
    `id`         int PRIMARY KEY AUTO_INCREMENT,
    `ip_or_user_id`    varchar(100) not null DEFAULT '',
    `process_id`       varchar(36)  not null DEFAULT '',
    `result`        char(1)  not null DEFAULT '0' COMMENT '处理结果，0：等待处理，1：处理中，2：处理完成，-1：处理失败',
    `result_message` varchar(100)   not null DEFAULT '状态信息',
    `img_url` varchar(200) not null DEFAULT '',
    `text`   varchar(50)          not null default 0 COMMENT '生成信息描述文本, 30字以内',
    `width`      int          not null default 512 COMMENT '图片宽度，(0，1920],默认512',
    `height`     int          not null default 512 COMMENT '图片高度，(0,1080]，默认512',
    `createBy`   varchar(64) COMMENT '创建人ID',
    `createTime` datetime              DEFAULT now() COMMENT '创建时间',
    `delete`     char(1)               DEFAULT '0' COMMENT '是否删除标记, 1:已删除，0：未删除',
    `updateTime` datetime              DEFAULT now() COMMENT '删除时间',
    `updateBy`   varchar(64) COMMENT '删除人ID',
    `remark`     varchar(200)          DEFAULT '' COMMENT '备注信息',
    `status`     char(1)               DEFAULT '1' COMMENT '状态，1:启用，0：停用'
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = 'AI图片生成信息'
  ROW_FORMAT = Dynamic;

create table ai_img_wait_queue_step_info
(
    `id`         int PRIMARY KEY AUTO_INCREMENT,
    `process_id`    varchar(36) not null DEFAULT '' COMMENT '作业ID',
    `type`        char(1)  not null DEFAULT '0' COMMENT '处理结果，0：等待处理，1：处理中，2：处理完成，-1：处理失败',
    `title` varchar(100)   not null DEFAULT '状态信息',
    `percentage`   varchar(10)          not null default '1' COMMENT '进度百分比',
    `mark`      varchar(100)          not null default '' COMMENT '进度说明',
    `currentStep`     varchar(10)          not null default '-' COMMENT '当前执行到第几步骤',
    `totalSteps`     varchar(10)          not null default '-' COMMENT '总共步骤数',
    `estimatedTime`     varchar(10)          not null default'-' COMMENT '预计剩余时间',
    `elapsedTime`     varchar(10)          not null default '-' COMMENT '已执行时间',
    `time`       bigint not null default 0 COMMENT '当前时间',
    `createBy`   varchar(64) COMMENT '创建人ID',
    `createTime` datetime              DEFAULT now() COMMENT '创建时间',
    `delete`     char(1)               DEFAULT '0' COMMENT '是否删除标记, 1:已删除，0：未删除',
    `updateTime` datetime              DEFAULT now() COMMENT '删除时间',
    `updateBy`   varchar(64) COMMENT '删除人ID',
    `remark`     varchar(200)          DEFAULT '' COMMENT '备注信息',
    `status`     char(1)               DEFAULT '1' COMMENT '状态，1:启用，0：停用'
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = 'AI图片生成进度信息'
  ROW_FORMAT = Dynamic;

CREATE TABLE sys_ip_whitelist (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      ipAddress VARCHAR(50) NOT NULL ,
      description VARCHAR(100) DEFAULT '',
      token varchar(30) DEFAULT '',
      `createBy`   varchar(64) COMMENT '创建人ID',
      `createTime` datetime              DEFAULT now() COMMENT '创建时间',
      `delete`     char(1)               DEFAULT '0' COMMENT '是否删除标记, 1:已删除，0：未删除',
      `updateTime` datetime              DEFAULT now() COMMENT '删除时间',
      `updateBy`   varchar(64) COMMENT '删除人ID',
      `remark`     varchar(200)          DEFAULT '' COMMENT '备注信息',
      `status`     char(1)               DEFAULT '1' COMMENT '状态，1:启用，0：停用'
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '动态白名单列表'
  ROW_FORMAT = Dynamic;



