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

def findMax(matrix)
    max = [0, 0]
    for i in 0..matrix.row_size - 1
        for j in 0..matrix.column_size - 1
            if matrix[i, j] > matrix[max[0], max[1]]
                max = [i, j]
            end
        end
    end
    puts max
    return max
end

def argMax(choices, matrix)
    max = choices[0]
    for choice in choices
        if matrix[choice[0], choice[1]] > matrix[max[0], max[1]]
            max = choice
        end
    end
    return max
end

def findBestPath(matrix)
    p = findMax(matrix)
    path = []

    dy = [-1, -1, 0]
    dx = [-1, 0, -1]

    choices = [[0, 0], [0, 0], [0, 0]]

    while p[0] > 0 && p[1] > 0 && matrix[p[0], p[1]] != 0
        path.push(p)
        for i in 0..2
            choices[i] = [p[0] + dx[i], p[1] + dy[i]]
        end
        p = argMax(choices, matrix)
    end
    return path
end

# Prints matrix on the screen
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

column = column[0...-1] # removing last space
row = row[0...-1] # removing last space

matrix = createMatrix(row, column)

bestPath = findBestPath(matrix)
puts bestPath

#prettyPrint(matrix)
