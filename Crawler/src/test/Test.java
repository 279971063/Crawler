package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Test {

	public static void main(String[] args) throws Exception {
//		String reg1 = ".*性别：([^\\|]*)\\|?.*";
//		String reg2 = ".*民族：([^\\|]*)\\|?.*";
//		String reg3 = ".*生日：([^\\|]*)\\|?.*";
//		 
//		String str = "性别：女士|民族：汉族|生日：1990-9-22";
//		 
//		String sex = str.replaceAll(reg1, "$1");
//		String mz = str.replaceAll(reg2, "$1");
//		String brith = str.replaceAll(reg3, "$1");
//		System.out.println (sex);
//		System.out.println (mz);
//		System.out.println (brith);
//		System.out.println(str);

		String keyvalue = "猫";
		StringBuilder url = new StringBuilder(
				"http://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=result&fr=ala&sf=1&fmq=1451902980456_R&pv=&ic=0&nc=1&z=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&ie=utf-8&word=");

		String value = URLEncoder.encode(keyvalue, "UTF-8");
		
		url.append(value);
		System.out.println(url);
		Connection conn = Jsoup.connect(url.toString());
		Document doc = conn.timeout(20000).get();		
		String xml = doc.toString();
		
		String reg1 = "\"objURL\":\"";
		String reg2 = "\",";
		
		String[] strs = xml.split(reg1);
		File file = new File("d://12.txt");
		FileOutputStream fos = new FileOutputStream(file);
		
		for(int i = 1;i < strs.length;i++){
			String[] strs2 = strs[i].split(reg2);
			for(int j = 0;j < strs2.length;j++){
				System.out.println(strs2[0]);
				fos.write(strs2[0].getBytes());
				fos.write("\r\n".getBytes());
				System.out.println(strs2.length);
				break;
			}
		}
		
		
	}

}
