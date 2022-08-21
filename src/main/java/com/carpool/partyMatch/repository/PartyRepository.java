package com.carpool.partyMatch.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import com.carpool.partyMatch.domain.Party;

public interface PartyRepository extends JpaRepository<Party, Long>{    // Repository Pattern Interface
  Party findByPartyInfoId(Long partyInfoId);
}
