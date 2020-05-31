package com.siner.dao;


import com.siner.entity.Comments;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CommentsDao")
public interface CommentsDao {

    String checkIsBuy(@Param("uid") int uid,@Param("bid") int bid);

    int addComments(Comments c);

    List<Comments> findCommentsByBid(int bid);

    List<Comments> allComments();

    boolean delComment(int cid);

}
