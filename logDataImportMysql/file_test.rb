
#"~/work/mycode/my_ruby_code") do |file| 
=begin
'sdfdsf  sd|fd|sf sdf'.split(/[ ]/).each do |word|
  p word
=end

$\='\r\n'

def insertSqlStatment(line)
  line = line.chomp("\n")
  arr1 = line.split(/[[:blank:]]/)
#  sql = ""
  sql = "insert into client_log_data\ (server_time, server_flag1, server_ip1, server_flag2, server_flag3, server_date1, server_time1, server_ip2, client_newcomm, client_net_type, client_net_status, client_server_code, client_gloab_commtype, client_this_commtype, client_resend_reason, client_ip, client_port, client_comm_fail_code, client_comm_fail_info, client_network_ip, client_provider_flag ) \nvalues (" 
#  p arr1.length
  arr1[0,8].each do |word|
    sql << "'"+word+"',"
  end
#p sql
  arr2 = arr1[8,10].join(' ').split('|')
  arr2.each do |word|
    sql << "'"+word+"',"
  end
#p sql.gsub(/,$/,'')
  return sql.gsub(/,$/,');')
end

sqlFile = File.open('insertClientDataLog.sql','w') do |out|


filePath = './Client/'
Dir.foreach(filePath) do |fileName| 
#  p fileName
  file = File.new(filePath+fileName,'r')
  p filePath+fileName
  if(!File.directory?(file)) 
    fileLines = IO.readlines(file)
    fileLines.each do |line|
#      p line
#      p insertSqlStatment(line)
      insterSql = insertSqlStatment(line)
       #p insterSql.split("','").length
      if ( insterSql.split("','").length == 21)
        out.puts insterSql
      else 
       out.puts "--"+insterSql.gsub(/values/,'--values')
      end
    end
  end

end

end
