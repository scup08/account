package com.lzh.account.model.dto.response;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.common.collect.ImmutableList;
import com.lzh.common.model.dto.response.RestfulResponse;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Zhao Junjian
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class ObjectCollectionResponse<T> extends RestfulResponse {
    private static final long serialVersionUID = 1862906172390850647L;

    private Collection<T> dataSet;

    public ObjectCollectionResponse(Collection<T> dataSet) {
        this.dataSet = ImmutableList.copyOf(dataSet);
    }
}
