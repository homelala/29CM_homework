package kr.co._29cm.homework;

import kr.co._29cm.homework.controller.OrderController;
import kr.co._29cm.homework.dto.ReceiptDto;
import kr.co._29cm.homework.service.GoodsService;
import kr.co._29cm.homework.service.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class HomeworkApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(HomeworkApplication.class, args);

		// OrderController 생성, 인자(OrderService, GoodsService)
		OrderController orderController = new OrderController(new OrderService(), new GoodsService());

		// 주문 시작 메소드 호출
		startOrder(orderController);
	}

	// 주문 시작 메소드
	private static void startOrder(OrderController orderController) throws Exception {
		Scanner scanner = new Scanner(System.in);
		while(true){
			System.out.println("입력(o[order]: 주문, q[quit]: 종료) : ");
			String select = scanner.next();

			if(select.equals("o") || select.equals("order")){
				ReceiptDto receipt = orderController.createOrder(); //OrderController의 createOrder 실행 후  ReceiptDto 생성
				if(receipt.getGoodsList().isEmpty()) continue; // soldOutException 발생
				orderController.printOrderInfo(receipt); // 반환된 주문 정보를 통해 프린트 메서드 호출
			}else if(select.equals("q") || select.equals("quit")){
				System.out.println("고객님의 주문 감사합니다.");
				return;
			}else{
				System.out.println("올바른 문구를 입력해주세요.");
			}
		}
	}
}
