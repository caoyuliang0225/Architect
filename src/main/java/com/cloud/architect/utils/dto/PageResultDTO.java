package com.cloud.architect.utils.dto;

import com.cloud.architect.exception.CustomException;
import com.fasterxml.jackson.annotation.*;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public class PageResultDTO<T> extends ResultDTO<T> {
    /**
     * 分页数据
     */
    private Page<T> data;

    ///////////////////////////////////////
    // Getter
    ///////////////////////////////////////
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "data", index = 2)
    @Override
    public List<T> getData() {
        return this.data != null ? this.data.getContent() : null;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "pageable", index = 3)
    @JsonIgnoreProperties({"content", "sort"})
    public Page<T> getPageable() {
        return this.data;
    }

    ///////////////////////////////////////
    // Setter
    ///////////////////////////////////////
    @JsonIgnore
    protected PageResultDTO<T> setErrors(final Error... errors) {
        super.setErrors(errors);
        return this;
    }

    @JsonIgnore
    private PageResultDTO<T> setData(final Page<T> data) {
        this.data = data;
        return this;
    }

    ///////////////////////////////////////
    // Constructor
    ///////////////////////////////////////
    @JsonCreator
    public PageResultDTO(
            @JsonProperty("status") final Status status,
            @JsonProperty("errors") final Error[] errors,
            @JsonProperty("pageable") final Page<T> data,
            @JsonProperty("timestamp") final Date timestamp
    ) {
        super(status);
        this.setErrors(errors);
        this.setData(data);
    }

    PageResultDTO(final Status status) {
        super(status);
    }

    ///////////////////////////////////////
    // Builder
    ///////////////////////////////////////
    public static <T> PageResultDTO<T> success(final Page<T> pageData) {
        if (pageData == null) {
            throw new CustomException("NullPointerException", "The formal parameter 'pageData' cannot be null");
        }

        return new PageResultDTO<T>(Status.success).setData(pageData);
    }

    public static <T> PageResultDTO<T> failure(final Page<T> pageData, final Error... errors) {

        return new PageResultDTO<T>(Status.failure).setData(pageData).setErrors(errors);
    }
}

