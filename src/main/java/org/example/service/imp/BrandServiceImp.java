package org.example.service.imp;

import org.example.model.BrandModel;
import org.example.repository.IBrandRepository;
import org.example.repository.imp.BrandRepositoryImp;
import org.example.service.IBrandService;

import java.util.List;

public class BrandServiceImp implements IBrandService {
    private IBrandRepository iBrandRepository=new BrandRepositoryImp();

    @Override
    public BrandModel findByBrandId(int id) {
        return iBrandRepository.findByBrandId(id);
    }

    @Override
    public List<BrandModel> findBrandList() {
        return iBrandRepository.findBrandList();
    }
}
