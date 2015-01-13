# evo ruby

require 'matrix'

$MATCHREWARD = 10
$MISMATCH = -9
$INSERTION = -20
$DELETION = -20

# Consumes two strings (row and column) and computes Smith&Waterman 
# matrix. 
def createMatrix(row, column)
    rowLength = row.length
    columnLength = column.length
    matrix = Matrix.build(rowLength + 1, columnLength + 1) {0}
    for r in 1..rowLength
        for c in 1..columnLength
            if row[r - 1] == column[c - 1]
                array = *matrix
                array[r][c] = $MATCHREWARD + matrix[r - 1, c - 1]
                matrix = Matrix[*array]
            else
                array = *matrix
                array[r][c] = [matrix[r, c - 1] + $DELETION, matrix[r - 1, c] + $INSERTION, matrix[r - 1, c - 1] + $MISMATCH, 0].max
                matrix = Matrix[*array]
            end
        end
    end
    
    return matrix
end

def prettyPrint(matrix) 
    for i in 0..matrix.row_size - 1 
        for j in 0..matrix.column_size - 1
            printf("%3d ", matrix[i, j])
        end
        puts 
    end
end

puts "START"
puts "---------------------------------------------"

column = IO.readlines(ARGV[0])[0]
row = IO.readlines(ARGV[1])[0]

column = column[0...-1]
row = row[0...-1]

puts column
puts row

matrix = createMatrix(row, column)
prettyPrint(matrix)
