package com.sql.er.controller;

import com.sql.er.common.Constant;
import com.sql.er.common.utils.Result;
import com.sql.er.dto.SignInDto;
import com.sql.er.dto.SignUpDto;
import com.sql.er.service.UserLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private UserLoginService userLoginService;

    @RequestMapping("/signin")
    public String signIn() {
        return "signin";
    }

    @RequestMapping("/signup")
    public String signUp(HttpServletRequest request) {
        request.getSession().setAttribute(Constant.SESSION_KEY_MSG_CODE, request.getRequestURI());
        return "signup";
    }

    @ResponseBody
    @RequestMapping("/signInSubmit")
    public Result signInSubmit(SignInDto dto) {
        logger.info("dto:{}", dto);
        return Result.createSuccess("");
    }

    @ResponseBody
    @RequestMapping("/signUpSubmit")
    public Result signUpSubmit(SignUpDto dto) {
        logger.info("dto:{}", dto);
        return Result.createSuccess("");
    }

    @ResponseBody
    @RequestMapping("/sendMsgCode")
    public Result sendMsgCode(HttpServletRequest request) {
        if (sendCheck(request.getSession())) {
            return Result.createSuccess("");
        }
        return Result.fail("check fail");
    }

    /**
     * sendCheck
     *
     * @param session
     * @return
     */
    private boolean sendCheck(HttpSession session) {
        if (null == session || null == session.getAttribute(Constant.SESSION_KEY_MSG_CODE)
                || !session.getAttribute(Constant.SESSION_KEY_MSG_CODE).toString().contains("signup")) {
            return false;
        }
        return true;
    }
}
