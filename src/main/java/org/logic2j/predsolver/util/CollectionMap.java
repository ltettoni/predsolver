package org.logic2j.predsolver.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * A {@link LinkedHashMap} whose values are collections of the value type.
 * 
 * @version $Id: CollectionMap.java,v 1.3 2011-10-14 22:53:46 tettoni Exp $
 */
public class CollectionMap<TypeKey, TypeValue> extends LinkedHashMap<TypeKey, Collection<TypeValue>> {
  private static final long serialVersionUID = 1L;

  /**
   * Add a value to the collection addressed by theKey. If nothing was registered a new
   * collection is instantiated by {@link #instantiateCollection()} and the theElement is added to it.
   * @param theKey
   * @param theElement
   */
  public void add(TypeKey theKey, TypeValue theElement) {
    Collection<TypeValue> collection = get(theKey);
    if (collection == null) {
      collection = instantiateCollection();
      put(theKey, collection);
    }
    collection.add(theElement);
  }

  /**
   * @param theKey
   * @return The collection associated to theKey, or a just created empty one
   * (see {@link #instantiateCollection()}, will never return null.
   */
  public Collection<TypeValue> getOrCreate(TypeKey theKey) {
    if (this.containsKey(theKey)) {
      return get(theKey);
    }
    Collection<TypeValue> value = instantiateCollection();
    this.put(theKey, value);
    return value;
  }

  /**
   * Override this to provide your preferred implementation of Collection.
   * @return An empty {@link ArrayList}.
   */
  protected Collection<TypeValue> instantiateCollection() {
    return new ArrayList<TypeValue>();
  }

  /**
   * @param theValue
   * @return true if theValue is contained either in keys or collections of values (ie anywhere).
   */
  public boolean containsInKeysOrValues(TypeValue theValue) {
    if (this.keySet().contains(theValue)) {
      return true;
    }
    for (Collection<TypeValue> values : this.values()) {
      if (values.contains(theValue)) {
        return true;
      }
    }
    return false;
  }

}
