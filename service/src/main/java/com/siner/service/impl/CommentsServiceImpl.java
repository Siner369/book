package com.siner.service.impl;


import com.siner.dao.CommentsDao;
import com.siner.entity.Comments;
import com.siner.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CommentsService")
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsDao commentsDao;

    @Override
    public boolean checkIsBuy(int uid,int bid) {
        String oid  = commentsDao.checkIsBuy(uid,bid);
        if (oid != null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean addComments(Comments c) {
        int n =commentsDao.addComments(c);
        if (n!=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Comments> findCommentsByBid(int bid) {
        return commentsDao.findCommentsByBid(bid);
    }

    @Override
    public List<Comments> allComments() {
        return commentsDao.allComments();
    }

    @Override
    public boolean delComment(int cid) {
        return commentsDao.delComment(cid);
    }
}
