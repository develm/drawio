package com.sql.er.controller;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.parser.ParserException;
import com.alibaba.druid.sql.parser.SQLParserUtils;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.sql.er.dao.model.User;
import com.sql.er.service.UserService;
import com.sql.er.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class HomeController {
    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Resource
    private UserService userService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/home")
    public String home() {
        Result<User> result = userService.getUserInfo(1L);
        if (Result.isSuccess(result)){
            logger.info(result.getData().getUserPhone());
        }
        return "home";
    }

    @ResponseBody
    @RequestMapping("/sqlCheck")
    public String sqlCheck(@RequestParam(name = "sql") String sql, @RequestParam(name = "dbType") String dbType) {
        List<SQLStatement> statementList = null;
        SQLStatementParser parser = null;
        try {
            parser = SQLParserUtils.createSQLStatementParser(sql, dbType);
            statementList = parser.parseStatementList();
        } catch (ParserException e) {
            return e.getMessage();
        }
        return statementList.toString();
    }
}
