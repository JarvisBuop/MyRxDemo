package com.zjy.table.data.model;


import java.io.Serializable;

/**
 * Table
 * Created by lxx on 2016/3/22.
 * todo 被不少别的地方用到，后面挪走，现在底层数据结构还是很乱
 */
public class Table implements Serializable {

    /**
     * 桌号
     */
    public String tableNo;
    /**
     * 桌名
     */
    public String tableName;
    /**
     * 餐区名
     */
    public String tableType;
    /**
     * 餐桌状态
     * {@link }
     */
    public int tableStatus;

    public Table() {
        super();
    }

    public Table(String tableNo, String tableName) {
        super();
        this.tableNo = tableNo;
        this.tableName = tableName;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(int tableStatus) {
        this.tableStatus = tableStatus;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Table table = (Table) o;

        return tableNo.equals(table.tableNo);

    }
}
