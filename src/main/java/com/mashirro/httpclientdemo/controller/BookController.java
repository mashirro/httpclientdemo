package com.mashirro.httpclientdemo.controller;


import com.mashirro.httpclientdemo.pojo.Book;
import com.mashirro.httpclientdemo.pojo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/book")
public class BookController {

    @RequestMapping("/add")
    @ResponseBody
    public Result add(Book book) {
        System.out.println(book);
        return new Result(true, 200, "增加标签成功");
    }

    @RequestMapping("/increase")
    @ResponseBody
    public Result increase(@RequestBody Book book) {
        System.out.println(book);
        return new Result(true, 200, "增加标签成功");
    }
}
