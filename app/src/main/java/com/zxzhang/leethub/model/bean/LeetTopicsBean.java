package com.zxzhang.leethub.model.bean;


import java.util.List;

public class LeetTopicsBean {
    private List<TopicsBean> topics;

    public List<TopicsBean> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicsBean> topics) {
        this.topics = topics;
    }

    public static class TopicsBean {
        /**
         * slug : array
         * name : Array
         * questions : [11,16,18,35,41,45,55,59,63,64,66,73,74,78,79,80,81,84,105,106,119,120,123,152,153,154,163,167,189,209,216,228,229,243,245,259,280,283,27,85,31,57,277,90,128,34,54,48,121,26,88,268,238,33,118,39,62,53,42,122,56,126,75,15,169,219,217,287,1,4,162,370,380,381,40,289,414,448,442,485,495,531,533,532,548,561,562,566,560,581,565,605,611,624,621,628,643,644,661,665,667,670,674,683,689,695,697,714,713,715,717,718,719,723,724,731,729,747,748,756,777,780,779,790,798,808,811,852,857,861,864,870,879,898,901,905,924,927,932,936,941,943,950,951]
         */

        private String slug;
        private String name;
        private List<Integer> questions;

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Integer> getQuestions() {
            return questions;
        }

        public void setQuestions(List<Integer> questions) {
            this.questions = questions;
        }
    }
}
