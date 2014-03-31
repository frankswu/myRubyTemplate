package org.springside.examples.quickstart.rest;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;
import org.springside.examples.quickstart.entity.TM<%= key %>;
import org.springside.examples.quickstart.service.tennis.<%= key %>Service;
import org.springside.modules.beanvalidator.BeanValidators;

/**
 * TM<%= key %>的Restful API的Controller.
 * <br>
 * List page : GET /api/v1/TM<%= key %>/ <br>
 * get one: GET /api/v1/TM<%= key %>/{id} <br>
 * Create page : GET /api/v1/TM<%= key %>/create <br>
 * Create action : POST /api/v1/TM<%= key %>/create <br>
 * Update page : GET /api/v1/TM<%= key %>/update/{id} <br>
 * Update action : POST /api/v1/TM<%= key %>/update <br>
 * Delete action : GET /api/v1/TM<%= key %>/delete/{id}
 * 
 * @author calvin
 */
@Controller
@RequestMapping(value = "/api/v1/TM<%= key %>")
public class <%= key %>RestController {

	private static Logger logger = LoggerFactory.getLogger(<%= key %>RestController.class);

	@Autowired
	private <%= key %>Service <%= key %>Service;

	@Autowired
	private Validator validator;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<TM<%= key %>> list() {
		return <%= key %>Service.getAllTM<%= key %>();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		TM<%= key %> TM<%= key %> = <%= key %>Service.getTM<%= key %>(id);
		if (TM<%= key %> == null) {
			logger.warn("TM<%= key %> with id {} not found", id);
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(TM<%= key %>, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody TM<%= key %> TM<%= key %>, UriComponentsBuilder uriBuilder) {
		// 调用JSR303 Bean Validator进行校验, 异常将由RestExceptionHandler统一处理.
		BeanValidators.validateWithException(validator, TM<%= key %>);

		// 保存任务
		<%= key %>Service.saveTM<%= key %>(TM<%= key %>);

		// 按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		Long id = TM<%= key %>.getId();
		URI uri = uriBuilder.path("/api/v1/TM<%= key %>/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody TM<%= key %> TM<%= key %>) {
		// 调用JSR303 Bean Validator进行校验, 异常将由RestExceptionHandler统一处理.
		BeanValidators.validateWithException(validator, TM<%= key %>);
		// 保存
		<%= key %>Service.saveTM<%= key %>(TM<%= key %>);

		// 按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		<%= key %>Service.deleteTM<%= key %>(id);
	}

	// Total control - setup a model and return the view name yourself. Or consider
	// subclassing ExceptionHandlerExceptionResolver (see below).
	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest req, Exception exception) {
		System.out.println("Request: " + req.getRequestURL() + ",method:" + req.getMethod() + ",raised " + exception);

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("error");
		return mav;
	}

}
