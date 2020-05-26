package com.how2java.tmall.dao;

import com.how2java.tmall.pojo.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardDAO extends JpaRepository<Card,Integer> {
}
