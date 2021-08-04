package com.smartbr.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * 订单退款
 */
public class BrOrderRefund implements Serializable {
    /**
     * 订单退款ID
     */
    private Long orderRefundId;

    /**
     * 预约订单ID
     */
    private Long orderId;

    /**
     * 退款名称
     */
    private String refundName;

    /**
     * 退款单号，用于微信退款
     */
    private String refundTradeNo;

    /**
     * 退款金额(元)
     */
    private Long refundAmount;

    /**
     * 退款备注
     */
    private String refundNote;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 退款时间
     */
    private Date refundTime;

    private static final long serialVersionUID = 1L;

    public Long getOrderRefundId() {
        return orderRefundId;
    }

    public void setOrderRefundId(Long orderRefundId) {
        this.orderRefundId = orderRefundId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getRefundName() {
        return refundName;
    }

    public void setRefundName(String refundName) {
        this.refundName = refundName;
    }

    public String getRefundTradeNo() {
        return refundTradeNo;
    }

    public void setRefundTradeNo(String refundTradeNo) {
        this.refundTradeNo = refundTradeNo;
    }

    public Long getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Long refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundNote() {
        return refundNote;
    }

    public void setRefundNote(String refundNote) {
        this.refundNote = refundNote;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        BrOrderRefund other = (BrOrderRefund) that;
        return (this.getOrderRefundId() == null ? other.getOrderRefundId() == null : this.getOrderRefundId().equals(other.getOrderRefundId()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getRefundName() == null ? other.getRefundName() == null : this.getRefundName().equals(other.getRefundName()))
            && (this.getRefundTradeNo() == null ? other.getRefundTradeNo() == null : this.getRefundTradeNo().equals(other.getRefundTradeNo()))
            && (this.getRefundAmount() == null ? other.getRefundAmount() == null : this.getRefundAmount().equals(other.getRefundAmount()))
            && (this.getRefundNote() == null ? other.getRefundNote() == null : this.getRefundNote().equals(other.getRefundNote()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getRefundTime() == null ? other.getRefundTime() == null : this.getRefundTime().equals(other.getRefundTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOrderRefundId() == null) ? 0 : getOrderRefundId().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getRefundName() == null) ? 0 : getRefundName().hashCode());
        result = prime * result + ((getRefundTradeNo() == null) ? 0 : getRefundTradeNo().hashCode());
        result = prime * result + ((getRefundAmount() == null) ? 0 : getRefundAmount().hashCode());
        result = prime * result + ((getRefundNote() == null) ? 0 : getRefundNote().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getRefundTime() == null) ? 0 : getRefundTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderRefundId=").append(orderRefundId);
        sb.append(", orderId=").append(orderId);
        sb.append(", refundName=").append(refundName);
        sb.append(", refundTradeNo=").append(refundTradeNo);
        sb.append(", refundAmount=").append(refundAmount);
        sb.append(", refundNote=").append(refundNote);
        sb.append(", createTime=").append(createTime);
        sb.append(", refundTime=").append(refundTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}