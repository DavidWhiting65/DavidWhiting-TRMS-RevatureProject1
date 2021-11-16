package com.revature.services;

import com.revature.models.Employee;
import com.revature.models.Reimbursement;
import com.revature.repositories.ReimbursementRepo;

import java.util.List;

public class ReimbursementService {

    {System.out.println("in ReimbursementService class");}

    ReimbursementRepo reimbursementRepo = new ReimbursementRepo();

    public Reimbursement createReimbursement(Reimbursement r) {
        {System.out.println("in createReimbursement() ReimbursementService class");}
            return reimbursementRepo.add(r);
    }

    public Reimbursement searchReimbursementById(Integer id) {
        return reimbursementRepo.getById(id);
    }

    public List<Reimbursement> searchReimbursementByEmployeeId(Integer id) {
        return reimbursementRepo.getByEmployeeId(id);
    }

    public List<Employee> getEmployeeDataByEmployeeIdService(Integer id) {
        return reimbursementRepo.getEmployeeDataByEmployeeId(id);
    }

    public List<Reimbursement> searchSupervisedPendingReimbursements(Integer id) {
        return reimbursementRepo.getSupervisedPendingReimbursements(id);
    }

    public List<Reimbursement> searchSupervisedReimbursements(Integer id) {
        return reimbursementRepo.getSupervisedReimbursements(id);
    }

    public List<Reimbursement> getAllReimbursements() {
        return reimbursementRepo.getAll();
    }

    public List<Reimbursement> getDirectSupervisorApprovedPendingReimbursementsService() {
        return reimbursementRepo.getDirectSupervisorApprovedPendingReimbursements();
    }

    public List<Reimbursement> getPendingReimbursementsService() {
        return reimbursementRepo.getPendingReimbursements();
    }

    public List<Reimbursement> getUrgentReimbursementsService() {
        return reimbursementRepo.getUrgentReimbursements();
    }

    public List<Reimbursement> getDirectSupervisorAndDepartmentHeadApprovedPendingReimbursementsService() {
        return reimbursementRepo.getDirectSupervisorAndDepartmentHeadApprovedPendingReimbursements();
    }

    public void updateReimbursement(Reimbursement r) {
        if (reimbursementRepo.getById(r.getReimbursementId()) != null) {
            reimbursementRepo.update(r);
        } else {
            System.out.println("That reimbursement does not exist...");
        }
    }

    public void uploadGradeService(Reimbursement r) {
        if (reimbursementRepo.getById(r.getReimbursementId()) != null) {
            reimbursementRepo.uploadGrade(r);
        } else {
            System.out.println("That reimbursement does not exist...");
        }
    }

    public void uploadAdditionalInfoService(Reimbursement r) {
        if (reimbursementRepo.getById(r.getReimbursementId()) != null) {
            reimbursementRepo.uploadAdditionalInfo(r);
        } else {
            System.out.println("That reimbursement does not exist...");
        }
    }

    public void updateAvailableBalanceService(Reimbursement r) {
        if (reimbursementRepo.getByEmployeeId(r.getEmployeeId()) != null) {
            reimbursementRepo.updateAvailableBalance(r);
        } else {
            System.out.println("That reimbursement does not exist...");
        }
    }

    public void uploadPresentationService(Reimbursement r) {
        if (reimbursementRepo.getById(r.getReimbursementId()) != null) {
            reimbursementRepo.uploadPresentation(r);
        } else {
            System.out.println("That reimbursement does not exist...");
        }
    }

    public void requestAddtionalInfoService(Reimbursement r) {
        if (reimbursementRepo.getById(r.getReimbursementId()) != null) {
            reimbursementRepo.requestAdditionalInfo(r);
        } else {
            System.out.println("That reimbursement does not exist...");
        }
    }

    public void approveReimbursementDirectSupervisorService(Reimbursement r) {
        if (reimbursementRepo.getById(r.getReimbursementId()) != null) {
            reimbursementRepo.approveReimbursementDirectSupervisor(r);
        } else {
            System.out.println("That reimbursement does not exist...");
        }
    }

    public void approveReimbursementDepartmentHeadService(Reimbursement r) {
        if (reimbursementRepo.getById(r.getReimbursementId()) != null) {
            reimbursementRepo.approveReimbursementDepartmentHead(r);
        } else {
            System.out.println("That reimbursement does not exist...");
        }
    }

    public void adjustAmountService(Reimbursement r) {
        if (reimbursementRepo.getById(r.getReimbursementId()) != null) {
            reimbursementRepo.adjustAmount(r);
        } else {
            System.out.println("That reimbursement does not exist...");
        }
    }

    public void declineReimbursementService(Reimbursement r) {
        if (reimbursementRepo.getById(r.getReimbursementId()) != null) {
            reimbursementRepo.declineReimbursement(r);
        } else {
            System.out.println("That reimbursement does not exist...");
        }
    }

    public void awardReimbursementService(Reimbursement r) {
        if (reimbursementRepo.getById(r.getReimbursementId()) != null) {
            reimbursementRepo.awardReimbursement(r);
        } else {
            System.out.println("That reimbursement does not exist...");
        }
    }

    public void deleteReimbursement(Integer id) {
        reimbursementRepo.delete(id);
    }

}
