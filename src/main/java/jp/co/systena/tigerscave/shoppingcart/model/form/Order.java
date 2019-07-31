package jp.co.systena.tigerscave.shoppingcart.model.form;


public class Order {
    /** アイテム1数 */
    private String item1Count;

    /** アイテム2数 */
    private String item2Count;

    /** アイテム3数 */
    private String item3Count;

    public String getItem1Count() {
        return item1Count;
    }

    public void setItem1Count(String item1Count) {
        this.item1Count = item1Count;
    }

    public String getItem2Count() {
        return item2Count;
    }

    public void setItem2Count(String item2Count) {
        this.item2Count = item2Count;
    }

    public String getItem3Count() {
        return item3Count;
    }

    public void setItem3Count(String item3Count) {
        this.item3Count = item3Count;
    }

}
