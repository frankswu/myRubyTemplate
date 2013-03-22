
def demo_yield

puts 'hello,yield'
result = yield(3)
  puts "jack#{result}"
end

demo_yield {|x| x * 33 }

puts self
class C
  puts self
  a = 1
  def print_a
    puts self
    a = 2
    puts a
  end
  puts a
end 

c = C.new 
c.print_a



p "[\"a\",\"b\",\"c\"].each_with_index{|x,i| printf \"%d:%s\n\",i,x}"
["a","b","c"].each_with_index{|x,i| printf "%d:%s\n",i,x}

ary = [1,2,3,4,5]
p ary.inject{|n,i| n * i}

# ruby filename (complie and run this code)




puts $0

print "print some !"
s =  gets
puts s
