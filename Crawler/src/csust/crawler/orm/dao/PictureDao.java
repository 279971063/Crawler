package csust.crawler.orm.dao;

import java.util.List;

import csust.crawler.orm.KeyWord;
import csust.crawler.orm.Picture;

public interface PictureDao {
	/**
	 * 用于获得所有图片
	 * @return
	 */
	public List<Picture> getAllPicture();
	/**
	 * 用于添加图片
	 * @param pic
	 * @return
	 */
	public int addPicture(Picture pic);
	
	/**
	 * 得到某一个关键词的所有图片信息
	 * @return
	 */
	public List<Picture> getPicturesByKey(String key);
	
}
