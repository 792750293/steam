package com.jt.test.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;










public class TestLuncene {
	
	@Test
	public void createIndex() throws Exception{
		
		/*Directory d=FSDirectory.open(new File("./index"));
		Analyzer  analyzer=new IKAnalyzer();
		IndexWriterConfig conf=new IndexWriterConfig(Version.LUCENE_4_10_2,analyzer);
		IndexWriter indexWriter =new IndexWriter(d,conf);
		
		Document document=new Document();
		document.add(new LongField("id",7299782,Store.YES));
		document.add(new StringField("title","诺基亚限量秒杀",Store.YES));
		document.add(new TextField("sellpoint","耐摔，抗击打",Store.YES));
		document.add(new LongField("price",123456,Store.YES));
		
		indexWriter.addDocument(document);
		indexWriter.close();*/
		
		
		
		
	}
}
