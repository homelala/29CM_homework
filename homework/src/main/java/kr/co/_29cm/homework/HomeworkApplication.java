package kr.co._29cm.homework;

import kr.co._29cm.homework.controller.OrderController;
import kr.co._29cm.homework.dto.ReceiptDto;
import kr.co._29cm.homework.error.SoldOutException;
import kr.co._29cm.homework.service.GoodsService;
import kr.co._29cm.homework.service.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.util.Scanner;

@SpringBootApplication
public class HomeworkApplication {

	public static void main(String[] args) throws FileNotFoundException, SoldOutException {
		SpringApplication.run(HomeworkApplication.class, args);
		OrderController orderController = new OrderController(new OrderService(), new GoodsService());

		Scanner scanner = new Scanner(System.in);
		System.out.println("입력(o[order]: 주문, q[quit]: 종료) : ");
		String select = scanner.next();
		if(select.equals("o") || select.equals("order")){
			ReceiptDto receipt = orderController.createOrder();
			orderController.printOrderInfo(receipt);
			return;
		}else if(select.equals("q") || select.equals("quit")){
			System.out.println("고객님의 주문 감사합니다.");
			return;
		}else{
			System.out.println("올바른 문구를 입력해주세요.");
		}
	}
}
