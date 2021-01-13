package com.mxgraph.controller;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.parser.ParserException;
import com.alibaba.druid.sql.parser.SQLParserUtils;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
//@RequestMapping("/home")
public class HomeController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }


    @RequestMapping("/sqlCheck")
    public ModelAndView sqlCheck(@RequestParam(name = "sql")String sql){
        ModelAndView view = new ModelAndView("login");
        List<SQLStatement> statementList = null;
        SQLStatementParser parser = null;
        try {
            parser = SQLParserUtils.createSQLStatementParser(sql, "mysql");
            statementList = parser.parseStatementList();
        } catch (ParserException e) {
            System.out.println("SQL转换中发生了错误：" + e.getMessage());
            view.addObject("errorMsg", e.getMessage());
            return view;
        }
        System.out.println(statementList);
        view.addObject("data", statementList);
        return view;
    }
}
