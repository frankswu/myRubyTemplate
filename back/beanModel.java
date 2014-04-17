package org.springside.examples.quickstart.test;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author gen code for bean
 *
 */
@Entity
@Table(name = "tb_<%= key.downcase %>")
public class <%= key %> extends IdEntity {
	<%
		value.each do |k,v|
		
		propName = k
		propType = v.split(',')[0]
		propDesc = v.split(',')[1]
        if propName.rindex(/ModelList$/) != nil 
        	propName = propName.gsub(/ModelList$/,'')
           propTypeValue = " = Lists.newArrayList()"
        end
        if propName.rindex(/Model$/) != nil 
        	propName = propName.gsub(/Model$/,'')
        end
	%>
    /** <%= propDesc %> */
    private <%= propType %> <%= propName %><%= propTypeValue%>;<% end%>

	<%
		value.each do |k,v|
		
		propName = k
		propType = v.split(',')[0]
		propDesc = v.split(',')[1]
        if propName.rindex(/ModelList$/) != nil 
        	propName = propName.gsub(/ModelList$/,'')
           propAnoantion = "\n	@Transient\n	public List<"+key+"DTO> get"+propName[0].upcase + propName[1..-1]+"List() {\n		List<"+key+"DTO> "+key.downcase+"DTOList = BeanMapper.mapList("+propName+", "+key+"DTO.class);\n		return "+key.downcase+"DTOList;\n	}"
           propAnoantion += "\n\n	@ManyToMany\n	@JoinTable(name = \"tb_"+key.downcase+"_"+propName.downcase+"\", joinColumns = { @JoinColumn(name = \""+key.downcase+"_id\") }, inverseJoinColumns = { @JoinColumn(name = \""+propName.downcase+"_id\") })\n	@Fetch(value = FetchMode.SUBSELECT)\n	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)\n	@JsonIgnore"
        end
        if propName.rindex(/Model$/) != nil 
        	propName = propName.gsub(/Model$/,'')
           propAnoantion = "\n	@NotNull\n	@OneToOne\n	@JoinColumn(name = \"" +propName+ "_id\")"
        end
		propMeth = propName[0].upcase + propName[1..-1]
	%>
    /** get <%= propDesc %> */<%= propAnoantion %>
    public <%= propType %> get<%= propMeth %>(){
    	return this.<%= propName %>;
    }

    /** set <%= propDesc %> */
    public void set<%= propMeth %>(<%= propType %> <%= propName %>){
    	this.<%= propName %> = <%= propName %>;
    }
    <% end%>

}