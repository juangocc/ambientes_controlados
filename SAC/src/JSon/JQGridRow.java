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
public class JQGridRow implements Serializable {

    private Integer id;
    private List<String> cell;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the cell
     */
    public List<String> getCell() {
        return cell;
    }

    /**
     * @param cell the cell to set
     */
    public void setCell(List<String> cell) {
        this.cell = cell;
    }
}