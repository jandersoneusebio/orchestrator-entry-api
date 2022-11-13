package io.github.jandersoneusebio.service;

import java.util.Optional;

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
	
	public Entry getEntry(String entry) {
		Entry entryClaimed = entryRepository.findFirstByEntryAndSituationOrderByIdDesc(entry, 1).orElse(null);
		return entryClaimed;
	}
	
	public void deleteEntry(String entry) throws Exception {
		Optional<Entry> entryOpt = entryRepository.findFirstByEntryAndSituationOrderByIdDesc(entry, 1);
		
		if(entryOpt.isPresent()) {
			Entry entryObtained = entryOpt.get();
			entryObtained.setSituation(0);
			entryRepository.save(entryObtained);
		} else {
			throw new Exception("Entry doesn't exists");
		}
		
	}
	
}
