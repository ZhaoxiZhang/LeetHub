package com.zxzhang.leethub.model.bean;


import java.util.List;

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
            private List<TopicTagsBean> topicTags;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public List<TopicTagsBean> getTopicTags() {
                return topicTags;
            }

            public void setTopicTags(List<TopicTagsBean> topicTags) {
                this.topicTags = topicTags;
            }

            public static class TopicTagsBean {
                private String name;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }
}
