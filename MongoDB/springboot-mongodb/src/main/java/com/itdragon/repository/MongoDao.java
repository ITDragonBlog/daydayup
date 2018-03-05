package com.itdragon.repository;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.itdragon.pojo.Id;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class MongoDao {

	@Autowired(required = false)
	private MongoTemplate mongoTemplate;
	private Class entityClass;
	private String collectionName;
	private String orderAscField;
	private String orderDescField;

	public MongoDao() {
	}

	public MongoDao(Class entityClass) {
		this.entityClass = entityClass;
		this.collectionName = _getCollectionName();
	}

	public MongoDao(Class entityClass, String collectionName) {
		this.entityClass = entityClass;
		this.collectionName = collectionName;
	}

	public long count() {
		return this.mongoTemplate.count(new Query(), this.collectionName);
	}

	public long count(Criteria criteria) {
		return this.mongoTemplate.count(new Query(criteria), this.collectionName);
	}

	public List find(Criteria criteria) {
		Query query = new Query(criteria);
		_sort(query);
		return this.mongoTemplate.find(query, this.entityClass, this.collectionName);
	}

	public Object group(Criteria criteria, GroupBy groupBy) {
		if (null == criteria) {
			return this.mongoTemplate.group(this.collectionName, groupBy, this.entityClass);
		}
		return this.mongoTemplate.group(criteria, this.collectionName, groupBy, this.entityClass);
	}

	public List find(Criteria criteria, Integer pageSize) {
		Query query = new Query(criteria).limit(pageSize.intValue());
		_sort(query);
		return this.mongoTemplate.find(query, this.entityClass, this.collectionName);
	}

	public List find(Criteria criteria, Integer pageSize, Integer pageNumber) {
		Query query = new Query(criteria).skip((pageNumber.intValue() - 1) * pageSize.intValue()).limit(pageSize.intValue());
		_sort(query);
		return this.mongoTemplate.find(query, this.entityClass, this.collectionName);
	}

	public Object findAndModify(Criteria criteria, Update update) {
		return this.mongoTemplate.findAndModify(new Query(criteria), update, this.entityClass, this.collectionName);
	}

	public Object findAndRemove(Criteria criteria) {
		return this.mongoTemplate.findAndRemove(new Query(criteria), this.entityClass, this.collectionName);
	}

	public List findAll() {
		return this.mongoTemplate.findAll(this.entityClass, this.collectionName);
	}

	public Object findById(Object id) {
		return this.mongoTemplate.findById(id, this.entityClass, this.collectionName);
	}

	public List findIds(Criteria criteria) {
		return this.mongoTemplate.find(new Query(criteria), Id.class, this.collectionName);
	}

	public Boolean checkExists(Criteria criteria) {
		Query query = new Query(criteria).limit(1);
		_sort(query);
		return Boolean.valueOf(null != this.mongoTemplate.findOne(query, this.entityClass, this.collectionName));
	}

	public Object findOne(Criteria criteria) {
		Query query = new Query(criteria).limit(1);
		_sort(query);
		return this.mongoTemplate.findOne(query, this.entityClass, this.collectionName);
	}

	public Object findOne(Criteria criteria, Integer skip) {
		Query query = new Query(criteria).skip(skip.intValue()).limit(1);
		_sort(query);
		return this.mongoTemplate.findOne(query, this.entityClass, this.collectionName);
	}

	public Object findOne(Integer skip) {
		Query query = new Query().skip(skip.intValue()).limit(1);
		_sort(query);
		return this.mongoTemplate.findOne(query, this.entityClass, this.collectionName);
	}

	public Boolean remove(Object object) {
		try {
			this.mongoTemplate.remove(object);
		} catch (Exception e) {
			e.printStackTrace();
			return Boolean.valueOf(false);
		}
		return Boolean.valueOf(true);
	}

	public Boolean remove(Criteria criteria) {
		try {
			this.mongoTemplate.remove(new Query(criteria), this.collectionName);
		} catch (Exception e) {
			e.printStackTrace();
			return Boolean.valueOf(false);
		}
		return Boolean.valueOf(true);
	}

	public WriteResult updateMulti(Criteria criteria, Update update) {
		return this.mongoTemplate.updateMulti(new Query(criteria), update, this.collectionName);
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

	public Boolean insert(Collection batchToSave) {
		try {
			this.mongoTemplate.insert(batchToSave, this.collectionName);
		} catch (Exception e) {
			e.printStackTrace();
			return Boolean.valueOf(false);
		}
		return Boolean.valueOf(true);
	}

	public MongoOperations getMongoOperation() {
		return this.mongoTemplate;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public String getCollectionName() {
		return this.collectionName;
	}

	public Class getEntityClass() {
		return this.entityClass;
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

	private void _sort(Query query) {
		if (null != this.orderAscField) {
			String[] fields = this.orderAscField.split(",");
			for (String field : fields) {
				if ("id".equals(field)) {
					field = "_id";
				}
				query.with(new Sort(Sort.Direction.ASC, new String[] { field }));
			}
		} else {
			if (null == this.orderDescField) {
				return;
			}
			String[] fields = this.orderDescField.split(",");
			for (String field : fields) {
				if ("id".equals(field)) {
					field = "_id";
				}
				query.with(new Sort(Sort.Direction.DESC, new String[] { field }));
			}
		}
	}

	private String _getCollectionName() {
		String className = this.entityClass.getName();
		Integer lastIndex = Integer.valueOf(className.lastIndexOf("."));
		className = className.substring(lastIndex.intValue() + 1);
		return StringUtils.uncapitalize(className);
	}

	public String getOrderAscField() {
		return this.orderAscField;
	}

	public void setOrderAscField(String orderAscField) {
		this.orderAscField = orderAscField;
	}

	public String getOrderDescField() {
		return this.orderDescField;
	}

	public void setOrderDescField(String orderDescField) {
		this.orderDescField = orderDescField;
	}

	private Criteria _parseRequestRestrictionOr(Map<String, Object> query) {
		Criteria allOrCriteria = new Criteria();
		List criterias = new ArrayList();
		if (null != query) {
			for (String key : query.keySet()) {
				Object value = query.get(key);
				if (StringUtils.startsWith(key, "$and")) {
					criterias.add(getRequestRestriction((Map) value));
				}
				else {
					criterias.addAll(_parseCriteria(key, value));
				}
			}
		}
		if (!(criterias.isEmpty())) {
			allOrCriteria.orOperator((Criteria[]) criterias.toArray(new Criteria[criterias.size()]));
		}
		return allOrCriteria;
	}

	private List<Criteria> _parseCriteria(String key, Object value) {
		if ("id".equals(key)) {
			key = "_id";
		}
		List criterias = new ArrayList();
		Map<String, Object> compareValue;
		if (value instanceof Map) {
			compareValue = (Map) value;
			for (String compare : compareValue.keySet()) {
				Object _compareValue = compareValue.get(compare);
				if (CommonDaoHelper.GE.equals(compare)) {
					criterias.add(Criteria.where(key).gte(_compareValue));
				} else if (CommonDaoHelper.LE.equals(compare)) {
					criterias.add(Criteria.where(key).lte(_compareValue));
				} else if ("$gt".equals(compare)) {
					criterias.add(Criteria.where(key).gt(_compareValue));
				} else if ("$lt".equals(compare)) {
					criterias.add(Criteria.where(key).lt(_compareValue));
				} else if ("$in".equals(compare)) {
					criterias.add(Criteria.where(key).in((Collection) _compareValue));
				} else if ("$like".equals(compare)) {
					criterias.add(Criteria.where(key).regex(
							Pattern.compile(Pattern.quote((String) _compareValue), 2)));
				} else if ("$left_like".equals(compare)) {
					criterias.add(Criteria.where(key).regex(
							Pattern.compile(Pattern.quote(((String) _compareValue) + "$"), 2)));
				} else if ("$right_like".equals(compare)) {
					criterias.add(Criteria.where(key).regex(
							Pattern.compile(Pattern.quote("^" + ((String) _compareValue)), 2)));
				} else if ("$not_like".equals(compare)) {
					criterias.add(Criteria.where(key).not().regex((String) _compareValue));
				} else if ("$left_like".equals(compare)) {
					criterias.add(Criteria.where(key).not()
							.regex(Pattern.compile(Pattern.quote(((String) _compareValue) + "$"), 2)));
				} else if ("$not_right_like".equals(compare)) {
					criterias.add(Criteria.where(key).not()
							.regex(Pattern.compile( Pattern.quote("^" + ((String) _compareValue)), 2)));
				} else if ("$ne".equals(compare)) {
					criterias.add(Criteria.where(key).ne(_compareValue));
				} else if ("$null".equals(compare)) {
					criterias.add(Criteria.where(key).is(null));
				} else if ("$not_null".equals(compare)) {
					criterias.add(Criteria.where(key).not().is(null));
				} else if ("$not_in".equals(compare)) {
					criterias.add(Criteria.where(key).not().in((Collection) _compareValue));
				} else if ("$where".equals(compare)) {
					criterias.add(Criteria.where("$where").is(_compareValue));
				}
			}
		} else {
			criterias.add(Criteria.where(key).is(value));
		}
		return criterias;
	}

	public Criteria getRequestRestriction(Map<String, Object> query) {
		Criteria allCriteria = new Criteria();
		List criterias = new ArrayList();
		if (null != query) {
			for (String key : query.keySet()) {
				if ("$or".equals(key)) {
					Object orObject = query.get(key);
					if (orObject instanceof Map) {
						Map orValues = (Map) orObject;
						criterias.add(_parseRequestRestrictionOr(orValues));
					} else if (orObject instanceof List) {
						List orValues = (List) orObject;
						Map orBigMap = new HashMap();
//						for (Iterator localIterator2 = orValues.iterator(); localIterator2.hasNext();) {
//							orValue = (Map) localIterator2.next();
//							for (String orMapKey : orValue.keySet())
//								orBigMap.put(orMapKey, orValue.get(orMapKey));
//						}
						Map orValue;
						criterias.add(_parseRequestRestrictionOr(orBigMap));
					}
				} else {
					Object value = query.get(key);
					criterias.addAll(_parseCriteria(key, value));
				}
			}
		}
		if (!(criterias.isEmpty())) {
			allCriteria.andOperator((Criteria[]) criterias
					.toArray(new Criteria[criterias.size()]));
		}

		return allCriteria;
	}

	public List fetchCollection(Map<String, Object> requestArgs) {
		Criteria criteria = getRequestRestriction((HashMap) requestArgs
				.get("query"));
		String sortField = CommonDaoHelper.getRequestSortField(requestArgs);
		String sortDirection = CommonDaoHelper
				.getRequestSortDirection(requestArgs);
		Integer pageSize = CommonDaoHelper.getRequestPageSize(requestArgs);
		Integer pageNumber = CommonDaoHelper.getRequestPageNumber(requestArgs);

		if ("-1".equals(sortDirection)) {
			setOrderDescField(sortField);
			setOrderAscField(null);
		} else {
			setOrderAscField(sortField);
			setOrderDescField(null);
		}

		return ((ArrayList) find(criteria, pageSize, pageNumber));
	}

	public Long fetchCollectionCount(Map<String, Object> requestArgs) {
		Criteria criteria = getRequestRestriction((HashMap) requestArgs
				.get("query"));

		return Long.valueOf(count(criteria));
	}

	public Object load(Object id) {
		return findById(id);
	}

	public Object fetchRow(Map<String, Object> requestArgs) {
		Criteria criteria = getRequestRestriction((HashMap) requestArgs
				.get("query"));

		return findOne(criteria);
	}

	public Boolean batchUpdate(Map<String, Object> requestArgs) {
		Map<String, Object> updates;
		try {
			Object ids = requestArgs.get("ids");
			if (null != ids) {
				Update update = new Update();
				updates = (Map) requestArgs.get("updates");
				updates.remove("id");
				updates.remove("ids");
				updates.remove("class");
				for (String key : updates.keySet()) {
					update.set(key, updates.get(key));
				}
				updateMulti(Criteria.where("_id").in((List) ids), update);
			} else {
				List<Map> allUpdates = (List) requestArgs.get("updates");
				for (Map<String, Object> perUpdates : allUpdates) {
					Object id = perUpdates.get("id");
					if (null == id) {
						continue;
					}
					Update update = new Update();
					perUpdates.remove("id");
					perUpdates.remove("class");
					for (String key : perUpdates.keySet()) {
						update.set(key, perUpdates.get(key));
					}
					findAndModify(Criteria.where("id").is(id), update);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

			return Boolean.valueOf(false);
		}

		return Boolean.valueOf(true);
	}

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

	public void delete(Object object) {
		remove(object);
	}

	public Boolean deleteById(Object id) {
		Object object = load(id);
		if (null == object) {
			return Boolean.valueOf(false);
		}

		return remove(object);
	}

	public BasicDBList group(Map<String, Object> requestArgs) throws Exception {
		Criteria criteria = getRequestRestriction((HashMap) requestArgs
				.get("query"));
		HashMap groupConditions = (HashMap) requestArgs.get("group");
		if (null == groupConditions) {
			return null;
		}
		String groupKey = (String) groupConditions.get("key");
		String groupInitialDocument = (String) groupConditions
				.get("initialDocument");
		String groupReduceFunction = (String) groupConditions
				.get("reduceFunction");
		if ((null == groupKey) || (null == groupInitialDocument)
				|| (null == groupReduceFunction)) {
			return null;
		}
		groupInitialDocument = URLDecoder.decode(groupInitialDocument, "UTF-8")
				.replace("ITDRAGON___PLUS____REPLACE___TOKEN", "+");
		groupReduceFunction = URLDecoder.decode(groupReduceFunction, "UTF-8")
				.replace("ITDRAGON___PLUS____REPLACE___TOKEN", "+");

		GroupBy groupBy = GroupBy.key(groupKey.split(","))
				.initialDocument(groupInitialDocument)
				.reduceFunction(groupReduceFunction);
		GroupByResults result = (GroupByResults) group(criteria, groupBy);

		BasicDBList o = (BasicDBList) result.getRawResults().get("retval");

		return o;
	}
}
