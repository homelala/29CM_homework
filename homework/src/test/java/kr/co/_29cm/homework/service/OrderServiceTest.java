package kr.co._29cm.homework.service;

import kr.co._29cm.homework.domain.Goods;
import kr.co._29cm.homework.dto.ReceiptDto;
import kr.co._29cm.homework.error.SoldOutException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private GoodsService goodsService;

    @Test()
    public void 주문재고_초과() throws Exception{
        //given
        HashMap<Long, Goods> goodsList = goodsService.insertGoods();
        HashMap<Long, Integer> orderList = new HashMap<>();
        orderList.put(768848l, 100);

        //when
        //then
        assertThrows(SoldOutException.class, () -> {
            orderService.checkOrder(orderList, goodsList);
        });
    }
}