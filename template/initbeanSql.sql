 
insert into (<%
		value.each do |k,v|
		propName = k
		propType = v.split(',')[0]
		propDesc = v.split(',')[1]
	%>`<%= propName.gsub(/[A-Z]/,'_\&') %>`,<%= end %>)
values (<%
		value.each do |k,v|
		propName = k
		propType = v.split(',')[0]
		propDesc = v.split(',')[1]
	%>'<%= propName %>',<%= end %>)

)
