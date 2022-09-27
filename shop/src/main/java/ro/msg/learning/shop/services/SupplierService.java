package ro.msg.learning.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entities.Supplier;
import ro.msg.learning.shop.repositories.SupplierRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class SupplierService {
    @Autowired
    private final SupplierRepository supplierRepository;

    public void createSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    public void deleteAllSuppliers() {
        supplierRepository.deleteAll();
    }
}
