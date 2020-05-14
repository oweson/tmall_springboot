package com.how2java.tmall.web;

import com.how2java.tmall.pojo.Card;
import com.how2java.tmall.service.CardService;
import com.how2java.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("card")
public class CardController {
    @Autowired
    private CardService cardService;

    /**
     * 1 查找
     */
    @GetMapping("get")
    public Card get(@RequestParam("id") Integer id) {
        return cardService.get(id);
    }

    /**
     * 2 删除
     */
    @RequestMapping("delete")
    public Object delete(@RequestParam("id") Integer id) {
        cardService.delete(id);
        return "success";
    }

    /**
     * 3 查询全部
     */
    @RequestMapping("list")
    @ResponseBody
    public Page4Navigator<Card> list(@RequestParam(value = "start", defaultValue = "0")
                                             int start, @RequestParam(value = "size",
            defaultValue = "5") int size) {
        return cardService.list(start, size, 5);

    }

    /**
     * 4 更新
     */

    @RequestMapping("update")
    public Card update(Card card) {
        cardService.update(card);
        return card;
    }

    /**
     * 5 新增
     */
    @PostMapping("save")
    public Object save(Card card) {
        cardService.add(card);
        return card;
    }

}
