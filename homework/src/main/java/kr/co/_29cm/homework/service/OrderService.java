package kr.co._29cm.homework.service;

import kr.co._29cm.homework.domain.Goods;
import kr.co._29cm.homework.dto.ReceiptDto;
import kr.co._29cm.homework.error.SoldOutException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    // 주문 체크 메서드 - 주문 체크 후 주문 정보 DTO 반환
    public ReceiptDto checkOrder(HashMap<Long, Integer> orderList, HashMap<Long, Goods> goodsList) throws Exception {
        HashMap<String, Integer> goods = new HashMap<>();
        int totalPrice = 0;
        for (Map.Entry<Long, Integer> temp : orderList.entrySet()) {
            /*
             * 1. 구매한 정보 dto에 저장
             * 2. 구매한 수량 만큼 재고 감소
             * 3. 총 가격 계산
             * */
            Goods goodsInfo = goodsList.get(temp.getKey());
            if(!goodsInfo.decreaseQuantity(temp.getValue())){ // 재고가 부족할 경우
                throw new SoldOutException();
            }
            goods.put(goodsInfo.getName(), temp.getValue());
            totalPrice += goodsInfo.getPrice();
        }
        ReceiptDto receiptDto = new ReceiptDto(goods, totalPrice); // 영수증 목록 생성
        return receiptDto;
    }
}
