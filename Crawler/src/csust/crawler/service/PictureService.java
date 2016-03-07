package csust.crawler.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import csust.crawler.orm.KeyWord;
import csust.crawler.orm.Picture;
import csust.crawler.orm.dao.impl.PictureDaoImpl;

public class PictureService extends ActionSupport implements
		ServletRequestAware {

	private int index;
	private int thisindex;
	private String searchName;
	private HttpServletRequest request;
	private PictureDaoImpl pdi;

	
	
	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getThisindex() {
		return thisindex;
	}

	public void setThisindex(int thisindex) {
		this.thisindex = thisindex;
	}

	public PictureDaoImpl getPdi() {
		return pdi;
	}

	public void setPdi(PictureDaoImpl pdi) {
		this.pdi = pdi;
	}

	public String seeAll() throws Exception {
		List<Picture> list = new ArrayList<Picture>();
		for(int i = 0;i < pdi.getAllPicture().size();i++){
			if(i < 20){
				list.add(pdi.getAllPicture().get(i));
			}
			
		}
		request.setAttribute("pictures", list);
		request.setAttribute("thisindex", 1);
		request.setAttribute("count", pdi.getAllPicture().size());
		return "seeAllSuccess";
	}
	
	
	
	public String search() throws Exception {
		List<Picture> list = new ArrayList<Picture>();
		list = pdi.getPicturesByKey(searchName);
		request.setAttribute("pictures", list);
		request.setAttribute("thisindex", 1);
		request.setAttribute("count", pdi.getPicturesByKey(searchName).size());
		return "seeAllSuccess";
	}

	public String jump() throws Exception {
		List<Picture> list = new ArrayList<Picture>();
		if (index == 1) {
			for (int i = 0; i < pdi.getAllPicture().size(); i++) {
				if (i < 20) {
					list.add(pdi.getAllPicture().get(i));
				}
			}
			request.setAttribute("pictures", list);
			request.setAttribute("thisindex", 1);
			request.setAttribute("count", pdi.getAllPicture().size());
		} else {
			for (int i = 0; i < pdi.getAllPicture().size(); i++) {
				if (i > (index-1) * 20 - 1 && i < (index) * 20) {
					list.add(pdi.getAllPicture().get(i));
				}
			}
			request.setAttribute("pictures", list);
			request.setAttribute("thisindex", index == 1 ? 1 : index);
			request.setAttribute("count", pdi.getAllPicture().size());
		}

		return "seeAllSuccess";
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
}
