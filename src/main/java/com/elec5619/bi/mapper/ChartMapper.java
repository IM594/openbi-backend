package com.elec5619.bi.mapper;

import com.elec5619.bi.model.entity.Chart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author luzhaohao
*/
@Repository
public interface ChartMapper extends BaseMapper<Chart> {
    List<Chart> getChartByUserId(long userId);
}




