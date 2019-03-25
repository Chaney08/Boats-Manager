package org.opentech.boatsmanager.repository;

import org.opentech.boatsmanager.model.Boat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoatRepository extends CrudRepository<Boat, Integer> {
    Boat findByBoatName(String name);
    void deleteBoatByBoatId(long id);
    Boat findBoatByBoatId(long id);
}
