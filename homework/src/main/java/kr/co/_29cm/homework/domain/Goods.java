package kr.co._29cm.homework.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Goods {
    private String name;
    private int price;
    private int quantity;

    public boolean decreaseQuantity(int orderQuantity){
        if(this.quantity - orderQuantity < 0){
            return false;
        }
        this.quantity -= orderQuantity;

        return true;
    }
}
