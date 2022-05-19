package hello.itemservice.domain.item;

import lombok.Data;

@Data
// @ScriptAssert 오브젝트 에러 처리로 권장하지 않음 (자바 코드로 작성하는 것을 권장)
//@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000", message = "총합이 10000원 넘게 입력해주세요.")
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
