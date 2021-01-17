package com.sql.er.controller;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.parser.ParserException;
import com.alibaba.druid.sql.parser.SQLParserUtils;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
//@RequestMapping("/home")
public class HomeController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/home")
    public String home() {
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
            System.out.println("SQL转换中发生了错误：" + e.getMessage());
            return e.getMessage();
        }
        System.out.println(statementList);
        return statementList.toString();
    }
}
