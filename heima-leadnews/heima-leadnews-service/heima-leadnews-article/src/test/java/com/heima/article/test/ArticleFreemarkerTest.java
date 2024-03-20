package com.heima.article.test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.heima.article.ArticleApplication;
import com.heima.article.mapper.ApArticleContentMapper;
import com.heima.article.service.ApArticleService;
import com.heima.file.service.FileStorageService;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.article.pojos.ApArticleContent;

import freemarker.template.Configuration;
import freemarker.template.Template;

@SpringBootTest(classes = ArticleApplication.class)
@RunWith(SpringRunner.class)

public class ArticleFreemarkerTest {
      
	@Autowired
	private ApArticleContentMapper apArticleContentMapper;
	
	@Autowired
	private  Configuration configuration;
	
	@Autowired
	private  FileStorageService fileStorageService ;
	
	@Autowired
	private ApArticleService apArticleService;
	
	
	@Test
	public void createStaticUrlTest()  throws Exception{
		
		// 記事のコンテンツを取得
		ApArticleContent apArticleContent = apArticleContentMapper.selectOne(Wrappers.<ApArticleContent>lambdaQuery().eq(ApArticleContent::getArticleId , "1383828014629179393L"));
		if(apArticleContent != null  && StringUtils.isNotBlank(apArticleContent.getContent())){
			
			// freemarker によって　htmlファイルを作成する
			Template template = configuration.getTemplate("article.ftl");
			
			//　データモデル
			HashMap<String,Object> content = new HashMap<>();
			content.put("content", JSONArray.parseArray(apArticleContent.getContent()));
			StringWriter out = new StringWriter();
			
			//合成
			template.process(content, out);
			
			//minioにアップロードする
			
			InputStream in = new ByteArrayInputStream(out.toString().getBytes());
			String path = fileStorageService.uploadHtmlFile("", apArticleContent.getArticleId() + ".html", in);
			
			//ap_article テーブルをアップデートし、static_urlフィールドに保存する
			apArticleService.update(Wrappers.<ApArticle>lambdaUpdate().eq(ApArticle::getId,apArticleContent.getArticleId())
					.set(ApArticle::getStaticUrl,path));
			
		}
		
		
		
		
		
		
	}
	
}
