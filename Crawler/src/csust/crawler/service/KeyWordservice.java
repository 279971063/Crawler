package csust.crawler.service;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

import csust.crawler.orm.KeyWord;
import csust.crawler.orm.dao.impl.KeyWordDaoImpl;

public class KeyWordservice extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	// 用于得到此关键字是否可以用
	private String keyword;

	// allkeyword.jsp中
	private int index = -1;
	// allkeyword.jsp中
	private String searchName;
	//����ɾ��ı��id
	private int id;

	private HttpServletRequest request;

	private HttpServletResponse response;

	private KeyWordDaoImpl kwdi;

	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public KeyWordDaoImpl getKwdi() {
		return kwdi;
	}

	public void setKwdi(KeyWordDaoImpl kwdi) {
		this.kwdi = kwdi;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;

	}

	public String add() throws Exception {
		List<Integer> list = new ArrayList<Integer>();
		KeyWord kw = new KeyWord();
		kw.setValue(request.getParameter("keyword").toString());
		int j = 0;
		for (int i = 0; i < kwdi.getAllKeyWord().size(); i++) {
			list.add(kwdi.getAllKeyWord().get(i).getId());
		}
		for (int i = 0; i < 1000000; i++) {
			if (!list.contains(i)) {
				j = i;
				break;
			}
		}
		// 找到里面没有的

		kw.setId(j);
		kw.setIsadd(0);
		HttpServletResponse response = ServletActionContext.getResponse();
		// 设置字符集
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if (kwdi.addKeyWord(kw) == 1) {
			System.out.println("�ɹ�");
			out.print("1");
		} else {
			System.out.println("ʧ��");
			out.print("0");
		}
		out.flush();
		out.close();
		return null;
	}

	public String exists() throws Exception {
		System.out.println("1122");
		KeyWord kw = new KeyWord();
		
		kw.setValue(new String(request.getParameter("keyword").getBytes("ISO-8859-1"),"UTF-8"));
		boolean boo = kwdi.exists(kw);

		HttpServletResponse response = ServletActionContext.getResponse();
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if (boo) {
			out.print("*关键字已存在*");
		

		} else {
			out.print("*关键字可用*");
		}

		out.flush();
		out.close();
		return null;
	}

	public String seeAll() throws Exception {
		List<KeyWord> list = new ArrayList<KeyWord>();
		for (int i = 0; i < kwdi.getAllKeyWord().size(); i++) {
			if (i < 10) {
				list.add(kwdi.getAllKeyWord().get(i));
			}
		}
		request.setAttribute("keywords", list);
		request.setAttribute("thisindex", 1);
		request.setAttribute("count", kwdi.getAllKeyWord().size());
		return "seeAllSuccess";
	}

	public String search() throws Exception {
		List<KeyWord> list = new ArrayList<KeyWord>();
		list.add(kwdi.getByValue(searchName.trim()));
		request.setAttribute("keywords", list);
		return "seeAllSuccess";
	}

	public String jump() throws Exception {
		List<KeyWord> list = new ArrayList<KeyWord>();
		if (index == 1) {
			for (int i = 0; i < kwdi.getAllKeyWord().size(); i++) {
				if (i < 10) {
					list.add(kwdi.getAllKeyWord().get(i));
				}
			}
			request.setAttribute("keywords", list);
			request.setAttribute("thisindex", 1);
			request.setAttribute("count", kwdi.getAllKeyWord().size());
		} else {
			for (int i = 0; i < kwdi.getAllKeyWord().size(); i++) {
				if (i > (index-1) * 10 - 1 && i < (index) * 10) {
					list.add(kwdi.getAllKeyWord().get(i));
				}
			}
			request.setAttribute("keywords", list);
			request.setAttribute("thisindex", index == 1 ? 1 : index);
			request.setAttribute("count", kwdi.getAllKeyWord().size());
		}

		return "seeAllSuccess";
	}
	
	public String delete() throws Exception{

		if(kwdi.delete(id)){
			List<KeyWord> list = new ArrayList<KeyWord>();
			for (int i = 0; i < kwdi.getAllKeyWord().size(); i++) {
				if (i < 10) {
					list.add(kwdi.getAllKeyWord().get(i));
				}
			}
			request.setAttribute("keywords", list);
			request.setAttribute("thisindex", 1);
			return "seeAllSuccess";
		}
		return null;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}

}
