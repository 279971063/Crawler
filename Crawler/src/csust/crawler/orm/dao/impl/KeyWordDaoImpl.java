package csust.crawler.orm.dao.impl;



import java.util.List;

import csust.crawler.orm.KeyWord;
import csust.crawler.orm.dao.KeyWordDao;

public class KeyWordDaoImpl extends Base implements KeyWordDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<KeyWord> getAllKeyWord() {
		String hql = "from KeyWord";
		return (List<KeyWord>)this.getSession().createQuery(hql).list();
	}

	@Override
	public int addKeyWord(KeyWord kw) {
		try {
			this.getSession().save(kw);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	
	}

	@Override
	public boolean exists(KeyWord kw) {
		String hql = "from KeyWord keyword where keyword.value = ?";
		if(this.getSession().createQuery(hql).setString(0, kw.getValue()).uniqueResult() != null){
			return true;
		}
		return false;
	}

	@Override
	public KeyWord getByValue(String value) {
		String hql = "from KeyWord keyword where keyword.value = ?";
		return (KeyWord) this.getSession().createQuery(hql).setString(0, value).uniqueResult();
		
	}

	@Override
	public boolean delete(int keyId) {
		String hql = "delete from KeyWord keyword where keyword.id = ?";
		int result = this.getSession().createQuery(hql).setInteger(0, keyId).executeUpdate();
		return result == 1 ? true : false;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KeyWord> getAllNotCraw() {
		String hql = "from KeyWord keyword where keyword.isadd = 0";
		return (List<KeyWord>)this.getSession().createQuery(hql).list();
	}

	@Override
	public int updateIsAdd(String value) {
		String hql = "update KeyWord keyword set keyword.isadd = 1 where keyword.value = ?";
		return this.getSession().createQuery(hql).setString(0, value).executeUpdate();

	}

}
