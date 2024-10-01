package com.kit.web.controller.system;

import com.kit.common.constant.Constants;
import com.kit.common.core.domain.AjaxResult;
import com.kit.common.core.domain.entity.SysMenu;
import com.kit.common.core.domain.entity.SysUser;
import com.kit.common.core.domain.model.LoginBody;
import com.kit.common.exception.user.LoginTypeUnSupportException;
import com.kit.common.utils.SecurityUtils;
import com.kit.common.utils.spring.SpringUtils;
import com.kit.framework.web.service.SysPermissionService;
import com.kit.framework.web.service.login.AbstractLoginService;
import com.kit.framework.web.service.login.LoginType;
import com.kit.framework.web.service.login.auth.LoginInfo;
import com.kit.system.service.ISysMenuService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * 登录验证
 *
 * @author xiao
 */
@RestController
public class SysLoginController {

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        LoginType loginType = LoginType.type(loginBody.getType());
        Class<? extends AbstractLoginService> serviceClass = loginType.getServiceClass();
        try {
            AbstractLoginService service = SpringUtils.getBean(serviceClass);
            LoginInfo loginInfo = service.login(loginBody);
            ajax.put(Constants.TOKEN, loginInfo.getToken());
            ajax.put(Constants.AVATAR, loginInfo.getAvatar());
            ajax.put(Constants.NICK_NAME, loginInfo.getNickName());
            return ajax;
        } catch (BeansException e) {
            throw new LoginTypeUnSupportException(loginType.getType());
        }
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo() {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters() {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return AjaxResult.success(menuService.buildMenus(menus));
    }
}
