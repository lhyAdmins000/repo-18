package com.finance.loan.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoanAuditRequest {

    @NotBlank(message = "名称不能为空")
    @Size(max = 255)
    private String name;

    private String description;

    // TODO: add pagination support

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
