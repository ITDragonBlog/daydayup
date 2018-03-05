package com.itdragon.repository;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
	private MongoTemplate mongoTemplate;
	private Class entityClass;
	private String collectionName;
	
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
	
	private String _getCollectionName() {
		String className = this.entityClass.getName();
		Integer lastIndex = Integer.valueOf(className.lastIndexOf("."));
		className = className.substring(lastIndex.intValue() + 1);
		return StringUtils.uncapitalize(className);
	}
	
	/**
	 * create entity
	 * @param requestArgs
	 * @return
	 */
	public Boolean save(Map<String, Object> requestArgs) {
		try {
			Object object = getEntityClass().newInstance();
			if (null == requestArgs.get("id")) {
				requestArgs.put("id", getNextId());
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
	 * update entity
	 * @param requestArgs
	 * @return
	 */
	public Boolean update(Map<String, Object> requestArgs) {
		Object id = requestArgs.get("id");
		if (null == id)
			return Boolean.valueOf(false);
		try {
			Update update = new Update();
			Map<String, Object> updates = (Map) requestArgs.get("updates");
			updates.remove("id");
			updates.remove("class");
			for (String key : updates.keySet()) {
				update.set(key, updates.get(key));
			}
			findAndModify(Criteria.where("id").is(id), update);
		} catch (Exception e) {
			e.printStackTrace();

			return Boolean.valueOf(false);
		}

		return Boolean.valueOf(true);
	}
	
	public Object findAndModify(Criteria criteria, Update update) {
		return this.mongoTemplate.findAndModify(new Query(criteria), update, this.entityClass, this.collectionName);
	}
	
	public String getNextId() {
		return getNextId(getCollectionName());
	}

	public String getNextId(String seq_name) {
		String sequence_collection = "seq";
		String sequence_field = "seq";
		DBCollection seq = this.mongoTemplate.getCollection(sequence_collection);
		DBObject query = new BasicDBObject();
		query.put("_id", seq_name);
		DBObject change = new BasicDBObject(sequence_field, Integer.valueOf(1));
		DBObject update = new BasicDBObject("$inc", change);
		DBObject res = seq.findAndModify(query, new BasicDBObject(), new BasicDBObject(), false, update, true, true);
		return res.get(sequence_field).toString();
	}
	
	public Boolean saveOrUpdate(Object object) {
		try {
			this.mongoTemplate.save(object, this.collectionName);
		} catch (Exception e) {
			e.printStackTrace();
			return Boolean.valueOf(false);
		}
		return Boolean.valueOf(true);
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

}
