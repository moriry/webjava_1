package jp.co.systena.tigerscave.shoppingcart.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.systena.tigerscave.shoppingcart.common.Constants.Url;
import jp.co.systena.tigerscave.shoppingcart.model.display.Item;
import jp.co.systena.tigerscave.shoppingcart.model.form.Order;
import jp.co.systena.tigerscave.shoppingcart.service.ListService;

@Controller
public class ListController {
    /** ロギング */
    private static final Logger LOGGER = LoggerFactory.getLogger(ListController.class);

    /** 表示画面 */
    private static final String LIST_VIEW = "ListView";

    @Autowired
    private HttpSession session;

    @Autowired
    private ListService listService;

    /**
     * 画面表示メソッド
     * @return モデル/ビュー情報
     */
    @GetMapping(Url.SHOPPING)
    public ModelAndView show() {
        LOGGER.info("ListController show start");
        ModelAndView mav = new ModelAndView(LIST_VIEW);
        Item item = listService.getItemList();
        Order order = new Order();
        if (session.getAttribute("item1Count") != null && session.getAttribute("item1Count") != "") {
            int item1Count = Integer.valueOf(String.valueOf(session.getAttribute("item1Count")));
            order.setItem1Count(String.valueOf(item1Count));
        }
        if (session.getAttribute("item2Count") != null && session.getAttribute("item2Count") != "") {
            int item2Count = Integer.valueOf(String.valueOf(session.getAttribute("item2Count")));
            order.setItem2Count(String.valueOf(item2Count));
        }
        if (session.getAttribute("item3Count") != null && session.getAttribute("item3Count") != "") {
            int item3Count = Integer.valueOf(String.valueOf(session.getAttribute("item3Count")));
            order.setItem3Count(String.valueOf(item3Count));
        }
        if (session.getAttribute("errorMessage") != null) {
            mav.addObject("errorMessage", session.getAttribute("errorMessage"));
        }
        mav.addObject("order", order);
        mav.addObject("item", item);
        return mav;
    }

    /**
     * カート情報をセッションに保存
     * @return モデル/ビュー情報
     */
    @PostMapping(Url.SHOPPING)
    public ModelAndView addCart(Order order) {
        LOGGER.info("addCart start");
        session.removeAttribute("errorMessage");
        if (order.getItem1Count() != null && order.getItem1Count() != ""
                && Integer.parseInt(order.getItem1Count()) >= 0) {
            session.setAttribute("item1Count", order.getItem1Count());
        } else if (order.getItem2Count() != null && order.getItem2Count() != ""
                && Integer.parseInt(order.getItem2Count()) >= 0) {
            session.setAttribute("item2Count", order.getItem2Count());
        } else if (order.getItem3Count() != null && order.getItem3Count() != ""
                && Integer.parseInt(order.getItem3Count()) >= 0) {
            session.setAttribute("item3Count", order.getItem3Count());
        } else {
            session.setAttribute("errorMessage", "個数は0以上の整数で入力してください");
        }
        return new ModelAndView("redirect:" + Url.SHOPPING);
    }

    /**
     * カート情報画面へ遷移
     * @return モデル/ビュー情報
     */
    @PostMapping(Url.SHOW_CART_INFO)
    public ModelAndView showCartInfo() {
        LOGGER.info("Redirect cart info");
        session.removeAttribute("errorMessage");
        return new ModelAndView("redirect:" + Url.CART);
    }

    /**
     * カート情報削除
     * @return モデル/ビュー情報
     */
    @PostMapping(Url.DELETE_CART_INFO)
    public ModelAndView deleteCartInfo() {
        LOGGER.info("Delete cart info");
        session.removeAttribute("errorMessage");
        session.removeAttribute("item1Count");
        session.removeAttribute("item2Count");
        session.removeAttribute("item3Count");
        return new ModelAndView("redirect:" + Url.SHOPPING);
    }
}
