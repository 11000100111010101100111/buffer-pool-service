-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('举报类型信息', '2018', '1', 'type', 'video/type/index', 1, 0, 'C', '0', '0', 'video:type:list', '#', 'admin', sysdate(), '', null, '举报类型信息菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('举报类型信息查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'video:type:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('举报类型信息新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'video:type:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('举报类型信息修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'video:type:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('举报类型信息删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'video:type:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('举报类型信息导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'video:type:export',       '#', 'admin', sysdate(), '', null, '');