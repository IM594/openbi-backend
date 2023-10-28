package com.elec5619.bi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.elec5619.bi.model.entity.Chart;
import com.elec5619.bi.service.ChartService;
import com.elec5619.bi.mapper.ChartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author luzhaohao
* @description 针对表【chart(图表信息表)】的数据库操作Service实现
*/
@Service
public class ChartServiceImpl extends ServiceImpl<ChartMapper, Chart>
    implements ChartService{

    @Autowired
    ChartMapper chartMapper;

    @Override
    public List<Chart> getChartByUserId(long id) {
        return chartMapper.getChartByUserId(id);
    }
}




