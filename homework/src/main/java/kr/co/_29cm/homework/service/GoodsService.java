package kr.co._29cm.homework.service;

import kr.co._29cm.homework.domain.Goods;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

@Controller
public class GoodsService {
    private static HashMap<Long, Goods> goodsList = new HashMap<>();

    // csv 파일로부터 상품 리스트 생성 메서드
    public HashMap<Long, Goods> insertGoods() throws FileNotFoundException {
        URL resource = GoodsService.class.getClassLoader().getResource("itemList.csv");
        String path = resource.getPath();
        Scanner goodsFile = new Scanner((Readable) new BufferedReader(new FileReader(path)));

        while (goodsFile.hasNext()) {
            String line = goodsFile.nextLine();
            String[] array = line.split(",");
            Goods goods = Goods.builder()
                    .name(array[1])
                    .price(Integer.parseInt(array[2].trim()))
                    .quantity(Integer.parseInt(array[3].trim()))
                    .build();
            goodsList.put(Long.valueOf(array[0].trim()),goods);
        }

        return goodsList;
    }
}
