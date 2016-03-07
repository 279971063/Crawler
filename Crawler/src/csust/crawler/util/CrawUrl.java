package csust.crawler.util;

import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import csust.crawler.service.Crawlservice;

public class CrawUrl {
	
	public String cutString(String url){
		String[] strs1 = url.split("\\(");
		String[] strs2 = strs1[1].split("\\)");
		return strs2[0];
	} 
	
	/**
	 * 通过htmlunit来获得一些搜狗的网址。
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String getNextUrl(String key){
		String page = new String();
		try {
			WebClient webClient = new WebClient();
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setJavaScriptEnabled(false);
			
			//去拿网页
			HtmlPage htmlPage = webClient.getPage("http://pic.sogou.com/");
			//得到表单
			HtmlForm form = htmlPage.getFormByName("searchForm");
			//得到提交按钮
			HtmlSubmitInput button = form.getInputByValue("搜狗搜索");
			//得到输入框

			HtmlTextInput textField = form.getInputByName("query");
			//输入内容
			textField.setValueAttribute(key);
			//点一下按钮
			HtmlPage nextPage = button.click();

			String str = nextPage.toString();
			page = cutString(str);
			webClient.close();
		} catch (Exception e) {
			// TODO: handle exception
		} finally{
			
		}
		System.out.println();
		return page;
		
	}
	
	public List<String> getObjInfo(String regHead,String regEnd,String xml){
		List<String> infos = new ArrayList<String>();
		String[] strs = xml.split(regHead);
		for(int i = 0;i < strs.length;i++){
			String[] strs2 = strs[i].split(regEnd);
			for(int j = 0;j < strs2.length;j++){
				infos.add(strs2[0]);
				break;
			}
		}
		return infos;
	}
}
