-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('视频产生的相关数据', '2018', '1', 'data', 'video/data/index', 1, 0, 'C', '0', '0', 'video:data:list', '#', 'admin', sysdate(), '', null, '视频产生的相关数据菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('视频产生的相关数据查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'video:data:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('视频产生的相关数据新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'video:data:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('视频产生的相关数据修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'video:data:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('视频产生的相关数据删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'video:data:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('视频产生的相关数据导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'video:data:export',       '#', 'admin', sysdate(), '', null, '');