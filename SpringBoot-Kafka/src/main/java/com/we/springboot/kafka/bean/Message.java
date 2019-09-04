package com.we.springboot.kafka.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


/**
 * @author we
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message implements Serializable {

    private static final long serialVersionUID = -6241333516056573905L;

    private String message;

    private Date createTime;
}
