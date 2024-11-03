-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('视频的转发信息', '2018', '1', 'forward', 'video/forward/index', 1, 0, 'C', '0', '0', 'video:forward:list', '#', 'admin', sysdate(), '', null, '视频的转发信息菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('视频的转发信息查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'video:forward:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('视频的转发信息新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'video:forward:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('视频的转发信息修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'video:forward:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('视频的转发信息删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'video:forward:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('视频的转发信息导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'video:forward:export',       '#', 'admin', sysdate(), '', null, '');