package csust.crawler.orm.dao.impl;

import java.util.List;

import csust.crawler.orm.KeyWord;
import csust.crawler.orm.Picture;
import csust.crawler.orm.dao.PictureDao;

public class PictureDaoImpl extends Base implements PictureDao{

	@Override
	public int addPicture(Picture pic) {
		this.getSession().save(pic);		
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Picture> getAllPicture() {
		String hql = "from Picture";
		return this.getSession().createQuery(hql).list();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Picture> getPicturesByKey(String key) {
		String hql = "from Picture picture where picture.keyWord.value = ?";
		return this.getSession().createQuery(hql).setString(0, key).list();
	}

}
