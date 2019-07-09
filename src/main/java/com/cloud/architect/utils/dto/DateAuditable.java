package com.cloud.architect.utils.dto;

import java.util.Date;

public interface DateAuditable {
    /**
     * 获取数据的创建时间
     *
     * @return 创建时间
     */
    Date getCreatedDate();

    /**
     * 获取数据的修改时间
     *
     * @return 修改时间
     */
    Date getLastModifiedDate();
}
