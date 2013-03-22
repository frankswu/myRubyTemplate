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
fileYaml = File.read("bean.yml")
p fileYaml.encoding
fileYaml.force_encoding(Encoding.default_external)
map =  YAML.load(fileYaml)
p map
map.each do |key,value|
  change_charset(value)

  open("#{key}Bean.java","w") { |out|
    out.puts temp.result(binding)
  }
end



map.each do |key,value|
  open("#{key.downcase}.xml","w") { |out|
    out.puts layoutTemple.result(binding)
  }
end




