package csust.crawler.orm.dao;

import csust.crawler.orm.IndexLocation;

public interface IndexLocationDao {
	/**
	 * 用于跟新地址
	 * @return
	 */
	public int updateLocation(String newurl);
	/**
	 * 只得到id为1的地址
	 * @return
	 */
	public IndexLocation getRootLocation();
}
