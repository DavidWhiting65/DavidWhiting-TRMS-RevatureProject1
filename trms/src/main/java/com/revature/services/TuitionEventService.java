package com.revature.services;

import com.revature.models.TuitionEvent;
import com.revature.repositories.TuitionEventRepo;

import java.util.List;

public class TuitionEventService {
    {System.out.println("in TuitionEventService class");}

    TuitionEventRepo tuitionEventRepo = new TuitionEventRepo();

    public TuitionEvent createTuitionEvent(TuitionEvent a) {
        return tuitionEventRepo.add(a);
    }

    public TuitionEvent searchTuitionEventById(Integer id) {
        return tuitionEventRepo.getById(id);
    }

    public List<TuitionEvent> getAllTuitionEvents() {
        return tuitionEventRepo.getAll();
    }

    public void updateTuitionEvent(TuitionEvent a) {
        tuitionEventRepo.update(a);
    }

    public void deleteTuitionEvent(Integer id) {
        tuitionEventRepo.delete(id);
    }

}
