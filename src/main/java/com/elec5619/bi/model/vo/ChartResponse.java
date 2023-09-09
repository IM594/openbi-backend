package com.elec5619.bi.model.vo;

import lombok.Data;

/**
 * 图表返回给前端的结果
 */

@Data
public class ChartResponse {

    /**
     * 图表代码
     */
    private String genChart;

    /**
     * 分析结论
     */
    private String genResult;

    /**
     * 图表id
     */
    private Long chartId;
}

