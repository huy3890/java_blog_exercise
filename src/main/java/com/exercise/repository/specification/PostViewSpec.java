package com.exercise.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import com.exercise.req.PostRequest;
import com.exercise.util.StringConstant;

@Component
public class PostViewSpec<T> extends BaseSpecification<T, PostRequest> {

  @Override
  public Specification<T> getFilter(PostRequest request) {
    return (root, query,
        cb) -> byPropertyIgnoreCase(StringConstant.STATUS.value(), request.getStatus())
            .and(byPublishDateDate(request.getPublishDate())).toPredicate(root, query, cb);
  }
}
