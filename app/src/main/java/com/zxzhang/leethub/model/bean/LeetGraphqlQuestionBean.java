package com.zxzhang.leethub.model.bean;


public class LeetGraphqlQuestionBean {


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private QuestionGraphqlBean question;

        public QuestionGraphqlBean getQuestionGraphql() {
            return question;
        }

        public void setQuestionGraphql(QuestionGraphqlBean question) {
            this.question = question;
        }

        public static class QuestionGraphqlBean {

            private String content;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
