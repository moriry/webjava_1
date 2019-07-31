package jp.co.systena.tigerscave.shoppingcart.service;

import org.springframework.stereotype.Service;

import jp.co.systena.tigerscave.shoppingcart.model.display.Item;

@Service
public class ListService {

    /**
     * アイテムリスト取得
     * @return アイテム
     */
    public Item getItemList() {
        Item item = new Item();
        return item;
    }
}
