 
insert into tb_<%= key.downcase %> ( <%
                value.each do |k,v|
                propName = k
                propType = v.split(',')[0]
                propDesc = v.split(',')[1]
        %>`<%= propName.gsub(/[A-Z]/,'_\&').downcase %>`,<% end %>)
values (<%
                value.each do |k,v|
                propName = k
                propType = v.split(',')[0]

		if propType == 'int' || propType == 'double'
		   propDesc = 0
		elsif propType == 'Date'
		  propDesc ="'2014-04-04 02:00:00'"
		else
		  propDesc = "'"+propName+"'"
		end

        %><%= propDesc %>,<% end %>);




