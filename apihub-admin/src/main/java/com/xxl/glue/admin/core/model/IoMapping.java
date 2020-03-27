package com.xxl.glue.admin.core.model;

import lombok.Data;

import java.util.Date;

@Data
public class IoMapping {

    private int id;
    private int projectId;
    private int interfaceId;
    private String inputRule;
    private String outputRule;
    private Date addTime;
    private String interfaceName;
    private String interfaceCode;
    private String projectName;

}
