package com.williamnb.readlistenapp.data.local.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Comment implements Serializable {

    @SerializedName("comment_list")
    @Expose
    private List<Data> dataList;

    public List<Data> getCommentList() {
        return this.dataList;
    }

    public void setCommentList(List<Data> dataList) {
        this.dataList = dataList;
    }

    public static class Data {

        @SerializedName("commentId")
        @Expose
        private String commentId;

        @SerializedName("imageAvatar")
        @Expose
        private String imageAvatar;

        @SerializedName("userName")
        @Expose
        private String userName;

        @SerializedName("date")
        @Expose
        private String date;

        @SerializedName("commentContent")
        @Expose
        private String commentContent;

        public String getCommentId() {
            return commentId;
        }

        public void setCommentId(String commentId) {
            this.commentId = commentId;
        }

        public String getImageAvatar() {
            return imageAvatar;
        }

        public void setImageAvatar(String imageAvatar) {
            this.imageAvatar = imageAvatar;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }
    }
}