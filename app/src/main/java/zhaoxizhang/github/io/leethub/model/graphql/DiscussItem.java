package zhaoxizhang.github.io.leethub.model.graphql;

public class DiscussItem {

    /**
     * data : {"topic":{"id":17,"viewCount":146721,"title":"Here is a Python solution in O(n) time","post":{"id":17,"voteCount":481,"content":"    class Solution(object):\\n        def twoSum(self, nums, target):\\n            if len(nums) <= 1:\\n                return False\\n            buff_dict = {}\\n            for i in range(len(nums)):\\n                if nums[i] in buff_dict:\\n                    return [buff_dict[nums[i]], i]\\n                else:\\n                    buff_dict[target - nums[i]] = i","updationDate":1540386099,"author":{"username":"Nathan_Fegard","profile":{"userAvatar":"https://assets.leetcode.com/users/nathan_fegard/avatar_1536531058.png","reputation":523}}}}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * topic : {"id":17,"viewCount":146721,"title":"Here is a Python solution in O(n) time","post":{"id":17,"voteCount":481,"content":"    class Solution(object):\\n        def twoSum(self, nums, target):\\n            if len(nums) <= 1:\\n                return False\\n            buff_dict = {}\\n            for i in range(len(nums)):\\n                if nums[i] in buff_dict:\\n                    return [buff_dict[nums[i]], i]\\n                else:\\n                    buff_dict[target - nums[i]] = i","updationDate":1540386099,"author":{"username":"Nathan_Fegard","profile":{"userAvatar":"https://assets.leetcode.com/users/nathan_fegard/avatar_1536531058.png","reputation":523}}}}
         */

        private TopicBean topic;

        public TopicBean getTopic() {
            return topic;
        }

        public void setTopic(TopicBean topic) {
            this.topic = topic;
        }

        public static class TopicBean {
            /**
             * id : 17
             * viewCount : 146721
             * title : Here is a Python solution in O(n) time
             * post : {"id":17,"voteCount":481,"content":"    class Solution(object):\\n        def twoSum(self, nums, target):\\n            if len(nums) <= 1:\\n                return False\\n            buff_dict = {}\\n            for i in range(len(nums)):\\n                if nums[i] in buff_dict:\\n                    return [buff_dict[nums[i]], i]\\n                else:\\n                    buff_dict[target - nums[i]] = i","updationDate":1540386099,"author":{"username":"Nathan_Fegard","profile":{"userAvatar":"https://assets.leetcode.com/users/nathan_fegard/avatar_1536531058.png","reputation":523}}}
             */

            private int id;
            private int viewCount;
            private String title;
            private PostBean post;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getViewCount() {
                return viewCount;
            }

            public void setViewCount(int viewCount) {
                this.viewCount = viewCount;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public PostBean getPost() {
                return post;
            }

            public void setPost(PostBean post) {
                this.post = post;
            }

            public static class PostBean {
                /**
                 * id : 17
                 * voteCount : 481
                 * content :     class Solution(object):\n        def twoSum(self, nums, target):\n            if len(nums) <= 1:\n                return False\n            buff_dict = {}\n            for i in range(len(nums)):\n                if nums[i] in buff_dict:\n                    return [buff_dict[nums[i]], i]\n                else:\n                    buff_dict[target - nums[i]] = i
                 * updationDate : 1540386099
                 * author : {"username":"Nathan_Fegard","profile":{"userAvatar":"https://assets.leetcode.com/users/nathan_fegard/avatar_1536531058.png","reputation":523}}
                 */

                private int id;
                private int voteCount;
                private String content;
                private int updationDate;
                private AuthorBean author;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getVoteCount() {
                    return voteCount;
                }

                public void setVoteCount(int voteCount) {
                    this.voteCount = voteCount;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public int getUpdationDate() {
                    return updationDate;
                }

                public void setUpdationDate(int updationDate) {
                    this.updationDate = updationDate;
                }

                public AuthorBean getAuthor() {
                    return author;
                }

                public void setAuthor(AuthorBean author) {
                    this.author = author;
                }

                public static class AuthorBean {
                    /**
                     * username : Nathan_Fegard
                     * profile : {"userAvatar":"https://assets.leetcode.com/users/nathan_fegard/avatar_1536531058.png","reputation":523}
                     */

                    private String username;
                    private ProfileBean profile;

                    public String getUsername() {
                        return username;
                    }

                    public void setUsername(String username) {
                        this.username = username;
                    }

                    public ProfileBean getProfile() {
                        return profile;
                    }

                    public void setProfile(ProfileBean profile) {
                        this.profile = profile;
                    }

                    public static class ProfileBean {
                        /**
                         * userAvatar : https://assets.leetcode.com/users/nathan_fegard/avatar_1536531058.png
                         * reputation : 523
                         */

                        private String userAvatar;
                        private int reputation;

                        public String getUserAvatar() {
                            return userAvatar;
                        }

                        public void setUserAvatar(String userAvatar) {
                            this.userAvatar = userAvatar;
                        }

                        public int getReputation() {
                            return reputation;
                        }

                        public void setReputation(int reputation) {
                            this.reputation = reputation;
                        }
                    }
                }
            }
        }
    }
}
