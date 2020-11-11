package com.xde.kernel.tools;




/**
 * Mongo操作类
 *
 * @author <a href="mailto:liukailk.ken@gmail.com"> Ken </a>
 * @date 2020/9/23 9:41 上午
 **/
//@Component
//@Slf4j
public class MongoTool {


//    @Value("${mongo.default.collectionName:xde_logs}")
//    private String DEFAULT_COLLECTION_NAME;
//
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//
//    public <T> void insert(T bean) {
//        String collectionName = collectionName(bean.getClass());
//        mongoTemplate.insert(bean, collectionName);
//    }
//
//    public void batchInsert(List<Map<String, Object>> dataList) {
//        //判断集合数据
//        if (dataList == null || dataList.size() == 0 || dataList.get(0) == null) {
//            log.error("类名:MongoDBUtil,方法名为:batchInsert,批量添加文档传入的数据对象为空！！！");
//            return;
//        }
//
//        //转化为MongDB文档对象
//        List<Document> documents = new ArrayList<Document>();
//        for (Map<String, Object> map : dataList) {
//            Document document = new Document();
//            document.putAll(map);
//            documents.add(document);
//        }
//        mongoTemplate.getCollection(DEFAULT_COLLECTION_NAME).insertMany(documents);
//    }
//
//    public void insert(Map<String, Object> data) {
//        //判断集合
//        if (data == null || data.size() == 0) {
//            log.error("类名:MongoDBUtil,方法名为:insert,传入的数据Map为空！！！");
//            return;
//        }
//        //转化为MongDB文档对象
//        Document document = new Document();
//        document.putAll(data);
//        mongoTemplate.getCollection(DEFAULT_COLLECTION_NAME).insertOne(document);
//    }
//
//    public void insert(Map<String, Object> data, String collectionName) {
//        //判断集合
//        if (data == null || data.size() == 0) {
//            log.error("类名:MongoDBUtil,方法名为:insert,传入的数据Map为空！！！");
//            return;
//        }
//        //转化为MongDB文档对象
//        Document document = new Document();
//        document.putAll(data);
//        mongoTemplate.getCollection(collectionName).insertOne(document);
//    }
//
//    public <T> Object findById(Serializable id, Class<T> entityClass) {
//        Object result = new Object();
//        String collectionName = collectionName(entityClass);
//        if (StringUtils.isEmpty(collectionName)) {
//            return result;
//        }
//        Query query = new Query(Criteria.where("id").is(id));
//        return mongoTemplate.findOne(query, entityClass, collectionName);
//    }
//
//    public <T> List<T> find(Map<String, Object> filterMap, Map<String, Object> orderByMap, int pageNo, int pageSize, Class<T> entityClass) throws Exception {
//        List<T> result = new ArrayList<T>();
//        //存储查询Mongodb文档集
//        List<Document> documentList = new ArrayList<Document>();
//        String collName = collectionName(entityClass);
//        if (StringUtils.isEmpty(collName)) {
//            log.error("类名:MongoDBUtil,方法名为:find,集合名称不能为空！！！");
//            return result;
//        }
//        //mongodb过滤条件对象
//        BasicDBObject filter = new BasicDBObject();
//        //添加过滤条件
//        filterCondition(filterMap, filter);
//        BasicDBObject orderBy = new BasicDBObject();
//        if (orderByMap != null && orderByMap.size() > 0) {
//            orderBy.putAll(orderByMap);
//        }
//        MongoCursor<Document> cursor = mongoTemplate.getCollection(collName).find(filter).sort(orderBy).skip(pageNo).limit(pageSize).iterator();
//        while (cursor.hasNext()) {
//            documentList.add(cursor.next());
//        }
//        //将Mongodb对象转换为java实体对象
//        result = document2Bean(documentList, entityClass);
//
//        return result;
//    }
//
//    public List<Map<String, Object>> find(Map<String, Object> filterMap) {
//        MongoCollection<Document> collection = null;
//        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();//返回结果集
//        //mongodb过滤条件对象
//        BasicDBObject filter = new BasicDBObject();
//        //添加过滤条件
//        filterCondition(filterMap, filter);
//        MongoCursor<Document> cursor = mongoTemplate.getCollection(DEFAULT_COLLECTION_NAME).find(filter).iterator();
//
//        while (cursor.hasNext()) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.putAll(cursor.next());
//            result.add(map);
//        }
//        return result;
//    }
//
//    public <T> long getCount(Class<T> entityClass) {
//        long result = MongoConstant.ERRORCODE;
//        //获取集合名称
//        String collName = collectionName(entityClass);
//        if (StringUtils.isEmpty(collName)) {
//            log.error("类名:MongoDBUtil,方法名为:getCount,集合名称不能为空！！！");
//            return result;
//        }
//        result = mongoTemplate.getCollection(collName).count();
//        return result;
//    }
//
//    public <T> long getCount(Map<String, Object> filterMap, Class<T> entityClass) {
//        long result = MongoConstant.ERRORCODE;
//        //获取集合名称
//        String collName = collectionName(entityClass);
//        if (StringUtils.isEmpty(collName)) {
//            log.error("类名:MongoDBUtil,方法名为:getCount,集合名称不能为空！！！");
//            return result;
//        }
//        //mongodb过滤条件对象
//        BasicDBObject filter = new BasicDBObject();
//        //添加过滤条件
//        filterCondition(filterMap, filter);
//        result = mongoTemplate.getCollection(collName).count(filter);
//        return result;
//    }
//
//    public <T> long getCount(Map<String, Object> filterMap, Map<String, Object> notFilterMap, Class<T> entityClass) {
//        long result = MongoConstant.ERRORCODE;
//        //获取集合名称
//        String collName = collectionName(entityClass);
//        if (StringUtils.isEmpty(collName)) {
//            log.error("类名:MongoDBUtil,方法名为:getCount,集合名称不能为空！！！");
//            return result;
//        }
//        //mongodb过滤条件对象
//        BasicDBObject filter = new BasicDBObject();
//        //添加过滤条件
//        filterCondition(filterMap, notFilterMap, filter);
//        result = mongoTemplate.getCollection(collName).count(filter);
//        return result;
//    }
//
//
//    public <T> long delete(Map<String, Object> filterMap, Class<T> entityClass) {
//        //获取集合名称
//        String collName = collectionName(entityClass);
//        if (StringUtils.isEmpty(collName)) {
//            log.error("类名:MongoDBUtil,方法名为:delete,集合名称不能为空！！！");
//            return MongoConstant.ERRORCODE;
//        }
//        if (filterMap == null || filterMap.size() < 1) {
//            log.error("类名:MongoDBUtil,方法名为:delete,过滤条件不能为空！！！");
//            return MongoConstant.ERRORCODE;
//        }
//        //过滤条件对象
//        BasicDBObject filter = new BasicDBObject();
//        //添加过滤条件
//        filterCondition(filterMap, filter);
//        DeleteResult result = mongoTemplate.getCollection(collName).deleteOne(filter);
//        return result.getDeletedCount();
//    }
//
//    public <T> long batchDelete(Map<String, Object> filterMap, Class<T> entityClass) {
//        //获取集合名称
//        String collName = collectionName(entityClass);
//        if (StringUtils.isEmpty(collName)) {
//            log.error("类名:MongoDBUtil,方法名为:delete,集合名称不能为空！！！");
//            return MongoConstant.ERRORCODE;
//        }
//        if (filterMap == null || filterMap.size() < 1) {
//            log.error("类名:MongoDBUtil,方法名为:delete,过滤条件不能为空！！！");
//            return MongoConstant.ERRORCODE;
//        }
//        //过滤条件对象
//        BasicDBObject filter = new BasicDBObject();
//        //添加过滤条件
//        filterCondition(filterMap, filter);
//        DeleteResult result = mongoTemplate.getCollection(collName).deleteMany(filter);
//        return result.getDeletedCount();
//    }
//
//
//    public <T> long update(Map<String, Object> filterMap, Map<String, Object> updateMap, Class<T> entityClass) {
//        //获取集合名称
//        String collName = collectionName(entityClass);
//        if (StringUtils.isEmpty(collName)) {
//            log.error("类名:MongoDBUtil,方法名为:update,集合名称不能为空！！！");
//            return MongoConstant.ERRORCODE;
//        } else if (updateMap == null || updateMap.size() < 1) {
//            log.error("类名:MongoDBUtil,方法名为:update,传入的修改条件为空！！！");
//            return MongoConstant.ERRORCODE;
//        } else if (filterMap == null || filterMap.size() < 1) {
//            log.error("类名:MongoDBUtil,方法名为:update,传入的过滤条件为空！！！");
//            return MongoConstant.ERRORCODE;
//        }
//
//        //过滤条件对象(譬如 where后面的条件)
//        BasicDBObject filter = new BasicDBObject();
//        //修改条件对象(譬如set后面的条件)
//        Document update = new Document();
//        //过滤条件
//        filterCondition(filterMap, filter);
//        //修改条件
//        updateCondition(updateMap, update);
//        UpdateResult result = mongoTemplate.getCollection(collName).updateOne(filter, update);
//        return result.getModifiedCount();
//    }
//
//    public <T> long batchUpdate(Map<String, Object> filterMap, Map<String, Object> updateMap, Class<T> entityClass) {
//        //获取集合名称
//        String collName = collectionName(entityClass);
//        if (StringUtils.isEmpty(collName)) {
//            log.error("类名:MongoDBUtil,方法名为:batchUpdate,集合名称不能为空！！！");
//            return MongoConstant.ERRORCODE;
//        } else if (updateMap == null || updateMap.size() < 1) {
//            log.error("类名:MongoDBUtil,方法名为:batchUpdate,传入的修改条件为空！！！");
//            return MongoConstant.ERRORCODE;
//        } else if (filterMap == null || filterMap.size() < 1) {
//            log.error("类名:MongoDBUtil,方法名为:batchUpdate,传入的过滤条件为空！！！");
//            return MongoConstant.ERRORCODE;
//        }
//        //过滤条件对象(譬如 where后面的条件)
//        BasicDBObject filter = new BasicDBObject();
//        //修改条件对象(譬如set后面的条件)
//        Document update = new Document();
//        //过滤条件
//        filterCondition(filterMap, filter);
//        //修改条件
//        updateCondition(updateMap, update);
//
//        UpdateResult result = mongoTemplate.getCollection(collName).updateMany(filter, update);
//        return result.getModifiedCount();
//    }
//
//
//    private String collectionName(Class<?> entityClass) {
//        //默认集合名称
//        String collName = DEFAULT_COLLECTION_NAME;
//        if (entityClass == null) {
//            return collName;
//        }
//        collName = entityClass.getSimpleName();
//        //获取集合都会添加默认前缀
//        return collName;
//    }
//
//
//    private static void filterCondition(Map<String, Object> filterMap, BasicDBObject filter) {
//        if (filterMap != null && filterMap.size() > 0) {
//            // 过滤条件,多条件查询需要先定义一个BasicDBObject数组来存储多个条件
//            List<BasicDBObject> objects = new ArrayList<BasicDBObject>();
//            for (Map.Entry<String, Object> entry : filterMap.entrySet()) {
//                if (entry.getValue() instanceof List) {
//                    BasicDBList values = new BasicDBList();
//                    for (Object id : (List<Object>) entry.getValue()) {
//                        values.add(id);
//                    }
//                    objects.add(new BasicDBObject(entry.getKey().toString(), new BasicDBObject("$in", values)));
//                } else {
//                    objects.add(new BasicDBObject(entry.getKey().toString(), entry.getValue()));
//                }
//            }
//            //目前的条件都是and ,如果有特殊要求再进行修改
//            filter.put(MongoConstant.AND, objects);
//        }
//    }
//
//    private static void updateCondition(Map<String, Object> updateMap, Document update) {
//        if (updateMap != null && updateMap.size() > 0) {
//            //临时对象
//            Document temp = new Document();
//            for (Map.Entry<String, Object> entry : updateMap.entrySet()) {
//                temp.put(entry.getKey(), entry.getValue());
//            }
//            //mongodb修改条件
//            update.put(MongoConstant.SET, temp);
//        }
//    }
//
//    private static void filterCondition(Map<String, Object> filterMap, Map<String, Object> notFilterMap, BasicDBObject filter) {
//        // 过滤条件,多条件查询需要先定义一个BasicDBObject数组来存储多个条件
//        List<BasicDBObject> objects = new ArrayList<BasicDBObject>();
//        //in 或者 ==
//        if (filterMap != null && filterMap.size() > 0) {
//            for (Map.Entry<String, Object> entry : filterMap.entrySet()) {
//                if (entry.getValue() instanceof List) {
//                    BasicDBList values = new BasicDBList();
//                    for (Object id : (List<Object>) entry.getValue()) {
//                        values.add(id);
//                    }
//                    objects.add(new BasicDBObject(entry.getKey().toString(), new BasicDBObject(MongoConstant.IN, values)));
//                } else {
//                    objects.add(new BasicDBObject(entry.getKey().toString(), entry.getValue()));
//                }
//            }
//        }
//        //not in 或者 ！=
//        if (notFilterMap != null && notFilterMap.size() > 0) {
//            for (Map.Entry<String, Object> entry : notFilterMap.entrySet()) {
//                if (entry.getValue() instanceof List) {
//                    BasicDBList values = new BasicDBList();
//                    for (Object id : (List<Object>) entry.getValue()) {
//                        values.add(id);
//                    }
//                    objects.add(new BasicDBObject(entry.getKey().toString(), new BasicDBObject(MongoConstant.NIN, values)));
//                } else {
//                    objects.add(new BasicDBObject(entry.getKey().toString(), new BasicDBObject(MongoConstant.NE, entry.getValue())));
//                }
//            }
//        }
//
//        //目前的条件都是and ,如果有特殊要求再进行修改
//        if (objects != null && objects.size() > 0) {
//            filter.put(MongoConstant.AND, objects);
//        }
//
//    }
//
//    private static <T> List<T> document2Bean(List<Document> documents, Class<T> entityClass) throws Exception {
//        List<T> list = new ArrayList<T>();
//        if (documents == null || documents.size() == 0) {
//            log.error("Document对象转化为java实体对象,Document对象为空！");
//            return list;
//        }
//        for (Document document : documents) {
//            T bean = entityClass.newInstance();
//            recyleDocument(document, bean);
//            list.add(bean);
//        }
//        return list;
//    }
//
//    private static void recyleDocument(Document document, Object bean) throws Exception {
//        Field[] fields = bean.getClass().getDeclaredFields();
//        for (Field field : fields) {
//            //修改访问控制权限(暂时保留)
//            boolean accessFlag = field.isAccessible();
//            // 修改访问控制权限  
//            if (!accessFlag) {
//                field.setAccessible(true);
//            }
//            String varName = field.getName();//获取该字段名称
//            Class<?> paramType = field.getType();//获取该字段类型
//            Object value = document.get(varName);
//            if (value == null) {
//                continue;
//            }
//            recyleValue(varName, value, paramType, bean);
//            // 恢复访问控制权限  
//            field.setAccessible(accessFlag);
//        }
//    }
//
//    private static void recyleValue(String key, Object value, Class<?> param, Object bean) throws Exception {
//        String packageUrl = value.getClass().toString();//获取该属性的包路径
//        String paramType = param.getName();
//        if (value instanceof List) {//存入mongodb的List对象与 解析后java 实体类中List 对象一致
//            BeanUtils.setProperty(bean, key, toRecyleList(value));
//        } else if ((value instanceof Map) && MongoConstant.MAPTYPE.equals(paramType)) { //添加一个 判断条件，为了区分 存入到Mongodb的Map对象与存入Document对象 ，mongodb的Document对象是继承了java 的Map对象
//            BeanUtils.setProperty(bean, key, toRecyleMap(value));
//        } else if ((value instanceof Document) && paramType.contains(MongoConstant.PACKAGEURL)) {//存入mongodb的Document对象 对应是 java 的一个bean,
//            Class<?> type = getPropertyType(bean, key);    //获取bean中 引入 另一个bean的类泛型
//            Object object = type.newInstance(); //初始化一个实例
//            recyleDocument((Document) value, object);
//            BeanUtils.setProperty(bean, key, object);
//        } else if (packageUrl.startsWith(MongoConstant.BASETYPE) || packageUrl.startsWith(MongoConstant.DATETYPE) || paramType.contains(MongoConstant.BIGDECIMALTYPE)) {
//            BeanUtils.setProperty(bean, key, value);//如果是基本类型和字符串，直接转
//        }
//    }
//
//    private static List<Object> toRecyleList(Object value) {
//        List<Object> list = new ArrayList<Object>();
//        @SuppressWarnings("unchecked")
//        Iterator<Object> it = (Iterator<Object>) ((List<?>) value).iterator();
//        while (it.hasNext()) {
//            Object object = it.next();
//            String packageUrl = object.getClass().toString();//获取该属性的包路径
//            if (packageUrl.startsWith(MongoConstant.BASETYPE) || packageUrl.startsWith(MongoConstant.DATETYPE)) {
//                list.add(object);
//            } else if (object instanceof List) {
//                list.add(toRecyleList(object));
//            } else if (object instanceof Map) {
//                list.add(toRecyleMap(object));
//            } else {
//                //遇到特殊的再进行处理
//            }
//        }
//        return list;
//
//    }
//
//    private static Map<String, Object> toRecyleMap(Object param) {
//        Map<String, Object> map = new HashMap<String, Object>();
//        @SuppressWarnings("unchecked")
//        Iterator<Map.Entry<String, Object>> it = ((Map<String, Object>) param).entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<String, Object> entry = it.next();
//            String key = entry.getKey();
//            Object value = entry.getValue();
//            String packageUrl = value.getClass().toString();//获取该属性的包路径
//            if (packageUrl.startsWith(MongoConstant.BASETYPE) || packageUrl.startsWith(MongoConstant.DATETYPE)) {
//                map.put(key, value);
//            } else if (value instanceof List) {
//                map.put(key, toRecyleList(value));
//            } else if (value instanceof Map) {
//                map.put(key, toRecyleMap(value));
//            } else {
//                //遇到特殊的再进行处理
//            }
//        }
//        return map;
//    }
//
//    private static Class<?> getPropertyType(Object bean, String key) throws Exception {
//        Class<?> clazz = bean.getClass();
//        Field f = clazz.getDeclaredField(key);
//        Class<?> t = f.getType();
//        if (t.isAssignableFrom(List.class)) {
//            Type gt = f.getGenericType();
//            if (gt == null) {
//                return t;
//            }
//            if (gt instanceof ParameterizedType) {
//                ParameterizedType pt = (ParameterizedType) gt;
//                Class<?> genericClazz = (Class<?>) pt.getActualTypeArguments()[0];
//                if (genericClazz != null) {
//                    return genericClazz;
//                }
//            }
//        }
//        return t;
//    }
}

