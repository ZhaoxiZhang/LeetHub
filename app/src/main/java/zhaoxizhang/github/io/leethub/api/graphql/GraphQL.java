package zhaoxizhang.github.io.leethub.api.graphql;

public class GraphQL {
    //"Content-Type", "application/json"
    public static final String DISCUSS_FORM = "{\"operationName\": \"questionTopicsList\",\"variables\":{\"orderBy\": \"%s\",\"query\": \"\",\"skip\": %d,\"first\": %d,\"questionId\": \"%d\"},\"query\": \"query questionTopicsList($questionId: String!, $orderBy: TopicSortingOption, $skip: Int, $query: String, $first: Int!) {  questionTopicsList(questionId: $questionId, orderBy: $orderBy, skip: $skip, query: $query, first: $first) {    ...TopicsList     }}fragment TopicsList on TopicConnection {  totalNum  edges {    node {      id      title      viewCount      post {        id        voteCount        creationDate        author {          username          profile {            userSlug            userAvatar          }}}}}}\"}";

    //"Content-Type", "application/json"
    public static final String DISCUSS_ITEM_FORM = "{\"operationName\":\"DiscussTopic\",\"variables\":{\"topicId\":%d},\"query\":\"query DiscussTopic($topicId: Int!) {  topic(id: $topicId) {    id    viewCount      title    post {      ...DiscussPost      }    }}fragment DiscussPost on PostNode {  id  voteCount  content  updationDate  author {   username    profile {      userAvatar      reputation     }    }}\"}";


}
