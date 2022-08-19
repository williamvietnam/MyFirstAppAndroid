package com.williamnb.readlistenapp.data.local.models;

import java.util.List;

public class CommentResponse {

    private List<Comment> commentList;

    public List<Comment> getCommentList() {
        return this.commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public static class Comment{
        private String commentId;

        private String imageAvatar;

        private String userName;

        private String date;

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