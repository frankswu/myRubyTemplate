
package org.springside.examples.quickstart.restdto;

import java.util.Date;
import org.springside.examples.quickstart.entity.TMBaseEnum;
import org.springside.modules.utils.Collections3;
import org.springside.modules.mapper.BeanMapper;



public class <%= key %>DTO {

	<%
		value.each do |k,v|
		
		propName = k
		propType = v.split(',')[0]
		propDesc = v.split(',')[1]
        if propType == 'Date'
            propType = 'String'
        end
	%>
    /** <%= propDesc %> */
    private <%= propType %> <%= propName %>;<% end%>


	<%
		value.each do |k,v|
		
		propName = k
		propType = v.split(',')[0]
        if propType == 'Date'
            propType = 'String'
        end
		propDesc = v.split(',')[1]
		propOtherMethod = ''
		propMeth = k[0].upcase + k[1..-1]
        if propName.rindex(/ModelList$/) != nil 

        	propOtherMethod += "\n    public List<Long> get"+propMeth+"Ids(){\n    	return Collections3.extractToList(this."+propName+",\"id\");\n    }"
        	propOtherMethod += "\n\n    public "+propType.gsub(/>$/,'DTO>')+" get"+propMeth+"DTO(){\n    	return BeanMapper.mapList(this."+propName+","+propType.gsub(/List</,'').gsub(/>$/,'DTO')+".class);\n    }"
        end
        if propName.rindex(/Model$/) != nil 
        	propOtherMethod += "\n    public Long get"+propMeth+"Id(){\n    	return this."+propName+".getId();\n    }"
        	propOtherMethod += "\n\n    public "+propType+"DTO get"+propMeth+"DTO(){\n    	return BeanMapper.map(this."+propName+","+propType+"DTO.class);\n    }"
        end
	%><%= propOtherMethod %>
    /** get <%= propDesc %> */
    public <%= propType %> get<%= propMeth %>(){
    	return this.<%= propName %>;
    }

    /** set <%= propDesc %> */
    public void set<%= propMeth %>(<%= propType %> <%= propName %>){
    	this.<%= propName %> = <%= propName %>;
    }
    <% end%>


}