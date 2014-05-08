package com.freeteam.jsonbean;

/**
 *
 * @author gen code 
 *
 */
public class <%= key %> {
	<%
		value.each do |k,v|
		
		propName = k
		propType = v.split(',')[0]
		if propType == 'Date'
			propType = 'String'
		end
		propDesc = v.split(',')[1]
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

		propMeth = k[0].upcase + k[1..-1]
	%>
    /** get <%= propDesc %> */
    public <%= propType %> get<%= propMeth %>(){
    	return this.<%= propName %>;
    }

    /** set <%= propDesc %> */
    public void set<%= propMeth %>(<%= propType %> <%= propName %>){
    	this.<%= propName %> = <%= propName %>;
    }
    <% end%>



    public String toString(){
		return "<%= key %>{" + 
		<%
		value.each do |k,v|

		propName = k
		propToString = ''
		if propName.rindex(/_List$/) != nil
			propToString = ".toString()"
		end
	%>	",<%= propName %>='" + <%= propName + propToString %> + "'" + 
		<% end%>
		"}";

    }



}