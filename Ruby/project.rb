# evo ruby

require 'matrix'

def createMatrix(rowString, columnString)
    puts "start"
end

puts "START"

m = Matrix.build(2, 4) { rand }

column = IO.readlines(ARGV[0])[0]
row = IO.readlines(ARGV[1])[0]

puts column
puts row

puts column.length
puts row.length

matrix = createMatrix(row, column)

