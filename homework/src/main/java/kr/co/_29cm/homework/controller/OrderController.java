package kr.co._29cm.homework.controller;

import kr.co._29cm.homework.domain.Goods;
import kr.co._29cm.homework.dto.ReceiptDto;
import kr.co._29cm.homework.error.SoldOutException;
import kr.co._29cm.homework.service.GoodsService;
import kr.co._29cm.homework.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final GoodsService goodsService;

    // 주문을 생성하는 메서드
    public ReceiptDto createOrder() throws Exception {
        // csv 파일 불러와 리스트 생성 - 리턴 타입 (HashMap<상품 id, 상품 정보 Objects>)
        HashMap<Long, Goods> GoodList = goodsService.insertGoods();
        Scanner sc = new Scanner(System.in);
        HashMap<Long, Integer> orderList = new HashMap<>(); // 주문 목록 리스트

        while(true){
            System.out.print("상품번호: ");
            String pId = sc.nextLine();
            System.out.print("수량: ");
            String pNumber = sc.nextLine();
            if(pId.isBlank() || pNumber.isBlank()){ // 둘 중 하나라도 빈칸이면 주문 종료
                break;
            }else{
                orderList.put(Long.valueOf(pId), Integer.valueOf(pNumber));
            }
        }
        try{
            return orderService.checkOrder(orderList, GoodList); // 생성된 주문 리스트로 주문 체크
        }catch(SoldOutException exception){
            System.out.println("SoldOutException 발생. 주문한 상품량이 재고량보다 큽니다.");
            HashMap<String, Integer> temp = new HashMap<>();
            return new ReceiptDto(temp,0);
        }
    }

    // 주문 정보 출력 메서드
    public void printOrderInfo(ReceiptDto receiptDto){
        DecimalFormat decFormat = new DecimalFormat("###,###");
        System.out.println("주문 내역: ");
        System.out.println("-----------------------------------------------------");
        for (ReceiptDto.GoodDto goodDto : receiptDto.getGoodsList()) {
            System.out.println(goodDto.getName() + " - " + goodDto.getQuantity() + "개");
        }
        System.out.println("-----------------------------------------------------");
        System.out.println("주문 금액: " + decFormat.format(receiptDto.getTotalGoodsPrice()));
        System.out.println("-----------------------------------------------------");
        System.out.println("지불 금액: " + decFormat.format(receiptDto.getTotalPrice()));
    }
}
