package com.how2java.tmall;

import com.how2java.tmall.es.CardESDAO;
import com.how2java.tmall.es.ProductESDAO;
import com.how2java.tmall.pojo.Card;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class EsTest {
    @Autowired
    private ProductESDAO productESDAO;
    @Autowired
    private CardESDAO cardESDAO;

    @Test
    public void save() {
        Card card = new Card();
        card.setId(2);
        card.setCode("211");

        Card save = cardESDAO.save(card);
        System.out.println(save);
    }

    /**
     * FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
     * .add(QueryBuilders.matchPhraseQuery("name", keyword),
     * ScoreFunctionBuilders.weightFactorFunction(100))
     * .scoreMode("sum")
     * .setMinScore(10);
     * Sort sort = new Sort(Sort.Direction.DESC, "id");
     * Pageable pageable = new PageRequest(start, size, sort);
     * SearchQuery searchQuery = new NativeSearchQueryBuilder()
     * .withPageable(pageable)
     * .withQuery(functionScoreQueryBuilder).build();
     * Page<Product> page = productESDAO.search(searchQuery);
     */
    @Test
    public void find() {
        // 1 统计数量
        System.out.println(cardESDAO.count() + "=========================================");
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery().
                add(QueryBuilders.matchPhrasePrefixQuery("code", "211"),
                        ScoreFunctionBuilders.weightFactorFunction(100)).scoreMode("sum").setMinScore(10);
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(0, 10, sort);
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withPageable(pageable).withQuery(functionScoreQueryBuilder).build();
        Page<Card> search = cardESDAO.search(nativeSearchQuery);
        System.out.println(search);
         System.out.println(search);

    }
}
