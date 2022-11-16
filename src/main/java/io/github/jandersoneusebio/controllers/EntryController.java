package io.github.jandersoneusebio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.jandersoneusebio.model.entities.Entry;
import io.github.jandersoneusebio.model.exceptions.BusinessException;
import io.github.jandersoneusebio.model.to.CreateEntryTO;
import io.github.jandersoneusebio.model.to.ErrorTO;
import io.github.jandersoneusebio.service.EntryService;

@RestController
@RequestMapping("api/v1/entries")
public class EntryController {
	
	@Autowired
	private EntryService entryService;
	
	@PostMapping("create-entry")
	public ResponseEntity<?> createEntry(@RequestBody CreateEntryTO createEntryTO){
		Entry entry;
		try {
			entry = entryService.createEntry(createEntryTO);
			entry.setId(null);
		} catch (BusinessException e) {
			return new ResponseEntity<ErrorTO>(e.getError(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Entry>(entry, HttpStatus.OK);
	}
	
	@GetMapping("get-entry")
	public ResponseEntity<Entry> getEntry(@RequestParam("entry") String entry){
		return new ResponseEntity<Entry>(entryService.getEntry(entry), HttpStatus.OK);
	}
	
	@DeleteMapping("delete-entry")
	public ResponseEntity<String> deleteEntry(@RequestParam("entry") String entry){
		try {
			entryService.deleteEntry(entry);
			return new ResponseEntity<String>("Entry deleted successfully", HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}
	
}
