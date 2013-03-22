require 'iconv' 

require 'erb'
require 'rexml/document'
require 'yaml'

def change_charset(value)
  if(value!= nil)
  case value
    when Array
    value.map {|e|change_charset(e)}
    else 
    value.force_encoding(Encoding.default_external)
  end
  end
end 


#--username frankswu911@gmail.com --password vq5ZZ6kP6RP2x
temp = ERB.new(File.read("yaml.template"))
layoutTemple = ERB.new(File.read("layout.template"))
listTemple = ERB.new(File.read("list.template"))
detailTemple = ERB.new(File.read("detail.template"))
#layoutTemple = ERB.new(File.read("layout.template"))

dbServiceTemple = ERB.new(File.read("dbservice.template"))
fileYaml = File.read("bean.yml")
p fileYaml.encoding
fileYaml.force_encoding(Encoding.default_external)
map =  YAML.load(fileYaml)
p map
# bean template
map.each do |key,value|
  change_charset(value)

  open("#{key}Bean.java","w") { |out|
    out.puts temp.result(binding)
  }
end

# layout template
map.each do |key,value|
  change_charset(value)

  open("#{key.downcase}.xml","w") { |out|
    out.puts layoutTemple.result(binding)
  }
# db service template
  open("#{key}DBService.java","w") { |out|
    out.puts dbServiceTemple.result(binding)
  }
# list activity  template
  open("#{key}ListActivity.java","w") { |out|
    out.puts listTemple.result(binding)
  }
# detail activity template
  open("#{key}InfoActivity.java","w") { |out|
    out.puts detailTemple.result(binding)
  }


end




