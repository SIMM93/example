package com.smartbr.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 * 预约订单
 */
public class BrReserveOrder implements Serializable {
    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 交易订单号，用于微信支付
     */
    private String orderTradeNo;

    /**
     * 线路ID
     */
    private Long lineId;

    /**
     * 预约调度ID
     */
    private Long schedulingId;

    /**
     * 预约人数
     */
    private Integer reservaCount;

    /**
     * 乘车时间
     */
    private String ridingTime;

    /**
     * 总金额(元)
     */
    private Long totalAmount;

    /**
     * 优惠金额(元)
     */
    private Long discountAmount;

    /**
     * 实付金额(元)
     */
    private Long paidAmount;

    /**
     * 订单状态（0：预约中；1：预约失败；2：退款中；3：已退款；4：退款失败；5：待使用；6：已使用）
     */
    private Integer orderState;

    /**
     * 操作失败的返回信息
     */
    private String failMsg;

    /**
     * 乘车码
     */
    private String rideCode;

    /**
     * 乘车码状态（0：待使用；1：已使用）,公交核销成功设置为1
     */
    private Integer rideCodeState;

    /**
     * 景点门票码状态（0：待使用；1：已使用）,景点门票核销成功设置为1
     */
    private Integer scenicCodeState;

    /**
     * 乘车码核销时间
     */
    private Date rideCodeUseTime;

    /**
     * 景点门票码核销时间
     */
    private Date scenicCodeUseTime;

    /**
     * 是否可退款(0：否；1：是)
     */
    private Integer isCanRefund;

    /**
     * 可退金额(元)
     */
    private Long refundAmount;

    /**
     * 退款说明
     */
    private String refundNote;

    /**
     * 所属单位
     */
    private Long deptId;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderTradeNo() {
        return orderTradeNo;
    }

    public void setOrderTradeNo(String orderTradeNo) {
        this.orderTradeNo = orderTradeNo;
    }

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public Long getSchedulingId() {
        return schedulingId;
    }

    public void setSchedulingId(Long schedulingId) {
        this.schedulingId = schedulingId;
    }

    public Integer getReservaCount() {
        return reservaCount;
    }

    public void setReservaCount(Integer reservaCount) {
        this.reservaCount = reservaCount;
    }

    public String getRidingTime() {
        return ridingTime;
    }

    public void setRidingTime(String ridingTime) {
        this.ridingTime = ridingTime;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Long discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Long getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Long paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public String getFailMsg() {
        return failMsg;
    }

    public void setFailMsg(String failMsg) {
        this.failMsg = failMsg;
    }

    public String getRideCode() {
        return rideCode;
    }

    public void setRideCode(String rideCode) {
        this.rideCode = rideCode;
    }

    public Integer getRideCodeState() {
        return rideCodeState;
    }

    public void setRideCodeState(Integer rideCodeState) {
        this.rideCodeState = rideCodeState;
    }

    public Integer getScenicCodeState() {
        return scenicCodeState;
    }

    public void setScenicCodeState(Integer scenicCodeState) {
        this.scenicCodeState = scenicCodeState;
    }

    public Date getRideCodeUseTime() {
        return rideCodeUseTime;
    }

    public void setRideCodeUseTime(Date rideCodeUseTime) {
        this.rideCodeUseTime = rideCodeUseTime;
    }

    public Date getScenicCodeUseTime() {
        return scenicCodeUseTime;
    }

    public void setScenicCodeUseTime(Date scenicCodeUseTime) {
        this.scenicCodeUseTime = scenicCodeUseTime;
    }

    public Integer getIsCanRefund() {
        return isCanRefund;
    }

    public void setIsCanRefund(Integer isCanRefund) {
        this.isCanRefund = isCanRefund;
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

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        BrReserveOrder other = (BrReserveOrder) that;
        return (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getOrderTradeNo() == null ? other.getOrderTradeNo() == null : this.getOrderTradeNo().equals(other.getOrderTradeNo()))
            && (this.getLineId() == null ? other.getLineId() == null : this.getLineId().equals(other.getLineId()))
            && (this.getSchedulingId() == null ? other.getSchedulingId() == null : this.getSchedulingId().equals(other.getSchedulingId()))
            && (this.getReservaCount() == null ? other.getReservaCount() == null : this.getReservaCount().equals(other.getReservaCount()))
            && (this.getRidingTime() == null ? other.getRidingTime() == null : this.getRidingTime().equals(other.getRidingTime()))
            && (this.getTotalAmount() == null ? other.getTotalAmount() == null : this.getTotalAmount().equals(other.getTotalAmount()))
            && (this.getDiscountAmount() == null ? other.getDiscountAmount() == null : this.getDiscountAmount().equals(other.getDiscountAmount()))
            && (this.getPaidAmount() == null ? other.getPaidAmount() == null : this.getPaidAmount().equals(other.getPaidAmount()))
            && (this.getOrderState() == null ? other.getOrderState() == null : this.getOrderState().equals(other.getOrderState()))
            && (this.getFailMsg() == null ? other.getFailMsg() == null : this.getFailMsg().equals(other.getFailMsg()))
            && (this.getRideCode() == null ? other.getRideCode() == null : this.getRideCode().equals(other.getRideCode()))
            && (this.getRideCodeState() == null ? other.getRideCodeState() == null : this.getRideCodeState().equals(other.getRideCodeState()))
            && (this.getScenicCodeState() == null ? other.getScenicCodeState() == null : this.getScenicCodeState().equals(other.getScenicCodeState()))
            && (this.getRideCodeUseTime() == null ? other.getRideCodeUseTime() == null : this.getRideCodeUseTime().equals(other.getRideCodeUseTime()))
            && (this.getScenicCodeUseTime() == null ? other.getScenicCodeUseTime() == null : this.getScenicCodeUseTime().equals(other.getScenicCodeUseTime()))
            && (this.getIsCanRefund() == null ? other.getIsCanRefund() == null : this.getIsCanRefund().equals(other.getIsCanRefund()))
            && (this.getRefundAmount() == null ? other.getRefundAmount() == null : this.getRefundAmount().equals(other.getRefundAmount()))
            && (this.getRefundNote() == null ? other.getRefundNote() == null : this.getRefundNote().equals(other.getRefundNote()))
            && (this.getDeptId() == null ? other.getDeptId() == null : this.getDeptId().equals(other.getDeptId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getOrderTradeNo() == null) ? 0 : getOrderTradeNo().hashCode());
        result = prime * result + ((getLineId() == null) ? 0 : getLineId().hashCode());
        result = prime * result + ((getSchedulingId() == null) ? 0 : getSchedulingId().hashCode());
        result = prime * result + ((getReservaCount() == null) ? 0 : getReservaCount().hashCode());
        result = prime * result + ((getRidingTime() == null) ? 0 : getRidingTime().hashCode());
        result = prime * result + ((getTotalAmount() == null) ? 0 : getTotalAmount().hashCode());
        result = prime * result + ((getDiscountAmount() == null) ? 0 : getDiscountAmount().hashCode());
        result = prime * result + ((getPaidAmount() == null) ? 0 : getPaidAmount().hashCode());
        result = prime * result + ((getOrderState() == null) ? 0 : getOrderState().hashCode());
        result = prime * result + ((getFailMsg() == null) ? 0 : getFailMsg().hashCode());
        result = prime * result + ((getRideCode() == null) ? 0 : getRideCode().hashCode());
        result = prime * result + ((getRideCodeState() == null) ? 0 : getRideCodeState().hashCode());
        result = prime * result + ((getScenicCodeState() == null) ? 0 : getScenicCodeState().hashCode());
        result = prime * result + ((getRideCodeUseTime() == null) ? 0 : getRideCodeUseTime().hashCode());
        result = prime * result + ((getScenicCodeUseTime() == null) ? 0 : getScenicCodeUseTime().hashCode());
        result = prime * result + ((getIsCanRefund() == null) ? 0 : getIsCanRefund().hashCode());
        result = prime * result + ((getRefundAmount() == null) ? 0 : getRefundAmount().hashCode());
        result = prime * result + ((getRefundNote() == null) ? 0 : getRefundNote().hashCode());
        result = prime * result + ((getDeptId() == null) ? 0 : getDeptId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", orderTradeNo=").append(orderTradeNo);
        sb.append(", lineId=").append(lineId);
        sb.append(", schedulingId=").append(schedulingId);
        sb.append(", reservaCount=").append(reservaCount);
        sb.append(", ridingTime=").append(ridingTime);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", discountAmount=").append(discountAmount);
        sb.append(", paidAmount=").append(paidAmount);
        sb.append(", orderState=").append(orderState);
        sb.append(", failMsg=").append(failMsg);
        sb.append(", rideCode=").append(rideCode);
        sb.append(", rideCodeState=").append(rideCodeState);
        sb.append(", scenicCodeState=").append(scenicCodeState);
        sb.append(", rideCodeUseTime=").append(rideCodeUseTime);
        sb.append(", scenicCodeUseTime=").append(scenicCodeUseTime);
        sb.append(", isCanRefund=").append(isCanRefund);
        sb.append(", refundAmount=").append(refundAmount);
        sb.append(", refundNote=").append(refundNote);
        sb.append(", deptId=").append(deptId);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}