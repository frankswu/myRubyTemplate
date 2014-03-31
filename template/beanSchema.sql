drop table if exists tb_<%= key %>;


create table tb_<%= key %> (
       id bigint auto_increment,
	<%
		value.each do |k,v|
		propName = k
		propType = v.split(',')[0]
		if 'String' == propType
		  sqlPropType = 'varchar(255)'
		else
		  sqlPropType = 'bigint '
		end
		propDesc = v.split(',')[1]
	%>
    `<%= propName %>` <%= sqlPropType %> null,<% end %>
    primary key (id)
) engine=InnoDB;

