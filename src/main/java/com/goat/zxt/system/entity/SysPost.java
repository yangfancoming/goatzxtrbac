package com.goat.zxt.system.entity;


public class SysPost extends BaseEntity {

    private static final long serialVersionUID = 8925514045582234222L;

    private Integer postId;

    private String postName;

    private Integer postStatus;

    private Integer postSort;

    /** 用户是否存在此岗位标识 默认不存在 */
    private boolean postFlag = false;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }


    public Integer getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(Integer postStatus) {
        this.postStatus = postStatus;
    }

    public Integer getPostSort() {
        return postSort;
    }

    public void setPostSort(Integer postSort) {
        this.postSort = postSort;
    }

    public boolean isPostFlag() {
        return postFlag;
    }

    public void setPostFlag(boolean postFlag) {
        this.postFlag = postFlag;
    }
}
