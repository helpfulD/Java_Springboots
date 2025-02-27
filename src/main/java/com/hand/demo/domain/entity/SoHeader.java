package com.hand.demo.domain.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;

import io.choerodon.mybatis.domain.AuditDomain;
import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hzero.boot.platform.lov.annotation.LovValue;

import java.util.Date;
import java.util.List;

/**
 * @author hpf 2021-07-19 10:31:46
 */
@ApiModel("")
@VersionAudit
@ModifyAudit
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Table(name = "hodr_so_header")
public class SoHeader extends AuditDomain {

    public static final String FIELD_SO_HEADER_ID = "soHeaderId";
    public static final String FIELD_ORDER_NUMBER = "orderNumber";
    public static final String FIELD_COMPANY_ID = "companyId";
    public static final String FIELD_ORDER_DATE = "orderDate";
    public static final String FIELD_ORDER_STATUS = "orderStatus";
    public static final String FIELD_CUSTOMER_ID = "customerId";

//
// 业务方法(按public protected private顺序排列)
// ------------------------------------------------------------------------------

//
// 数据库字段
// ------------------------------------------------------------------------------


    @ApiModelProperty("订单头ID(主键)")
    @Id
    @GeneratedValue
    private Long soHeaderId;
    @ApiModelProperty(value = "订单编号", required = true)
    @NotBlank
    private String orderNumber;
    @ApiModelProperty(value = "公司ID", required = true)
    @NotNull
    private Long companyId;
    @ApiModelProperty(value = "订单日期", required = true)
    @NotNull
    private Date orderDate;
    @ApiModelProperty(value = "订单状态", required = true)
    @NotBlank
    @LovValue
    private String orderStatus;
    @ApiModelProperty(value = "客户ID", required = true)
    @NotNull
    private Long customerId;

    @Transient
    private List<Company> companyList;

    @Transient
    private List<Customer> customerList;

    @Transient
    private List<SoLine> soLineList;

    @Transient
    @ApiModelProperty(value = "公司编号精确查询", required = false)
    private String companyNumber;

    @Transient
    @ApiModelProperty(value = "客户名称精确查询", required = false)
    private String customerName;

    @Transient
    @ApiModelProperty(value = "销售订单号模糊查询", required = false)
    private String orderNumberInput;

    @Transient
    @ApiModelProperty(value = "订单状态精确查询", required = false)
    private String orderStatusInput;

//
// 非数据库字段
// ------------------------------------------------------------------------------

//
// getter/setter
// ------------------------------------------------------------------------------

    /**
     * @return 订单头ID(主键)
     */
    public Long getSoHeaderId() {
        return soHeaderId;
    }

    public SoHeader setSoHeaderId(Long soHeaderId) {
        this.soHeaderId = soHeaderId;
        return this;
    }

    /**
     * @return 订单编号
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    public SoHeader setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    /**
     * @return 公司ID
     */
    public Long getCompanyId() {
        return companyId;
    }

    public SoHeader setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    /**
     * @return 订单日期
     */
    public Date getOrderDate() {
        return orderDate;
    }

    public SoHeader setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    /**
     * @return 订单状态
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    public SoHeader setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    /**
     * @return 客户ID
     */
    public Long getCustomerId() {
        return customerId;
    }

    public SoHeader setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    /**
     *
     * @return 客户对象列表
     */
    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    /**
     *
     * @return 公司对象列表
     */
    public List<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
    }

    /**
     *
     * @return订单详情列表
     */
    public List<SoLine> getSoLineList() {
        return soLineList;
    }

    public void setSoLineList(List<SoLine> soLineList) {
        this.soLineList = soLineList;
    }

    /**
     *
     * @return 公司编号
     */
    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    /**
     *
     * @return
     */
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     *
     * @return 订单编号输入
     */
    public String getOrderNumberInput() {
        return orderNumberInput;
    }

    public void setOrderNumberInput(String orderNumberInput) {
        this.orderNumberInput = orderNumberInput;
    }

    /**
     *
     * @return 订单状态输入
     */
    public String getOrderStatusInput() {
        return orderStatusInput;
    }

    public void setOrderStatusInput(String orderStatusInput) {
        this.orderStatusInput = orderStatusInput;
    }
}
