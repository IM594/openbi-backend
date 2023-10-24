package com.elec5619.bi.service;

import com.elec5619.bi.model.entity.Chart;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author luzhaohao
* @description 针对表【chart(图表信息表)】的数据库操作Service
*/
public interface ChartService extends IService<Chart> {
    List<Chart> getChartByUserId(long id);
}
