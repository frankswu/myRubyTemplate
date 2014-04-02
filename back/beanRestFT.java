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
	public void listTM<%= key %>s() {
		TM<%= key %>List TM<%= key %>s = restTemplate.getForObject(resoureUrl, TM<%= key %>List.class);
		assertEquals(5, TM<%= key %>s.size());
//		assertEquals("Study PlayFramework 2.0", TM<%= key %>s.get(0).getTitle());
	}

	/**
	 * 获取任务.
	 */
	@Test
	@Category(Smoke.class)
	public void getTM<%= key %>() {
		TM<%= key %> TM<%= key %> = restTemplate.getForObject(resoureUrl + "/{id}", TM<%= key %>.class, 1L);
//		assertEquals("Study PlayFramework 2.0", TM<%= key %>.getTitle());
	}

	/**
	 * 创建/更新/删除任务.
	 */
	@Test
	@Category(Smoke.class)
	public void createUpdateAndDeleteTM<%= key %>() {

		// create
//		TM<%= key %> TM<%= key %> = TM<%= key %>Data.randomTM<%= key %>();
		TM<%= key %> TM<%= key %> = new TM<%= key %>();

		URI TM<%= key %>Uri = restTemplate.postForLocation(resoureUrl, TM<%= key %>);
		System.out.println(TM<%= key %>Uri.toString());
		TM<%= key %> createdTM<%= key %> = restTemplate.getForObject(TM<%= key %>Uri, TM<%= key %>.class);
//		assertEquals(TM<%= key %>.getTitle(), createdTM<%= key %>.getTitle());

		// update
		String id = StringUtils.substringAfterLast(TM<%= key %>Uri.toString(), "/");
		TM<%= key %>.setId(new Long(id));
//		TM<%= key %>.setTitle(TM<%= key %>Data.randomTitle());

		restTemplate.put(TM<%= key %>Uri, TM<%= key %>);

		TM<%= key %> updatedTM<%= key %> = restTemplate.getForObject(TM<%= key %>Uri, TM<%= key %>.class);
//		assertEquals(TM<%= key %>.getTitle(), updatedTM<%= key %>.getTitle());

		// delete
		restTemplate.delete(TM<%= key %>Uri);

		try {
			restTemplate.getForObject(TM<%= key %>Uri, TM<%= key %>.class);
			fail("Get should fail while feth a deleted TM<%= key %>");
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
		}
	}

	@Test
	public void invalidCreateInput() {

		// create
		TM<%= key %> titleBlankTM<%= key %> = new TM<%= key %>();
		try {
			restTemplate.postForLocation(resoureUrl, titleBlankTM<%= key %>);
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
		TM<%= key %> titleBlankTM<%= key %> = new TM<%= key %>();
		// update
		titleBlankTM<%= key %>.setId(1L);
		try {
			restTemplate.put(resoureUrl + "/1", titleBlankTM<%= key %>);
			fail("Update should fail while title is blank");
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
			Map messages = jsonMapper.fromJson(e.getResponseBodyAsString(), Map.class);
			assertEquals(1, messages.size());
			assertEquals("may not be empty", messages.get("title"));
		}

	}

}
