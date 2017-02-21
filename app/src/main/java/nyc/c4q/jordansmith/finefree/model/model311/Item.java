package nyc.c4q.jordansmith.finefree.model.model311;

import java.util.List;

/**
 * Created by jordansmith on 2/20/17.
 */

public class Item {

    private String date;
    private String todayId;
    private List<Item_> items = null;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTodayId() {
        return todayId;
    }

    public void setTodayId(String todayId) {
        this.todayId = todayId;
    }

    public List<Item_> getItems() {
        return items;
    }

    public void setItems(List<Item_> items) {
        this.items = items;
    }

}
