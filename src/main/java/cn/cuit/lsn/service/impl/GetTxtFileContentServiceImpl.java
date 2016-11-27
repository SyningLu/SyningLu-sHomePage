package cn.cuit.lsn.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.cuit.lsn.dao.FileDao;
import cn.cuit.lsn.json.entity.Articles;
import cn.cuit.lsn.json.entity.Content;
import cn.cuit.lsn.pojo.MyFile;
import cn.cuit.lsn.service.GetTxtFileContentService;


/**
 * 读取数据库中txt文件存放的路径，通过IO操作获得txt文件内容并用json按行封装
 * @author 路双宁
 * @version 0.4.0
 */

@Service
public class GetTxtFileContentServiceImpl implements GetTxtFileContentService{

	@Autowired
	private FileDao fileDao;
	
	/**
	 * 通过数据库查询该文件的信息
	 * @param String fileName 文件名
	 * @return MyFile 该文件对应的entity
	 */
	@Override
	public MyFile readFile(String fileName) {
		long start = System.nanoTime();
		MyFile myFile = fileDao.querryFileByName(fileName);
		System.out.println("查询" + fileName + "耗时：" 
				+ (double)((System.nanoTime() - start)/1000000) + "毫秒");
		return myFile;
	}
	
	/**
	 * IO操作获取txt文件内容
	 * @param String fileLoc 文件路径
	 * @return StringBuffer txt文件的内容
	 */
	
	@Override
	public StringBuffer getContent(String fileLoc){
		BufferedReader br = null;
		StringBuffer content = new StringBuffer();
		String stringLine = null;
		try {
			
			File file = new File(fileLoc);
			br = new BufferedReader(new FileReader(file));
			while ((stringLine = br.readLine()) != null) {
				content.append(stringLine + " ");
			}
						
		} catch (FileNotFoundException e) {
			System.out.println("未找到文件：" + e.getMessage());
			e.printStackTrace();
		}catch (IOException e) {
			System.out.println("文件IO操作异常：" + e.getMessage());
		}finally{
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return content;
	}
	
	/**
	 * @param String fileName 文件名
	 * @return 用阿里的fastjson 封装好的json对象
	 *
	 */

	@Override
	public String contentPackaged(String fileName) {
		long start = System.nanoTime();
		StringTokenizer fx = null;
		StringBuffer contentAll = new StringBuffer();
		MyFile myFile = null;
		Articles articles = new Articles();
		Content content = null;
		
		//读取文件
		myFile = readFile(fileName);
		//IO操作读取文件内容
		contentAll = getContent(myFile.getFileLoc());
		//默认使用空格来进行分割
		fx = new StringTokenizer(contentAll.toString());
		
		while(fx.hasMoreTokens()){
			content = new Content();
			content.setContent(fx.nextToken());
			articles.getArticles().add(content);
			content = null;
		}
		String json = JSON.toJSONString(articles);
		System.out.println("封装json耗时：" 
				+ (double)((System.nanoTime() - start)/1000000) + "毫秒");

		return json;
	}
	
}