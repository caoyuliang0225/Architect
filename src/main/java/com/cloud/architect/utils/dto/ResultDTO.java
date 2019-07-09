package com.cloud.architect.utils.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * 返回结果
 */
@SuppressWarnings("unused")
public class ResultDTO<T> {
    /**
     * 请求的处理结果状态
     */
    public enum Status {
        /**
         * 成功 或 失败
         */
        success, failure
    }

    /**
     * 处理结果状态
     */
    private final Status status;

    /**
     * 错误消息
     */
    private Error[] errors;

    /**
     * 对象数据
     */
    private T data;

    ///////////////////////////////////////
    // Getter
    ///////////////////////////////////////
    /*
        @JsonProperty 此注解用于属性上，作用是把该属性的名称序列化为另外一个名称  index 返回顺序数值越小越靠前
     */
    @JsonProperty(value = "status", index = 0)
    public Status getStatus() {
        return this.status;
    }

    /*
     @JsonInclude可以返回制定格式的json数据  ： 如果属性返回值为空，则不返回任何内容
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty(value = "errors", index = 1)
    public Error[] getErrors() {
        return this.errors;
    }

    /*
     @JsonInclude可以返回制定格式的json数据  ： 如果属性返回值为空，则不返回任何内容
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "data", index = 2)
    public Object getData() {
        return this.data;
    }

    @JsonIgnore
    public T getRawData() {
        return this.data;
    }

    ///////////////////////////////////////
    // Setter
    ///////////////////////////////////////
    @JsonIgnore
    protected ResultDTO<T> setErrors(final Error... errors) {
        this.errors = errors;
        return this;
    }


    /*
    @JsonIgnore作用是json序列化时将java bean中的一些属性忽略掉，序列化和反序列化都受影响。
    此注解用于属性或者方法上（最好是属性上），作用和@JsonIgnoreProperties一样。
     */
    @JsonIgnore
    private ResultDTO<T> setData(final T data) {
        this.data = data;
        return this;
    }

    ///////////////////////////////////////
    // Constructor
    ///////////////////////////////////////
    /*
    当json在反序列化时，默认选择类的无参构造函数创建类对象，当没有无参构造函数时会报错，@JsonCreator作用就是指定反序列化时用的无参构造函数。
    构造方法的参数前面需要加上@JsonProperty,否则会报错。
     */
    @JsonCreator
    public ResultDTO(
            @JsonProperty("status") final Status status,
            @JsonProperty("errors") final Error[] errors,
            @JsonProperty("data") final T data
    ) {
        this.status = status;
        this.data = data;
        this.errors = errors;
    }

    ResultDTO(Status status) {
        this.status = status;
    }

    ///////////////////////////////////////
    // Builder
    ///////////////////////////////////////
    public static ResultDTO<Void> success() {
        return new ResultDTO<>(Status.success);
    }

    public static ResultDTO<Void> failure(final Error... errors) {
        return new ResultDTO<Void>(Status.failure).setErrors(errors);
    }

    public static <T> ResultDTO<T> success(final T data) {
        return new ResultDTO<T>(Status.success).setData(data);
    }

    public static <T> ResultDTO<T> failure(final T data, final Error... errors) {
        return new ResultDTO<T>(Status.failure).setData(data).setErrors(errors);
    }

    ///////////////////////////////////////
    // Error
    ///////////////////////////////////////
    public static Error error(final String message, final String field) {
        return new Error(message, field);
    }

    public static Error error(final String code, final String message, final String field) {
        return new Error(code, message, field);
    }

    /**
     * 错误结果
     */
    @Getter
    @Setter(value = AccessLevel.NONE)
    public static final class Error {
        /**
         * 错误编码
         */
        @JsonProperty("code")
        private String code;

        /**
         * 错误消息
         */
        @JsonProperty("message")
        private String message;

        /**
         * 错误关联的字段
         */
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @JsonProperty("field")
        private String field;

        ///////////////////////////////////////
        // Constructor
        ///////////////////////////////////////
        private Error(final String message, final String field) {
            this.field = field;
            this.message = message;
        }

        @JsonCreator
        private Error(
                @JsonProperty("code") final String code,
                @JsonProperty("msg") final String message,
                @JsonProperty("field") final String field
        ) {
            this.field = field;
            this.code = code;
            this.message = message;
        }

        @Override
        public String toString() {
            return String.format("{code: %s, message: %s, field: %s}", this.code, this.message, this.field);
        }
    }
}
