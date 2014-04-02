package org.springside.examples.quickstart.data;

import org.springside.examples.quickstart.entity.TM<%= key %>;
import org.springside.modules.test.data.RandomData;

/**
 * <%= key %>相关实体测试数据生成.
 * 
 * @author calvin
 */
public class TM<%= key %>Data {

	public static TM<%= key %> random<%= key %>() {
		TM<%= key %> <%= key.downcase %> = new TM<%= key %>();
        <%
		    value.each do |k,v|
		       propName = k
		       propType = v.split(',')[0]
		       propDesc = v.split(',')[1]
				       %>      <%= key.downcase %>.set<%= propName.capitalize %>(random<%= propName.capitalize %>()); 
<% end %>
		return <%= key.downcase %>;
	}
	<%
		value.each do |k,v|
		propName = k
		propType = v.split(',')[0]
		propDesc = v.split(',')[1]
	%>
	/** <%= propDesc %> */
  	public static String random<%= propName.capitalize %>() {
		return RandomData.randomName("<%= propName.downcase %>");
	}<% end%>


}
