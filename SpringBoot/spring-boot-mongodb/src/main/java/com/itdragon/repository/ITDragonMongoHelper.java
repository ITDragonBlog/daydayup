package com.itdragon.repository;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class ITDragonMongoHelper {
	
	@Autowired(required = false)
	private MongoTemplate mongoTemplate; // Spring Data提供支持MongoDB的方法
	private Class entityClass;		// 实体类
	private String collectionName;	// 数据库表名
	private String orderAscField;	// 升序字段
	private String orderDescField;	// 降序字段
	
	private static final String ID = "id";
	private static final String MONGODB_ID = "_id";
	
	public ITDragonMongoHelper() {
	}

	public ITDragonMongoHelper(Class entityClass) {
		this.entityClass = entityClass;
		this.collectionName = _getCollectionName();
	}

	public ITDragonMongoHelper(Class entityClass, String collectionName) {
		this.entityClass = entityClass;
		this.collectionName = collectionName;
	}
	
	/**
	 * @Title save
	 * @Description 通过Map创建实体类
	 * @param object Map，无需自带ID
	 * @return
	 */
	public Boolean save(Map<String, Object> requestArgs) {
		try {
			Object object = getEntityClass().newInstance();
			if (null == requestArgs.get(ID)) {
				requestArgs.put(ID, getNextId());
			}
			BeanUtils.populate(object, requestArgs);
			saveOrUpdate(object);
		} catch (Exception e) {
			e.printStackTrace();
			return Boolean.valueOf(false);
		}
		return Boolean.valueOf(true);
	}
	
	/**
	 * @Title save
	 * @Description 通过对象创建实体类
	 * @param object 实体类，需自带ID
	 * @return
	 */
	public Boolean saveOrUpdate(Object object) {
		try {
			this.mongoTemplate.save(object, this.collectionName);
		} catch (Exception e) {
			e.printStackTrace();
			return Boolean.valueOf(false);
		}
		return Boolean.valueOf(true);
	}
	
	/**
	 * @Title update
	 * @Description 通过Map更新实体类具体字段，可以减少更新出错字段，执行的销率更高，需严格要求数据结构的正确性
	 * @param requestArgs Map，需自带ID， 形如：{id: idValue, name: nameValue, ....}
	 * @return
	 */
	public Boolean update(Map<String, Object> requestArgs) {
		Object id = requestArgs.get(ID);
		if (null == id) {
			return Boolean.valueOf(false);
		}
		try {
			Update updateObj = new Update();
			requestArgs.remove(ID);
			for (String key : requestArgs.keySet()) {
				updateObj.set(key, requestArgs.get(key));
			}
			findAndModify(Criteria.where(ID).is(id), updateObj);
		} catch (Exception e) {
			e.printStackTrace();
			return Boolean.valueOf(false);
		}
		return Boolean.valueOf(true);
	}
	
	/**
	 * @Title find
	 * @Description 根据查询条件返回所有数据，不推荐
	 * @param criteria 查询条件
	 * @return
	 */
	public List find(Criteria criteria) {
		Query query = new Query(criteria);
		_sort(query);
		return this.mongoTemplate.find(query, this.entityClass, this.collectionName);
	}
	
	/**
	 * @Title find
	 * @Description 根据查询条件返回指定数量数据
	 * @param criteria 查询条件
	 * @param pageSize 查询数量
	 * @return
	 */
	public List find(Criteria criteria, Integer pageSize) {
		Query query = new Query(criteria).limit(pageSize.intValue());
		_sort(query);
		return this.mongoTemplate.find(query, this.entityClass, this.collectionName);
	}

	/**
	 * @Title find
	 * @Description 根据查询条件分页返回指定数量数据
	 * @param criteria 查询条件
	 * @param pageSize 查询数量
	 * @param pageNumber 当前页数
	 * @return
	 */
	public List find(Criteria criteria, Integer pageSize, Integer pageNumber) {
		Query query = new Query(criteria).skip((pageNumber.intValue() - 1) * pageSize.intValue()).limit(pageSize.intValue());
		_sort(query);
		return this.mongoTemplate.find(query, this.entityClass, this.collectionName);
	}
	
	public Object findAndModify(Criteria criteria, Update update) {
		// 第一个参数是查询条件，第二个参数是需要更新的字段，第三个参数是需要更新的对象，第四个参数是MongoDB数据库中的表名
		return this.mongoTemplate.findAndModify(new Query(criteria), update, this.entityClass, this.collectionName);
	}
	
	/**
	 * @Title findById
	 * @Description 通过ID查询数据
	 * @param id 实体类ID
	 * @return
	 */
	public Object findById(Object id) {
		return this.mongoTemplate.findById(id, this.entityClass, this.collectionName);
	}
	
	/**
	 * @Title findOne
	 * @Description 通过查询条件返回一条数据
	 * @param id 实体类ID
	 * @return
	 */
	public Object findOne(Criteria criteria) {
		Query query = new Query(criteria).limit(1);
		_sort(query);
		return this.mongoTemplate.findOne(query, this.entityClass, this.collectionName);
	}
	
	// id自增长
	public String getNextId() {
		return getNextId(getCollectionName());
	}

	public String getNextId(String seq_name) {
		String sequence_collection = "seq";
		String sequence_field = "seq";
		DBCollection seq = this.mongoTemplate.getCollection(sequence_collection);
		DBObject query = new BasicDBObject();
		query.put(MONGODB_ID, seq_name);
		DBObject change = new BasicDBObject(sequence_field, Integer.valueOf(1));
		DBObject update = new BasicDBObject("$inc", change);
		DBObject res = seq.findAndModify(query, new BasicDBObject(), new BasicDBObject(), false, update, true, true);
		return res.get(sequence_field).toString();
	}
	
	private void _sort(Query query) {
		if (null != this.orderAscField) {
			String[] fields = this.orderAscField.split(",");
			for (String field : fields) {
				if (ID.equals(field)) {
					field = MONGODB_ID;
				}
				query.with(new Sort(Sort.Direction.ASC, new String[] { field }));
			}
		} else {
			if (null == this.orderDescField) {
				return;
			}
			String[] fields = this.orderDescField.split(",");
			for (String field : fields) {
				if (ID.equals(field)) {
					field = MONGODB_ID;
				}
				query.with(new Sort(Sort.Direction.DESC, new String[] { field }));
			}
		}
	}
	
	// 获取Mongodb数据库中的表名，若表名不是实体类首字母小写，则会影响后续操作
	private String _getCollectionName() {
		String className = this.entityClass.getName();
		Integer lastIndex = Integer.valueOf(className.lastIndexOf("."));
		className = className.substring(lastIndex.intValue() + 1);
		return StringUtils.uncapitalize(className);
	}
	
	public Class getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class entityClass) {
		this.entityClass = entityClass;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public String getOrderAscField() {
		return orderAscField;
	}

	public void setOrderAscField(String orderAscField) {
		this.orderAscField = orderAscField;
	}

	public String getOrderDescField() {
		return orderDescField;
	}

	public void setOrderDescField(String orderDescField) {
		this.orderDescField = orderDescField;
	}

}
