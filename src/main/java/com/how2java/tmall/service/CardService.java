package com.how2java.tmall.service;

import com.how2java.tmall.dao.CardDAO;
import com.how2java.tmall.pojo.Card;
import com.how2java.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "cards")
public class CardService {
    @Autowired
    private CardDAO cardDAO;

    @CacheEvict(allEntries = true)
    public void add(Card card) {
        cardDAO.save(card);
    }

    @CacheEvict(allEntries = true)
    public void delete(Integer id) {
        cardDAO.delete(id);
    }

    @CacheEvict(allEntries = true)
    public void update(Card card) {
        cardDAO.save(card);
    }

    @Cacheable(key = "'cards-one-'+#p0")
    public Card get(Integer id) {
        return cardDAO.findOne(id);
    }

    @Cacheable(key = "cards-all")
    public List<Card> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return cardDAO.findAll(sort);
    }

    //     @Cacheable(key = "'categories-page-'+#p0+ '-' + #p1")
    @Cacheable(key = "'cards-page-'+#p0+'-'+#p1")
    public Page4Navigator<Card> list(int start, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page<Card> cardDAOAll = cardDAO.findAll(pageable);
        return new Page4Navigator<>(cardDAOAll, navigatePages);
    }


}
