package com.qhit.baseUserRole.controller;

import com.qhit.baseUserRole.pojo.BaseUserRole;
import com.qhit.baseUserRole.service.IBaseUserRoleService;
import com.qhit.baseUserRole.service.impl.BaseUserRoleServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 16682 on 2018/12/2.
 */
@Controller
@RequestMapping("baseUserRole")
public class BaseUserRoleCon {
    IBaseUserRoleService service = new BaseUserRoleServiceImpl();

}
