package org.tryout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tryout.repository.CellPhoneRepository;

@Service
public class CellPhoneService implements BaseService {

    private CellPhoneRepository repository;

    @Autowired
    public CellPhoneService(CellPhoneRepository repository) {
        this.repository = repository;
    }

    @Override
    public CellPhoneRepository getRepository() {
        return repository;
    }

    public long getCount() {
        return repository.count();
    }
}
