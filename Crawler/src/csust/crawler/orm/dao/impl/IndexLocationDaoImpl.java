package csust.crawler.orm.dao.impl;

import csust.crawler.orm.IndexLocation;
import csust.crawler.orm.dao.IndexLocationDao;

public class IndexLocationDaoImpl extends Base implements IndexLocationDao{

	@Override
	public int updateLocation(String newurl) {
		String hql = "update IndexLocation indexLocation set indexLocation.value = ? where indexLocation.id = 2";

		return this.getSession().createQuery(hql).setString(0, newurl).executeUpdate();
	}

	@Override
	public IndexLocation getRootLocation() {
		String hql = "from IndexLocation indexLocation where indexLocation.id = 1";
		return (IndexLocation)this.getSession().createQuery(hql).uniqueResult();
	}

}
