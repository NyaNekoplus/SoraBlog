<template>
  <div>
    <cover
        :author="blog.author"
        :title="blog.title"
        :release-time="blog.createTime"
        :view-count="blog.viewCount"
        :comment-count="blog.commentCount"
        :tag-list="blog.tagList"
        :cover-src="blog.coverUrl"
        avatar-src="https://cdn.jsdelivr.net/gh/Nyanekoplus/js@master/data/avatar0.png"
    />
    <div id="content" class="site-content">
      <div id="primary" class="content-area">
        <main id="main" class="site-main" role="main">
          <article id="post-4491"
                   class="post-4491 post type-post status-publish format-standard has-post-thumbnail hentry category-hacking tag-graphql tag-javascript tag-wordpress">
            <div class="entry-content">
              <div v-html="blog.contentMd"></div>
              <!--
              <p>当我们需要用 GraphQL 查询多层套嵌的数据，比如像 WordPress 这样套嵌的评论信息时，通常的写法是：</p>
              <pre class="highlight-wrap" autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false"
                   design="by Mashiro" contenteditable="false"><code class="language-json hljs" data-rel="JSON"
                                                                     id="hljs-0"><table class="hljs-ln"><tbody><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="1"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">{</div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="2"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line">  posts(first: <span class="hljs-number">100</span>) {</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="3"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">    nodes {</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="4"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">      id</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="5"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">      title</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="6"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">      comments {</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="7"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">        nodes {</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="8"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">          ...CommentFields</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="9"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">          replies: children {</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="10"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">            nodes {</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="11"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">              ...CommentFields</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="12"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">              replies: children {</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="13"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">                nodes {</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="14"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">                  ...CommentFields</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="15"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">                  replies: children {</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="16"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">                    nodes {</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="17"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">                      ...CommentFields</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="18"></div></td><td
                  class="hljs-ln-code"><div
                  class="hljs-ln-line">                      replies: children {</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="19"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">                        nodes {</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="20"></div></td><td
                  class="hljs-ln-code"><div
                  class="hljs-ln-line">                          ...CommentFields</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="21"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">                        }</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="22"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">                      }</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="23"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">                    }</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="24"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">                  }</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="25"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">                }</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="26"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">              }</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="27"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">            }</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="28"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">          }</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="29"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">        }</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="30"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">      }</div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="31"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line">    }</div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="32"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line">  }</div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="33"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line">}</div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="34"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line"> </div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="35"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line">fragment CommentFields on Comment {</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="36"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">  id</div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="37"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line">  date</div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="38"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line">  type</div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="39"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line">  approved</div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="40"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line">  content</div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="41"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line">}</div></td></tr></tbody></table></code><a class="copy-code" href="javascript:"
                                                                                  data-clipboard-target="#hljs-0"
                                                                                  title="拷贝代码"><i
                  class="fa fa-clipboard" aria-hidden="true"></i></a></pre>
              <p>以上的写法只实现了四层套嵌评论的查询，很麻烦对不对？这或许是 GraphQL 的缺陷，但这也或许正体现了 GraphQL 的设计理念——所得即所查。</p>
              <p>找了一下，没有现成的轮子，就自己写一个套嵌实现吧（注意 graphql 查询语句要顶头写，多余的缩进会影响递归结果）：</p>
              <pre class="highlight-wrap" autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false"
                   design="by Mashiro" contenteditable="false"><code class="language-js hljs javascript"
                                                                     data-rel="JAVASCRIPT" id="hljs-1"><table
                  class="hljs-ln"><tbody><tr><td class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n"
                                                                              data-line-number="1"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"><span
                  class="hljs-keyword">import</span> ApolloClient <span class="hljs-keyword">from</span> <span
                  class="hljs-string">'apollo-boost'</span></div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="2"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line"><span class="hljs-keyword">import</span> gql <span
                  class="hljs-keyword">from</span> <span class="hljs-string">'graphql-tag'</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="3"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"> </div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="4"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line"><span class="hljs-class"><span class="hljs-keyword">class</span> <span
                  class="hljs-title">getPostCommentByPostId</span> </span>{</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="5"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">  private postId: number</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="6"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">  private MaxChildrenLevel: number</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="7"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">  private data: any</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="8"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"> </div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="9"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line">  <span class="hljs-comment"><span
                  class="hljs-comment">/**</span></span></div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="10"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line"><span class="hljs-comment">   * @param {number} postId           wordpress post id</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="11"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"><span class="hljs-comment">   * @param {number} MaxChildrenLevel post threaded (nested) comments levels deep (/wp-admin/options-discussion.php)</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="12"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"><span class="hljs-comment">   */</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="13"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">  public <span class="hljs-keyword">constructor</span>(postId: number, MaxChildrenLevel) {</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="14"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">    <span class="hljs-keyword">this</span>.postId = postId</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="15"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">    <span class="hljs-keyword">this</span>.MaxChildrenLevel = MaxChildrenLevel</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="16"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">  }</div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="17"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line"> </div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="18"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line">  <span class="hljs-comment">// 处理递归部分</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="19"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">  private queryChildren() {</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="20"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">    <span class="hljs-keyword">let</span> queryHeader: string = <span
                  class="hljs-string">``</span></div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="21"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line">    <span class="hljs-keyword">let</span> queryFooter: string = <span
                  class="hljs-string">``</span></div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="22"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line">    <span class="hljs-comment">// 迭代之前的内容</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="23"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">    <span class="hljs-keyword">const</span> childrenHeader: string = <span
                  class="hljs-string"><span class="hljs-string">`</span></span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="24"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"><span class="hljs-string">children {</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="25"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"><span
                  class="hljs-string">  nodes {</span></div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="26"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line"><span class="hljs-string">    ...CommentFields`</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="27"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">    <span class="hljs-comment">// 迭代之后的内容</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="28"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">    <span class="hljs-keyword">const</span> childrenFooter: string = <span
                  class="hljs-string"><span class="hljs-string">`</span></span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="29"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"><span class="hljs-string">  }</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="30"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"><span class="hljs-string">}`</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="31"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"> </div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="32"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line">    <span class="hljs-comment">// 处理每行前的空格</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="33"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">    <span
                  class="hljs-keyword">let</span> addTabs = <span class="hljs-function"><span class="hljs-keyword">function</span> (<span
                  class="hljs-params">str: string, n: number</span>) </span>{</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="34"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">      <span class="hljs-keyword">return</span> str.replace(<span
                  class="hljs-regexp">/^/gm</span>, <span class="hljs-string">'  '</span>.repeat(n)) <span
                  class="hljs-comment">// 注意我用的是两格缩进，四格缩进请自行调整</span></div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="35"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line">    }</div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="36"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line"> </div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="37"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line">    <span class="hljs-keyword">for</span> (<span class="hljs-keyword">let</span> i = <span
                  class="hljs-number">0</span>; i &lt; <span
                  class="hljs-keyword">this</span>.MaxChildrenLevel; i++) {</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="38"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">      queryHeader = addTabs(childrenHeader + queryHeader, <span
                  class="hljs-number">2</span>)</div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="39"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line">      queryFooter = addTabs(queryFooter + childrenFooter, <span
                  class="hljs-number">2</span>)</div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="40"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line">    }</div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="41"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line">    <span
                  class="hljs-keyword">return</span> addTabs(queryHeader + queryFooter, <span
                  class="hljs-number">2</span>)</div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="42"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line">  }</div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="43"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line"> </div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="44"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line">  <span class="hljs-comment">// 查询部分</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="45"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">  private query() {</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="46"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">    <span class="hljs-keyword">const</span> client: ApolloClient = <span
                  class="hljs-keyword">new</span> ApolloClient()</div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="47"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line">    client.query({</div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="48"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line">      <span class="hljs-attr">query</span>: gql<span class="hljs-string"><span
                  class="hljs-string">`</span></span></div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="49"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line"><span class="hljs-string">query GET_POST($postId: Int) {</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="50"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"><span
                  class="hljs-string">  postBy(postId: $postId) {</span></div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="51"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line"><span class="hljs-string">    id</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="52"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"><span class="hljs-string">    postId</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="53"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"><span
                  class="hljs-string">    title</span></div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="54"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line"><span class="hljs-string">    date</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="55"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"><span
                  class="hljs-string">    uri</span></div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="56"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line"><span class="hljs-string">    content</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="57"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"><span class="hljs-string">  }</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="58"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"><span
                  class="hljs-string">  comments {</span></div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="59"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line"><span class="hljs-string">    edges {</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="60"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"><span
                  class="hljs-string">      node {</span></div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="61"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line"><span class="hljs-string">        ...CommentFields<span
                  class="hljs-subst">${<span class="hljs-keyword">this</span>.queryChildren()}</span></span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="62"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"><span
                  class="hljs-string">      }</span></div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="63"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line"><span class="hljs-string">    }</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="64"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"><span class="hljs-string">  }</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="65"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"><span class="hljs-string">}</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="66"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"><span class="hljs-string">fragment CommentFields on Comment {</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="67"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"><span class="hljs-string">  date</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="68"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"><span
                  class="hljs-string">  agent</span></div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="69"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line"><span class="hljs-string">  content(format: RENDERED)</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="70"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"><span class="hljs-string">  commentId</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="71"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"><span class="hljs-string">  author {</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="72"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"><span
                  class="hljs-string">    ... on CommentAuthor {</span></div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="73"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line"><span class="hljs-string">      email</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="74"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"><span class="hljs-string">      name</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="75"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"><span
                  class="hljs-string">      url</span></div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="76"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line"><span class="hljs-string">    }</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="77"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"><span class="hljs-string">  }</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="78"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"><span class="hljs-string">  authorIp</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="79"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"><span class="hljs-string">}</span></div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="80"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line"><span class="hljs-string">`</span>,</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="81"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">      <span class="hljs-attr">variables</span>: {</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="82"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">        <span
                  class="hljs-string">"postId"</span>: <span class="hljs-keyword">this</span>.postId</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="83"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">      },</div></td></tr><tr><td
                  class="hljs-ln-numbers"><div class="hljs-ln-line hljs-ln-n" data-line-number="84"></div></td><td
                  class="hljs-ln-code"><div class="hljs-ln-line">    })</div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="85"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line">      .then(<span class="hljs-function"><span class="hljs-params">data</span> =&gt;</span> <span
                  class="hljs-built_in">console</span>.log(data))</div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="86"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line">      .catch(<span class="hljs-function"><span class="hljs-params">error</span> =&gt;</span> <span
                  class="hljs-built_in">console</span>.log(error))</div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="87"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line">  }</div></td></tr><tr><td class="hljs-ln-numbers"><div
                  class="hljs-ln-line hljs-ln-n" data-line-number="88"></div></td><td class="hljs-ln-code"><div
                  class="hljs-ln-line">}</div></td></tr></tbody></table></code><a class="copy-code" href="javascript:"
                                                                                  data-clipboard-target="#hljs-1"
                                                                                  title="拷贝代码"><i
                  class="fa fa-clipboard" aria-hidden="true"></i></a></pre>
              <p>Q.E.D. <i class="fa fa-meetup" aria-hidden="true" style="color:#d34836"></i></p>
              <h2 class="footer-comment-flag" style="opacity:0;max-height:0;margin:0">Comments</h2>
          -->
            </div>
            <div class="single-reward">
              <div class="reward-open">赏
                <div class="reward-main">
                  <ul class="reward-row">
                    <li class="alipay-code"><img
                        src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.3.3/img/other/AliPayQR.jpg"></li>
                    <li class="wechat-code"><img
                        src="https://cdn.jsdelivr.net/gh/moezx/cdn@3.3.3/img/other/WeChanQR.png"></li>
                  </ul>
                </div>
              </div>
            </div>
            <footer class="post-footer">
              <div class="post-lincenses"><a href="https://creativecommons.org/licenses/by-nc-sa/4.0/deed.zh"
                                             target="_blank" rel="nofollow"><i class="fa fa-creative-commons"
                                                                               aria-hidden="true"></i>
                知识共享署名-非商业性使用-相同方式共享 4.0 国际许可协议</a></div>
              <div class="post-tags"><i class="iconfont icon-tags"></i> <a href="https://2heng.xin/tag/graphql/"
                                                                           rel="tag">GraphQL</a> <a
                  href="https://2heng.xin/tag/javascript/" rel="tag">JavaScript</a> <a
                  href="https://2heng.xin/tag/wordpress/" rel="tag">WordPress</a></div>
              <div class="post-like"><a href="javascript:;" data-action="ding" data-id="4491" class="specsZan "> <i
                  class="iconfont icon-like"></i> <span class="count"> 39</span> </a></div>
              <div class="post-share">
                <div class="social-share sharehidden share-component"><a class="social-share-icon icon-weibo"
                                                                         href="http://service.weibo.com/share/share.php?url=https%3A%2F%2F2heng.xin%2F2019%2F12%2F31%2Fgraphql-recursion%2F&amp;title=GraphQL%20%E5%AE%9E%E7%8E%B0%E9%80%92%E5%BD%92%E6%9F%A5%E8%AF%A2%20%7C%20%E6%A8%B1%E8%8A%B1%E5%BA%84%E7%9A%84%E7%99%BD%E7%8C%AB&amp;pic=https%3A%2F%2Fcdn.jsdelivr.net%2Fgh%2Fmoezx%2Fcdn%403.1.9%2Fimg%2FSakura%2Fimages%2Fnext-b.svg&amp;appkey="
                                                                         target="_blank"></a><a
                    class="social-share-icon icon-qq"
                    href="http://connect.qq.com/widget/shareqq/index.html?url=https%3A%2F%2F2heng.xin%2F2019%2F12%2F31%2Fgraphql-recursion%2F&amp;title=GraphQL%20%E5%AE%9E%E7%8E%B0%E9%80%92%E5%BD%92%E6%9F%A5%E8%AF%A2%20%7C%20%E6%A8%B1%E8%8A%B1%E5%BA%84%E7%9A%84%E7%99%BD%E7%8C%AB&amp;source=GraphQL%20%E5%AE%9E%E7%8E%B0%E9%80%92%E5%BD%92%E6%9F%A5%E8%AF%A2%20%7C%20%E6%A8%B1%E8%8A%B1%E5%BA%84%E7%9A%84%E7%99%BD%E7%8C%AB&amp;desc=%E5%BD%93%E6%88%91%E4%BB%AC%E9%9C%80%E8%A6%81%E7%94%A8%20GraphQL%20%E6%9F%A5%E8%AF%A2%E5%A4%9A%E5%B1%82%E5%A5%97%E5%B5%8C%E7%9A%84%E6%95%B0%E6%8D%AE%EF%BC%8C%E6%AF%94%E5%A6%82%E5%83%8F%20WordPress%20%E8%BF%99%E6%A0%B7%E5%A5%97%E5%B5%8C%E7%9A%84%E8%AF%84%E8%AE%BA%E4%BF%A1%E6%81%AF%E6%97%B6%EF%BC%8C%E9%80%9A%E5%B8%B8%E7%9A%84%E5%86%99%E6%B3%95%E6%98%AF%EF%BC%9A%20%20%7B%0A%20posts(first%3A%20100)%20%7B%0A%20nodes%20%7B%0A%20id%0A%20title%0A%20comments%20%7B%0A%20nodes%20%7B%0A%20...CommentFields%0A%20replies%3A%20childre%E2%80%A6&amp;pics=https%3A%2F%2Fcdn.jsdelivr.net%2Fgh%2Fmoezx%2Fcdn%403.1.9%2Fimg%2FSakura%2Fimages%2Fnext-b.svg&amp;summary=&quot;%E5%BD%93%E6%88%91%E4%BB%AC%E9%9C%80%E8%A6%81%E7%94%A8%20GraphQL%20%E6%9F%A5%E8%AF%A2%E5%A4%9A%E5%B1%82%E5%A5%97%E5%B5%8C%E7%9A%84%E6%95%B0%E6%8D%AE%EF%BC%8C%E6%AF%94%E5%A6%82%E5%83%8F%20WordPress%20%E8%BF%99%E6%A0%B7%E5%A5%97%E5%B5%8C%E7%9A%84%E8%AF%84%E8%AE%BA%E4%BF%A1%E6%81%AF%E6%97%B6%EF%BC%8C%E9%80%9A%E5%B8%B8%E7%9A%84%E5%86%99%E6%B3%95%E6%98%AF%EF%BC%9A%20%20%7B%0A%20posts(first%3A%20100)%20%7B%0A%20nodes%20%7B%0A%20id%0A%20title%0A%20comments%20%7B%0A%20nodes%20%7B%0A%20...CommentFields%0A%20replies%3A%20childre%E2%80%A6&quot;"
                    target="_blank"></a><a class="social-share-icon icon-wechat" href="javascript:">
                  <div class="wechat-qrcode"><h4>微信扫一扫：分享</h4>
                    <div class="qrcode" title="https://2heng.xin/2019/12/31/graphql-recursion/">
                      <canvas width="100" height="100" style="display: none;"></canvas>
                      <img alt="Scan me!" style="display: block;"
                           src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAGQAAABkCAYAAABw4pVUAAAECklEQVR4nO2VQY7jQAwD/f9Pey+7QDboloqS7DgzFOCL3JZIVk/mOI7j7D7/Knr3WtG71bkJHeqMTEdXa/AYyCOBqKUGQQ12QiJeKlCredAykG8CMiFYhUWfwER6rqPtKs8GYiAG8lEgnTBVcFTHVXOvAm0gvwHIKvwMiPpEpWqjcyNfBgLCMhADmQeiCsoEEDNZrwomm0/3RJ7JO6rNQOCeyDN5R7X9B6TzRKYnezSkT2trPgYyrc1AfhKQ8+LqmN7NUoBUz6tzp8pANucfCSRaGomiQWS7xn4GNrvVi0GBqPvxT5aB7H3dBuQOE9U/9w7oju6qtkjH9iEfZiYMxEDKQVR0V7VFOlIgUWBqLxOizqVBkO9ocJ0d0XfphSdLDeQDQFRx0WIqTq3KDDkQ2FP10p6BGIiBSECo+KinCFWeSoXmxQtE8+iANpCnAamGH4lXQ+qcp9qIbuVCTIDevDcQAzGQHAgdTBarZrKeGlJ2qciFUC9mx/PbYyCr+hgQxfyUGRocmauapiGqAasZbXVEAw3EQAxE/Xg1ZCcyO796X905cZF289QiAIOegex2fAUQNbjqz9NqDjU48VOkAqnM3TwGks1QcmkDiQzKw8Twq7MqRedOAIl6qab3l6oZA2H+sKb3l6oZA2H+BE1aAJGJjpCJoheCAKnsUucayDcAiRZNmJ/odaoavqqNwkkvKxFCjBqIgaD6eiB0SfW7nTgaDjlH310FnvaWD11iIHHdCiQToJ7r/EmretVLEPWu1nae8Ccremcgc9rO00CeB0QNSTUd9TLTkwAnQ73yEhqIULcCoUuVRa/nBwVLu6ozVB0V0Jv9BpKdq+rOKgRChxHT6vmKaXWuWpk24m81D/QMpKKN+FvNAz0DqWgj/lbzQK/2u9cxPbHzKt1CcNtZlYvz8hhIpu0xQKpBZOcnTWfV+bmrZlO9qH/nGoiincw1EFHL1wKhy4kZ2lN1rN5H32Zz6a7onArk7TsDeRQQIrg0+N5blYYZAbxTdzrv/WWnDEQrA/kGIMSMGkhFiCA4PZ/1lD0df/ScgQh7bgeiiiMCVudpjxpQZ1Q9VS5B1d95ngZiIAaS6dTMqIKpuOwiTJmu7KjqreRmIE8DghUURUbnaI8GSHp0hhr0RB7HcRjI44CoAsiSChDybSdMdYbay3YJ+w0k02YgBqL/K+kEF52LTKy+pXOplyrobC71bCDg/EeBqCFVYEbiox4NjuxR9KuQKMBNz0A62gzEQDiQiihimoRFA6Yhqbqn8jCQnwykY1qdq+5Sq7IrmiGAM5BVGYg4V92l1mOAdARTIdHOq4Eo+5VLFe0UewZCtUfnop1ij4WnBlutCjgSunoJKHwCS8nPQJLzBvLLgfwBkdSMuSRKV6oAAAAASUVORK5CYII=">
                    </div>
                    <div class="help"><p>微信里点“发现”，扫一下</p>
                      <p>二维码便可将本文分享至朋友圈。</p></div>
                  </div>
                </a><a class="social-share-icon icon-tencent"
                       href="http://share.v.t.qq.com/index.php?c=share&amp;a=index&amp;title=GraphQL%20%E5%AE%9E%E7%8E%B0%E9%80%92%E5%BD%92%E6%9F%A5%E8%AF%A2%20%7C%20%E6%A8%B1%E8%8A%B1%E5%BA%84%E7%9A%84%E7%99%BD%E7%8C%AB&amp;url=https%3A%2F%2F2heng.xin%2F2019%2F12%2F31%2Fgraphql-recursion%2F&amp;pic=https%3A%2F%2Fcdn.jsdelivr.net%2Fgh%2Fmoezx%2Fcdn%403.1.9%2Fimg%2FSakura%2Fimages%2Fnext-b.svg"
                       target="_blank"></a><a class="social-share-icon icon-douban"
                                              href="http://shuo.douban.com/!service/share?href=https%3A%2F%2F2heng.xin%2F2019%2F12%2F31%2Fgraphql-recursion%2F&amp;name=GraphQL%20%E5%AE%9E%E7%8E%B0%E9%80%92%E5%BD%92%E6%9F%A5%E8%AF%A2%20%7C%20%E6%A8%B1%E8%8A%B1%E5%BA%84%E7%9A%84%E7%99%BD%E7%8C%AB&amp;text=%E5%BD%93%E6%88%91%E4%BB%AC%E9%9C%80%E8%A6%81%E7%94%A8%20GraphQL%20%E6%9F%A5%E8%AF%A2%E5%A4%9A%E5%B1%82%E5%A5%97%E5%B5%8C%E7%9A%84%E6%95%B0%E6%8D%AE%EF%BC%8C%E6%AF%94%E5%A6%82%E5%83%8F%20WordPress%20%E8%BF%99%E6%A0%B7%E5%A5%97%E5%B5%8C%E7%9A%84%E8%AF%84%E8%AE%BA%E4%BF%A1%E6%81%AF%E6%97%B6%EF%BC%8C%E9%80%9A%E5%B8%B8%E7%9A%84%E5%86%99%E6%B3%95%E6%98%AF%EF%BC%9A%20%20%7B%0A%20posts(first%3A%20100)%20%7B%0A%20nodes%20%7B%0A%20id%0A%20title%0A%20comments%20%7B%0A%20nodes%20%7B%0A%20...CommentFields%0A%20replies%3A%20childre%E2%80%A6&amp;image=https%3A%2F%2Fcdn.jsdelivr.net%2Fgh%2Fmoezx%2Fcdn%403.1.9%2Fimg%2FSakura%2Fimages%2Fnext-b.svg&amp;starid=0&amp;aid=0&amp;style=11"
                                              target="_blank"></a><a class="social-share-icon icon-qzone"
                                                                     href="http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url=https%3A%2F%2F2heng.xin%2F2019%2F12%2F31%2Fgraphql-recursion%2F&amp;title=GraphQL%20%E5%AE%9E%E7%8E%B0%E9%80%92%E5%BD%92%E6%9F%A5%E8%AF%A2%20%7C%20%E6%A8%B1%E8%8A%B1%E5%BA%84%E7%9A%84%E7%99%BD%E7%8C%AB&amp;desc=%E5%BD%93%E6%88%91%E4%BB%AC%E9%9C%80%E8%A6%81%E7%94%A8%20GraphQL%20%E6%9F%A5%E8%AF%A2%E5%A4%9A%E5%B1%82%E5%A5%97%E5%B5%8C%E7%9A%84%E6%95%B0%E6%8D%AE%EF%BC%8C%E6%AF%94%E5%A6%82%E5%83%8F%20WordPress%20%E8%BF%99%E6%A0%B7%E5%A5%97%E5%B5%8C%E7%9A%84%E8%AF%84%E8%AE%BA%E4%BF%A1%E6%81%AF%E6%97%B6%EF%BC%8C%E9%80%9A%E5%B8%B8%E7%9A%84%E5%86%99%E6%B3%95%E6%98%AF%EF%BC%9A%20%20%7B%0A%20posts(first%3A%20100)%20%7B%0A%20nodes%20%7B%0A%20id%0A%20title%0A%20comments%20%7B%0A%20nodes%20%7B%0A%20...CommentFields%0A%20replies%3A%20childre%E2%80%A6&amp;summary=%E5%BD%93%E6%88%91%E4%BB%AC%E9%9C%80%E8%A6%81%E7%94%A8%20GraphQL%20%E6%9F%A5%E8%AF%A2%E5%A4%9A%E5%B1%82%E5%A5%97%E5%B5%8C%E7%9A%84%E6%95%B0%E6%8D%AE%EF%BC%8C%E6%AF%94%E5%A6%82%E5%83%8F%20WordPress%20%E8%BF%99%E6%A0%B7%E5%A5%97%E5%B5%8C%E7%9A%84%E8%AF%84%E8%AE%BA%E4%BF%A1%E6%81%AF%E6%97%B6%EF%BC%8C%E9%80%9A%E5%B8%B8%E7%9A%84%E5%86%99%E6%B3%95%E6%98%AF%EF%BC%9A%20%20%7B%0A%20posts(first%3A%20100)%20%7B%0A%20nodes%20%7B%0A%20id%0A%20title%0A%20comments%20%7B%0A%20nodes%20%7B%0A%20...CommentFields%0A%20replies%3A%20childre%E2%80%A6&amp;site=GraphQL%20%E5%AE%9E%E7%8E%B0%E9%80%92%E5%BD%92%E6%9F%A5%E8%AF%A2%20%7C%20%E6%A8%B1%E8%8A%B1%E5%BA%84%E7%9A%84%E7%99%BD%E7%8C%AB"
                                                                     target="_blank"></a><a
                    class="social-share-icon icon-linkedin"
                    href="http://www.linkedin.com/shareArticle?mini=true&amp;ro=true&amp;title=GraphQL%20%E5%AE%9E%E7%8E%B0%E9%80%92%E5%BD%92%E6%9F%A5%E8%AF%A2%20%7C%20%E6%A8%B1%E8%8A%B1%E5%BA%84%E7%9A%84%E7%99%BD%E7%8C%AB&amp;url=https%3A%2F%2F2heng.xin%2F2019%2F12%2F31%2Fgraphql-recursion%2F&amp;summary=%E5%BD%93%E6%88%91%E4%BB%AC%E9%9C%80%E8%A6%81%E7%94%A8%20GraphQL%20%E6%9F%A5%E8%AF%A2%E5%A4%9A%E5%B1%82%E5%A5%97%E5%B5%8C%E7%9A%84%E6%95%B0%E6%8D%AE%EF%BC%8C%E6%AF%94%E5%A6%82%E5%83%8F%20WordPress%20%E8%BF%99%E6%A0%B7%E5%A5%97%E5%B5%8C%E7%9A%84%E8%AF%84%E8%AE%BA%E4%BF%A1%E6%81%AF%E6%97%B6%EF%BC%8C%E9%80%9A%E5%B8%B8%E7%9A%84%E5%86%99%E6%B3%95%E6%98%AF%EF%BC%9A%20%20%7B%0A%20posts(first%3A%20100)%20%7B%0A%20nodes%20%7B%0A%20id%0A%20title%0A%20comments%20%7B%0A%20nodes%20%7B%0A%20...CommentFields%0A%20replies%3A%20childre%E2%80%A6&amp;source=GraphQL%20%E5%AE%9E%E7%8E%B0%E9%80%92%E5%BD%92%E6%9F%A5%E8%AF%A2%20%7C%20%E6%A8%B1%E8%8A%B1%E5%BA%84%E7%9A%84%E7%99%BD%E7%8C%AB&amp;armin=armin"
                    target="_blank"></a><a class="social-share-icon icon-diandian"
                                           href="http://www.diandian.com/share?lo=https%3A%2F%2F2heng.xin%2F2019%2F12%2F31%2Fgraphql-recursion%2F&amp;ti=GraphQL%20%E5%AE%9E%E7%8E%B0%E9%80%92%E5%BD%92%E6%9F%A5%E8%AF%A2%20%7C%20%E6%A8%B1%E8%8A%B1%E5%BA%84%E7%9A%84%E7%99%BD%E7%8C%AB&amp;type=link"
                                           target="_blank"></a><a class="social-share-icon icon-facebook"
                                                                  href="https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2F2heng.xin%2F2019%2F12%2F31%2Fgraphql-recursion%2F"
                                                                  target="_blank"></a><a
                    class="social-share-icon icon-twitter"
                    href="https://twitter.com/intent/tweet?text=GraphQL%20%E5%AE%9E%E7%8E%B0%E9%80%92%E5%BD%92%E6%9F%A5%E8%AF%A2%20%7C%20%E6%A8%B1%E8%8A%B1%E5%BA%84%E7%9A%84%E7%99%BD%E7%8C%AB&amp;url=https%3A%2F%2F2heng.xin%2F2019%2F12%2F31%2Fgraphql-recursion%2F&amp;via=https%3A%2F%2F2heng.xin"
                    target="_blank"></a><a class="social-share-icon icon-google"
                                           href="https://plus.google.com/share?url=https%3A%2F%2F2heng.xin%2F2019%2F12%2F31%2Fgraphql-recursion%2F"
                                           target="_blank"></a></div>
                <i class="iconfont show-share icon-forward"></i></div>
            </footer>
          </article>
          <section class="post-squares nextprev">
            <div class="post-nepre full previous"><a href="https://2heng.xin/2019/09/18/fix-100-vh-in-mobile-browser/"
                                                     rel="prev">
              <div class="background"><img class="lazyload"
                                           data-src="https://2heng.xin/wp-content/uploads//2018/06/child-1024x576.jpg"
                                           style="width: 100%; height: 100%; object-fit: cover; pointer-events: none;"
                                           onerror="imgError(this,3)"
                                           src="https://2heng.xin/wp-content/uploads//2018/06/child-1024x576.jpg"></div>
              <span class="label">Previous Post</span>
              <div class="info"><h3>解决移动端浏览器 vh 单位异常问题</h3>
                <hr>
              </div>
            </a></div>
            <div class="post-nepre half next"></div>
          </section>
        </main>
      </div>
    </div>
    <comment-block :blog-uid="blog.uid" :enable-comment="blog.enableComment" :comment-count="blog.commentCount" :comment-source="'BLOG'"></comment-block>
  </div>
</template>

<script>
import {getBlogByTitle} from "../api/article";
import {mapMutations} from "vuex";

export default {
  name: "page",
  components: {
    Cover: () => import('@/layouts/sora/widgets/Cover'),
    CommentBlock: () => import('@/components/CommentBlock/index'),
  },
  data: () => ({
    blog: [],
  }),
  methods: {
    ...mapMutations(['setBlog','removeToInfo']),
    getBlogByLink(){
      getBlogByTitle(this.$route.params.title).then(response => {
        console.log("page: "+response.state);
        if (response.state === this.$STATE.SUCCESS) {
          console.log("blog: "+response.data);
          this.blog = response.data;
          this.setBlog(response.data)
          console.log("page: "+this.blog);
        } else {
          alert(response.message);
          alert('获取文章失败');
          this.$router.push('/404');
        }
      });
    }

  },
  created() {
    this.removeToInfo();


  },
  mounted() {

    //this.blog = this.$store.getters.blog;
    //this.toId = this.$store.getters.toId;
    console.log('page create: ' + this.$store.getters.blog);
    if (this.$store.getters.blog!==undefined && this.$store.getters.blog!==null) {
      let t_blog = this.$store.getters.blog;
      if (t_blog.link[0]==='/'){
        t_blog.link = t_blog.link.substr(1);
      }
      console.log('t_blog.link: '+t_blog.link);
      console.log('this.$route.params.title: '+this.$route.params.title);
      if (t_blog.link === this.$route.params.title){
        this.blog = this.$store.getters.blog;
      }else{
        this.getBlogByLink();
      }
    } else {
      this.getBlogByLink();
    }
  }

}
</script>

<style scoped>

</style>