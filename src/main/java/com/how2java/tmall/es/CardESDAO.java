package com.how2java.tmall.es;

import com.how2java.tmall.pojo.Card;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CardESDAO extends ElasticsearchRepository<Card,Integer> {
}
