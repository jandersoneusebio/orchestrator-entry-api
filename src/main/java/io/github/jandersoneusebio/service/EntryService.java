package io.github.jandersoneusebio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.jandersoneusebio.model.entities.Entry;
import io.github.jandersoneusebio.model.to.CreateEntryTO;
import io.github.jandersoneusebio.persistence.repositories.EntryRepository;

@Service
public class EntryService {
	
	@Autowired
	private EntryRepository entryRepository;
	
	public void createEntry(CreateEntryTO createEntryTO) {
		entryRepository.save(Entry.fromCreateEntryTO(createEntryTO));
	}
	
}
