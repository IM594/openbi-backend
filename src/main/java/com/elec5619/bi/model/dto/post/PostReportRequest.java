package com.elec5619.bi.model.dto.post;

import lombok.Data;

import java.io.Serializable;

/**
 * 删除请求
 *
 * @author Zhaohao Lu
 */
@Data
public class PostReportRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}
