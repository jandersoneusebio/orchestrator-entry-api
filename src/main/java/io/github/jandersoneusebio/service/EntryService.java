package io.github.jandersoneusebio.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.jandersoneusebio.model.entities.Entry;
import io.github.jandersoneusebio.model.enums.EntryErrorsEnum;
import io.github.jandersoneusebio.model.enums.EntryTypeEnum;
import io.github.jandersoneusebio.model.exceptions.BusinessException;
import io.github.jandersoneusebio.model.to.CreateEntryTO;
import io.github.jandersoneusebio.persistence.repositories.EntryRepository;

@Service
public class EntryService {
	
	@Autowired
	private EntryRepository entryRepository;
	
	public Entry createEntry(CreateEntryTO createEntryTO) throws BusinessException {
		validateEntry(createEntryTO);
		return entryRepository.save(Entry.fromCreateEntryTO(createEntryTO));
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
	
	public void validateEntry(CreateEntryTO createEntryTO) throws BusinessException {
		Optional<Entry> entryDB = entryRepository.findFirstByEntryAndSituationOrderByIdDesc(createEntryTO.getEntry(), 1);
		
		if(entryDB.isPresent()) {
			throw new BusinessException(EntryErrorsEnum.ENTRY_ALREADY_EXISTS);
		}
		
		if(!EntryTypeEnum.verifyIfExists(createEntryTO.getEntryType())) {
			throw new BusinessException(
					String.format(EntryErrorsEnum.INVALID_ENTRY_TYPE_WITH_PARAM.getMsg(), createEntryTO.getEntryType()),
					EntryErrorsEnum.INVALID_ENTRY_TYPE_WITH_PARAM.getErrorCode());
		}
		
	}
	
}
