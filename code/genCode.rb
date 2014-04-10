require 'iconv' 

#require 'fileutils'
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

# prop file
fileYaml = File.read("bean.yml")
#p fileYaml.encoding
#fileYaml.force_encoding(Encoding.default_external)
map =  YAML.load(fileYaml)
#p map

gencodePath = '../gencode'
#FileUtils.mkdir_p gencodePath

filePath = '../template/'
Dir.foreach(filePath) do |templateFileName| 
  if templateFileName != '.' && templateFileName !='..'
    #p fileName.gsub(/bean/,'franks')
    tempate = ERB.new(File.read(filePath + templateFileName))
    p filePath + templateFileName
    # bean template
    map.each do |key,value|
  #    change_charset(value)
      
      genCodeFile = '../gencode/' + templateFileName.gsub(/bean/,"#{key}")
      p genCodeFile
      
      open(genCodeFile,"w") { |out|
        out.puts tempate.result(binding)
      }
    end
  #  end
  end

end



