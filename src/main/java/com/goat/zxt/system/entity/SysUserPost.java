package com.goat.zxt.system.entity;


import java.io.Serializable;


public class SysUserPost implements Serializable {

    private static final long serialVersionUID = 8925514045582235321L;

    private Integer userId;

    private String postId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
