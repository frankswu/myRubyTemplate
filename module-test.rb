
module MyModuleTest

  def moduleTest
    puts 'test the module method'
  end 

end

class Test
  
  include MyModuleTest

end


test = Test.new
test.moduleTest
