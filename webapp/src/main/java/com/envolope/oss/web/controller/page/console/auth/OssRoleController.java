package com.envolope.oss.web.controller.page.console.auth;

import com.envolope.oss.consts.*;
import com.envolope.oss.model.OssMenuFunction;
import com.envolope.oss.model.dto.OssRoleDetailDto;
import com.envolope.oss.model.dto.PageDto;
import com.envolope.oss.model.form.RoleSaveForm;
import com.envolope.oss.model.form.RoleUpdateForm;
import com.envolope.oss.model.vo.LoginAdminInfo;
import com.envolope.oss.service.OssRoleService;
import com.envolope.oss.util.CommonMethod;
import com.envolope.oss.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuqiang on 15-12-30.
 *
 * @author wuqiang
 */
@RequestMapping(value = "/page/console/auth/role", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class OssRoleController {

    @Autowired
    private OssRoleService ossRoleService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request,
                       @RequestParam(value = ParamConsts.pageNo, required = false) Integer pageNo) {
        if (pageNo == null || pageNo <= 0) {
            pageNo = 1;
        }
        request.setAttribute("SUPER_ADMINISTRATOR_ROLE_ID", BaseConsts.super_administrator_role_id);
        PageDto pageDto = ossRoleService.selectByPage(pageNo, SizeConsts.PAGE_SIZE_DEFAULT);
        request.setAttribute("pg", pageDto);
        return "console/auth/role-list";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request) {
        // 指定这个页面，功能菜单自动focus到角色列表页上
        LoginAdminInfo loginAdminInfo = CommonMethod.getAdminLoginSession(request.getSession());
        request.setAttribute("allFunctionMenuModuleDtoList", ossRoleService.selectAllFunctionMenuModuleDtoList(loginAdminInfo.getRoleId()));
        return "console/auth/role-add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(HttpServletRequest request, @Valid RoleSaveForm roleSaveForm, BindingResult result) {
        LoginAdminInfo loginAdminInfo = CommonMethod.getAdminLoginSession(request.getSession());
        List<Integer> checkedFunctionIds = roleSaveForm.getFunctionIds();
        if (checkedFunctionIds != null) {
            List<Integer> functionIds = new ArrayList<>(checkedFunctionIds.size());
            for (Integer functionId : checkedFunctionIds) {
                OssMenuFunction function = loginAdminInfo.getPermittedFunctionByFunctionId(functionId);
                if (function != null) {
                    // 当前用户有此权限
                    functionIds.add(functionId);
                }
            }
            roleSaveForm.setFunctionIds(functionIds);
        }
        RequestContext requestContext = new RequestContext(request);
        if (ossRoleService.insert(roleSaveForm)) {
            return JsonUtil.buildSuccess(requestContext.getMessage("msg.success"));
        }
        return JsonUtil.buildError(requestContext.getMessage("msg.fail"));
    }

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, @RequestParam(value = ParamConsts.id, required = false) Integer roleId) {
        if (roleId == null) {
            return RequestConsts.ERROR_404_PAGE_RESULT;
        }
        RequestContext requestContext = new RequestContext(request);
        if (BaseConsts.super_administrator_role_id.equals(roleId)) {
            // 禁止修改管理员
            request.setAttribute(AttributeConsts.ERROR_MSG, requestContext.getMessage("oss.error.forbid.edit.super.role"));
            return RequestConsts.ERROR_500_PAGE_RESULT;
        }
        OssRoleDetailDto role = ossRoleService.selectById(roleId);
        if (role == null) {
            return RequestConsts.ERROR_404_PAGE_RESULT;
        }
        request.setAttribute("role", role);
        // 指定这个页面，功能菜单自动focus到角色列表页上
        LoginAdminInfo loginAdminInfo = CommonMethod.getAdminLoginSession(request.getSession());
        request.setAttribute("allFunctionMenuModuleDtoList", ossRoleService.selectAllFunctionMenuModuleDtoList(loginAdminInfo.getRoleId()));
        return "console/auth/role-edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(HttpServletRequest request, @Valid RoleUpdateForm roleUpdateForm, BindingResult result) {
        RequestContext requestContext = new RequestContext(request);
        if (BaseConsts.super_administrator_role_id.equals(roleUpdateForm.getRoleId())) {
            // 禁止修改管理员
            return JsonUtil.buildError(requestContext.getMessage("oss.error.forbid.edit.super.role"));
        }
        LoginAdminInfo loginAdminInfo = CommonMethod.getAdminLoginSession(request.getSession());
        List<Integer> checkedFunctionIds = roleUpdateForm.getFunctionIds();
        if (checkedFunctionIds != null) {
            List<Integer> functionIds = new ArrayList<>(checkedFunctionIds.size());
            for (Integer functionId : checkedFunctionIds) {
                OssMenuFunction function = loginAdminInfo.getPermittedFunctionByFunctionId(functionId);
                if (function != null) {
                    // 当前用户有此权限
                    functionIds.add(functionId);
                }
            }
            roleUpdateForm.setFunctionIds(functionIds);
        }
        if (ossRoleService.update(roleUpdateForm)) {
            return JsonUtil.buildSuccess(requestContext.getMessage("msg.success"));
        }
        return JsonUtil.buildError(requestContext.getMessage("msg.fail"));
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public String remove(HttpServletRequest request, @RequestParam(value = ParamConsts.ids, required = false) List<Integer> roleIds) {
        RequestContext requestContext = new RequestContext(request);
        if (roleIds == null || roleIds.isEmpty()) {
            return JsonUtil.buildError(requestContext.getMessage("common.error.required-select", new Object[]{requestContext.getMessage("oss.role")}));
        }
        if (roleIds.contains(BaseConsts.super_administrator_role_id)) {
            // 禁止删除管理员
            return JsonUtil.buildError(requestContext.getMessage("oss.error.forbid.edit.super.role"));
        }
        if (ossRoleService.delete(roleIds)) {
            return JsonUtil.buildSuccess(requestContext.getMessage("msg.success"));
        }
        return JsonUtil.buildError(requestContext.getMessage("msg.fail"));
    }
}
