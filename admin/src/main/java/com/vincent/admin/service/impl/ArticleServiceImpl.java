package com.vincent.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vincent.admin.entity.*;
import com.vincent.admin.enums.Proxy;
import com.vincent.admin.holder.RequestHolder;
import com.vincent.admin.mapper.ArticleMapper;
import com.vincent.admin.service.*;
import com.vincent.admin.util.JsonUtil;
import com.vincent.admin.util.RedisUtil;
import com.vincent.admin.util.Result;
import com.vincent.admin.vo.ArticleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author Vincent Tsai
 * @date 2021/2/4 14:31
 */
@Service
@CacheConfig(cacheNames = "article")
@Slf4j
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;
    @Autowired
    private FileService fileService;
    @Autowired
    private SystemConfigService systemConfigService;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    @Cacheable(key = "#p0")
    public String getBlogByKeyword(String keyword, Long currentPage, Long pageSize) {
        Page<Article> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(currentPage);
        IPage<Article> pageList = baseMapper.searchArticle(page,keyword);
        List<Article> articleList = pageList.getRecords();
        if (articleList.size()==0){
            return Result.failure("什么也没有");
        }
        SystemConfig config = systemConfigService.getConfig((long) 1);
        setCover(config,articleList);
        return Result.success("查找文章成功",pageList);
    }

    @Override
    @Cacheable
    public String getRecommend() {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(Article.class,a-> a.getProperty().equals("link")||a.getProperty().equals("title")||a.getProperty().equals("uid"));
        queryWrapper.eq("is_draft",false);
        queryWrapper.orderByDesc("view_count");
        queryWrapper.last("LIMIT 10");
        List<Article> articleList = articleService.list(queryWrapper);
        return Result.success("获取推荐列表成功",articleList);
    }

    @Override
    @Cacheable(key = "#p0")
    public String getBlogByLink(String link) {
        Article article = baseMapper.getBlogByLink(link);
        if (article == null){
            return Result.failure("文章不存在");
        }
        SystemConfig config = systemConfigService.getConfig((long) 1);
        if (article.getCoverUrl()==null){
            article.setCoverUrl(config.getLocalImageBaseUrl()+config.getDefaultCoverUrl());
        }else {
            if (article.getCoverJsDelivrUrl() != null){
                article.setCoverUrl(article.getCoverJsDelivrUrl());
            }else {
                article.setCoverUrl(config.getLocalImageBaseUrl()+article.getCoverUrl());
            }
        }
        tagService.getTagListByArticle(article);
        article.setViewCount(article.getViewCount() + 1);
        article.updateById();
        article.setViewCount(null);
        return Result.success("获取文章成功，链接："+link,article);
    }

    @Override
    @Cacheable(key = "#p0.currentPage + #p0.pageSize + #p0.categoryUid")
    public String getArticleListByPage(ArticleVO articleVO) {
        log.info("articleVO.getCategoryUid(): "+articleVO.getCategoryUid());
        Page<Article> page = new Page<>();
        page.setSize(articleVO.getPageSize());
        page.setCurrent(articleVO.getCurrentPage());

        IPage<Article> pageList = baseMapper.getBlogByPageWithoutTags(page,
                articleVO.getCategoryUid(),
                false);
        List<Article> articleList = pageList.getRecords(); // 深拷贝，防止下面获取tag时改变？
        SystemConfig config = systemConfigService.getConfig((long) 1);
        setCover(config,articleList);
        /*
        IPage<Article> pageList = articleService.page(page, queryWrapper);
        List<Article> articleList = new ArrayList<>(pageList.getRecords());

        Collection<Long> coverImageUidList = new ArrayList<>();
        Collection<Long> categoryUidList = new ArrayList<>();
        articleList.forEach(article -> {
            if (article.getCoverUid()!=null){
                coverImageUidList.add(article.getCoverUid());
            }
            if (article.getCategoryUid()!=null){
                categoryUidList.add(article.getCategoryUid());
            }
        });

        if (coverImageUidList.size()>0){
            List<File> coverList = fileService.listByIds(coverImageUidList);
            HashMap<Long,String> fileMap = new HashMap<>();
            coverList.forEach(cover -> {
                fileMap.put(cover.getUid(),cover.getUrl());
            });
            SystemConfig systemConfig = systemConfigService.getById(1);
            articleList.forEach(article -> {
                if (article.getCoverUid()!=null){
                    article.setCoverUrl(systemConfig.getLocalImageBaseUrl()+fileMap.get(article.getCoverUid()));
                }else {
                    article.setCoverUrl("https://cdn.jsdelivr.net/gh/Nyanekoplus/js@master/data/cover.png");
                }
            });
        }else {
            articleList.forEach(article -> {
                article.setCoverUrl("https://cdn.jsdelivr.net/gh/Nyanekoplus/js@master/data/cover.png");
            });
        }

        if (categoryUidList.size()>0){
            List<Category> categoryList = categoryService.listByIds(categoryUidList);
            HashMap<Long,String> categoryMap = new HashMap<>();
            categoryList.forEach(category -> {
                categoryMap.put(category.getUid(),category.getName());
            });
            articleList.forEach(article -> {
                if (article.getCategoryUid()!=null){
                    article.setCategory(categoryMap.get(article.getCategoryUid()));
                }else {
                    article.setCategory("奇怪的分类");
                }
                if (article.getTagUid()!=null){
                    String[] tagUidArray = article.getTagUid().split(";");
                    Collection<Long> tagUidList = new ArrayList<>();
                    for (String s : tagUidArray) {
                        tagUidList.add(Long.parseLong(s));
                    }
                    log.info("Tag uid list: "+ tagUidList);
                    List<Tag> tagList = tagService.listByIds(tagUidList);
                    log.info("Tag List: "+ tagList);
                    article.setTagList(tagList);
                }
            });
        }
        */
        pageList.setRecords(articleList);
        String msg = "获取文章列表成功";
        return Result.success(msg, pageList);
    }

    @Override
    @Cacheable
    public String getTopBlog() {
        List<Article> topList = baseMapper.getTopBlogWithCover();//new ArrayList<>(3);
        SystemConfig config = systemConfigService.getConfig((long) 1);
        topList.forEach(top ->{
            if (top.getCoverJsDelivrUrl() != null){
                top.setCoverUrl(top.getCoverJsDelivrUrl());
            }else {
                top.setCoverUrl(config.getLocalImageBaseUrl()+top.getCoverUrl());
            }
        });
        return Result.success("查找置顶文章成功",topList);
    }

    @Override
    public String likeBlog(Long uid) {
        HttpServletRequest request = RequestHolder.getRequest();
        String token = request.getHeader("Authorization");
        log.info("token: "+token);
        if (token == null || token.equals("undefined")) {
            return Result.failure("登录后才可以点赞哦~");
        }
        String user = redisUtil.get("LOGIN_TOKEN"+':'+token);
        if (StringUtils.isEmpty(user)){
            return Result.failure("登录后才可以点赞哦~");
        }
        Map<String,Object> info = JsonUtil.jsonToMap(user);
        Long userUid = Integer.toUnsignedLong((Integer) info.get("uid"));
        log.info("用户信息： "+info);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_uid",userUid);
        queryWrapper.eq("blog_uid",uid);
        queryWrapper.eq("source","BLOG");
        queryWrapper.eq("type",1); // 1 for liked, 0 for comment
        queryWrapper.last("LIMIT 1");
        Comment isLiked = commentService.getOne(queryWrapper);
        if (isLiked != null){
            return Result.failure("感谢支持!你已经点过赞了~");
        }
        articleService.update(new UpdateWrapper<Article>().eq("uid",uid).setSql("liked_count = liked_count+1"));
        Comment likeInc = new Comment();
        likeInc.setSource("BLOG");
        likeInc.setType(1);
        likeInc.setBlogUid(uid);
        likeInc.setUserUid(userUid);
        likeInc.insert();
        return Result.success("感谢支持~");
    }

    private void setCover(SystemConfig config,List<Article> articleList){
        articleList.forEach(article -> {
            if (article.getCoverUrl()==null){
                article.setCoverUrl(config.getLocalImageBaseUrl()+config.getDefaultCoverUrl());
            }else {
                if (article.getCoverJsDelivrUrl() != null){
                    article.setCoverUrl(article.getCoverJsDelivrUrl());
                }else {
                    article.setCoverUrl(config.getLocalImageBaseUrl()+article.getCoverUrl());
                }
            }
            //tagService.getTagListByArticle(article);
        });
    }

}
