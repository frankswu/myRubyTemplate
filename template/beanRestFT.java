package org.springside.examples.quickstart.functional.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.net.URI;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springside.examples.quickstart.data.TM<%= key %>Data;
import org.springside.examples.quickstart.entity.TM<%= key %>;
import org.springside.examples.quickstart.functional.BaseFunctionalTestCase;
import org.springside.modules.mapper.JsonMapper;
import org.springside.modules.test.category.Smoke;

/**
 * 任务管理的功能测试, 测试页面JavaScript及主要用户故事流程.
 * 
 * @author frankswu
 */
public class <%= key %>RestFT extends BaseFunctionalTestCase {

	private final RestTemplate restTemplate = new RestTemplate();

	private final JsonMapper jsonMapper = new JsonMapper();

	private static class TM<%= key %>List extends ArrayList<TM<%= key %>> {
	}

	private static String resoureUrl;

	@BeforeClass
	public static void initUrl() {
		resoureUrl = baseUrl + "/api/v1/<%= key.downcase %>";
	}

	/**
	 * 查看任务列表.
	 */
	@Test
	@Category(Smoke.class)
	public void listTm<%= key %>s() {
		TM<%= key %>List tm<%= key %>s = restTemplate.getForObject(resoureUrl, TM<%= key %>List.class);
		assertEquals(5, tm<%= key %>s.size());
		assertEquals("Study PlayFramework 2.0", tm<%= key %>s.get(0).getTitle());
	}

	/**
	 * 获取任务.
	 */
	@Test
	@Category(Smoke.class)
	public void getTm<%= key %>() {
		TM<%= key %> tm<%= key %> = restTemplate.getForObject(resoureUrl + "/{id}", TM<%= key %>.class, 1L);
		assertEquals("Study PlayFramework 2.0", tm<%= key %>.getTitle());
	}

	/**
	 * 创建/更新/删除任务.
	 */
	@Test
	@Category(Smoke.class)
	public void createUpdateAndDeleteTm<%= key %>() {

		// create
		TM<%= key %> tm<%= key %> = TM<%= key %>Data.randomTm<%= key %>();

		URI tm<%= key %>Uri = restTemplate.postForLocation(resoureUrl, tm<%= key %>);
		System.out.println(tm<%= key %>Uri.toString());
		Tm<%= key %> createdTm<%= key %> = restTemplate.getForObject(tm<%= key %>Uri, Tm<%= key %>.class);
		assertEquals(tm<%= key %>.getTitle(), createdTm<%= key %>.getTitle());

		// update
		String id = StringUtils.substringAfterLast(tm<%= key %>Uri.toString(), "/");
		tm<%= key %>.setId(new Long(id));
		tm<%= key %>.setTitle(Tm<%= key %>Data.randomTitle());

		restTemplate.put(tm<%= key %>Uri, tm<%= key %>);

		TM<%= key %> updatedTm<%= key %> = restTemplate.getForObject(tm<%= key %>Uri, Tm<%= key %>.class);
		assertEquals(tm<%= key %>.getTitle(), updatedTm<%= key %>.getTitle());

		// delete
		restTemplate.delete(tm<%= key %>Uri);

		try {
			restTemplate.getForObject(tm<%= key %>Uri, TM<%= key %>.class);
			fail("Get should fail while feth a deleted tm<%= key %>");
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
		}
	}

	@Test
	public void invalidCreateInput() {

		// create
		TM<%= key %> titleBlankTm<%= key %> = new TM<%= key %>();
		try {
			restTemplate.postForLocation(resoureUrl, titleBlankTm<%= key %>);
			fail("Create should fail while title is blank");
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
			Map messages = jsonMapper.fromJson(e.getResponseBodyAsString(), Map.class);
			assertEquals(1, messages.size());
			assertEquals("may not be empty", messages.get("title"));
		}

	}

	@Test
	public void invalidUpdateInput() {
		TM<%= key %> titleBlankTm<%= key %> = new TM<%= key %>();
		// update
		titleBlankTm<%= key %>.setId(1L);
		try {
			restTemplate.put(resoureUrl + "/1", titleBlankTm<%= key %>);
			fail("Update should fail while title is blank");
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
			Map messages = jsonMapper.fromJson(e.getResponseBodyAsString(), Map.class);
			assertEquals(1, messages.size());
			assertEquals("may not be empty", messages.get("title"));
		}

	}

}
