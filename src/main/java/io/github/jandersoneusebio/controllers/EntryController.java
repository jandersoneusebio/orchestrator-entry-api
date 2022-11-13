package io.github.jandersoneusebio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.jandersoneusebio.model.to.CreateEntryTO;
import io.github.jandersoneusebio.service.EntryService;

@RestController
@RequestMapping("api/v1/entries")
public class EntryController {
	
	@Autowired
	private EntryService entryService;
	
	@PostMapping("create-entry")
	public ResponseEntity<String> createEntry(@RequestBody CreateEntryTO createEntryTO){
		entryService.createEntry(createEntryTO);
		return new ResponseEntity<String>("Entry created successfully", HttpStatus.OK);
	}
	
}
