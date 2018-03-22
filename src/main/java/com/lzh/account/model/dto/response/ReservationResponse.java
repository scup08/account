package com.lzh.account.model.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.lzh.account.model.dto.Participant;
import com.lzh.common.model.dto.response.Response;

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
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class ReservationResponse implements Response {

    private static final long serialVersionUID = 841299264931794001L;

//    @ApiModelProperty(value = "参与者确认资源", required = true)
    private Participant participantLink;

    public ReservationResponse(Participant participant) {
        this.participantLink = participant;
    }

}
