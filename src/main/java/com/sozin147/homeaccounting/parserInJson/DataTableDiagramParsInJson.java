package com.sozin147.homeaccounting.parserInJson;


import com.sozin147.homeaccounting.parserInJson.cols.Cols;
import com.sozin147.homeaccounting.parserInJson.rows.Rows;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class DataTableDiagramParsInJson {

    private List<Cols> cols;

    private List<Rows> rows;

    public DataTableDiagramParsInJson(List<Cols> cols, List<Rows> rows) {
        this.cols = cols;
        this.rows = rows;
    }

}
