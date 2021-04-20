package com.vincent.admin.controller.api.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vincent.admin.entity.*;
import com.vincent.admin.service.*;
import com.vincent.admin.util.Result;
import com.vincent.admin.vo.ArticleVO;
import com.vincent.admin.vo.TagVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author Vincent Tsai
 * @date 2021/2/7 13:52
 */

@RestController
@RequestMapping("/admin/article")
@Slf4j
public class ArticleApi {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;
    @Autowired
    private FileService fileService;
    @Autowired
    private SystemConfigService systemConfigService;

    @PostMapping("/list")
    public String list(@RequestBody ArticleVO articleVO){
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_draft", articleVO.getIsDraft());
        queryWrapper.orderByDesc("create_time");
        //queryWrapper.isNull("updateTime");

        Page<Article> page = new Page<>();
        page.setCurrent(articleVO.getCurrentPage());
        page.setSize(articleVO.getPageSize());

        IPage<Article> pageList = articleService.page(page,queryWrapper); // 需要加
        // SELECT summary,content,contentMd FROM article ORDER BY releaseTime DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
        //SQLSERVER2012 用 “FETCH NEXT ” 关键字分页时候 ORDER BY 是必须写的，
        // 于是在 “OFFSET” 前加上 "ORDER BY" 排序条件，不报错了。
        //OFFSET 需要加 ORDER BY 否则会报错
        //System.out.println(pageList);
        return Result.success("查询文章列表成功",pageList);
    }

    @PostMapping("/getArticleListByPage")
    public String getArticleListByPage(@RequestBody ArticleVO articleVO){
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_draft", articleVO.getIsDraft());
        queryWrapper.orderByDesc("create_time");
        log.info("articleVO:"+articleVO);
        log.info("sort map："+articleVO.getSortMap().toString());

        queryWrapper.orderByDesc("create_time");
        Page<Article> page = new Page<>();
        page.setSize(articleVO.getPageSize());
        page.setCurrent(articleVO.getCurrentPage());

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
            SystemConfig systemConfig = systemConfigService.getById(1);
            List<File> coverList = fileService.listByIds(coverImageUidList);
            HashMap<Long,String> fileMap = new HashMap<>();
            coverList.forEach(cover -> {
                if (cover.getJsDelivrUrl() == null){ // 如果jsdevUrl为空，则使用本地图片
                    fileMap.put(cover.getUid(),systemConfig.getLocalImageBaseUrl()+cover.getUrl());
                }else {
                    fileMap.put(cover.getUid(),cover.getJsDelivrUrl());
                }
            });
            articleList.forEach(article -> {
                if (article.getCoverUid()!=null){
                    article.setCoverUrl(fileMap.get(article.getCoverUid()));
                }else {
                    article.setCoverUrl(systemConfig.getLocalImageBaseUrl()+systemConfig.getDefaultCoverUrl());
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

        pageList.setRecords(articleList);
        String msg = "获取文章列表成功";
        return Result.success(msg, pageList);

        // 需要加
        // SELECT summary,content,contentMd FROM article ORDER BY releaseTime DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
        //SQLSERVER2012 用 “FETCH NEXT ” 关键字分页时候 ORDER BY 是必须写的，
        // 于是在 “OFFSET” 前加上 "ORDER BY" 排序条件，不报错了。
        //OFFSET 需要加 ORDER BY 否则会报错
        //System.out.println(pageList);
    }

    @PostMapping("/add")
    String addArticle(@RequestBody ArticleVO articleVO){
        Article article = new Article();
        List<TagVO> tagList = articleVO.getTagList();
        StringBuilder tagUid = new StringBuilder("");
        Collection<Long> tagUidList = new ArrayList<>();
        for (TagVO tagVO : tagList){
            if (tagVO.getUid() != null) {
                tagUid.append(tagVO.getUid().toString()).append(';');
                tagUidList.add(tagVO.getUid());
            }else {
                Tag tempTag = new Tag(tagVO.getName());
                tagService.save(tempTag);
                if (tempTag.getUid()!=null){
                    log.info("save后返回的id："+tempTag.getUid());
                    tagUid.append(tempTag.getUid().toString()+';');
                }
                log.info(tagUid.toString());
            }
        }
        if (tagUidList.size()>0){ // 使已存在得标签权重+1
            List<Tag> tagExistedList = tagService.listByIds(tagUidList);
            tagExistedList.forEach(tag -> {
                tag.setWeight(tag.getWeight()+1);
            });
            tagService.updateBatchById(tagExistedList);
        }

        article.setCategoryUid(articleVO.getCategoryUid());
        article.setTagUid(tagUid.toString());
        log.info("所有的tag uid："+article.getTagUid());
        article.setTitle(articleVO.getTitle());
        article.setLink(articleVO.getLink());
        article.setLang(articleVO.getLang());

        if (articleVO.getContent().length()>35){
            article.setSummary(articleVO.getContent().substring(0,35)); //
        }else {
            article.setSummary(articleVO.getContent());
        }

        article.setContent(articleVO.getContent());
        article.setContentMd(articleVO.getContentMd());
        article.setEnableComment(articleVO.getEnableComment());
        article.setIsDraft(articleVO.getIsDraft());
        article.setLevel(articleVO.getLevel());
        article.setCoverUid(articleVO.getCoverUid());
        /*
        if (articleVO.getCoverUid()==null){
            SystemConfig config = systemConfigService.getById(1);
            article.setCoverUid(config.getDefaultCoverUid());
        }else {
            article.setCoverUid(articleVO.getCoverUid());
        }*/
        if (articleVO.getCreateTime() != null) {
            log.info("Add article: create time is "+articleVO.getCreateTime());
            article.setCreateTime(articleVO.getCreateTime());
        }
        //article.setViewCount();  // 0 by default

        boolean isSaved = articleService.save(article);
        if (isSaved){
            return Result.success("发布文章成功");
        }else{
            return Result.failure("发布文章失败");
        }
    }

    @PostMapping("/update")
    String updateArticleState(@RequestBody ArticleVO articleVO){

        UpdateWrapper<Article> wrapper = new UpdateWrapper<>();
        wrapper.set("enable_comment",articleVO.getEnableComment());
        wrapper.set("is_draft",articleVO.getIsDraft());
        wrapper.set("content",articleVO.getContent());
        wrapper.set("link",articleVO.getLink());
        wrapper.set("title",articleVO.getTitle());
        wrapper.set("level",articleVO.getLevel());

        if (articleVO.getUpdateTime() == null){
            wrapper.set("update_time", new Date());
        }else {
            wrapper.set("update_time", articleVO.getUpdateTime());
        }

        wrapper.eq("uid", articleVO.getUid());
        //wrapper.set("level",articleVO.getLevel());
        boolean result = articleService.update(wrapper);
        return result?Result.success("Back-end：更新文章状态成功"):Result.failure("Back-end：更新文章状态失败");
    }

    @DeleteMapping("/delete/{uid}")
    public String deleteArticle(@PathVariable(value = "uid") Long uid){
        boolean result = articleService.removeById(uid);;
        return result?Result.success("Back-end：删除文章成功"):Result.failure("Back-end：删除文章失败");
    }

}
