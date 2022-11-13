package io.github.jandersoneusebio.persistence.repositories;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.jandersoneusebio.model.entities.Entry;

@Repository
public interface EntryRepository extends JpaRepository<Entry, BigInteger>{
	
	public Optional<Entry> findFirstByEntryAndSituationOrderByIdDesc(String entry, Integer situation);
	
}
