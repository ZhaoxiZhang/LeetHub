package zhaoxizhang.github.io.leethub.model.graphql;

import java.io.Serializable;
import java.util.List;

public class Discuss implements Serializable{

    /**
     * data : {"questionTopicsList":{"totalNum":1875,"edges":[{"node":{"id":"17","title":"Here is a Python solution in O(n) time","viewCount":146173,"post":{"id":17,"voteCount":473,"creationDate":1440998099,"author":{"username":"Nathan_Fegard","profile":{"userSlug":"nathan_fegard","userAvatar":"https://assets.leetcode.com/users/nathan_fegard/avatar_1536531058.png"}}}}},{"node":{"id":"3","title":"Accepted Java O(n) Solution","viewCount":243924,"post":{"id":3,"voteCount":473,"creationDate":1406394321,"author":{"username":"jiaming2","profile":{"userSlug":"jiaming2","userAvatar":"https://assets.leetcode.com/users/jiaming2/avatar_1539060294.png"}}}}},{"node":{"id":"13","title":"Accepted C++ O(n) Solution","viewCount":140576,"post":{"id":13,"voteCount":330,"creationDate":1410541028,"author":{"username":"naveed.zafar","profile":{"userSlug":"naveedzafar","userAvatar":"https://www.gravatar.com/avatar/afff4e209ce59d0d54b06db8f8b86c62.png?s=200"}}}}},{"node":{"id":"6","title":"My (short) Java solution [O(n) + HashMap!]","viewCount":30480,"post":{"id":6,"voteCount":63,"creationDate":1434841629,"author":{"username":"naus","profile":{"userSlug":"naus","userAvatar":"https://assets.leetcode.com/users/waisuan/avatar_1518936114.png"}}}}},{"node":{"id":"2","title":"Python solution using hash","viewCount":21156,"post":{"id":2,"voteCount":53,"creationDate":1427247920,"author":{"username":"Google","profile":{"userSlug":"google","userAvatar":"https://www.gravatar.com/avatar/fb4c5336bbcac8ef753ba8d3e3e351e8.png?s=200"}}}}},{"node":{"id":"141","title":"Very short and simple Java code for Two Sum","viewCount":52728,"post":{"id":141,"voteCount":53,"creationDate":1419378106,"author":{"username":"oKephart","profile":{"userSlug":"okephart","userAvatar":"https://www.gravatar.com/avatar/5b103fdb34492c6346c9b1d486c87be1.png?s=200"}}}}},{"node":{"id":"7","title":"Java, O(nlogn), beats  98.85%","viewCount":17973,"post":{"id":7,"voteCount":40,"creationDate":1462863256,"author":{"username":"yangliguang","profile":{"userSlug":"yangliguang","userAvatar":"https://www.gravatar.com/avatar/bce2528dfe99269800da604f63048aaf.png?s=200"}}}}},{"node":{"id":"711","title":"How is difficulty evaluated and what does acceptance mean?","viewCount":4746,"post":{"id":711,"voteCount":36,"creationDate":1434484173,"author":{"username":"larryleguo","profile":{"userSlug":"larryleguo","userAvatar":"https://www.gravatar.com/avatar/a8a4b5a26828a290df9aa1487b2afc32.png?s=200"}}}}},{"node":{"id":"126","title":"Accepted C++ in 11 lines","viewCount":16353,"post":{"id":126,"voteCount":34,"creationDate":1433641905,"author":{"username":"mako","profile":{"userSlug":"mako","userAvatar":"https://www.gravatar.com/avatar/7fd0e4f5b879b97d258584a4254ff445.png?s=200"}}}}},{"node":{"id":"583","title":"TwoSum Java code using HashMap","viewCount":6758,"post":{"id":583,"voteCount":33,"creationDate":1448936358,"author":{"username":"Sunny1988","profile":{"userSlug":"sunny1988","userAvatar":"https://www.gravatar.com/avatar/b6c6d02c06cdf4a0d122939e07c3d988.png?s=200"}}}}},{"node":{"id":"57","title":"4ms accepted solution with C","viewCount":12288,"post":{"id":57,"voteCount":27,"creationDate":1430780103,"author":{"username":"KarlBaoJJJ","profile":{"userSlug":"karlbaojjj","userAvatar":"https://www.gravatar.com/avatar/a0e94017a8b53bc5a28a5ee66235eed8.png?s=200"}}}}},{"node":{"id":"80","title":"Python seven lines","viewCount":10062,"post":{"id":80,"voteCount":26,"creationDate":1446261682,"author":{"username":"xxfer","profile":{"userSlug":"xxfer","userAvatar":"https://www.gravatar.com/avatar/41ed9438ff5ffedd96a7926f448caf08.png?s=200"}}}}},{"node":{"id":"684","title":"11 lines and 1 for in Java","viewCount":4937,"post":{"id":684,"voteCount":24,"creationDate":1438059163,"author":{"username":"fredericosar","profile":{"userSlug":"fredericosar","userAvatar":"https://www.gravatar.com/avatar/4c270a20c25e109c10a0dd4360cbeda3.png?s=200"}}}}},{"node":{"id":"600","title":"My O(NlogN) and O(N) time complexity solution, with O(N) space","viewCount":7148,"post":{"id":600,"voteCount":22,"creationDate":1405057946,"author":{"username":"songrenchu","profile":{"userSlug":"songrenchu","userAvatar":"https://assets.leetcode.com/users/songrenchu/avatar_1550376880.png"}}}}},{"node":{"id":"96","title":"Hashtable,Python,5 lines","viewCount":2754,"post":{"id":96,"voteCount":21,"creationDate":1454036573,"author":{"username":"hxuanz","profile":{"userSlug":"hxuanz","userAvatar":"https://assets.leetcode.com/users/hxuanz/avatar_1533776907.png"}}}}}]}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * questionTopicsList : {"totalNum":1875,"edges":[{"node":{"id":"17","title":"Here is a Python solution in O(n) time","viewCount":146173,"post":{"id":17,"voteCount":473,"creationDate":1440998099,"author":{"username":"Nathan_Fegard","profile":{"userSlug":"nathan_fegard","userAvatar":"https://assets.leetcode.com/users/nathan_fegard/avatar_1536531058.png"}}}}},{"node":{"id":"3","title":"Accepted Java O(n) Solution","viewCount":243924,"post":{"id":3,"voteCount":473,"creationDate":1406394321,"author":{"username":"jiaming2","profile":{"userSlug":"jiaming2","userAvatar":"https://assets.leetcode.com/users/jiaming2/avatar_1539060294.png"}}}}},{"node":{"id":"13","title":"Accepted C++ O(n) Solution","viewCount":140576,"post":{"id":13,"voteCount":330,"creationDate":1410541028,"author":{"username":"naveed.zafar","profile":{"userSlug":"naveedzafar","userAvatar":"https://www.gravatar.com/avatar/afff4e209ce59d0d54b06db8f8b86c62.png?s=200"}}}}},{"node":{"id":"6","title":"My (short) Java solution [O(n) + HashMap!]","viewCount":30480,"post":{"id":6,"voteCount":63,"creationDate":1434841629,"author":{"username":"naus","profile":{"userSlug":"naus","userAvatar":"https://assets.leetcode.com/users/waisuan/avatar_1518936114.png"}}}}},{"node":{"id":"2","title":"Python solution using hash","viewCount":21156,"post":{"id":2,"voteCount":53,"creationDate":1427247920,"author":{"username":"Google","profile":{"userSlug":"google","userAvatar":"https://www.gravatar.com/avatar/fb4c5336bbcac8ef753ba8d3e3e351e8.png?s=200"}}}}},{"node":{"id":"141","title":"Very short and simple Java code for Two Sum","viewCount":52728,"post":{"id":141,"voteCount":53,"creationDate":1419378106,"author":{"username":"oKephart","profile":{"userSlug":"okephart","userAvatar":"https://www.gravatar.com/avatar/5b103fdb34492c6346c9b1d486c87be1.png?s=200"}}}}},{"node":{"id":"7","title":"Java, O(nlogn), beats  98.85%","viewCount":17973,"post":{"id":7,"voteCount":40,"creationDate":1462863256,"author":{"username":"yangliguang","profile":{"userSlug":"yangliguang","userAvatar":"https://www.gravatar.com/avatar/bce2528dfe99269800da604f63048aaf.png?s=200"}}}}},{"node":{"id":"711","title":"How is difficulty evaluated and what does acceptance mean?","viewCount":4746,"post":{"id":711,"voteCount":36,"creationDate":1434484173,"author":{"username":"larryleguo","profile":{"userSlug":"larryleguo","userAvatar":"https://www.gravatar.com/avatar/a8a4b5a26828a290df9aa1487b2afc32.png?s=200"}}}}},{"node":{"id":"126","title":"Accepted C++ in 11 lines","viewCount":16353,"post":{"id":126,"voteCount":34,"creationDate":1433641905,"author":{"username":"mako","profile":{"userSlug":"mako","userAvatar":"https://www.gravatar.com/avatar/7fd0e4f5b879b97d258584a4254ff445.png?s=200"}}}}},{"node":{"id":"583","title":"TwoSum Java code using HashMap","viewCount":6758,"post":{"id":583,"voteCount":33,"creationDate":1448936358,"author":{"username":"Sunny1988","profile":{"userSlug":"sunny1988","userAvatar":"https://www.gravatar.com/avatar/b6c6d02c06cdf4a0d122939e07c3d988.png?s=200"}}}}},{"node":{"id":"57","title":"4ms accepted solution with C","viewCount":12288,"post":{"id":57,"voteCount":27,"creationDate":1430780103,"author":{"username":"KarlBaoJJJ","profile":{"userSlug":"karlbaojjj","userAvatar":"https://www.gravatar.com/avatar/a0e94017a8b53bc5a28a5ee66235eed8.png?s=200"}}}}},{"node":{"id":"80","title":"Python seven lines","viewCount":10062,"post":{"id":80,"voteCount":26,"creationDate":1446261682,"author":{"username":"xxfer","profile":{"userSlug":"xxfer","userAvatar":"https://www.gravatar.com/avatar/41ed9438ff5ffedd96a7926f448caf08.png?s=200"}}}}},{"node":{"id":"684","title":"11 lines and 1 for in Java","viewCount":4937,"post":{"id":684,"voteCount":24,"creationDate":1438059163,"author":{"username":"fredericosar","profile":{"userSlug":"fredericosar","userAvatar":"https://www.gravatar.com/avatar/4c270a20c25e109c10a0dd4360cbeda3.png?s=200"}}}}},{"node":{"id":"600","title":"My O(NlogN) and O(N) time complexity solution, with O(N) space","viewCount":7148,"post":{"id":600,"voteCount":22,"creationDate":1405057946,"author":{"username":"songrenchu","profile":{"userSlug":"songrenchu","userAvatar":"https://assets.leetcode.com/users/songrenchu/avatar_1550376880.png"}}}}},{"node":{"id":"96","title":"Hashtable,Python,5 lines","viewCount":2754,"post":{"id":96,"voteCount":21,"creationDate":1454036573,"author":{"username":"hxuanz","profile":{"userSlug":"hxuanz","userAvatar":"https://assets.leetcode.com/users/hxuanz/avatar_1533776907.png"}}}}}]}
         */

        private QuestionTopicsListBean questionTopicsList;

        public QuestionTopicsListBean getQuestionTopicsList() {
            return questionTopicsList;
        }

        public void setQuestionTopicsList(QuestionTopicsListBean questionTopicsList) {
            this.questionTopicsList = questionTopicsList;
        }

        public static class QuestionTopicsListBean implements Serializable{
            /**
             * totalNum : 1875
             * edges : [{"node":{"id":"17","title":"Here is a Python solution in O(n) time","viewCount":146173,"post":{"id":17,"voteCount":473,"creationDate":1440998099,"author":{"username":"Nathan_Fegard","profile":{"userSlug":"nathan_fegard","userAvatar":"https://assets.leetcode.com/users/nathan_fegard/avatar_1536531058.png"}}}}},{"node":{"id":"3","title":"Accepted Java O(n) Solution","viewCount":243924,"post":{"id":3,"voteCount":473,"creationDate":1406394321,"author":{"username":"jiaming2","profile":{"userSlug":"jiaming2","userAvatar":"https://assets.leetcode.com/users/jiaming2/avatar_1539060294.png"}}}}},{"node":{"id":"13","title":"Accepted C++ O(n) Solution","viewCount":140576,"post":{"id":13,"voteCount":330,"creationDate":1410541028,"author":{"username":"naveed.zafar","profile":{"userSlug":"naveedzafar","userAvatar":"https://www.gravatar.com/avatar/afff4e209ce59d0d54b06db8f8b86c62.png?s=200"}}}}},{"node":{"id":"6","title":"My (short) Java solution [O(n) + HashMap!]","viewCount":30480,"post":{"id":6,"voteCount":63,"creationDate":1434841629,"author":{"username":"naus","profile":{"userSlug":"naus","userAvatar":"https://assets.leetcode.com/users/waisuan/avatar_1518936114.png"}}}}},{"node":{"id":"2","title":"Python solution using hash","viewCount":21156,"post":{"id":2,"voteCount":53,"creationDate":1427247920,"author":{"username":"Google","profile":{"userSlug":"google","userAvatar":"https://www.gravatar.com/avatar/fb4c5336bbcac8ef753ba8d3e3e351e8.png?s=200"}}}}},{"node":{"id":"141","title":"Very short and simple Java code for Two Sum","viewCount":52728,"post":{"id":141,"voteCount":53,"creationDate":1419378106,"author":{"username":"oKephart","profile":{"userSlug":"okephart","userAvatar":"https://www.gravatar.com/avatar/5b103fdb34492c6346c9b1d486c87be1.png?s=200"}}}}},{"node":{"id":"7","title":"Java, O(nlogn), beats  98.85%","viewCount":17973,"post":{"id":7,"voteCount":40,"creationDate":1462863256,"author":{"username":"yangliguang","profile":{"userSlug":"yangliguang","userAvatar":"https://www.gravatar.com/avatar/bce2528dfe99269800da604f63048aaf.png?s=200"}}}}},{"node":{"id":"711","title":"How is difficulty evaluated and what does acceptance mean?","viewCount":4746,"post":{"id":711,"voteCount":36,"creationDate":1434484173,"author":{"username":"larryleguo","profile":{"userSlug":"larryleguo","userAvatar":"https://www.gravatar.com/avatar/a8a4b5a26828a290df9aa1487b2afc32.png?s=200"}}}}},{"node":{"id":"126","title":"Accepted C++ in 11 lines","viewCount":16353,"post":{"id":126,"voteCount":34,"creationDate":1433641905,"author":{"username":"mako","profile":{"userSlug":"mako","userAvatar":"https://www.gravatar.com/avatar/7fd0e4f5b879b97d258584a4254ff445.png?s=200"}}}}},{"node":{"id":"583","title":"TwoSum Java code using HashMap","viewCount":6758,"post":{"id":583,"voteCount":33,"creationDate":1448936358,"author":{"username":"Sunny1988","profile":{"userSlug":"sunny1988","userAvatar":"https://www.gravatar.com/avatar/b6c6d02c06cdf4a0d122939e07c3d988.png?s=200"}}}}},{"node":{"id":"57","title":"4ms accepted solution with C","viewCount":12288,"post":{"id":57,"voteCount":27,"creationDate":1430780103,"author":{"username":"KarlBaoJJJ","profile":{"userSlug":"karlbaojjj","userAvatar":"https://www.gravatar.com/avatar/a0e94017a8b53bc5a28a5ee66235eed8.png?s=200"}}}}},{"node":{"id":"80","title":"Python seven lines","viewCount":10062,"post":{"id":80,"voteCount":26,"creationDate":1446261682,"author":{"username":"xxfer","profile":{"userSlug":"xxfer","userAvatar":"https://www.gravatar.com/avatar/41ed9438ff5ffedd96a7926f448caf08.png?s=200"}}}}},{"node":{"id":"684","title":"11 lines and 1 for in Java","viewCount":4937,"post":{"id":684,"voteCount":24,"creationDate":1438059163,"author":{"username":"fredericosar","profile":{"userSlug":"fredericosar","userAvatar":"https://www.gravatar.com/avatar/4c270a20c25e109c10a0dd4360cbeda3.png?s=200"}}}}},{"node":{"id":"600","title":"My O(NlogN) and O(N) time complexity solution, with O(N) space","viewCount":7148,"post":{"id":600,"voteCount":22,"creationDate":1405057946,"author":{"username":"songrenchu","profile":{"userSlug":"songrenchu","userAvatar":"https://assets.leetcode.com/users/songrenchu/avatar_1550376880.png"}}}}},{"node":{"id":"96","title":"Hashtable,Python,5 lines","viewCount":2754,"post":{"id":96,"voteCount":21,"creationDate":1454036573,"author":{"username":"hxuanz","profile":{"userSlug":"hxuanz","userAvatar":"https://assets.leetcode.com/users/hxuanz/avatar_1533776907.png"}}}}}]
             */

            private int totalNum;
            private List<EdgesBean> edges;

            public int getTotalNum() {
                return totalNum;
            }

            public void setTotalNum(int totalNum) {
                this.totalNum = totalNum;
            }

            public List<EdgesBean> getEdges() {
                return edges;
            }

            public void setEdges(List<EdgesBean> edges) {
                this.edges = edges;
            }

            public static class EdgesBean implements Serializable {
                /**
                 * node : {"id":"17","title":"Here is a Python solution in O(n) time","viewCount":146173,"post":{"id":17,"voteCount":473,"creationDate":1440998099,"author":{"username":"Nathan_Fegard","profile":{"userSlug":"nathan_fegard","userAvatar":"https://assets.leetcode.com/users/nathan_fegard/avatar_1536531058.png"}}}}
                 */

                private NodeBean node;

                public NodeBean getNode() {
                    return node;
                }

                public void setNode(NodeBean node) {
                    this.node = node;
                }

                public static class NodeBean implements Serializable{
                    /**
                     * id : 17
                     * title : Here is a Python solution in O(n) time
                     * viewCount : 146173
                     * post : {"id":17,"voteCount":473,"creationDate":1440998099,"author":{"username":"Nathan_Fegard","profile":{"userSlug":"nathan_fegard","userAvatar":"https://assets.leetcode.com/users/nathan_fegard/avatar_1536531058.png"}}}
                     */

                    private int id;
                    private String title;
                    private int viewCount;
                    private PostBean post;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public int getViewCount() {
                        return viewCount;
                    }

                    public void setViewCount(int viewCount) {
                        this.viewCount = viewCount;
                    }

                    public PostBean getPost() {
                        return post;
                    }

                    public void setPost(PostBean post) {
                        this.post = post;
                    }

                    public static class PostBean implements Serializable{
                        /**
                         * id : 17
                         * voteCount : 473
                         * creationDate : 1440998099
                         * author : {"username":"Nathan_Fegard","profile":{"userSlug":"nathan_fegard","userAvatar":"https://assets.leetcode.com/users/nathan_fegard/avatar_1536531058.png"}}
                         */

                        private int id;
                        private int voteCount;
                        private long creationDate;
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

                        public long getCreationDate() {
                            return creationDate;
                        }

                        public void setCreationDate(long creationDate) {
                            this.creationDate = creationDate;
                        }

                        public AuthorBean getAuthor() {
                            return author;
                        }

                        public void setAuthor(AuthorBean author) {
                            this.author = author;
                        }

                        public static class AuthorBean implements Serializable{
                            /**
                             * username : Nathan_Fegard
                             * profile : {"userSlug":"nathan_fegard","userAvatar":"https://assets.leetcode.com/users/nathan_fegard/avatar_1536531058.png"}
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

                            public static class ProfileBean implements Serializable{
                                /**
                                 * userSlug : nathan_fegard
                                 * userAvatar : https://assets.leetcode.com/users/nathan_fegard/avatar_1536531058.png
                                 */

                                private String userSlug;
                                private String userAvatar;

                                public String getUserSlug() {
                                    return userSlug;
                                }

                                public void setUserSlug(String userSlug) {
                                    this.userSlug = userSlug;
                                }

                                public String getUserAvatar() {
                                    return userAvatar;
                                }

                                public void setUserAvatar(String userAvatar) {
                                    this.userAvatar = userAvatar;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
