package kr.co._29cm.homework.controller;

import kr.co._29cm.homework.domain.Goods;
import kr.co._29cm.homework.dto.ReceiptDto;
import kr.co._29cm.homework.error.SoldOutException;
import kr.co._29cm.homework.service.GoodsService;
import kr.co._29cm.homework.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final GoodsService goodsService;

    public ReceiptDto createOrder() throws FileNotFoundException, SoldOutException {
        HashMap<Long, Goods> GoodList = goodsService.insertGoods();
        Scanner sc = new Scanner(System.in);
        HashMap<Long, Integer> orderList = new HashMap<>();

        while(true){
            System.out.print("상품번호: ");
            String pId = sc.nextLine();
            System.out.print("수량: ");
            String pNumber = sc.nextLine();
            if(pId.isBlank() || pNumber.isBlank()){
                break;
            }else{
                orderList.put(Long.valueOf(pId), Integer.valueOf(pNumber));
            }
        }

        return orderService.checkOrder(orderList, GoodList);
    }

    public void printOrderInfo(ReceiptDto receiptDto){
        System.out.println("주문 내역: ");
        System.out.println("-----------------------------------------------------");
        for (ReceiptDto.GoodDto goodDto : receiptDto.getGoodsList()) {
            System.out.println(goodDto.getName() + " " + goodDto.getQuantity() + "개");
        }
        System.out.println("-----------------------------------------------------");
        System.out.println("주문 금액: " + receiptDto.getTotalGoodsPrice());
        System.out.println("-----------------------------------------------------");
        System.out.println("지불 금액: " + receiptDto.getTotalPrice());
    }
}
