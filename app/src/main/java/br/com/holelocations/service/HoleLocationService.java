package br.com.holelocations.service;

import br.com.holelocations.entities.HoleLocation;
import br.com.holelocations.repository.HoleLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class HoleLocationService {

    @Autowired
    private HoleLocationRepository repository;

    public List<HoleLocation> findAll() {
        return repository.findAll();
    }

    public HoleLocation findById(String id) {
        return repository.findById(id);
    }

    public String save(HoleLocation holeLocation) {
        return repository.save(holeLocation);
    }

    public void delete(String id) {
        repository.delete(id);
    }

    public String update(String id, HoleLocation hole) {
        return repository.update(id, hole);
    }
}
