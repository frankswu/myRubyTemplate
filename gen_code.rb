require 'erb'
require 'rexml/document'

doc = REXML::Document.new(File.open("bean-config.xml"))
temp = ERB.new <<-END
public class <%= name %>Bean {
<% elem.each_element("attrs"){|attr|
  propName = attr.attributes["name"].capitalize
  propType = attr.attributes["type"] %>

  public  <%= propType %> get<%=propName %>();

  public void set<%=propName %>(<%=propType %> <%=propName.downcase %>);
  <% } %>
}





END
#the time now is <%= Time.now.to_s %>
#<% 3.times do |i| %>
#<%= i %>
#<% end %>

doc.root.each_element("bean"){|elem|
  name = elem.attributes["name"].capitalize
  open("#{name}Bean.java","w") {|out|
    out.puts temp.result(binding)
  }
}







