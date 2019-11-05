package com.sozin147.homeaccounting.parser;

import com.sozin147.homeaccounting.parser.cols.Cols;
import com.sozin147.homeaccounting.parser.rows.Rows;

import java.util.List;

public class DataTableDiagramPars {

    private List<Cols> cols;

    private List<Rows> rows;

    public DataTableDiagramPars() {
    }

    public DataTableDiagramPars(List<Cols> cols, List<Rows> rows) {
        this.cols = cols;
        this.rows = rows;
    }

    public List<Cols> getCols() {
        return cols;
    }

    public List<Rows> getRows() {
        return rows;
    }
}
