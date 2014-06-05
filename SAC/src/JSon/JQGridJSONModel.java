/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JSon;

/**
 *
 * @author juangocc
 */
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author amartinez
 */
public class JQGridJSONModel implements Serializable {

    private String page;
    private String total;
    private Integer records;
    private List<JQGridRow> rows;

    /**
     * @return the page
     */
    public String getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(String page) {
        this.page = page;
    }

    /**
     * @return the total
     */
    public String getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(String total) {
        this.total = total;
    }

    /**
     * @return the records
     */
    public Integer getRecords() {
        return records;
    }

    /**
     * @param records the records to set
     */
    public void setRecords(Integer records) {
        this.records = records;
    }

    /**
     * @return the rows
     */
    public List<JQGridRow> getRows() {
        return rows;
    }

    /**
     * @param rows the rows to set
     */
    public void setRows(List<JQGridRow> rows) {
        this.rows = rows;
    }
}