package com.vincent.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vincent.admin.entity.Article;
import com.vincent.admin.entity.Tag;
import com.vincent.admin.mapper.TagMapper;
import com.vincent.admin.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Vincent Tsai
 * @date 2021/3/19 18:49
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Autowired
    private TagService tagService;

    public void getTagListByArticle(Article article){
        if (article.getTagUid()!=null){
            String[] tagUidArray = article.getTagUid().split(";");
            Collection<Long> tagUidList = new ArrayList<>();
            for (String s : tagUidArray) {
                tagUidList.add(Long.parseLong(s));
            }
            List<Tag> tagList = tagService.listByIds(tagUidList);
            article.setTagList(tagList);
        }
    }
}
