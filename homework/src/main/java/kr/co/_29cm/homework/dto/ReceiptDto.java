package kr.co._29cm.homework.dto;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class ReceiptDto {
    List<GoodDto> goodsList;
    private int totalGoodsPrice;
    private int deliveryPrice;

    @Getter
    public class GoodDto {
        private String name;
        private int quantity;

        public GoodDto(Map.Entry<String, Integer> goods) {
            this.name = goods.getKey();
            this.quantity = goods.getValue();
        }
    }

    protected ReceiptDto() {

    }

    public ReceiptDto(HashMap<String, Integer> goodList, int totalPrice){
        this.goodsList = goodList.entrySet().stream().map(goods -> new GoodDto(goods))
                .collect(Collectors.toList());
        this.totalGoodsPrice = totalPrice;
        if(totalPrice <= 50000){
            this.deliveryPrice = 2500;
        }else{
            this.deliveryPrice = 0;
        }
    }

    public int getTotalPrice(){
        return this.totalGoodsPrice + this.deliveryPrice;
    }
}
