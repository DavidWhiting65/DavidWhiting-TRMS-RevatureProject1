package com.revature.models;

import java.sql.Date;

public class Reimbursement {

    public int reimbursementId;
    public int employeeId;
    public Date claimDate;
    public Date eventStartDate;
    public Date eventEndDate;
    public float cost;
    public float claimAmount;
    public float approvedAmount;
    public String eventLocation;
    public String tuitionType;
    public String workJustification;
    public String grade;
    public String directSupervisorMessage;
    public boolean isPending;
    public boolean isAwarded;
    public boolean isDeclined;
    public boolean isArchived;
    public String eventName;
    public String additionalInfo;
    public boolean isPresentationUploaded;
    public boolean isDirectSupervisorApproved;
    public boolean isDepartmentHeadApproved;
    public boolean isRequestAdditionalInfo;
    public int directSupervisorId;
    public boolean isUrgent;
    public boolean isDepartmentHead;
    public float availableBalance;

    // constructors
    public Reimbursement() {
    }

    public Reimbursement(int reimbursementId, int employeeId, Date claimDate,
                         Date eventStartDate, Date eventEndDate, float cost,
                         float claimAmount, float approved_amount, String eventLocation,
                         String tuitionType, String workJustification, String grade,
                         String directSupervisorMessage, boolean isPending, boolean isAwarded,
                         boolean isDeclined, boolean isArchived, String eventName, String additionalInfo,
                         boolean isPresentationUploaded, boolean isDirectSupervisorApproved,
                         boolean isDepartmentHeadApproved, boolean isRequestAdditionalInfo, int directSupervisorId,
                         boolean isUrgent, boolean isDepartmentHead, float availableBalance) {
        this.reimbursementId = reimbursementId;
        this.employeeId = employeeId;
        this.claimDate = claimDate;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.cost = cost;
        this.claimAmount = claimAmount;
        this.approvedAmount = approved_amount;
        this.eventLocation = eventLocation;
        this.tuitionType = tuitionType;
        this.workJustification = workJustification;
        this.grade = grade;
        this.directSupervisorMessage = directSupervisorMessage;
        this.isPending = isPending;
        this.isAwarded = isAwarded;
        this.isDeclined = isDeclined;
        this.isArchived = isArchived;
        this.eventName = eventName;
        this.additionalInfo = additionalInfo;
        this.isPresentationUploaded = isPresentationUploaded;
        this.isDirectSupervisorApproved = isDirectSupervisorApproved;
        this.isDepartmentHeadApproved = isDepartmentHeadApproved;
        this.isRequestAdditionalInfo = isRequestAdditionalInfo;
        this.directSupervisorId = directSupervisorId;
        this.isUrgent = isUrgent;
        this.isDepartmentHead = isDepartmentHead;
        this.availableBalance = availableBalance;
    }

    // getters and setters
    public int getReimbursementId() {
        return reimbursementId;
    }

    public void setReimbursementId(int reimbursementId) {
        this.reimbursementId = reimbursementId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public Date getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(Date eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public Date getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(Date eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(float claimAmount) {
        this.claimAmount = claimAmount;
    }

    public float getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(float approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getTuitionType() {
        return tuitionType;
    }

    public void setTuitionType(String tuitionType) {
        this.tuitionType = tuitionType;
    }

    public String getWorkJustification() {
        return workJustification;
    }

    public void setWorkJustification(String workJustification) {
        this.workJustification = workJustification;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDirectSupervisorMessage() {
        return directSupervisorMessage;
    }

    public void setDirectSupervisorMessage(String directSupervisorMessage) {
        this.directSupervisorMessage = directSupervisorMessage;
    }

    public boolean getIsPending() {
        return isPending;
    }

    public void setIsPending(boolean isPending) {
        this.isPending = isPending;
    }

    public boolean getIsAwarded() {
        return isAwarded;
    }

    public void setIsAwarded(boolean isAwarded) {
        this.isAwarded = isAwarded;
    }

    public boolean getIsDeclined() {
        return isDeclined;
    }

    public void setIsDeclined(boolean isDeclined) {
        this.isDeclined = isDeclined;
    }

    public boolean getIsArchived() {
        return isArchived;
    }

    public void setIsArchived(boolean isArchived) {
        this.isArchived = isArchived;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public boolean getIsPresentationUploaded() {
        return isPresentationUploaded;
    }

    public void setIsPresentationUploaded(boolean isPresentationUploaded) {
        this.isPresentationUploaded = isPresentationUploaded;
    }

    public boolean getIsDirectSupervisorApproved() {
        return isDirectSupervisorApproved;
    }

    public void setIsDirectSupervisorApproved(boolean isDirectSupervisorApproved) {
        this.isDirectSupervisorApproved = isDirectSupervisorApproved;
    }

    public boolean getIsDepartmentHeadApproved() {
        return isDepartmentHeadApproved;
    }

    public void setIsDepartmentHeadApproved(boolean isDepartmentHeadApproved) {
        this.isDepartmentHeadApproved = isDepartmentHeadApproved;
    }

    public boolean getIsRequestAdditionalInfo() {
        return isRequestAdditionalInfo;
    }

    public void setIsRequestAdditionalInfo(boolean isRequestAdditionalInfo) {
        this.isRequestAdditionalInfo = isRequestAdditionalInfo;
    }

    public int getDirectSupervisorId() {
        return directSupervisorId;
    }

    public void setDirectSupervisorId(int directSupervisorId) {
        this.directSupervisorId = directSupervisorId;
    }

    public boolean getIsUrgent() {
        return isUrgent;
    }

    public void setIsUrgent(boolean isUrgent) {
        this.isUrgent = isUrgent;
    }

    public boolean getIsDepartmentHead() {
        return isDepartmentHead;
    }

    public void setIsDepartmentHead(boolean isDepartmentHead) {
        this.isDepartmentHead = isDepartmentHead;
    }

    public float getavailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(float availableBalance) {
        this.availableBalance = availableBalance;
    }


    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbursementId=" + reimbursementId +
                ", employeeId=" + employeeId +
                ", claimDate=" + claimDate +
                ", eventStartDate=" + eventStartDate +
                ", eventEndDate=" + eventEndDate +
                ", cost=" + cost +
                ", claimAmount=" + claimAmount +
                ", approved_amount=" + approvedAmount +
                ", eventLocation='" + eventLocation + '\'' +
                ", tuitionType=" + tuitionType + '\'' +
                ", workJustification=" + workJustification + '\'' +
                ", grade=" + grade + '\'' +
                ", directSupervisorMessage=" + directSupervisorMessage + '\'' +
                ", isPending=" + isPending +
                ", isAwarded=" + isAwarded +
                ", isDeclined=" + isDeclined +
                ", isArchived=" + isArchived +
                ", eventName=" + eventName +
                ", additionalInfo=" + additionalInfo +
                ", isPresentationUploaded=" + isPresentationUploaded +'\'' +
                ", isDirectSupervisorApproved=" + isDirectSupervisorApproved +'\'' +
                ", isDepartmentHeadApproved=" + isDepartmentHeadApproved +'\'' +
                ", isRequestAdditionalInfo=" + isRequestAdditionalInfo +'\'' +
                ", directSupervisorId=" + directSupervisorId +'\'' +
                ", isUrgent=" + isUrgent +'\'' +
                ", is_department_head=" + isDepartmentHead +'\'' +
                ", available_balance=" + availableBalance +'\'' +
                '}';
    }
}
