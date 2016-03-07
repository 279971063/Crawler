package csust.crawler.orm.dao;



import java.util.List;

import csust.crawler.orm.KeyWord;

public interface KeyWordDao {
	/**
	 * 用于获得所有keyword
	 * @return
	 */
	public List<KeyWord> getAllKeyWord();
	
	/**
	 * 用于添加word
	 * @param kw
	 * @return
	 */
	public int addKeyWord(KeyWord kw);
	
	/**
	 * 用于检验某个keyword是否存在
	 * @param kw
	 * @return
	 */
	public boolean exists(KeyWord kw);
	/**
	 * 用于通过某个value来获得整个key
	 * @param value
	 * @return
	 */
	public KeyWord getByValue(String value);
	/**
	 * 用于删除某个keyword
	 * @param keyId
	 * @return
	 */
	public boolean delete(int keyId);
	/**
	 * 用于获得所有未抓取的
	 * @return
	 */
	public List<KeyWord> getAllNotCraw();
	
	/**
	 * 通过某一个value来改，value是唯一的
	 * 用于将已经建立完索引的关键词标记
	 * @return
	 */
	public int updateIsAdd(String value);
}
