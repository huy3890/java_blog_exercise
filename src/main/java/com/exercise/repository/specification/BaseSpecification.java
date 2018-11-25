package com.exercise.repository.specification;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import com.exercise.util.StringConstant;

public abstract class BaseSpecification<T, U> {

  abstract Specification<T> getFilter(U request);

  <V> Specification<T> byProperty(String property, V value) {
    return (root, query, cb) -> {
      if (StringUtils.isEmpty(value)) {
        return null;
      }
      return cb.equal(root.get(property), value);
    };
  }

  Specification<T> byPropertyIgnoreCase(String property, String value) {
    return (root, query, cb) -> {
      if (StringUtils.isEmpty(value)) {
        return null;
      }
      return cb.equal(cb.lower(root.get(property)), value.toLowerCase());
    };
  }

  Specification<T> byPropertyIn(String property, List ids) {
    return (root, query, cb) -> {
      if (CollectionUtils.isEmpty(ids)) {
        return null;
      }
      return root.get(property).in(ids);
    };
  }

  Specification<T> byPropertyInIgnoreCase(String property, List<String> arrayString) {
    return (root, query, cb) -> {
      if (CollectionUtils.isEmpty(arrayString)) {
        return null;
      }
      return cb.lower(root.get(property))
          .in(arrayString.stream().map(String::toLowerCase).collect(Collectors.toSet()));
    };
  }

  Specification<T> byPublishDateDate(Date publishDate) {
    return (root, query, cb) -> {
      return cb.lessThanOrEqualTo(root.get(StringConstant.PUBLISHDATE.value()), publishDate);
    };
  }

}
