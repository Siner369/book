package com.siner.service;

import com.siner.entity.Comments;

import java.util.List;

public interface CommentsService {
    boolean checkIsBuy(int uid,int bid);

    boolean addComments(Comments c);

    List<Comments> findCommentsByBid(int bid);

    List<Comments> allComments();

    boolean delComment(int cid);
}
