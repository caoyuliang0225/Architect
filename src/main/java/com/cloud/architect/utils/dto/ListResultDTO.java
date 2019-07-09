package com.cloud.architect.utils.dto;

import com.cloud.architect.exception.CustomException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListResultDTO<T> extends ResultDTO<T> {
    /**
     * 最新数据的时间戳（可选）
     */
    private Date timestamp;

    /**
     * 列表数据
     */
    private List<T> data;

    ///////////////////////////////////////
    // Getter
    ///////////////////////////////////////
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "timestamp", index = 2)
    public Date getTimestamp() {
        return this.timestamp;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "data", index = 3)
    @Override
    public List<T> getData() {
        return this.data;
    }

    ///////////////////////////////////////
    // Setter
    ///////////////////////////////////////
    @JsonIgnore
    private void setTimestamp(final Date timestamp) {
        this.timestamp = timestamp;
    }

    @JsonIgnore
    protected ListResultDTO<T> setErrors(final Error... errors) {
        super.setErrors(errors);
        return this;
    }

    @JsonIgnore
    private ListResultDTO<T> setData(final List<T> data) {
        this.data = data;
        return this;
    }

    ///////////////////////////////////////
    // Constructor
    ///////////////////////////////////////
    @JsonCreator
    public ListResultDTO(
            @JsonProperty("status") final Status status,
            @JsonProperty("errors") final Error[] errors,
            @JsonProperty("data") final List<T> data,
            @JsonProperty("timestamp") final Date timestamp
    ) {
        super(status);
        this.setErrors(errors);
        this.setData(data);
        this.timestamp = timestamp;
    }

    ListResultDTO(final Status status) {
        super(status);
    }

    /**
     * 根据时间戳过滤包含审计时间的集合类数据
     * <br>
     * 目前只处理包含多条记录且不含分页信息的集合类数据，过滤后的结果只包含比过滤时间戳更新的数据。 单记录的数据无须过滤；
     * 含分页信息的集合类数据如果也进行过滤会导致分页信息不准确。
     *
     * @param timestamp 用于过滤的时间戳
     * @return 过滤后的 ListResultDTO 对象
     */
    public ListResultDTO<T> filter(final Date timestamp) {
        final List<DateAuditable> collectors = (List<DateAuditable>) this.data;
        final Optional<Date> lastModifiedDate = collectors.stream()
                .filter(p -> p.getLastModifiedDate() != null)
                .map(DateAuditable::getLastModifiedDate)
                .max(Date::compareTo);
        if (lastModifiedDate.isPresent()) {
            this.setTimestamp(lastModifiedDate.get());

            final Date filterDate = (timestamp == null ? new Date(0) : timestamp);
            this.setData((List<T>) collectors.stream()
                    .filter(p -> p.getLastModifiedDate() != null)
                    .filter(p -> p.getLastModifiedDate().compareTo(filterDate) > 0)
                    .collect(Collectors.toList()));
        }

        return this;
    }

    ///////////////////////////////////////
    // Builder
    ///////////////////////////////////////
    public static <T> ListResultDTO<T> success(@NotNull final Stream<T> stream) {
        if (stream == null) {
            throw new CustomException("NullPointerException", "The formal parameter 'stream' cannot be null");
        }

        return new ListResultDTO<T>(Status.success).setData(stream.collect(Collectors.toList()));
    }

    public static <T> ListResultDTO<T> success(@NotNull final List<T> listData) {
        if (listData == null) {
            throw new CustomException("NullPointerException", "The formal parameter 'listData' cannot be null");
        }

        return new ListResultDTO<T>(Status.success).setData(listData);
    }

    public static <T> ListResultDTO<T> failure(final List<T> list, final Error... errors) {
        return new ListResultDTO<T>(Status.failure).setData(list).setErrors(errors);
    }
}