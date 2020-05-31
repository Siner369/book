package com.siner.web;


import com.alibaba.fastjson.JSONObject;
import com.siner.entity.Book;
import com.siner.entity.Comments;
import com.siner.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("CommentsController")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @PostMapping(value = "/bookstore/addComments", produces = "application/json;charset=UTF-8")
    public @ResponseBody String addComments(String uid,String uname,String bid,String cscore,String comments) {
        System.out.println(uid+","+bid+","+comments);
        Comments c = new Comments();
        c.setUid(Integer.valueOf(uid));
        c.setUname(uname);
        c.setBid(Integer.valueOf(bid));
        c.setCscore(Integer.valueOf(cscore));
        boolean b = commentsService.checkIsBuy(Integer.valueOf(uid),Integer.valueOf(bid));
        c.setIsbuy(b);
        c.setComments(comments);
        boolean b1 = commentsService.addComments(c);
        JSONObject json = new JSONObject();
        if (b1){
            json.put("msg","success");
        }
        return json.toString();
    }

    @PostMapping(value = "/bookstore/findComments",
            produces = "application/json;charset=UTF-8")
    public @ResponseBody List<Comments> findCommentsByBid(String bid) {
        System.out.println("评论 bid:"+bid);
        List<Comments> commentsList = commentsService.findCommentsByBid(Integer.valueOf(bid));
        System.out.println(commentsList);
        return commentsList;
    }
    @GetMapping(value = "/admin/allComments")
    public @ResponseBody String allComments() {
        List<Comments> list = commentsService.allComments();
        JSONObject json = new JSONObject();
        json.put("code",0);
        json.put("msg","");
        json.put("count",list.size());
        json.put("data",list);
        return json.toString();
    }

    @GetMapping("/admin/comment_list")
    public String comments_list() {
        return "/admin/comment_list";
    }

    @PostMapping("/delComments")
    public @ResponseBody String dellComments(String cid){
        boolean flag = commentsService.delComment(Integer.valueOf(cid));
        JSONObject json = new JSONObject();
        if (flag) {
            json.put("del", "success");
            return json.toString();
        } else {
            return json.toString();
        }
    }


}
