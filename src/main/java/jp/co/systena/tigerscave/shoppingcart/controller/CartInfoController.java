package jp.co.systena.tigerscave.shoppingcart.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.systena.tigerscave.shoppingcart.common.Constants.Url;
import jp.co.systena.tigerscave.shoppingcart.model.display.Item;
import jp.co.systena.tigerscave.shoppingcart.service.ListService;

@Controller
public class CartInfoController {
    /** ロギング */
    private static final Logger LOGGER = LoggerFactory.getLogger(CartInfoController.class);

    /** 表示画面 */
    private static final String CART_VIEW = "CartInfo";

    @Autowired
    private HttpSession session;

    @Autowired
    private ListService listService;

    /**
     * 画面表示メソッド
     * @return モデル/ビュー情報
     */
    @GetMapping(Url.CART)
    public ModelAndView showCartInfo() {
        LOGGER.info("CartInfoController start");
        ModelAndView mav = new ModelAndView(CART_VIEW);
        Item item = listService.getItemList();
        mav.addObject("item", item);
        int item1TotalPrice = 0;
        int item2TotalPrice = 0;
        int item3TotalPrice = 0;
        if (session.getAttribute("item1Count") != null && session.getAttribute("item1Count") != "") {
            int item1Count = Integer.valueOf(String.valueOf(session.getAttribute("item1Count")));
            item1TotalPrice = item.getItemPrice1() * item1Count;
            mav.addObject("item1Count", item1Count);
            mav.addObject("item1TotalPrice", item1TotalPrice);
        }
        if (session.getAttribute("item2Count") != null && session.getAttribute("item2Count") != "") {
            int item2Count = Integer.valueOf(String.valueOf(session.getAttribute("item2Count")));
            item2TotalPrice = item.getItemPrice2() * item2Count;
            mav.addObject("item2Count", item2Count);
            mav.addObject("item2TotalPrice", item2TotalPrice);
        }
        if (session.getAttribute("item3Count") != null && session.getAttribute("item3Count") != "") {
            int item3Count = Integer.valueOf(String.valueOf(session.getAttribute("item3Count")));
            item3TotalPrice = item.getItemPrice3() * item3Count;
            mav.addObject("item3Count", item3Count);
            mav.addObject("item3TotalPrice", item3TotalPrice);
        }
        int totalPrice = item1TotalPrice + item2TotalPrice + item3TotalPrice;
        mav.addObject("totalPrice", totalPrice);
        return mav;
    }

}
