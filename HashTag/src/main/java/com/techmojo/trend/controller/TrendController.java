package com.techmojo.trend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.techmojo.trend.domain.Tag;
import com.techmojo.trend.exception.ServiceException;
import com.techmojo.trend.service.TrendService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="TrendStats", description="Monitor Trends from HashTags") 
public class TrendController {
	@Autowired
	TrendService trendService;
	
	@PostMapping("/addText")
	@ApiOperation(value = "Add a text", response = Iterable.class)
	public @ResponseBody ResponseEntity<Boolean> addText(@RequestBody Tag tag, BindingResult result) throws ServiceException{
		boolean added=trendService.parseString(tag.getTag());
		
		return new ResponseEntity<Boolean>(added, HttpStatus.OK);
	}
	
	@GetMapping("/getTrends")
	@ApiOperation(value = "Get top 10 Tags", response = Iterable.class)
	public @ResponseBody ResponseEntity<List<Tag>> getTrend() throws ServiceException{
		List<Tag> tags=trendService.getTrend();
		
		return new ResponseEntity<List<Tag>>(tags, HttpStatus.OK);
	}
}
