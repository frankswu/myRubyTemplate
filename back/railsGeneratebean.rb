
rails generate model <%= key.downcase %> <%
		value.each do |k,v|
		
		propName = k
		propType = v.split(',')[0]
		propDesc = v.split(',')[1]
        if propType == 'int'
            propType = 'integer'
        end
        if propType == 'long'
            propType = 'integer'
        end
        if propType == 'Date'
            propType = 'timestamp'
        end
        if propType == 'double'
            propType = 'float'
        end

        if propName.rindex(/ModelList$/) != nil 
        	propName = propName.gsub(/ModelList$/,'')
        end
        if propName.rindex(/Model$/) != nil 
        	propName = propName.gsub(/Model$/,'')
            propType = 'integer'
            propName = propName + '_id' 
        end
	%><%= propName.downcase %>:<%= propType.downcase %> <% end %>

