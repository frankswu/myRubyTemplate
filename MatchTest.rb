puts str = "My phone number is (1233) 555-2424"
puts parten = /\((\d{4})\)\s+(\d{3})-(\d{4})/

puts m = parten.match(str)

puts m.string
puts m[0]
puts "the three"

3.times do |index|
  puts "##{index + 1} : #{m.captures[index]}" 
end

puts m[1]

puts m.captures

puts m.pre_match
