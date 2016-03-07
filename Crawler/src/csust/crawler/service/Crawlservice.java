package csust.crawler.service;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.semanticmetadata.lire.DocumentBuilder;
import net.semanticmetadata.lire.DocumentBuilderFactory;

import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.struts2.ServletActionContext;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.opensymphony.xwork2.ActionSupport;

import csust.crawler.orm.Picture;
import csust.crawler.orm.dao.impl.IndexLocationDaoImpl;
import csust.crawler.orm.dao.impl.KeyWordDaoImpl;
import csust.crawler.orm.dao.impl.PictureDaoImpl;
import csust.crawler.util.CrawUrl;

public class Crawlservice extends ActionSupport {

	// 用于得到要搜索图片关键字
	private String keyvalue;
	// 做好百度图片的头搜索
	private IndexLocationDaoImpl ildi;
	private KeyWordDaoImpl kwdi;
	private PictureDaoImpl pdi;
	private CrawUrl crawUrl;

	
	
	
	
	public CrawUrl getCrawUrl() {
		return crawUrl;
	}

	public void setCrawUrl(CrawUrl crawUrl) {
		this.crawUrl = crawUrl;
	}

	public IndexLocationDaoImpl getIldi() {
		return ildi;
	}

	public void setIldi(IndexLocationDaoImpl ildi) {
		this.ildi = ildi;
	}

	public KeyWordDaoImpl getKwdi() {
		return kwdi;
	}

	public void setKwdi(KeyWordDaoImpl kwdi) {
		this.kwdi = kwdi;
	}

	public PictureDaoImpl getPdi() {
		return pdi;
	}

	public void setPdi(PictureDaoImpl pdi) {
		this.pdi = pdi;
	}

	public String getKeyvalue() {
		return keyvalue;
	}

	public void setKeyvalue(String keyvalue) {
		this.keyvalue = keyvalue;
	}



	public String craw() throws Exception {
		keyvalue = URLDecoder.decode(keyvalue, "utf-8");
		String url = new String();
		url = crawUrl.getNextUrl(keyvalue);
//		try {
//			url = crawUrl.getNextUrl(keyvalue);
//		} catch (Exception e) {
//			if(e instanceof InvocationTargetException){
//				try {
//					throw ((InvocationTargetException) e).getTargetException();
//				} catch (Throwable e1) {
//					System.out.println();
//				}  
//			}
//			System.out.println("1111");
//		}
		System.out.println("2222");
		List<String> urls = new ArrayList<String>();
		List<String> infos = new ArrayList<String>();
		List<String> homeUrls = new ArrayList<String>();
//		String value = URLEncoder.encode(keyvalue, "UTF-8");
//		url.append(value);
		Connection connJsoup = Jsoup.connect(url.toString());
		Document dochtml = connJsoup.timeout(20000).get();
		String xml = dochtml.toString();

		// 把xml拆开，然后提取目标url
//		String reg1 = "\"objURL\":\"";
//		String reg2 = "\",";
//
//		String[] strs = xml.split(reg1);
//
//		for (int i = 1; i < strs.length; i++) {
//			String[] strs2 = strs[i].split(reg2);
//			for (int j = 0; j < strs2.length; j++) {
//				urls.add(strs2[0]);
//				break;
//			}
//		}
		
		urls = crawUrl.getObjInfo("\"pic_url\":\"", "\",", xml);
		infos = crawUrl.getObjInfo("\"title\":\"", "\",", xml);
		homeUrls = crawUrl.getObjInfo("\"page_url\":\"", "\",", xml);
		
		
		// 得到索引存放地址
		String location = ildi.getRootLocation().getValue();

		// 创建一个合适的文件生成器，Lire针对图像的多种属性有不同的生成器
		DocumentBuilder db = DocumentBuilderFactory.getJCDDocumentBuilder();
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_33,
				new SimpleAnalyzer(Version.LUCENE_33));
		IndexWriter iw = new IndexWriter(FSDirectory.open(new File(location)),
				iwc);

		int num = 0;
		// 建立索引，只建立前二十个
		for (int i = 1; i < urls.size(); i++, num++) {

			// 以下为建立索引的过程
			java.net.URL myurl = new java.net.URL(urls.get(i));
			java.net.HttpURLConnection conn = (java.net.HttpURLConnection) myurl
					.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);

			InputStream inStream;

			try {
				inStream = conn.getInputStream();// 通过输入流获取图片数据
			} catch (Exception e) {
				continue;
			}

			// 以下为插入图片过程
			Picture picture = new Picture();
			List<Integer> listKey = new ArrayList<Integer>();

			int j = 0;
			for (int k = 0; k < pdi.getAllPicture().size(); k++) {
				listKey.add(pdi.getAllPicture().get(k).getId());
			}
			for (int k = 0; k < 1000000; k++) {
				if (!listKey.contains(k)) {
					j = k;
					break;
				}
			}

			picture.setId(j);
			picture.setKeyWord(kwdi.getByValue(keyvalue));
			picture.setName(urls.get(i));
			picture.setDescription(infos.get(i));
			picture.setObjurl(homeUrls.get(i));
			if (num > 20) {
				break;
			}
			pdi.addPicture(picture);

			// 将图片存为doc
			org.apache.lucene.document.Document doc = db.createDocument(
					inStream, urls.get(i));
			iw.addDocument(doc);
			inStream.close();
		}

		// 将标记设为已建立索引
		kwdi.updateIsAdd(keyvalue);

		iw.optimize();
		iw.close();

		HttpServletResponse response = ServletActionContext.getResponse();
		// 设置字符集
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print("1");

		return null;
	}
	
	
	

}
