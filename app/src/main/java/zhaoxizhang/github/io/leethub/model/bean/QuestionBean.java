package zhaoxizhang.github.io.leethub.model.bean;


public class QuestionBean {

    /**
     * question_id : 968
     * frontend_question_id : 932
     * article_live : true
     * article_slug : beautiful-array
     * title : Beautiful Array
     * title_slug : beautiful-array
     * content : <p>For some fixed <code>N</code>, an array <code>A</code> is <em>beautiful</em> if it is a permutation of the integers <code>1, 2, ..., N</code>, such that:</p>

     <p>For every <code>i &lt; j</code>, there is <strong>no</strong>&nbsp;<code>k</code> with <code>i &lt; k &lt; j</code>&nbsp;such that <code>A[k] * 2 = A[i] + A[j]</code>.</p>

     <p>Given <code>N</code>, return <strong>any</strong> beautiful array <code>A</code>.&nbsp; (It is guaranteed that one exists.)</p>

     <p>&nbsp;</p>

     <p><strong>Example 1:</strong></p>

     <pre>
     <strong>Input: </strong><span id="example-input-1-1">4</span>
     <strong>Output: </strong><span id="example-output-1">[2,1,4,3]</span>
     </pre>

     <div>
     <p><strong>Example 2:</strong></p>

     <pre>
     <strong>Input: </strong><span id="example-input-2-1">5</span>
     <strong>Output: </strong><span>[3,1,2,5,4]</span></pre>

     <p>&nbsp;</p>
     </div>

     <p><strong>Note:</strong></p>

     <ul>
     <li><code>1 &lt;= N &lt;= 1000</code></li>
     </ul>

     <div>
     <div>&nbsp;</div>
     </div>

     * tags :
     * difficulty : 2
     * frontend_article_id : 932
     * article_content : <div class="article-body">
     <div class="block-markdown">
     <div class="toc">
     <ul>
     <li><a href="#solution">Solution</a>
     <ul>
     <li><a href="#approach-1-divide-and-conquer">Approach 1: Divide and Conquer</a></li>
     </ul> </li>
     </ul>
     </div>
     <h2 id="solution">Solution</h2>
     <hr>
     <h4 id="approach-1-divide-and-conquer">Approach 1: Divide and Conquer</h4>
     <p><strong>Intuition</strong></p>
     <p>This answer is quite unintuitive.</p>
     <p>First, notice that the condition is equivalent to saying that <code>A</code> has no arithmetic subsequence. We'll use the term "<em>arithmetic-free</em>" interchangeably with "<em>beautiful</em>".</p>
     <p>One way is to guess that we should divide and conquer. One reason for this is that the condition is linear, so if the condition is satisfied by variables taking on values <code>(1, 2, ..., n)</code>, it is satisfied by those variables taking on values <code>(a + b, a + 2*b, a + 3*b, ..., a + (n-1)*b)</code> instead.</p>
     <p>If we perform a divide and conquer, then we have two parts <code>left</code> and <code>right</code>, such that each part is arithmetic-free, and we only want that a triple from both parts is not arithmetic. Looking at the conditions:</p>
     <ul>
     <li><code>2*A[k] = A[i] + A[j]</code></li>
     <li><code>(i &lt; k &lt; j)</code>, <code>i</code> from <code>left</code>, <code>j</code> from <code>right</code></li>
     </ul>
     <p>we can guess that because the left hand side <code>2*A[k]</code> is even, we can choose <code>left</code> to have all odd elements, and <code>right</code> to have all even elements.</p>
     <p>Another way we could arrive at this is to try to place a number in the middle, like <code>5</code>. We will have <code>4</code> and <code>6</code> say, to the left of <code>5</code>, and <code>7</code> to the right of <code>6</code>, etc. We see that in general, odd numbers move towards one direction and even numbers towards another direction.</p>
     <p>One final way we could arrive at this is to inspect possible answers arrived at by brute force. On experimentation, we see that many answers have all the odd elements to one side, and all the even elements to the other side, with only minor variation.</p>
     <p><strong>Algorithm</strong></p>
     <p>Looking at the elements <code>1, 2, ..., N</code>, there are <code>(N+1) / 2</code> odd numbers and <code>N / 2</code> even numbers.</p>
     <p>We solve for elements <code>1, 2, ..., (N+1) / 2</code> and map these numbers onto <code>1, 3, 5, ...</code>. Similarly, we solve for elements <code>1, 2, ..., N/2</code> and map these numbers onto <code>2, 4, 6, ...</code>.</p>
     <p>We can compose these solutions by concatenating them, since an arithmetic sequence never starts and ends with elements of different parity.</p>
     <p>We memoize the result to arrive at the answer quicker.</p>
     <iframe src="https://leetcode.com/playground/3NT7Bgm6/shared" frameborder="0" width="100%" height="480" name="3NT7Bgm6"></iframe>
     <p><strong>Complexity Analysis</strong></p>
     <ul>
     <li> <p>Time Complexity: <script type="math/tex; mode=display">O(N \log N)</script>. The function <code>f</code> is called only <script type="math/tex; mode=display">O(\log N)</script> times, and each time does <script type="math/tex; mode=display">O(N)</script> work.</p> </li>
     <li> <p>Space Complexity: <script type="math/tex; mode=display">O(N \log N)</script>. <br> <br></p> </li>
     </ul>
     <hr>
     <p>Analysis written by: <a href="https://leetcode.com/awice">@awice</a>.</p>
     </div>
     </div>
     */

    private int question_id;
    private int frontend_question_id;
    private boolean article_live;
    private String article_slug;
    private String title;
    private String title_slug;
    private String content;
    private String tags;
    private int difficulty;
    private int frontend_article_id;
    private String article_content;

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public int getFrontend_question_id() {
        return frontend_question_id;
    }

    public void setFrontend_question_id(int frontend_question_id) {
        this.frontend_question_id = frontend_question_id;
    }

    public boolean isArticle_live() {
        return article_live;
    }

    public void setArticle_live(boolean article_live) {
        this.article_live = article_live;
    }

    public String getArticle_slug() {
        return article_slug;
    }

    public void setArticle_slug(String article_slug) {
        this.article_slug = article_slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_slug() {
        return title_slug;
    }

    public void setTitle_slug(String title_slug) {
        this.title_slug = title_slug;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getFrontend_article_id() {
        return frontend_article_id;
    }

    public void setFrontend_article_id(int frontend_article_id) {
        this.frontend_article_id = frontend_article_id;
    }

    public String getArticle_content() {
        return article_content;
    }

    public void setArticle_content(String article_content) {
        this.article_content = article_content;
    }
}
