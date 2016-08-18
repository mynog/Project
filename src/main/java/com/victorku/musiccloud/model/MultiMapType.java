package com.victorku.musiccloud.model;

import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.collections.MultiMap;
import org.hibernate.HibernateException;
import org.hibernate.collection.spi.PersistentCollection;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.persister.collection.CollectionPersister;
import org.hibernate.usertype.UserCollectionType;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class MultiMapType implements UserCollectionType {

    @Override
    public boolean contains(Object collection, Object entity) {
        return ((MultiMap) collection).containsValue(entity);
    }

    @Override
    public Iterator getElementsIterator(Object collection) {
        return ((MultiMap) collection).values().iterator();
    }

    @Override
    public Object indexOf(Object collection, Object entity) {
        for (Iterator i = ((MultiMap) collection).entrySet().iterator(); i.hasNext();) {
            Map.Entry entry = (Map.Entry) i.next();
            Collection value = (Collection) entry.getValue();
            if (value.contains(entity)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public Object instantiate() {
        return new MultiHashMap();
    }

    @Override
    public PersistentCollection instantiate(SessionImplementor session, CollectionPersister persister) throws HibernateException {
        return new PersistentMultiMap(session);
    }

    @Override
    public PersistentCollection wrap(SessionImplementor session, Object collection) {
        return new PersistentMultiMap(session, (MultiMap) collection);
    }

    @Override
    public Object replaceElements(Object original, Object target, CollectionPersister persister, Object owner, Map copyCache, SessionImplementor session) throws HibernateException {

        MultiMap result = (MultiMap) target;
        result.clear();

        Iterator iter = ((java.util.Map) original).entrySet().iterator();
        while (iter.hasNext()) {
            java.util.Map.Entry me = (java.util.Map.Entry) iter.next();
            Object key = persister.getIndexType().replace(me.getKey(), null, session, owner, copyCache);
            Collection collection = (Collection) me.getValue();
            for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
                Object value = persister.getElementType().replace(iterator.next(), null, session, owner, copyCache);
                result.put(key, value);
            }
        }

        return result;
    }

    @Override
    public Object instantiate(int i) {

        if (i == -1) {
            return new MultiHashMap();
        }
        return new MultiHashMap(i);
    }
}
